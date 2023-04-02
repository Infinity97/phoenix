package com.pheonix.core.service.impl;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.pheonix.core.dto.ApiResponseStatus;
import com.pheonix.core.dto.request.DeleteFileRequest;
import com.pheonix.core.dto.request.GetImageRequest;
import com.pheonix.core.dto.request.UploadFileRequest;
import com.pheonix.core.dto.vo.GeneralFileVo;
import com.pheonix.core.model.GeneralFiles;
import com.pheonix.core.repository.dao.GeneralFilesDao;
import com.pheonix.core.service.IFileService;
import com.pheonix.core.utils.constants.PropertyConstants;
import com.pheonix.core.utils.enums.FileType;
import com.pheonix.core.utils.exception.PheonixException;
import com.pheonix.core.utils.helper.CommonUtil;
import com.pheonix.core.utils.helper.FileUtils;
import com.pheonix.core.utils.helper.VarUtils;
import com.pheonix.core.utils.mapper.MapperUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.pheonix.core.utils.constants.AppConstants.SLASH;
import static com.pheonix.core.utils.constants.AppConstants.UNDERSCORE;


@Service
@Slf4j
@RequiredArgsConstructor
public class FileServiceImpl implements IFileService {

	private final GeneralFilesDao filesDao;
	private final Environment environment;
	private final AmazonS3 amazonS3;
	private final MapperUtil mapperUtil;

	public GeneralFileVo uploadFile(UploadFileRequest fileRequest) throws IllegalStateException {
		return uploadInputStreamToS3Bucket(fileRequest);
	}

	@Override
	public GeneralFileVo saveGeneralFile(GeneralFiles files) {
		return mapperUtil.map(filesDao.save(files));
	}

	private GeneralFileVo uploadInputStreamToS3Bucket(UploadFileRequest fileRequest) throws IllegalStateException {

		InputStream inputStream = FileUtils.convertBase64ToInputStream(fileRequest.getFileData());

		ObjectMetadata metadata = new ObjectMetadata();
		String bucketName = environment.getProperty(PropertyConstants.AWS_S3_BUCKET_DEFAULT);
		String fileId = CommonUtil.generateUUID().toString();
		String fileName = fileRequest.getFileName();
		if(StringUtils.isBlank(fileName)) {
			String extension = FileUtils.findExtensionFromBase64(fileRequest.getFileData().charAt(0));
			fileName = fileId + extension;
		}else {
			fileName = fileId+UNDERSCORE+fileName;
		}
		String uploadPath = fileRequest.getFileType().getTableMappedTo() + SLASH + fileRequest.getContextId() + SLASH +
			fileRequest.getFileType() + SLASH + fileName;

		try {
			amazonS3.putObject(bucketName, uploadPath, inputStream, metadata);
		} catch (AmazonServiceException e) {
			throw new IllegalStateException("Failed to upload the file due to AWS Exception", e);
		}
		return saveGeneralFile(GeneralFiles.builder().id(fileId).fileName(fileRequest.getFileName()).type(fileRequest.getFileType()).bucketName(bucketName).fullPath(uploadPath).contextId(fileRequest.getContextId()).build());
	}

	@Override
	public Map<FileType, List<GeneralFiles>> getFilesByFileType(String contextId, String tableName) {
		return filesDao.findByContextId(contextId).stream().filter(files -> files.getType().getTableMappedTo().equalsIgnoreCase(tableName)).toList()
			.stream().collect(Collectors.groupingBy(GeneralFiles::getType));
	}

	@Override
	public List<GeneralFileVo> getImages(GetImageRequest getImageRequest) {
		if(getImageRequest.getFileType() == FileType.HOME)
			return mapperUtil.convertFileToList(filesDao.findByType(getImageRequest.getFileType()));
		return Collections.emptyList();
	}

	@Override
	public List<GeneralFiles> findByFileType(FileType fileType) {
		return filesDao.findByType(fileType);
	}

	@Override
	public void deleteFile(DeleteFileRequest deleteFileRequest) throws PheonixException {
		GeneralFiles files = new GeneralFiles();
		if(VarUtils.isValid(deleteFileRequest.getId())){
			files = filesDao.findById(deleteFileRequest.getId());
		}
		if(VarUtils.isValid(deleteFileRequest.getUrl())){
			files = filesDao.findById(extractIdFromFileUrl(deleteFileRequest.getUrl()));
		}

		if(files==null)
			throw new PheonixException(ApiResponseStatus.FILE_DOES_NOT_EXIST);
		files.setDeleted(true);
		filesDao.save(files);
	}

	public String extractIdFromFileUrl(String fileUrl){
		int lastIndex = fileUrl.lastIndexOf('_')==-1?fileUrl.lastIndexOf('.'):fileUrl.lastIndexOf('_');
		return fileUrl.substring(fileUrl.lastIndexOf('/')+1,lastIndex);
	}

}

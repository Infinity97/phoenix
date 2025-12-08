package com.pheonix.core.service;

import com.pheonix.core.dto.request.DeleteFileRequest;
import com.pheonix.core.dto.request.GetImageRequest;
import com.pheonix.core.dto.request.UploadFileRequest;
import com.pheonix.core.dto.response.FileTypeResponse;
import com.pheonix.core.dto.vo.GeneralFileVo;
import com.pheonix.core.model.GeneralFiles;
import com.pheonix.core.utils.enums.FileType;
import com.pheonix.core.utils.exception.PheonixException;

import java.util.List;
import java.util.Map;

public interface IFileService {

	GeneralFileVo uploadFile(UploadFileRequest fileRequest)throws IllegalStateException;
	GeneralFileVo saveGeneralFile(GeneralFiles files);
	Map<FileType, List<GeneralFiles>> getFilesByFileType(String contextId, String tableName);
	List<GeneralFileVo> getImages(GetImageRequest getImageRequest);
	List<GeneralFiles> findByFileType(FileType fileType);
	void deleteFile(DeleteFileRequest deleteFileRequest) throws PheonixException;
}

package com.pheonix.core.controller;

import com.pheonix.core.dto.ApiResponse;
import com.pheonix.core.dto.ApiResponseStatus;
import com.pheonix.core.dto.request.DeleteFileRequest;
import com.pheonix.core.dto.request.GetImageRequest;
import com.pheonix.core.dto.request.UploadFileRequest;
import com.pheonix.core.dto.vo.GeneralFileVo;
import com.pheonix.core.service.IFileService;
import com.pheonix.core.utils.constants.RestConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static com.pheonix.core.utils.constants.RestConstants.SESSION_ID;

@Slf4j
@RestController
@RequestMapping(value = RestConstants.FILES)
@RequiredArgsConstructor
public class FilesController {

	private final IFileService fileService;

	@PostMapping(value = RestConstants.UPLOAD_FILE)
	public ApiResponse<GeneralFileVo> uploadFile(@Valid @RequestBody UploadFileRequest fileRequest, @RequestHeader(value = SESSION_ID) String sessionId)throws Exception{
		return new ApiResponse<>(fileService.uploadFile(fileRequest));
	}

	@GetMapping(value = RestConstants.GET_FILES)
	public ApiResponse<List<GeneralFileVo>> getImages(@RequestBody GetImageRequest getImageRequest){
		return new ApiResponse<>(fileService.getImages(getImageRequest));
	}

	@PostMapping(value = RestConstants.DELETE_FILE)
	public ApiResponse<Void> deleteFile(@RequestBody DeleteFileRequest deleteFileRequest, @RequestHeader(value = SESSION_ID) String sessionId)throws Exception{
		fileService.deleteFile(deleteFileRequest);
		return new ApiResponse<>(ApiResponseStatus.SUCCESS);
	}

}

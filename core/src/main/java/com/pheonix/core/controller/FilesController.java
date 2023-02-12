package com.pheonix.core.controller;

import com.pheonix.core.dto.ApiResponse;
import com.pheonix.core.dto.request.UploadFileRequest;
import com.pheonix.core.dto.vo.GeneralFileVo;
import com.pheonix.core.service.IFileService;
import com.pheonix.core.utils.constants.RestConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = RestConstants.FILES)
@RequiredArgsConstructor
public class FilesController {

	private final IFileService fileService;

	@PostMapping(value = RestConstants.UPLOAD_FILE)
	public ApiResponse<GeneralFileVo> uploadFile(@Valid @RequestBody UploadFileRequest fileRequest)throws Exception{
		return new ApiResponse<>(fileService.uploadFile(fileRequest));
	}

}

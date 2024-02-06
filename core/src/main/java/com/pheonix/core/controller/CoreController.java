package com.pheonix.core.controller;

import com.pheonix.core.dto.ApiResponse;
import com.pheonix.core.dto.ApiResponseStatus;
import com.pheonix.core.dto.vo.CategoryVo;
import com.pheonix.core.service.ICoreService;
import com.pheonix.core.utils.constants.RestConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = RestConstants.CORE)
@RequiredArgsConstructor
public class CoreController {

	private final ICoreService coreService;

	@PostMapping(value = RestConstants.POPULATE_DATA)
	public ResponseEntity<ApiResponse<Void>> populateInitialData() {
		coreService.populateInitialData();
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping(value = "test2")
	public ResponseEntity<ApiResponse<Void>> test2() {
		coreService.test();
		return new ResponseEntity<>(HttpStatus.OK);
	}

}

package com.pheonix.core.repository.dao;

import com.pheonix.core.dto.ApiResponseStatus;
import com.pheonix.core.model.GeneralFiles;
import com.pheonix.core.repository.IGeneralFilesRepo;
import com.pheonix.core.utils.enums.FileType;
import com.pheonix.core.utils.exception.PheonixException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class GeneralFilesDao {

	private final IGeneralFilesRepo filesRepo;

	public GeneralFiles save(GeneralFiles generalFiles){
		return filesRepo.save(generalFiles);
	}

	public List<GeneralFiles> findByContextId(String contextId){
		return filesRepo.findByContextIdAndDeletedIsFalse(contextId);
	}

	public List<GeneralFiles> findByType(FileType fileType){return filesRepo.findByType(fileType);}

	public GeneralFiles findById(String id)throws PheonixException{
		return filesRepo.findById(id).orElseThrow(()-> new PheonixException(ApiResponseStatus.FILE_DOES_NOT_EXIST));
	}
}

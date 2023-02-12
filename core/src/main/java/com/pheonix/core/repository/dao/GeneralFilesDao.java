package com.pheonix.core.repository.dao;

import com.pheonix.core.model.GeneralFiles;
import com.pheonix.core.repository.IGeneralFilesRepo;
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

	public List<GeneralFiles> findByContextId(String contextId){return filesRepo.findByContextId(contextId);}

}

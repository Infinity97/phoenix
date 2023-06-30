package com.pheonix.core.repository;

import com.pheonix.core.model.GeneralFiles;
import com.pheonix.core.utils.enums.FileType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GeneralFilesRepo extends JpaRepository<GeneralFiles,String> {

	List<GeneralFiles> findByContextIdAndDeletedIsFalse(String contextId);
	List<GeneralFiles> findByType(FileType fileType);

}

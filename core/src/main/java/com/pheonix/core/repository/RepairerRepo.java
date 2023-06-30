package com.pheonix.core.repository;

import com.pheonix.core.model.Repairers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.websocket.server.PathParam;
import java.util.List;

public interface RepairerRepo extends JpaRepository<Repairers, String> {

	@Query(value = "select r.* from REPAIRERS r left join COMPANY_REPAIRERS cr on cr.REPAIRER_ID = r.ID where cr.COMPANY_ID = :companyId", nativeQuery = true)
	List<Repairers> findByBrand(@PathParam("companyId") String companyId);

}

package ug.co.absa.schoolfees.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import ug.co.absa.schoolfees.domain.School;

/**
 * Spring Data JPA repository for the School entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {}

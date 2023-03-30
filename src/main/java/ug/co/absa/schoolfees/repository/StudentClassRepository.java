package ug.co.absa.schoolfees.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import ug.co.absa.schoolfees.domain.StudentClass;

/**
 * Spring Data JPA repository for the StudentClass entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StudentClassRepository extends JpaRepository<StudentClass, Long> {}

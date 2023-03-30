package ug.co.absa.schoolfees.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import ug.co.absa.schoolfees.domain.ContactInfo;

/**
 * Spring Data JPA repository for the ContactInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ContactInfoRepository extends JpaRepository<ContactInfo, Long> {}

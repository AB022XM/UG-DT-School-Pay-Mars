package ug.co.absa.schoolfees.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import ug.co.absa.schoolfees.domain.PaymentChannel;

/**
 * Spring Data JPA repository for the PaymentChannel entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PaymentChannelRepository extends JpaRepository<PaymentChannel, Long> {}

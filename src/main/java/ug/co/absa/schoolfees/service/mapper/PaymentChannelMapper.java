package ug.co.absa.schoolfees.service.mapper;

import org.mapstruct.*;
import ug.co.absa.schoolfees.domain.PaymentChannel;
import ug.co.absa.schoolfees.service.dto.PaymentChannelDTO;

/**
 * Mapper for the entity {@link PaymentChannel} and its DTO {@link PaymentChannelDTO}.
 */
@Mapper(componentModel = "spring")
public interface PaymentChannelMapper extends EntityMapper<PaymentChannelDTO, PaymentChannel> {}

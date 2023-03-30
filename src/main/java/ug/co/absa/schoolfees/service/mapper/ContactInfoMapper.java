package ug.co.absa.schoolfees.service.mapper;

import org.mapstruct.*;
import ug.co.absa.schoolfees.domain.ContactInfo;
import ug.co.absa.schoolfees.service.dto.ContactInfoDTO;

/**
 * Mapper for the entity {@link ContactInfo} and its DTO {@link ContactInfoDTO}.
 */
@Mapper(componentModel = "spring")
public interface ContactInfoMapper extends EntityMapper<ContactInfoDTO, ContactInfo> {}

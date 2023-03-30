package ug.co.absa.schoolfees.service.mapper;

import org.mapstruct.*;
import ug.co.absa.schoolfees.domain.School;
import ug.co.absa.schoolfees.service.dto.SchoolDTO;

/**
 * Mapper for the entity {@link School} and its DTO {@link SchoolDTO}.
 */
@Mapper(componentModel = "spring")
public interface SchoolMapper extends EntityMapper<SchoolDTO, School> {}

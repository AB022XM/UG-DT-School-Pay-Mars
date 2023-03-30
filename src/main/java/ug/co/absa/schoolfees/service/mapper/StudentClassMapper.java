package ug.co.absa.schoolfees.service.mapper;

import org.mapstruct.*;
import ug.co.absa.schoolfees.domain.StudentClass;
import ug.co.absa.schoolfees.service.dto.StudentClassDTO;

/**
 * Mapper for the entity {@link StudentClass} and its DTO {@link StudentClassDTO}.
 */
@Mapper(componentModel = "spring")
public interface StudentClassMapper extends EntityMapper<StudentClassDTO, StudentClass> {}

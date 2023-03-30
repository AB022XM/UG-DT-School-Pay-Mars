package ug.co.absa.schoolfees.service.mapper;

import org.mapstruct.*;
import ug.co.absa.schoolfees.domain.AssociatedFees;
import ug.co.absa.schoolfees.domain.StudentClass;
import ug.co.absa.schoolfees.service.dto.AssociatedFeesDTO;
import ug.co.absa.schoolfees.service.dto.StudentClassDTO;

/**
 * Mapper for the entity {@link AssociatedFees} and its DTO {@link AssociatedFeesDTO}.
 */
@Mapper(componentModel = "spring")
public interface AssociatedFeesMapper extends EntityMapper<AssociatedFeesDTO, AssociatedFees> {
    @Mapping(target = "studentClass", source = "studentClass", qualifiedByName = "studentClassId")
    AssociatedFeesDTO toDto(AssociatedFees s);

    @Named("studentClassId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    StudentClassDTO toDtoStudentClassId(StudentClass studentClass);
}

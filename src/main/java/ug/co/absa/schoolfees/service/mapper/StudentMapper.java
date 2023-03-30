package ug.co.absa.schoolfees.service.mapper;

import org.mapstruct.*;
import ug.co.absa.schoolfees.domain.School;
import ug.co.absa.schoolfees.domain.Student;
import ug.co.absa.schoolfees.domain.StudentClass;
import ug.co.absa.schoolfees.service.dto.SchoolDTO;
import ug.co.absa.schoolfees.service.dto.StudentClassDTO;
import ug.co.absa.schoolfees.service.dto.StudentDTO;

/**
 * Mapper for the entity {@link Student} and its DTO {@link StudentDTO}.
 */
@Mapper(componentModel = "spring")
public interface StudentMapper extends EntityMapper<StudentDTO, Student> {
    @Mapping(target = "studentClass", source = "studentClass", qualifiedByName = "studentClassId")
    @Mapping(target = "school", source = "school", qualifiedByName = "schoolId")
    StudentDTO toDto(Student s);

    @Named("studentClassId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    StudentClassDTO toDtoStudentClassId(StudentClass studentClass);

    @Named("schoolId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    SchoolDTO toDtoSchoolId(School school);
}

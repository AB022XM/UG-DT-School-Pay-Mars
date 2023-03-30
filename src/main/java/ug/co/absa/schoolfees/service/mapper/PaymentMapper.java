package ug.co.absa.schoolfees.service.mapper;

import org.mapstruct.*;
import ug.co.absa.schoolfees.domain.Payment;
import ug.co.absa.schoolfees.domain.Student;
import ug.co.absa.schoolfees.service.dto.PaymentDTO;
import ug.co.absa.schoolfees.service.dto.StudentDTO;

/**
 * Mapper for the entity {@link Payment} and its DTO {@link PaymentDTO}.
 */
@Mapper(componentModel = "spring")
public interface PaymentMapper extends EntityMapper<PaymentDTO, Payment> {
    @Mapping(target = "student", source = "student", qualifiedByName = "studentId")
    PaymentDTO toDto(Payment s);

    @Named("studentId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    StudentDTO toDtoStudentId(Student student);
}

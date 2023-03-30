package ug.co.absa.schoolfees.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link ug.co.absa.schoolfees.domain.Student} entity.
 */
@Schema(description = "This is a student class,\n@author Banada Ismael ABSA DT team")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class StudentDTO implements Serializable {

    private Long id;

    @NotNull
    private UUID recordUniqueIdentifier;

    @NotNull
    private Integer studentId;

    @NotNull
    @Size(min = 3, max = 30)
    private String firstName;

    @NotNull
    @Size(min = 3, max = 30)
    private String middleName;

    @NotNull
    @Size(min = 3, max = 30)
    private String lastName;

    @NotNull
    @Size(min = 3, max = 20)
    private String paymentCode;

    @NotNull
    private LocalDate dateOfBirth;

    @NotNull
    @Size(min = 3, max = 8)
    private String outStandingAmount;

    @NotNull
    private Boolean status;

    private String studentContact;

    private String studentAddress;

    private String freeField1;

    private String freeField2;

    private String freeField3;

    private LocalDate createdAt;

    private LocalDate updatedAt;

    private LocalDate isDeleted;

    private StudentClassDTO studentClass;

    private SchoolDTO school;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getRecordUniqueIdentifier() {
        return recordUniqueIdentifier;
    }

    public void setRecordUniqueIdentifier(UUID recordUniqueIdentifier) {
        this.recordUniqueIdentifier = recordUniqueIdentifier;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getOutStandingAmount() {
        return outStandingAmount;
    }

    public void setOutStandingAmount(String outStandingAmount) {
        this.outStandingAmount = outStandingAmount;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getStudentContact() {
        return studentContact;
    }

    public void setStudentContact(String studentContact) {
        this.studentContact = studentContact;
    }

    public String getStudentAddress() {
        return studentAddress;
    }

    public void setStudentAddress(String studentAddress) {
        this.studentAddress = studentAddress;
    }

    public String getFreeField1() {
        return freeField1;
    }

    public void setFreeField1(String freeField1) {
        this.freeField1 = freeField1;
    }

    public String getFreeField2() {
        return freeField2;
    }

    public void setFreeField2(String freeField2) {
        this.freeField2 = freeField2;
    }

    public String getFreeField3() {
        return freeField3;
    }

    public void setFreeField3(String freeField3) {
        this.freeField3 = freeField3;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDate getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(LocalDate isDeleted) {
        this.isDeleted = isDeleted;
    }

    public StudentClassDTO getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(StudentClassDTO studentClass) {
        this.studentClass = studentClass;
    }

    public SchoolDTO getSchool() {
        return school;
    }

    public void setSchool(SchoolDTO school) {
        this.school = school;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StudentDTO)) {
            return false;
        }

        StudentDTO studentDTO = (StudentDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, studentDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "StudentDTO{" +
            "id=" + getId() +
            ", recordUniqueIdentifier='" + getRecordUniqueIdentifier() + "'" +
            ", studentId=" + getStudentId() +
            ", firstName='" + getFirstName() + "'" +
            ", middleName='" + getMiddleName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", paymentCode='" + getPaymentCode() + "'" +
            ", dateOfBirth='" + getDateOfBirth() + "'" +
            ", outStandingAmount='" + getOutStandingAmount() + "'" +
            ", status='" + getStatus() + "'" +
            ", studentContact='" + getStudentContact() + "'" +
            ", studentAddress='" + getStudentAddress() + "'" +
            ", freeField1='" + getFreeField1() + "'" +
            ", freeField2='" + getFreeField2() + "'" +
            ", freeField3='" + getFreeField3() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", isDeleted='" + getIsDeleted() + "'" +
            ", studentClass=" + getStudentClass() +
            ", school=" + getSchool() +
            "}";
    }
}

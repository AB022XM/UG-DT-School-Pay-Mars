package ug.co.absa.schoolfees.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link ug.co.absa.schoolfees.domain.School} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SchoolDTO implements Serializable {

    private Long id;

    @NotNull
    private UUID recordUniqueIdentifier;

    @NotNull
    private Integer schoolId;

    @NotNull
    private String schoolCode;

    private String schoolPhoneNumber;

    private String schoolAlternativePhoneNumber;

    private String schoolemailAddess;

    @NotNull
    private String schoolName;

    @NotNull
    private Boolean status;

    private String freeField1;

    private String freeField2;

    private String freeField3;

    private LocalDate createdAt;

    private LocalDate updatedAt;

    private LocalDate isDeleted;

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

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    public String getSchoolPhoneNumber() {
        return schoolPhoneNumber;
    }

    public void setSchoolPhoneNumber(String schoolPhoneNumber) {
        this.schoolPhoneNumber = schoolPhoneNumber;
    }

    public String getSchoolAlternativePhoneNumber() {
        return schoolAlternativePhoneNumber;
    }

    public void setSchoolAlternativePhoneNumber(String schoolAlternativePhoneNumber) {
        this.schoolAlternativePhoneNumber = schoolAlternativePhoneNumber;
    }

    public String getSchoolemailAddess() {
        return schoolemailAddess;
    }

    public void setSchoolemailAddess(String schoolemailAddess) {
        this.schoolemailAddess = schoolemailAddess;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SchoolDTO)) {
            return false;
        }

        SchoolDTO schoolDTO = (SchoolDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, schoolDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SchoolDTO{" +
            "id=" + getId() +
            ", recordUniqueIdentifier='" + getRecordUniqueIdentifier() + "'" +
            ", schoolId=" + getSchoolId() +
            ", schoolCode='" + getSchoolCode() + "'" +
            ", schoolPhoneNumber='" + getSchoolPhoneNumber() + "'" +
            ", schoolAlternativePhoneNumber='" + getSchoolAlternativePhoneNumber() + "'" +
            ", schoolemailAddess='" + getSchoolemailAddess() + "'" +
            ", schoolName='" + getSchoolName() + "'" +
            ", status='" + getStatus() + "'" +
            ", freeField1='" + getFreeField1() + "'" +
            ", freeField2='" + getFreeField2() + "'" +
            ", freeField3='" + getFreeField3() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", isDeleted='" + getIsDeleted() + "'" +
            "}";
    }
}

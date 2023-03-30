/*
 * Copyright (c) 2023.   - Digital Transformation Team- ABSA BANK UGANDA.  All Rights Reserved. You may not use, distribute and/or modify this code.
 * Contributors
 *
 * Developer Details
 * Name: Banada Ismael
 * Department: IT-Digital transformation team
 * Organisation: ABSA Bank Group
 *
 *
 */

package ug.co.absa.schoolfees.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

/**
 * A School.
 */
@Entity
@Table(name = "school")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class School implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Type(type = "uuid-char")
    @Column(name = "record_unique_identifier", length = 36, nullable = false)
    private UUID recordUniqueIdentifier;

    @NotNull
    @Column(name = "school_id", nullable = false)
    private Integer schoolId;

    @NotNull
    @Column(name = "school_code", nullable = false)
    private String schoolCode;

    @Column(name = "school_phone_number")
    private String schoolPhoneNumber;

    @Column(name = "school_alternative_phone_number")
    private String schoolAlternativePhoneNumber;

    @Column(name = "schoolemail_addess")
    private String schoolemailAddess;

    @NotNull
    @Column(name = "school_name", nullable = false)
    private String schoolName;

    @NotNull
    @Column(name = "status", nullable = false)
    private Boolean status;

    @Column(name = "free_field_1")
    private String freeField1;

    @Column(name = "free_field_2")
    private String freeField2;

    @Column(name = "free_field_3")
    private String freeField3;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @Column(name = "is_deleted")
    private LocalDate isDeleted;

    @OneToMany(mappedBy = "school")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "payments", "studentClass", "school" }, allowSetters = true)
    private Set<Student> students = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public School id(Long id) {
        this.setId(id);
        return this;
    }

    public UUID getRecordUniqueIdentifier() {
        return this.recordUniqueIdentifier;
    }

    public void setRecordUniqueIdentifier(UUID recordUniqueIdentifier) {
        this.recordUniqueIdentifier = recordUniqueIdentifier;
    }

    public School recordUniqueIdentifier(UUID recordUniqueIdentifier) {
        this.setRecordUniqueIdentifier(recordUniqueIdentifier);
        return this;
    }

    public Integer getSchoolId() {
        return this.schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public School schoolId(Integer schoolId) {
        this.setSchoolId(schoolId);
        return this;
    }

    public String getSchoolCode() {
        return this.schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    public School schoolCode(String schoolCode) {
        this.setSchoolCode(schoolCode);
        return this;
    }

    public String getSchoolPhoneNumber() {
        return this.schoolPhoneNumber;
    }

    public void setSchoolPhoneNumber(String schoolPhoneNumber) {
        this.schoolPhoneNumber = schoolPhoneNumber;
    }

    public School schoolPhoneNumber(String schoolPhoneNumber) {
        this.setSchoolPhoneNumber(schoolPhoneNumber);
        return this;
    }

    public String getSchoolAlternativePhoneNumber() {
        return this.schoolAlternativePhoneNumber;
    }

    public void setSchoolAlternativePhoneNumber(String schoolAlternativePhoneNumber) {
        this.schoolAlternativePhoneNumber = schoolAlternativePhoneNumber;
    }

    public School schoolAlternativePhoneNumber(String schoolAlternativePhoneNumber) {
        this.setSchoolAlternativePhoneNumber(schoolAlternativePhoneNumber);
        return this;
    }

    public String getSchoolemailAddess() {
        return this.schoolemailAddess;
    }

    public void setSchoolemailAddess(String schoolemailAddess) {
        this.schoolemailAddess = schoolemailAddess;
    }

    public School schoolemailAddess(String schoolemailAddess) {
        this.setSchoolemailAddess(schoolemailAddess);
        return this;
    }

    public String getSchoolName() {
        return this.schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public School schoolName(String schoolName) {
        this.setSchoolName(schoolName);
        return this;
    }

    public Boolean getStatus() {
        return this.status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public School status(Boolean status) {
        this.setStatus(status);
        return this;
    }

    public String getFreeField1() {
        return this.freeField1;
    }

    public void setFreeField1(String freeField1) {
        this.freeField1 = freeField1;
    }

    public School freeField1(String freeField1) {
        this.setFreeField1(freeField1);
        return this;
    }

    public String getFreeField2() {
        return this.freeField2;
    }

    public void setFreeField2(String freeField2) {
        this.freeField2 = freeField2;
    }

    public School freeField2(String freeField2) {
        this.setFreeField2(freeField2);
        return this;
    }

    public String getFreeField3() {
        return this.freeField3;
    }

    public void setFreeField3(String freeField3) {
        this.freeField3 = freeField3;
    }

    public School freeField3(String freeField3) {
        this.setFreeField3(freeField3);
        return this;
    }

    public LocalDate getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public School createdAt(LocalDate createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public LocalDate getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public School updatedAt(LocalDate updatedAt) {
        this.setUpdatedAt(updatedAt);
        return this;
    }

    public LocalDate getIsDeleted() {
        return this.isDeleted;
    }

    public void setIsDeleted(LocalDate isDeleted) {
        this.isDeleted = isDeleted;
    }

    public School isDeleted(LocalDate isDeleted) {
        this.setIsDeleted(isDeleted);
        return this;
    }

    public Set<Student> getStudents() {
        return this.students;
    }

    public void setStudents(Set<Student> students) {
        if (this.students != null) {
            this.students.forEach(i -> i.setSchool(null));
        }
        if (students != null) {
            students.forEach(i -> i.setSchool(this));
        }
        this.students = students;
    }

    public School students(Set<Student> students) {
        this.setStudents(students);
        return this;
    }

    public School addStudent(Student student) {
        this.students.add(student);
        student.setSchool(this);
        return this;
    }

    public School removeStudent(Student student) {
        this.students.remove(student);
        student.setSchool(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof School)) {
            return false;
        }
        return id != null && id.equals(((School) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "School{" +
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

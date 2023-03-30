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
 * A StudentClass.
 */
@Entity
@Table(name = "student_class")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class StudentClass implements Serializable {

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
    @Column(name = "student_class_id", nullable = false, unique = true)
    private Integer studentClassId;

    @NotNull
    @Column(name = "student_class_code", nullable = false)
    private String studentClassCode;

    @NotNull
    @Column(name = "student_class_name", nullable = false)
    private String studentClassName;

    @NotNull
    @Column(name = "student_class_description", nullable = false)
    private String studentClassDescription;

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

    @OneToMany(mappedBy = "studentClass")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "studentClass" }, allowSetters = true)
    private Set<AssociatedFees> associatedFees = new HashSet<>();

    @OneToMany(mappedBy = "studentClass")
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

    public StudentClass id(Long id) {
        this.setId(id);
        return this;
    }

    public UUID getRecordUniqueIdentifier() {
        return this.recordUniqueIdentifier;
    }

    public void setRecordUniqueIdentifier(UUID recordUniqueIdentifier) {
        this.recordUniqueIdentifier = recordUniqueIdentifier;
    }

    public StudentClass recordUniqueIdentifier(UUID recordUniqueIdentifier) {
        this.setRecordUniqueIdentifier(recordUniqueIdentifier);
        return this;
    }

    public Integer getStudentClassId() {
        return this.studentClassId;
    }

    public void setStudentClassId(Integer studentClassId) {
        this.studentClassId = studentClassId;
    }

    public StudentClass studentClassId(Integer studentClassId) {
        this.setStudentClassId(studentClassId);
        return this;
    }

    public String getStudentClassCode() {
        return this.studentClassCode;
    }

    public void setStudentClassCode(String studentClassCode) {
        this.studentClassCode = studentClassCode;
    }

    public StudentClass studentClassCode(String studentClassCode) {
        this.setStudentClassCode(studentClassCode);
        return this;
    }

    public String getStudentClassName() {
        return this.studentClassName;
    }

    public void setStudentClassName(String studentClassName) {
        this.studentClassName = studentClassName;
    }

    public StudentClass studentClassName(String studentClassName) {
        this.setStudentClassName(studentClassName);
        return this;
    }

    public String getStudentClassDescription() {
        return this.studentClassDescription;
    }

    public void setStudentClassDescription(String studentClassDescription) {
        this.studentClassDescription = studentClassDescription;
    }

    public StudentClass studentClassDescription(String studentClassDescription) {
        this.setStudentClassDescription(studentClassDescription);
        return this;
    }

    public Boolean getStatus() {
        return this.status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public StudentClass status(Boolean status) {
        this.setStatus(status);
        return this;
    }

    public String getFreeField1() {
        return this.freeField1;
    }

    public void setFreeField1(String freeField1) {
        this.freeField1 = freeField1;
    }

    public StudentClass freeField1(String freeField1) {
        this.setFreeField1(freeField1);
        return this;
    }

    public String getFreeField2() {
        return this.freeField2;
    }

    public void setFreeField2(String freeField2) {
        this.freeField2 = freeField2;
    }

    public StudentClass freeField2(String freeField2) {
        this.setFreeField2(freeField2);
        return this;
    }

    public String getFreeField3() {
        return this.freeField3;
    }

    public void setFreeField3(String freeField3) {
        this.freeField3 = freeField3;
    }

    public StudentClass freeField3(String freeField3) {
        this.setFreeField3(freeField3);
        return this;
    }

    public LocalDate getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public StudentClass createdAt(LocalDate createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public LocalDate getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public StudentClass updatedAt(LocalDate updatedAt) {
        this.setUpdatedAt(updatedAt);
        return this;
    }

    public LocalDate getIsDeleted() {
        return this.isDeleted;
    }

    public void setIsDeleted(LocalDate isDeleted) {
        this.isDeleted = isDeleted;
    }

    public StudentClass isDeleted(LocalDate isDeleted) {
        this.setIsDeleted(isDeleted);
        return this;
    }

    public Set<AssociatedFees> getAssociatedFees() {
        return this.associatedFees;
    }

    public void setAssociatedFees(Set<AssociatedFees> associatedFees) {
        if (this.associatedFees != null) {
            this.associatedFees.forEach(i -> i.setStudentClass(null));
        }
        if (associatedFees != null) {
            associatedFees.forEach(i -> i.setStudentClass(this));
        }
        this.associatedFees = associatedFees;
    }

    public StudentClass associatedFees(Set<AssociatedFees> associatedFees) {
        this.setAssociatedFees(associatedFees);
        return this;
    }

    public StudentClass addAssociatedFees(AssociatedFees associatedFees) {
        this.associatedFees.add(associatedFees);
        associatedFees.setStudentClass(this);
        return this;
    }

    public StudentClass removeAssociatedFees(AssociatedFees associatedFees) {
        this.associatedFees.remove(associatedFees);
        associatedFees.setStudentClass(null);
        return this;
    }

    public Set<Student> getStudents() {
        return this.students;
    }

    public void setStudents(Set<Student> students) {
        if (this.students != null) {
            this.students.forEach(i -> i.setStudentClass(null));
        }
        if (students != null) {
            students.forEach(i -> i.setStudentClass(this));
        }
        this.students = students;
    }

    public StudentClass students(Set<Student> students) {
        this.setStudents(students);
        return this;
    }

    public StudentClass addStudent(Student student) {
        this.students.add(student);
        student.setStudentClass(this);
        return this;
    }

    public StudentClass removeStudent(Student student) {
        this.students.remove(student);
        student.setStudentClass(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StudentClass)) {
            return false;
        }
        return id != null && id.equals(((StudentClass) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "StudentClass{" +
            "id=" + getId() +
            ", recordUniqueIdentifier='" + getRecordUniqueIdentifier() + "'" +
            ", studentClassId=" + getStudentClassId() +
            ", studentClassCode='" + getStudentClassCode() + "'" +
            ", studentClassName='" + getStudentClassName() + "'" +
            ", studentClassDescription='" + getStudentClassDescription() + "'" +
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

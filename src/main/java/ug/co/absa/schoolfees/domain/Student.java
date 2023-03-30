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
import javax.validation.constraints.Size;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

/**
 * This is a student class,\n@author Banada Ismael ABSA DT team
 */
@Entity
@Table(name = "student")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Type(type = "uuid-char")
    @Column(name = "record_unique_identifier", length = 36, nullable = false, unique = true)
    private UUID recordUniqueIdentifier;

    @NotNull
    @Column(name = "student_id", nullable = false)
    private Integer studentId;

    @NotNull
    @Size(min = 3, max = 30)
    @Column(name = "first_name", length = 30, nullable = false)
    private String firstName;

    @NotNull
    @Size(min = 3, max = 30)
    @Column(name = "middle_name", length = 30, nullable = false)
    private String middleName;

    @NotNull
    @Size(min = 3, max = 30)
    @Column(name = "last_name", length = 30, nullable = false)
    private String lastName;

    @NotNull
    @Size(min = 3, max = 20)
    @Column(name = "payment_code", length = 20, nullable = false)
    private String paymentCode;

    @NotNull
    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @NotNull
    @Size(min = 3, max = 8)
    @Column(name = "out_standing_amount", length = 8, nullable = false)
    private String outStandingAmount;

    @NotNull
    @Column(name = "status", nullable = false)
    private Boolean status;

    @Column(name = "student_contact")
    private String studentContact;

    @Column(name = "student_address")
    private String studentAddress;

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

    @OneToMany(mappedBy = "student")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "student" }, allowSetters = true)
    private Set<Payment> payments = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = { "associatedFees", "students" }, allowSetters = true)
    private StudentClass studentClass;

    @ManyToOne
    @JsonIgnoreProperties(value = { "students" }, allowSetters = true)
    private School school;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student id(Long id) {
        this.setId(id);
        return this;
    }

    public UUID getRecordUniqueIdentifier() {
        return this.recordUniqueIdentifier;
    }

    public void setRecordUniqueIdentifier(UUID recordUniqueIdentifier) {
        this.recordUniqueIdentifier = recordUniqueIdentifier;
    }

    public Student recordUniqueIdentifier(UUID recordUniqueIdentifier) {
        this.setRecordUniqueIdentifier(recordUniqueIdentifier);
        return this;
    }

    public Integer getStudentId() {
        return this.studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Student studentId(Integer studentId) {
        this.setStudentId(studentId);
        return this;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Student firstName(String firstName) {
        this.setFirstName(firstName);
        return this;
    }

    public String getMiddleName() {
        return this.middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Student middleName(String middleName) {
        this.setMiddleName(middleName);
        return this;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Student lastName(String lastName) {
        this.setLastName(lastName);
        return this;
    }

    public String getPaymentCode() {
        return this.paymentCode;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }

    public Student paymentCode(String paymentCode) {
        this.setPaymentCode(paymentCode);
        return this;
    }

    public LocalDate getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Student dateOfBirth(LocalDate dateOfBirth) {
        this.setDateOfBirth(dateOfBirth);
        return this;
    }

    public String getOutStandingAmount() {
        return this.outStandingAmount;
    }

    public void setOutStandingAmount(String outStandingAmount) {
        this.outStandingAmount = outStandingAmount;
    }

    public Student outStandingAmount(String outStandingAmount) {
        this.setOutStandingAmount(outStandingAmount);
        return this;
    }

    public Boolean getStatus() {
        return this.status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Student status(Boolean status) {
        this.setStatus(status);
        return this;
    }

    public String getStudentContact() {
        return this.studentContact;
    }

    public void setStudentContact(String studentContact) {
        this.studentContact = studentContact;
    }

    public Student studentContact(String studentContact) {
        this.setStudentContact(studentContact);
        return this;
    }

    public String getStudentAddress() {
        return this.studentAddress;
    }

    public void setStudentAddress(String studentAddress) {
        this.studentAddress = studentAddress;
    }

    public Student studentAddress(String studentAddress) {
        this.setStudentAddress(studentAddress);
        return this;
    }

    public String getFreeField1() {
        return this.freeField1;
    }

    public void setFreeField1(String freeField1) {
        this.freeField1 = freeField1;
    }

    public Student freeField1(String freeField1) {
        this.setFreeField1(freeField1);
        return this;
    }

    public String getFreeField2() {
        return this.freeField2;
    }

    public void setFreeField2(String freeField2) {
        this.freeField2 = freeField2;
    }

    public Student freeField2(String freeField2) {
        this.setFreeField2(freeField2);
        return this;
    }

    public String getFreeField3() {
        return this.freeField3;
    }

    public void setFreeField3(String freeField3) {
        this.freeField3 = freeField3;
    }

    public Student freeField3(String freeField3) {
        this.setFreeField3(freeField3);
        return this;
    }

    public LocalDate getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public Student createdAt(LocalDate createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public LocalDate getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Student updatedAt(LocalDate updatedAt) {
        this.setUpdatedAt(updatedAt);
        return this;
    }

    public LocalDate getIsDeleted() {
        return this.isDeleted;
    }

    public void setIsDeleted(LocalDate isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Student isDeleted(LocalDate isDeleted) {
        this.setIsDeleted(isDeleted);
        return this;
    }

    public Set<Payment> getPayments() {
        return this.payments;
    }

    public void setPayments(Set<Payment> payments) {
        if (this.payments != null) {
            this.payments.forEach(i -> i.setStudent(null));
        }
        if (payments != null) {
            payments.forEach(i -> i.setStudent(this));
        }
        this.payments = payments;
    }

    public Student payments(Set<Payment> payments) {
        this.setPayments(payments);
        return this;
    }

    public Student addPayment(Payment payment) {
        this.payments.add(payment);
        payment.setStudent(this);
        return this;
    }

    public Student removePayment(Payment payment) {
        this.payments.remove(payment);
        payment.setStudent(null);
        return this;
    }

    public StudentClass getStudentClass() {
        return this.studentClass;
    }

    public void setStudentClass(StudentClass studentClass) {
        this.studentClass = studentClass;
    }

    public Student studentClass(StudentClass studentClass) {
        this.setStudentClass(studentClass);
        return this;
    }

    public School getSchool() {
        return this.school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public Student school(School school) {
        this.setSchool(school);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Student)) {
            return false;
        }
        return id != null && id.equals(((Student) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Student{" +
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
            "}";
    }
}

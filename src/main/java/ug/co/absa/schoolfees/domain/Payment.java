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
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import ug.co.absa.schoolfees.domain.enumeration.PaymentChannel;

/**
 * A Payment.
 */
@Entity
@Table(name = "payment")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Payment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Type(type = "uuid-char")
    @Column(name = "record_unique_identifier", length = 36, nullable = false, unique = true)
    private UUID recordUniqueIdentifier;

    @Size(max = 3)
    @Column(name = "return_code", length = 3)
    private String returnCode;

    @NotNull
    @Size(max = 50)
    @Column(name = "return_message", length = 50, nullable = false)
    private String returnMessage;

    @Column(name = "process_timestamp")
    private LocalDate processTimestamp;

    @Column(name = "fee_amount")
    private Integer feeAmount;

    @Size(min = 3, max = 200)
    @Column(name = "fee_description", length = 200)
    private String feeDescription;

    @Column(name = "fee_due_from_date")
    private LocalDate feeDueFromDate;

    @Column(name = "fee_due_to_date")
    private LocalDate feeDueToDate;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "fee_id", length = 50, nullable = false)
    private String feeId;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @NotNull
    @Size(min = 3, max = 50)
    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @NotNull
    @Size(min = 3, max = 50)
    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    @NotNull
    @Size(min = 3, max = 50)
    @Column(name = "middle_name", length = 50, nullable = false)
    private String middleName;

    @Column(name = "outstanding_amount")
    private Integer outstandingAmount;

    @NotNull
    @Size(min = 3, max = 50)
    @Column(name = "payment_code", length = 50, nullable = false)
    private String paymentCode;

    @NotNull
    @Size(min = 3, max = 50)
    @Column(name = "school_code", length = 50, nullable = false)
    private String schoolCode;

    @NotNull
    @Size(min = 3, max = 200)
    @Column(name = "school_name", length = 200, nullable = false)
    private String schoolName;

    @NotNull
    @Size(min = 3, max = 50)
    @Column(name = "student_class", length = 50, nullable = false)
    private String studentClass;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_channel", nullable = false)
    private PaymentChannel paymentChannel;

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

    @ManyToOne
    @JsonIgnoreProperties(value = { "payments", "studentClass", "school" }, allowSetters = true)
    private Student student;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Payment id(Long id) {
        this.setId(id);
        return this;
    }

    public UUID getRecordUniqueIdentifier() {
        return this.recordUniqueIdentifier;
    }

    public void setRecordUniqueIdentifier(UUID recordUniqueIdentifier) {
        this.recordUniqueIdentifier = recordUniqueIdentifier;
    }

    public Payment recordUniqueIdentifier(UUID recordUniqueIdentifier) {
        this.setRecordUniqueIdentifier(recordUniqueIdentifier);
        return this;
    }

    public String getReturnCode() {
        return this.returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public Payment returnCode(String returnCode) {
        this.setReturnCode(returnCode);
        return this;
    }

    public String getReturnMessage() {
        return this.returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }

    public Payment returnMessage(String returnMessage) {
        this.setReturnMessage(returnMessage);
        return this;
    }

    public LocalDate getProcessTimestamp() {
        return this.processTimestamp;
    }

    public void setProcessTimestamp(LocalDate processTimestamp) {
        this.processTimestamp = processTimestamp;
    }

    public Payment processTimestamp(LocalDate processTimestamp) {
        this.setProcessTimestamp(processTimestamp);
        return this;
    }

    public Integer getFeeAmount() {
        return this.feeAmount;
    }

    public void setFeeAmount(Integer feeAmount) {
        this.feeAmount = feeAmount;
    }

    public Payment feeAmount(Integer feeAmount) {
        this.setFeeAmount(feeAmount);
        return this;
    }

    public String getFeeDescription() {
        return this.feeDescription;
    }

    public void setFeeDescription(String feeDescription) {
        this.feeDescription = feeDescription;
    }

    public Payment feeDescription(String feeDescription) {
        this.setFeeDescription(feeDescription);
        return this;
    }

    public LocalDate getFeeDueFromDate() {
        return this.feeDueFromDate;
    }

    public void setFeeDueFromDate(LocalDate feeDueFromDate) {
        this.feeDueFromDate = feeDueFromDate;
    }

    public Payment feeDueFromDate(LocalDate feeDueFromDate) {
        this.setFeeDueFromDate(feeDueFromDate);
        return this;
    }

    public LocalDate getFeeDueToDate() {
        return this.feeDueToDate;
    }

    public void setFeeDueToDate(LocalDate feeDueToDate) {
        this.feeDueToDate = feeDueToDate;
    }

    public Payment feeDueToDate(LocalDate feeDueToDate) {
        this.setFeeDueToDate(feeDueToDate);
        return this;
    }

    public String getFeeId() {
        return this.feeId;
    }

    public void setFeeId(String feeId) {
        this.feeId = feeId;
    }

    public Payment feeId(String feeId) {
        this.setFeeId(feeId);
        return this;
    }

    public LocalDate getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Payment dateOfBirth(LocalDate dateOfBirth) {
        this.setDateOfBirth(dateOfBirth);
        return this;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Payment firstName(String firstName) {
        this.setFirstName(firstName);
        return this;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Payment lastName(String lastName) {
        this.setLastName(lastName);
        return this;
    }

    public String getMiddleName() {
        return this.middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Payment middleName(String middleName) {
        this.setMiddleName(middleName);
        return this;
    }

    public Integer getOutstandingAmount() {
        return this.outstandingAmount;
    }

    public void setOutstandingAmount(Integer outstandingAmount) {
        this.outstandingAmount = outstandingAmount;
    }

    public Payment outstandingAmount(Integer outstandingAmount) {
        this.setOutstandingAmount(outstandingAmount);
        return this;
    }

    public String getPaymentCode() {
        return this.paymentCode;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }

    public Payment paymentCode(String paymentCode) {
        this.setPaymentCode(paymentCode);
        return this;
    }

    public String getSchoolCode() {
        return this.schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    public Payment schoolCode(String schoolCode) {
        this.setSchoolCode(schoolCode);
        return this;
    }

    public String getSchoolName() {
        return this.schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public Payment schoolName(String schoolName) {
        this.setSchoolName(schoolName);
        return this;
    }

    public String getStudentClass() {
        return this.studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public Payment studentClass(String studentClass) {
        this.setStudentClass(studentClass);
        return this;
    }

    public PaymentChannel getPaymentChannel() {
        return this.paymentChannel;
    }

    public void setPaymentChannel(PaymentChannel paymentChannel) {
        this.paymentChannel = paymentChannel;
    }

    public Payment paymentChannel(PaymentChannel paymentChannel) {
        this.setPaymentChannel(paymentChannel);
        return this;
    }

    public String getFreeField1() {
        return this.freeField1;
    }

    public void setFreeField1(String freeField1) {
        this.freeField1 = freeField1;
    }

    public Payment freeField1(String freeField1) {
        this.setFreeField1(freeField1);
        return this;
    }

    public String getFreeField2() {
        return this.freeField2;
    }

    public void setFreeField2(String freeField2) {
        this.freeField2 = freeField2;
    }

    public Payment freeField2(String freeField2) {
        this.setFreeField2(freeField2);
        return this;
    }

    public String getFreeField3() {
        return this.freeField3;
    }

    public void setFreeField3(String freeField3) {
        this.freeField3 = freeField3;
    }

    public Payment freeField3(String freeField3) {
        this.setFreeField3(freeField3);
        return this;
    }

    public LocalDate getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public Payment createdAt(LocalDate createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public LocalDate getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Payment updatedAt(LocalDate updatedAt) {
        this.setUpdatedAt(updatedAt);
        return this;
    }

    public Student getStudent() {
        return this.student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Payment student(Student student) {
        this.setStudent(student);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Payment)) {
            return false;
        }
        return id != null && id.equals(((Payment) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Payment{" +
            "id=" + getId() +
            ", recordUniqueIdentifier='" + getRecordUniqueIdentifier() + "'" +
            ", returnCode='" + getReturnCode() + "'" +
            ", returnMessage='" + getReturnMessage() + "'" +
            ", processTimestamp='" + getProcessTimestamp() + "'" +
            ", feeAmount=" + getFeeAmount() +
            ", feeDescription='" + getFeeDescription() + "'" +
            ", feeDueFromDate='" + getFeeDueFromDate() + "'" +
            ", feeDueToDate='" + getFeeDueToDate() + "'" +
            ", feeId='" + getFeeId() + "'" +
            ", dateOfBirth='" + getDateOfBirth() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", middleName='" + getMiddleName() + "'" +
            ", outstandingAmount=" + getOutstandingAmount() +
            ", paymentCode='" + getPaymentCode() + "'" +
            ", schoolCode='" + getSchoolCode() + "'" +
            ", schoolName='" + getSchoolName() + "'" +
            ", studentClass='" + getStudentClass() + "'" +
            ", paymentChannel='" + getPaymentChannel() + "'" +
            ", freeField1='" + getFreeField1() + "'" +
            ", freeField2='" + getFreeField2() + "'" +
            ", freeField3='" + getFreeField3() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            "}";
    }
}

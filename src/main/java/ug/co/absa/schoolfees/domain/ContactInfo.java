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

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

/**
 * A ContactInfo.
 */
@Entity
@Table(name = "contact_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ContactInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Type(type = "uuid-char")
    @Column(name = "record_unique_identifier", length = 36, nullable = false)
    private UUID recordUniqueIdentifier;

    @Column(name = "contact_id", unique = true)
    private String contactId;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "parents_phone_number")
    private String parentsPhoneNumber;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "updated_at")
    private LocalDate updatedAt;

    /**
     * this is used to identify whether the\nrecord has been deleted or not (Y-deleted, N-Not deleted)
     */
    @Column(name = "is_deleted")
    private LocalDate isDeleted;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ContactInfo id(Long id) {
        this.setId(id);
        return this;
    }

    public UUID getRecordUniqueIdentifier() {
        return this.recordUniqueIdentifier;
    }

    public void setRecordUniqueIdentifier(UUID recordUniqueIdentifier) {
        this.recordUniqueIdentifier = recordUniqueIdentifier;
    }

    public ContactInfo recordUniqueIdentifier(UUID recordUniqueIdentifier) {
        this.setRecordUniqueIdentifier(recordUniqueIdentifier);
        return this;
    }

    public String getContactId() {
        return this.contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public ContactInfo contactId(String contactId) {
        this.setContactId(contactId);
        return this;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ContactInfo phoneNumber(String phoneNumber) {
        this.setPhoneNumber(phoneNumber);
        return this;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public ContactInfo emailAddress(String emailAddress) {
        this.setEmailAddress(emailAddress);
        return this;
    }

    public String getParentsPhoneNumber() {
        return this.parentsPhoneNumber;
    }

    public void setParentsPhoneNumber(String parentsPhoneNumber) {
        this.parentsPhoneNumber = parentsPhoneNumber;
    }

    public ContactInfo parentsPhoneNumber(String parentsPhoneNumber) {
        this.setParentsPhoneNumber(parentsPhoneNumber);
        return this;
    }

    public LocalDate getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public ContactInfo createdAt(LocalDate createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public LocalDate getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public ContactInfo updatedAt(LocalDate updatedAt) {
        this.setUpdatedAt(updatedAt);
        return this;
    }

    public LocalDate getIsDeleted() {
        return this.isDeleted;
    }

    public void setIsDeleted(LocalDate isDeleted) {
        this.isDeleted = isDeleted;
    }

    public ContactInfo isDeleted(LocalDate isDeleted) {
        this.setIsDeleted(isDeleted);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ContactInfo)) {
            return false;
        }
        return id != null && id.equals(((ContactInfo) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ContactInfo{" +
            "id=" + getId() +
            ", recordUniqueIdentifier='" + getRecordUniqueIdentifier() + "'" +
            ", contactId='" + getContactId() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", emailAddress='" + getEmailAddress() + "'" +
            ", parentsPhoneNumber='" + getParentsPhoneNumber() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", isDeleted='" + getIsDeleted() + "'" +
            "}";
    }
}

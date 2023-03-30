package ug.co.absa.schoolfees.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link ug.co.absa.schoolfees.domain.ContactInfo} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ContactInfoDTO implements Serializable {

    private Long id;

    @NotNull
    private UUID recordUniqueIdentifier;

    private String contactId;

    private String phoneNumber;

    private String emailAddress;

    private String parentsPhoneNumber;

    private LocalDate createdAt;

    private LocalDate updatedAt;

    /**
     * this is used to identify whether the\nrecord has been deleted or not (Y-deleted, N-Not deleted)
     */
    @Schema(description = "this is used to identify whether the\nrecord has been deleted or not (Y-deleted, N-Not deleted)")
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

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getParentsPhoneNumber() {
        return parentsPhoneNumber;
    }

    public void setParentsPhoneNumber(String parentsPhoneNumber) {
        this.parentsPhoneNumber = parentsPhoneNumber;
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
        if (!(o instanceof ContactInfoDTO)) {
            return false;
        }

        ContactInfoDTO contactInfoDTO = (ContactInfoDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, contactInfoDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ContactInfoDTO{" +
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

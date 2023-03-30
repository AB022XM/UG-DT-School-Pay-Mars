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
import java.time.ZonedDateTime;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

/**
 * A PaymentChannel.
 */
@Entity
@Table(name = "payment_channel")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PaymentChannel implements Serializable {

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
    @Column(name = "channel_id", nullable = false)
    private Integer channelId;

    @NotNull
    @Column(name = "channel_code", nullable = false)
    private Integer channelCode;

    @NotNull
    @Column(name = "channel_name", nullable = false)
    private Integer channelName;

    @NotNull
    @Column(name = "status", nullable = false)
    private Boolean status;

    /**
     * This is an additional field\ncreated in case there is need to\nadd a field without the need to alter database configs
     */
    @Column(name = "free_field_1")
    private String freeField1;

    /**
     * This is an additional field\ncreated in case there is need to\nadd a field without the need to alter database configs
     */
    @Column(name = "free_field_2")
    private String freeField2;

    /**
     * This is an additional field\ncreated in case there is need to\nadd a field without the need to alter database configs
     */
    @Column(name = "free_field_3")
    private String freeField3;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDate updatedAt;

    /**
     * this is used to identify whether the\nrecord has been deleted or not (Y-deleted, N-Not deleted)
     */
    @Column(name = "is_deleted")
    private Boolean isDeleted;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PaymentChannel id(Long id) {
        this.setId(id);
        return this;
    }

    public UUID getRecordUniqueIdentifier() {
        return this.recordUniqueIdentifier;
    }

    public void setRecordUniqueIdentifier(UUID recordUniqueIdentifier) {
        this.recordUniqueIdentifier = recordUniqueIdentifier;
    }

    public PaymentChannel recordUniqueIdentifier(UUID recordUniqueIdentifier) {
        this.setRecordUniqueIdentifier(recordUniqueIdentifier);
        return this;
    }

    public Integer getChannelId() {
        return this.channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public PaymentChannel channelId(Integer channelId) {
        this.setChannelId(channelId);
        return this;
    }

    public Integer getChannelCode() {
        return this.channelCode;
    }

    public void setChannelCode(Integer channelCode) {
        this.channelCode = channelCode;
    }

    public PaymentChannel channelCode(Integer channelCode) {
        this.setChannelCode(channelCode);
        return this;
    }

    public Integer getChannelName() {
        return this.channelName;
    }

    public void setChannelName(Integer channelName) {
        this.channelName = channelName;
    }

    public PaymentChannel channelName(Integer channelName) {
        this.setChannelName(channelName);
        return this;
    }

    public Boolean getStatus() {
        return this.status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public PaymentChannel status(Boolean status) {
        this.setStatus(status);
        return this;
    }

    public String getFreeField1() {
        return this.freeField1;
    }

    public void setFreeField1(String freeField1) {
        this.freeField1 = freeField1;
    }

    public PaymentChannel freeField1(String freeField1) {
        this.setFreeField1(freeField1);
        return this;
    }

    public String getFreeField2() {
        return this.freeField2;
    }

    public void setFreeField2(String freeField2) {
        this.freeField2 = freeField2;
    }

    public PaymentChannel freeField2(String freeField2) {
        this.setFreeField2(freeField2);
        return this;
    }

    public String getFreeField3() {
        return this.freeField3;
    }

    public void setFreeField3(String freeField3) {
        this.freeField3 = freeField3;
    }

    public PaymentChannel freeField3(String freeField3) {
        this.setFreeField3(freeField3);
        return this;
    }

    public ZonedDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public PaymentChannel createdAt(ZonedDateTime createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public LocalDate getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public PaymentChannel updatedAt(LocalDate updatedAt) {
        this.setUpdatedAt(updatedAt);
        return this;
    }

    public Boolean getIsDeleted() {
        return this.isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public PaymentChannel isDeleted(Boolean isDeleted) {
        this.setIsDeleted(isDeleted);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PaymentChannel)) {
            return false;
        }
        return id != null && id.equals(((PaymentChannel) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PaymentChannel{" +
            "id=" + getId() +
            ", recordUniqueIdentifier='" + getRecordUniqueIdentifier() + "'" +
            ", channelId=" + getChannelId() +
            ", channelCode=" + getChannelCode() +
            ", channelName=" + getChannelName() +
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

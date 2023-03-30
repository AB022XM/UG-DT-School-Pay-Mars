package ug.co.absa.schoolfees.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link ug.co.absa.schoolfees.domain.PaymentChannel} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PaymentChannelDTO implements Serializable {

    private Long id;

    @NotNull
    private UUID recordUniqueIdentifier;

    @NotNull
    private Integer channelId;

    @NotNull
    private Integer channelCode;

    @NotNull
    private Integer channelName;

    @NotNull
    private Boolean status;

    /**
     * This is an additional field\ncreated in case there is need to\nadd a field without the need to alter database configs
     */
    @Schema(
        description = "This is an additional field\ncreated in case there is need to\nadd a field without the need to alter database configs"
    )
    private String freeField1;

    /**
     * This is an additional field\ncreated in case there is need to\nadd a field without the need to alter database configs
     */
    @Schema(
        description = "This is an additional field\ncreated in case there is need to\nadd a field without the need to alter database configs"
    )
    private String freeField2;

    /**
     * This is an additional field\ncreated in case there is need to\nadd a field without the need to alter database configs
     */
    @Schema(
        description = "This is an additional field\ncreated in case there is need to\nadd a field without the need to alter database configs"
    )
    private String freeField3;

    private ZonedDateTime createdAt;

    private LocalDate updatedAt;

    /**
     * this is used to identify whether the\nrecord has been deleted or not (Y-deleted, N-Not deleted)
     */
    @Schema(description = "this is used to identify whether the\nrecord has been deleted or not (Y-deleted, N-Not deleted)")
    private Boolean isDeleted;

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

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public Integer getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(Integer channelCode) {
        this.channelCode = channelCode;
    }

    public Integer getChannelName() {
        return channelName;
    }

    public void setChannelName(Integer channelName) {
        this.channelName = channelName;
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

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PaymentChannelDTO)) {
            return false;
        }

        PaymentChannelDTO paymentChannelDTO = (PaymentChannelDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, paymentChannelDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PaymentChannelDTO{" +
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

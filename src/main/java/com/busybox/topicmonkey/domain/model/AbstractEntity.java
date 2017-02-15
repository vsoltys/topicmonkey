package com.busybox.topicmonkey.domain.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

import static com.busybox.topicmonkey.domain.utils.UniqueIdGenerator.uniqueId;
import static java.time.LocalDateTime.now;

@MappedSuperclass
public abstract class AbstractEntity
        implements Serializable {

    private static final String SYSTEM = "SYSTEM";

    @Id
    @Column(name = "ID")
    private String id;

    @Version
    @Column(name = "VERSION")
    private Long version;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    @Column(name = "UPDATED_BY")
    private String updatedBy;

/*
    @Enumerated(EnumType.STRING)
    @Column(name = "STATE", nullable = false)
    private EntityState state = EntityState.ACTIVE;
*/

    public String getId() {
        return id;
    }

    public Long getVersion() {
        return version;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("Should be implemented by subclass.");
    }

    @Override
    public boolean equals(Object o) {
        throw new UnsupportedOperationException("Should be implemented by subclass.");
    }

    @PrePersist
    private void onPrePersist() {
        setUniqueId();
        setCreatedAuditInfo();
        setUpdateAuditInfo();
    }

    @PreUpdate
    private void onPreUpdate() {
        setUpdateAuditInfo();
    }

    private void setUniqueId() {
        id = uniqueId();
    }

    private void setCreatedAuditInfo() {
        createdAt = now();
        createdBy = SYSTEM;
    }

    private void setUpdateAuditInfo() {
        updatedAt = now();
        updatedBy = SYSTEM;
    }

    /*
        public EntityState getState() {
            return state;
        }

        public void onDelete() throws SystemException {
            if (EntityState.INACTIVE.equals(state)) {
                throw new SystemException("Entity with id[" + getId() + "] already deleted");
            }
            state = EntityState.INACTIVE;
        }

        public isActive() {
            return EntityState.ACTIVE.equals(state);
        }
    */
}

package com.busybox.topicmonkey.domain.model;

import com.busybox.topicmonkey.domain.SystemException;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "created_by")
    private String createdBy;

    // TODO: add audit support

    @Column(name = "state", nullable = false)
    private EntityState state = EntityState.ACTIVE;

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public EntityState getState() {
        return state;
    }

    public void onDelete() throws SystemException {
        if (EntityState.INACTIVE.equals(state)) {
            throw new SystemException("Entity with id[" + getId() + "] already deleted");
        }
        state = EntityState.INACTIVE;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}

package com.busybox.topicmonkey.domain.model;

import static org.springframework.util.Assert.hasLength;
import static org.springframework.util.Assert.notNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "SEARCH_ENTRY")
public class SearchEntry
        extends AbstractEntity {

    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

    @Column(name = "CONTENT", nullable = false, unique = true)
    private String content;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    public SearchEntry() {
        // jpa
    }

    private SearchEntry(Builder builder) {
        this.name = builder.name;
        this.content = builder.content;
        this.user = builder.user;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static class Builder {
        private String name;
        private String content;
        private User user;

        private Builder() {
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withContent(String content) {
            this.content = content;
            return this;
        }

        public Builder withUser(User user) {
            this.user = user;
            return this;
        }

        public SearchEntry build() {
            verify();
            return new SearchEntry(this);
        }

        private void verify() {
            hasLength(name);
            hasLength(content);
            notNull(user);
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", getId())
                .append("name", name)
                .toString();
    }
}

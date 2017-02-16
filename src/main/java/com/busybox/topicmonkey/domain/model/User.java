package com.busybox.topicmonkey.domain.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "USER")
public class User
        extends AbstractEntity {

    @Column(name = "LOGIN_NAME", nullable = false, unique = true)
    private String loginName;

    @Column(name = "EMAIL_ADDRESS", nullable = false, unique = true)
    private String emailAddress;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<SearchEntry> searchEntries;

    private User(Builder builder) {
        this.loginName = builder.loginName;
        this.emailAddress = builder.emailAddress;
        this.searchEntries = builder.searchEntries;
    }

    private User() {
        // jpa
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getLoginName() {
        return loginName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Set<SearchEntry> getSearchEntries() {
        return searchEntries;
    }

    public void setSearchEntries(Set<SearchEntry> searchEntries) {
        this.searchEntries = searchEntries;
    }

    public static class Builder {

        private String loginName;
        private String emailAddress;
        private Set<SearchEntry> searchEntries;

        private Builder() {
        }

        public Builder withLoginName(String loginName) {
            this.loginName = loginName;
            return this;
        }

        public Builder withEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }

        public void withSearchEntries(Set<SearchEntry> searchEntries) {
            this.searchEntries = searchEntries;
        }

        public User build() {
            verify();
            return new User(this);
        }

        private void verify() {
            Assert.hasLength(loginName);
            Assert.hasLength(emailAddress);
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", getId())
                .append("loginName", loginName)
                .append("searchEntries", searchEntries)
                .toString();
    }
}

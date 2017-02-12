package com.busybox.topicmonkey.domain.model;

import org.springframework.util.Assert;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tm_user")
public class User extends AbstractEntity {
    private static final long serialVersionUID = 6770553344586829213L;

    @Column(name = "login_name", nullable = false, unique = true)
    private String loginName;

    @Column(name = "email_address", nullable = false)
    private String emailAddress;

    private User(Builder builder) {
        this.loginName = builder.loginName;
        this.emailAddress = builder.emailAddress;
    }

    protected User() {
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

    @Override
    public String toString() {
        return "User[id=" + getId() + ", loginName='" + loginName + "']";
    }

    public static class Builder {

        private String loginName;
        private String emailAddress;

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

        public User build() {
            verify();
            return new User(this);
        }

        private void verify() {
            Assert.hasLength(loginName);
            Assert.hasLength(emailAddress);
        }
    }
}
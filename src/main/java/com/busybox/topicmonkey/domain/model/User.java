package com.busybox.topicmonkey.domain.model;

import org.springframework.util.Assert;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "USER")
public class User
        extends AbstractEntity {

    @Column(name = "LOGIN_NAME", nullable = false, unique = true)
    private String loginName;

    @Column(name = "EMAIL_ADDRESS", nullable = false, unique = true)
    private String emailAddress;

    private User(Builder builder) {
        this.loginName = builder.loginName;
        this.emailAddress = builder.emailAddress;
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

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + loginName.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof User))
            return false;
        if (!super.equals(o))
            return false;

        User user = (User) o;
        return loginName.equals(user.loginName);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder("User{");
        builder.append("loginName='").append(loginName).append('\'');
        builder.append('}');
        return builder.toString();
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

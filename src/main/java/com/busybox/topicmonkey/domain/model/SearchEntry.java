package com.busybox.topicmonkey.domain.model;

import org.springframework.util.Assert;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "SEARCH_ENTRY")
public class SearchEntry
        extends AbstractEntity {

    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

    @Column(name = "CONTENT", nullable = false, unique = true)
    private String content;

    public SearchEntry() {
        // jpa
    }

    private SearchEntry(Builder builder) {
        this.name = builder.name;
        this.content = builder.content;
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

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof SearchEntry))
            return false;
        if (!super.equals(o))
            return false;

        SearchEntry that = (SearchEntry) o;
        return name.equals(that.name);
    }

    public static class Builder {
        private String name;
        private String content;

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

        public SearchEntry build() {
            verify();
            return new SearchEntry(this);
        }

        private void verify() {
            Assert.hasLength(name);
        }
    }
}

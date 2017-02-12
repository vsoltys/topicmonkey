package com.busybox.topicmonkey.domain.model;

import org.springframework.util.Assert;

public class Topic extends AbstractEntity {
    private static final long serialVersionUID = -3926810136371349946L;

    private String name;
    private String content;

    private Topic(Builder builder) {
        this.name = builder.name;
        this.content = builder.content;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getContent() {
        return content;
    }

    public String getName() {
        return name;
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

        public Topic build() {
            verify();
            return new Topic(this);
        }

        private void verify() {
            Assert.hasLength(name);
        }
    }
}

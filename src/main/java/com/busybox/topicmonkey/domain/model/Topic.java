package com.busybox.topicmonkey.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

public class Topic extends ResourceSupport {

    private String name;
    private String content;

    @JsonCreator
    public Topic(@JsonProperty("name") String name, @JsonProperty("content") String content) {
        this.name = name;
        this.content = content;
    }

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
        private static final String DEFAULT_CONTENT = "default content";

        private String name;
        private String content;

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
            Assert.isTrue(!StringUtils.isEmpty(name), "name should not be empty");
            if (StringUtils.isEmpty(content)) {
                content = DEFAULT_CONTENT;
            }
        }
    }
}

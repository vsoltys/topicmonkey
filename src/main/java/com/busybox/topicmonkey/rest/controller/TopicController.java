package com.busybox.topicmonkey.rest.controller;

import com.busybox.topicmonkey.domain.model.Topic;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class TopicController {

    public static final String DEFAULT_CONTENT_STRING = "default content";

    @RequestMapping(value = "/topic", method = RequestMethod.GET)
    public HttpEntity<Topic> getTopic(@RequestParam(value = "name", required = false, defaultValue = "new topic") String name) {

        Topic topic = Topic.builder().withName(name).build();
        topic.add(linkTo(
                methodOn(TopicController.class).getTopic(name)).withSelfRel());

        return new ResponseEntity(topic, HttpStatus.OK);
    }
}

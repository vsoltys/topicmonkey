package com.busybox.topicmonkey.rest.controller;

import com.busybox.topicmonkey.domain.model.SearchEntry;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopicController {

    // TODO: update after SearchEntry entity stack
    @RequestMapping(value = "/topic", method = RequestMethod.GET)
    public HttpEntity<SearchEntry> getTopic(@RequestParam(value = "name", required = false,
            defaultValue = "new topic") String name) {
        SearchEntry searchEntry = SearchEntry.builder().withName(name).build();
        //searchEntry.add(linkTo(methodOn(TopicController.class).getTopic(name)).withSelfRel());

        return new ResponseEntity(searchEntry, HttpStatus.OK);
    }
}

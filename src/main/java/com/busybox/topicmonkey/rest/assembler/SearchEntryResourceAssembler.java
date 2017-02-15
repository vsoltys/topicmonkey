package com.busybox.topicmonkey.rest.assembler;

import com.busybox.topicmonkey.domain.model.SearchEntry;
import com.busybox.topicmonkey.domain.model.User;
import com.busybox.topicmonkey.rest.controller.SearchEntryController;
import com.busybox.topicmonkey.rest.controller.UserController;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class SearchEntryResourceAssembler implements ResourceAssembler<SearchEntry, Resource<SearchEntry>> {

    private Class<SearchEntryController> controllerClass = SearchEntryController.class;

    @Override
    public Resource<SearchEntry> toResource(SearchEntry entry) {
        Resource<SearchEntry> resource = new Resource(entry);
        resource.add(linkTo(methodOn(controllerClass).get(entry.getId())).withSelfRel());
        return resource;
    }
}

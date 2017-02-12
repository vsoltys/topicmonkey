package com.busybox.topicmonkey.rest.assembler;

import com.busybox.topicmonkey.domain.model.User;
import com.busybox.topicmonkey.rest.controller.UserController;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class UserResourceAssembler implements ResourceAssembler<User, Resource<User>> {

    // TODO: EntityResource for domain models or ResourceAssemblers?
    private Class<UserController> controllerClass = UserController.class;

    @Override
    public Resource<User> toResource(User user) {
        Resource<User> resource = new Resource(user);
        resource.add(linkTo(methodOn(controllerClass).getUser(user.getId())).withSelfRel());
        return resource;
    }
}

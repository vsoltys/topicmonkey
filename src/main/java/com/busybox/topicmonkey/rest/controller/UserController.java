package com.busybox.topicmonkey.rest.controller;

import com.busybox.topicmonkey.domain.model.User;
import com.busybox.topicmonkey.rest.assembler.UserResourceAssembler;
import com.busybox.topicmonkey.rest.utils.RestApiUrls;
import com.busybox.topicmonkey.service.UserService;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@ExposesResourceFor(User.class)
@RequestMapping(value = RestApiUrls.USER_ROOT, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private UserResourceAssembler userResourceAssembler;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public HttpEntity<User> getUser(@PathVariable("id") long userId) {
        ResponseEntity response = new ResponseEntity(HttpStatus.NOT_FOUND);
        Optional<User> user = userService.findById(userId);
        if (user.isPresent()) {
            response = toResponse(user.get());
        }
        return response;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public HttpEntity<User> getAllUsers() {
        List<org.springframework.hateoas.Resource<User>> users = userService.findAll().stream()
                .map(user -> userResourceAssembler.toResource(user))
                .collect(Collectors.toList());

        return new ResponseEntity(users, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public HttpEntity<User> addUser(@RequestBody User user) {
        return toResponse(userService.create(User.builder()
                .withLoginName(user.getLoginName())
                .withEmailAddress(user.getEmailAddress())
                .build()));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public HttpEntity<User> removeUser(@PathVariable("id") long userId) {
        ResponseEntity response = new ResponseEntity(HttpStatus.NOT_FOUND);
        Optional<User> user = userService.delete(userId);
        if (user.isPresent()) {
            response = toResponse(user.get());
        }
        return response;
    }

    private ResponseEntity toResponse(User updatedUser) {
        return new ResponseEntity(userResourceAssembler.toResource(updatedUser), HttpStatus.OK);
    }
}

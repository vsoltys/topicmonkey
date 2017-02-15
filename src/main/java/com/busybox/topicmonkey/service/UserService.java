package com.busybox.topicmonkey.service;

import com.busybox.topicmonkey.domain.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService extends AbstractService<User, String> {

    Optional<User> findByLoginName(String loginName);
}

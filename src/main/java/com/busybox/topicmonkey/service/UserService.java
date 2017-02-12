package com.busybox.topicmonkey.service;

import com.busybox.topicmonkey.domain.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends AbstractService<User, Long> {

    User findByLoginName(String loginName);
}

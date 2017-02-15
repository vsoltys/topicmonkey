package com.busybox.topicmonkey.service.impl;

import com.busybox.topicmonkey.domain.model.User;
import com.busybox.topicmonkey.domain.repository.UserRepository;
import com.busybox.topicmonkey.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl extends TransactionalService<User, String>
        implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository repository;

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findByLoginName(String loginName) {
        Assert.hasLength(loginName);
        log.debug("Searching user by loginName: {}", loginName);
        return repository.findByLoginName(loginName);
    }

    @Override
    protected JpaRepository<User, String> getRepository() {
        return repository;
    }
}

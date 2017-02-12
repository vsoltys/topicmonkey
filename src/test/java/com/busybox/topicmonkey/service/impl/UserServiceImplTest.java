package com.busybox.topicmonkey.service.impl;

import com.busybox.topicmonkey.domain.model.User;
import com.busybox.topicmonkey.domain.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserServiceImpl service;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void shouldFindByUserId() throws Exception {
        // given
        User user = prepareUser();
        when(repository.findOne(anyLong())).thenReturn(user);

        // when
        Optional<User> resultUser = service.findById(anyLong());

        // then
        assertNotNull(resultUser);
        assertTrue(resultUser.isPresent());
        assertEquals(user.getLoginName(), resultUser.get().getLoginName());
        assertEquals(user.getEmailAddress(), resultUser.get().getEmailAddress());
    }

    @Test
    public void shouldFindByLoginName() throws Exception {
/*
        // given
        User user = prepareUser();
        when(repository.findByLoginName(user.getLoginName())).thenReturn(user);

        // when
        User resultUser = service.findByLoginName(user.getLoginName());

        // then
        assertEquals(user.getLoginName(), resultUser.getLoginName());
        assertEquals(user.getEmailAddress(), resultUser.getEmailAddress());
*/
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailFindByEmptyLoginName() throws Exception {
        // when
        User resultUser = service.findByLoginName(null);

        // then
        verifyZeroInteractions(repository);
    }

    private User prepareUser() {
        return User.builder()
                .withLoginName("test")
                .withEmailAddress("email@test.com")
                .build();
    }
}
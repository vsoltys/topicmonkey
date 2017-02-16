package com.busybox.topicmonkey.service.impl;

import com.busybox.topicmonkey.domain.model.SearchEntry;
import com.busybox.topicmonkey.domain.model.User;
import com.busybox.topicmonkey.domain.repository.SearchEntryRepository;
import com.busybox.topicmonkey.domain.repository.UserRepository;
import com.busybox.topicmonkey.service.SearchEntryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SearchEntryServiceImpl extends TransactionalService<SearchEntry, String>
        implements SearchEntryService {

    private static final Logger log = LoggerFactory.getLogger(SearchEntryServiceImpl.class);

    @Autowired
    private SearchEntryRepository searchEntryRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<SearchEntry> findByName(String name) {
        Assert.hasLength(name);
        log.debug("Looking for searchEntry by name: {}", name);
        return searchEntryRepository.findByName(name);
    }

    @Override
    public List<SearchEntry> findAllByUserId(String userId) {
        // TODO: implement repository lookup
        return searchEntryRepository.findAllByUserId(userId);
    }

    @Override
    public Optional<SearchEntry> addSearchEntryForUser(String userId, SearchEntry searchEntry) {
        User user = userRepository.findOne(userId);
        if (user != null) {
            return Optional.of(searchEntryRepository.save(SearchEntry.builder()
                    .withName(searchEntry.getName())
                    .withContent(searchEntry.getContent())
                    .withUser(user)
                    .build()));
        }
        // TODO: use SystemException if user not found?
        return Optional.empty();
    }

    @Override
    protected JpaRepository<SearchEntry, String> getRepository() {
        return searchEntryRepository;
    }
}

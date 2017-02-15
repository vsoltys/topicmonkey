package com.busybox.topicmonkey.service.impl;

import com.busybox.topicmonkey.domain.model.SearchEntry;
import com.busybox.topicmonkey.domain.repository.SearchEntryRepository;
import com.busybox.topicmonkey.service.SearchEntryService;
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
public class SearchEntryServiceImpl extends TransactionalService<SearchEntry, String>
        implements SearchEntryService {

    private static final Logger log = LoggerFactory.getLogger(SearchEntryServiceImpl.class);

    @Autowired
    private SearchEntryRepository repository;

    @Override
    public Optional<SearchEntry> findByName(String name) {
        Assert.hasLength(name);
        log.debug("Looking for searchEntry by name: {}", name);
        return repository.findByName(name);
    }

    @Override
    protected JpaRepository<SearchEntry, String> getRepository() {
        return repository;
    }
}

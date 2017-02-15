package com.busybox.topicmonkey.service;

import com.busybox.topicmonkey.domain.model.SearchEntry;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface SearchEntryService extends AbstractService<SearchEntry, String> {

    Optional<SearchEntry> findByName(String name);
}

package com.busybox.topicmonkey.service.impl;

import com.busybox.topicmonkey.domain.model.SearchEntry;
import com.busybox.topicmonkey.domain.repository.SearchEntryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class SearchEntryServiceImplTest {

    @Mock
    private SearchEntryRepository repository;

    @InjectMocks
    private SearchEntryServiceImpl service;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void shouldFindBySearchEntryId() throws Exception {
        // given
        SearchEntry searchEntry = prepareSearchEntry();
        when(repository.findOne(anyString())).thenReturn(searchEntry);

        // when
        Optional<SearchEntry> resultEntry = service.findById(anyString());

        // then
        assertNotNull(resultEntry);
        assertTrue(resultEntry.isPresent());
        assertEquals(searchEntry.getName(), resultEntry.get().getName());
        assertEquals(searchEntry.getContent(), resultEntry.get().getContent());
    }

    private SearchEntry prepareSearchEntry() {
        return SearchEntry.builder().withName("test_name").withContent("test_content").build();
    }
}
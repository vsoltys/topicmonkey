package com.busybox.topicmonkey.rest.controller;

import static com.busybox.topicmonkey.rest.utils.RestApiUrls.SEARCH_ENTRIES_ROOT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.busybox.topicmonkey.Application;
import com.busybox.topicmonkey.domain.model.SearchEntry;

@RunWith(SpringRunner.class)
@SpringBootTest(classes =
        { Application.class })
@AutoConfigureMockMvc
@Ignore
public class SearchEntryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldCreateNewSearchEntry() throws Exception {
        SearchEntry entry = SearchEntry.builder()
                .withName("test_name")
                .withContent("test_content")
                .build();

        // TODO: use Jackson to marshal into json content

        mockMvc.perform(post(SEARCH_ENTRIES_ROOT)
                .content("{'name':'test_name', 'content':'test_content'}"))
                .andExpect(status().isOk());
    }

}

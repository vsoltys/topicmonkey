package com.busybox.topicmonkey.rest.controller;

import com.busybox.topicmonkey.Application;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@AutoConfigureMockMvc
public class SearchEntryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnDefaultTopic() throws Exception {
        mockMvc.perform(get("/topic"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnJsonContentType() throws Exception {
        mockMvc.perform(get("/topic").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"));
    }

    @Test
    @Ignore("enable after database schema update")
    public void shouldReturnCustomTopic() throws Exception {
        mockMvc.perform(get("/topic?name=test"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("test")))
                .andExpect(jsonPath("$.content", is("default content")))
                .andExpect(jsonPath("$._links.self.href", is("http://localhost/topic?name=test")));
    }
}
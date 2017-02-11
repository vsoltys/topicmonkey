package com.busybox.topicmonkey.rest.controller;

import com.busybox.topicmonkey.application.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebAppConfiguration
@ContextConfiguration(classes = {Application.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class TopicControllerTest {

    @Autowired
    private WebApplicationContext applicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
    }

    @Test
    public void testGetTopic() throws Exception {
        mockMvc.perform(get("/topic"))
                .andExpect(status().isOk());
    }

    @Test
    public void testContentType() throws Exception {
        mockMvc.perform(get("/topic"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/hal+json;charset=UTF-8"));
    }

    @Test
    public void testGetCustomTopic() throws Exception {
        mockMvc.perform(get("/topic?name=test"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("test")))
                .andExpect(jsonPath("$.content", is("default content")))
                .andExpect(jsonPath("$._links.self.href", is("http://localhost/topic?name=test")));
    }


}
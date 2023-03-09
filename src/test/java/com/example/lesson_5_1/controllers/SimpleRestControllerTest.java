package com.example.lesson_5_1.controllers;

import com.example.lesson_5_1.domain.SimpleClass;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(SimpleRestController.class)
class SimpleRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getStringRest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/getStringRest"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("!"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void getSimpleClassRest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/getSimpleClassRest"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        SimpleClass simpleClass = objectMapper.readValue(result.getResponse().getContentAsString(), SimpleClass.class);
        assertEquals(simpleClass, new SimpleClass("Str"));
    }

    @Test
    void postSimpleClassRest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/postSimpleClassRest")
                        .content(objectMapper.writeValueAsString(new SimpleClass("Str")))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        SimpleClass simpleClass = objectMapper.readValue(result.getResponse().getContentAsString(), SimpleClass.class);
        assertEquals(simpleClass, new SimpleClass("Str"));
    }

    @Test
    void redirectWithUsingRedirectViewSimpleClassRest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/redirectWithUsingRedirectViewSimpleClassRest"))
                .andExpect(MockMvcResultMatchers.status().is(302))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/getSimpleClassRest?attribute=redirectWithRedirectView"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void redirectWithUsingRedirectPrefix() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/redirectWithRedirectPrefixSimpleClassRest"))
                .andExpect(MockMvcResultMatchers.status().is(302))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/getSimpleClassRest?attribute=redirectWithRedirectPrefix"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void redirectWithUsingForwardPrefix() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/forwardWithForwardPrefixSimpleClassRest"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.forwardedUrl("/getSimpleClassRest"))
                .andDo(MockMvcResultHandlers.print());
    }
}
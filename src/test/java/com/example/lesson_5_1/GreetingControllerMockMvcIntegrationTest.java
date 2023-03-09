package com.example.lesson_5_1;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.swing.text.html.HTML;

@SpringBootTest
@AutoConfigureMockMvc
public class GreetingControllerMockMvcIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void defaultTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/greeting"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void differentialTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/greeting")
                        .param("name", "A")
                )
                .andExpect(MockMvcResultMatchers.model().attribute("name", "A"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

}

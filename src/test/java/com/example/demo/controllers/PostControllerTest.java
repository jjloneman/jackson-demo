package com.example.demo.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenRetrievingPostsWithListMapping_thenReturnsValidJson() throws Exception {
        String expectedJson = "[{ 'id': 1, 'title': 'Post 1', 'tagsWithList': [{ 'id':1, 'name':'foo' },{ 'id':2, 'name': 'bar' }] }]";

        this.mockMvc.perform(get("/api/posts-with-list"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }

    @Test
    public void whenRetrievingPostsWithSetMapping_thenThrowsException() throws Exception {
        String expectedExceptionMessage = "Could not write JSON: Infinite recursion (StackOverflowError); " +
                "nested exception is com.fasterxml.jackson.databind.JsonMappingException: " +
                "Infinite recursion (StackOverflowError) (through reference chain: " +
                "java.util.ArrayList[0]->com.example.demo.models.PostWithSet[\"tagsWithSet\"])";

        this.mockMvc.perform(get("/api/posts-with-set"))
                .andExpect(mvcResult ->
                        assertEquals(expectedExceptionMessage, mvcResult.getResolvedException().getMessage())
                );
    }
}
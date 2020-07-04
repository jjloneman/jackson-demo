package com.example.demo;

import com.example.demo.models.PostWithList;
import com.example.demo.models.PostWithSet;
import com.example.demo.models.TagWithList;
import com.example.demo.models.TagWithSet;
import com.example.demo.util.H2Runner;
import com.example.demo.util.HibernateUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JacksonHibernateTest {

    H2Runner h2Runner;
    Session session;

    @BeforeEach
    void setUp() {
        h2Runner = new H2Runner();
        h2Runner.start();

        session = HibernateUtil.getSessionFactory().openSession();
    }

    @AfterEach
    void tearDown() {
        session.close();
        h2Runner.interrupt();
    }

    @Test
    void givenIgnoredBidirectionalListMapping_thenPostShouldSerializeToValidJson() throws JsonProcessingException {
        // Given post
        PostWithList postToSave = new PostWithList("Post with list");
        postToSave.addTag(new TagWithList("foo"));
        postToSave.addTag(new TagWithList("bar"));

        // When saving and querying the post
        session.save(postToSave);
        PostWithList post = session.createQuery("from PostWithList", PostWithList.class).getSingleResult();

        // And serializing the result
        String actualJson = new ObjectMapper().writeValueAsString(post);

        // Then should serialize and ignore backreference
        String expectedJson = "{\"id\":1,\"title\":\"Post with list\",\"tagsWithList\":[{\"id\":1,\"name\":\"foo\"},{\"id\":2,\"name\":\"bar\"}]}";

        assertEquals(expectedJson, actualJson);
    }

    @Test
    void givenIgnoredBidirectionalSetMapping_thenPostShouldSerializeToValidJson() throws JsonProcessingException {
        // Given post
        PostWithSet postToSave = new PostWithSet("Post with set");
        postToSave.addTag(new TagWithSet("foo"));
        postToSave.addTag(new TagWithSet("bar"));

        // When saving and querying the post
        session.save(postToSave);
        PostWithSet post = session.createQuery("from PostWithSet", PostWithSet.class).getSingleResult();

        // And serializing the result
        String actualJson = new ObjectMapper().writeValueAsString(post);

        // Then should serialize and ignore backreference
        String expectedJson = "{\"id\":1,\"title\":\"Post with set\",\"tagsWithSet\":[{\"id\":1,\"name\":\"foo\"},{\"id\":2,\"name\":\"bar\"}]}";

        assertEquals(expectedJson, actualJson);
    }

}
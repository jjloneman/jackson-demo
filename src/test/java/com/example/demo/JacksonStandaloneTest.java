package com.example.demo;

import com.example.demo.models.Post;
import com.example.demo.models.Tag;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JacksonStandaloneTest {

    @Test
    void givenIgnoredRecursiveMappings_thenPostShouldSerializeToValidJson() throws JsonProcessingException {
        // Given post
        Post post = new Post(1, "Post 1");
        List<Post> postList = List.of(post);
        Set<Post> postSet = Set.of(post);

        // And tags that reference the post
        Tag fooTag = new Tag(1, "foo");
        fooTag.setPostList(postList);
        fooTag.setPostSet(postSet);

        Tag barTag = new Tag(2, "bar");
        barTag.setPostList(postList);
        barTag.setPostSet(postSet);

        // And setting the post to reference the tags
        post.setTagsList(List.of(fooTag, barTag));
        post.setTagsSet(new LinkedHashSet<>(Set.of(fooTag, barTag)));

        // When serializing the post
        String actualJson = new ObjectMapper().writeValueAsString(post);

        // Then the post's tags list and set should be equal
        String expectedJson = "{\"id\":1,\"title\":\"Post 1\",\"tagsList\":[{\"id\":1,\"name\":\"foo\"},{\"id\":2,\"name\":\"bar\"}],\"tagsSet\":[{\"id\":1,\"name\":\"foo\"},{\"id\":2,\"name\":\"bar\"}]}";

        assertEquals(expectedJson, actualJson);
    }

}
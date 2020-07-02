package com.example.demo;

import com.example.demo.models.Post;
import com.example.demo.models.Tag;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Set;

public class DemoApplication {

    public static void main(String[] args) throws JsonProcessingException {
        Post post = new Post(1, "Post 1");

        List<Post> postList = List.of(post);
        Set<Post> postSet = Set.of(post);

        Tag fooTag = new Tag(1, "foo");
        fooTag.setPostList(postList);
        fooTag.setPostSet(postSet);

        Tag barTag = new Tag(2, "bar");
        barTag.setPostList(postList);
        barTag.setPostSet(postSet);

        List<Tag> tagList = List.of(fooTag, barTag);
        Set<Tag> tagSet = Set.of(fooTag, barTag);

        post.setTagsList(tagList);
        post.setTagsSet(tagSet);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonList = objectMapper.writeValueAsString(postList);
        String jsonSet = objectMapper.writeValueAsString(postSet);

        System.out.println(jsonList);
        System.out.println(jsonSet);
    }

}

package com.example.demo.controllers;

import com.example.demo.models.PostWithList;
import com.example.demo.models.PostWithSet;
import com.example.demo.repositories.PostWithListRepository;
import com.example.demo.repositories.PostWithSetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

    private final PostWithListRepository postWithListRepository;
    private final PostWithSetRepository postWithSetRepository;

    @GetMapping("/posts-with-list")
    public Iterable<PostWithList> getPostsWithList() {
        return postWithListRepository.findAll();
    }

    @GetMapping("/posts-with-set")
    public Iterable<PostWithSet> getPostsWithSet() {
        return postWithSetRepository.findAll();
    }
}

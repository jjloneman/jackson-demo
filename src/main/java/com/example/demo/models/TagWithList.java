package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tag")
public class TagWithList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToMany(mappedBy = "tagsWithList")
    @JsonIgnore
    private List<PostWithList> postsWithList = new ArrayList<>();

    public TagWithList() {
    }

    public TagWithList(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PostWithList> getPostsWithList() {
        return postsWithList;
    }

    public void setPostsWithList(List<PostWithList> postsWithList) {
        this.postsWithList = postsWithList;
    }

    @Override
    public boolean equals(Object o) {
        return this == o || getClass().isInstance(o) && Objects.equals(id, getClass().cast(o).getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

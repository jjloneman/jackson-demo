package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tag")
public class TagWithSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToMany(mappedBy = "tagsWithSet")
    @JsonIgnore
    private Set<PostWithSet> postWithSets = new LinkedHashSet<>();

    public TagWithSet() {
    }

    public TagWithSet(String name) {
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

    public Set<PostWithSet> getPostWithSets() {
        return postWithSets;
    }

    public void setPostWithSets(Set<PostWithSet> postWithSets) {
        this.postWithSets = postWithSets;
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

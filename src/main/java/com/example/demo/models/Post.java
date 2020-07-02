package com.example.demo.models;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class Post implements Serializable {

    private int id;
    private String title;
    private List<Tag> tagsList;
    private Set<Tag> tagsSet;

    public Post() {
    }

    public Post(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Tag> getTagsList() {
        return tagsList;
    }

    public void setTagsList(List<Tag> tagsList) {
        this.tagsList = tagsList;
    }

    public Set<Tag> getTagsSet() {
        return tagsSet;
    }

    public void setTagsSet(Set<Tag> tagsSet) {
        this.tagsSet = tagsSet;
    }
}

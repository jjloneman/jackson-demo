package com.example.demo.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "post")
public class PostWithList implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "post_tag",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<TagWithList> tagsWithList = new ArrayList<>();

    public PostWithList() {
    }

    public PostWithList(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<TagWithList> getTagsWithList() {
        return tagsWithList;
    }

    public void setTagsWithList(List<TagWithList> tagsWithList) {
        this.tagsWithList = tagsWithList;
    }

    public void addTag(TagWithList tag) {
        tagsWithList.add(tag);
        tag.getPostsWithList().add(this);
    }

    public void removeTag(TagWithList tag) {
        tagsWithList.remove(tag);
        tag.getPostsWithList().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        return this == o || getClass().isInstance(o) && Objects.equals(id, getClass().cast(o).getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}

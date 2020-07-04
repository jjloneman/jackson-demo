package com.example.demo.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "post")
public class PostWithSet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "post_tag",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<TagWithSet> tagsWithSet = new LinkedHashSet<>();

    public PostWithSet() {
    }

    public PostWithSet(String title) {
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

    public Set<TagWithSet> getTagsWithSet() {
        return tagsWithSet;
    }

    public void setTagsWithSet(Set<TagWithSet> tagsWithSet) {
        this.tagsWithSet = tagsWithSet;
    }

    public void addTag(TagWithSet tag) {
        tagsWithSet.add(tag);
        tag.getPostWithSets().add(this);
    }

    public void removeTag(TagWithSet tag) {
        tagsWithSet.remove(tag);
        tag.getPostWithSets().remove(this);
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

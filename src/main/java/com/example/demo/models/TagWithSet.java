package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tag")
@Data
public class TagWithSet {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "tagsWithSet")
    @JsonIgnore
    private Set<PostWithSet> postWithSets;
}

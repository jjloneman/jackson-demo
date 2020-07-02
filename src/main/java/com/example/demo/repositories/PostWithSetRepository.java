package com.example.demo.repositories;

import com.example.demo.models.PostWithSet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostWithSetRepository extends CrudRepository<PostWithSet, Long> {
}

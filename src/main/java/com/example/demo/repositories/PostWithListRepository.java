package com.example.demo.repositories;

import com.example.demo.models.PostWithList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostWithListRepository extends CrudRepository<PostWithList, Long> {
}

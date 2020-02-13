package com.quest.etna.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.quest.etna.model.Post;

@Repository
public interface PostRepository extends CrudRepository<Post, Integer> {

	@Query("SELECT p FROM Post p WHERE p.user.id =:id")
	public List<Post> findByUserId(int id);
}

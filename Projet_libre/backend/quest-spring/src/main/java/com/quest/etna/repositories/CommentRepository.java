package com.quest.etna.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.quest.etna.model.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Integer> {
	
	
	@Query("SELECT c FROM Comment c WHERE c.user.id =:id")
	public List<Comment> findByUserId(int id);
}

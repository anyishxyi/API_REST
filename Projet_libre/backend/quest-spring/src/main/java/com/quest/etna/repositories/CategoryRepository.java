package com.quest.etna.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.quest.etna.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Integer>{

	@Query("SELECT c FROM Category c WHERE c.name =:name")
	public Category findByName(String name);
}

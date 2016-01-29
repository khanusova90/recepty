package cz.ppro.recepty.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cz.ppro.recepty.domain.Photo;
import cz.ppro.recepty.domain.Recipe;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {

	public List<Photo> findByRecipe(Recipe recipe);

}

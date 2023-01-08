package com.skillbox.users.repository;

import com.skillbox.users.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface NewsRepository extends JpaRepository<News, UUID> {
       
	List<News> findByUserCreatorId(UUID userCreator);
	
 	Optional<News> findBySubject(String subject);

}
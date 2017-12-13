package com.poligran.edu.Entrevista_Sergio.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.poligran.edu.Entrevista_Sergio.Entity.UserEntity;

@Transactional
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	// @Query("select u from UserEntity u where u.name = :name")
	// List<UserEntity> findAllByIdForum(@Param("name") String name);

}

package com.url.repository;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.url.entity.Url;

@Repository
@Scope("prototype")
public interface UrlHandleRepo extends JpaRepository<Url, Integer> {
	@Query(value = "select originalurl from Url u where shorturl = ?1", nativeQuery = true)
	String findByShortUrl(String id);
}

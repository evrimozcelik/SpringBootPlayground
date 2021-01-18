package org.evrim.spring.exam.mvc.springboot.dao;

import org.evrim.spring.exam.mvc.springboot.ds.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleDao extends CrudRepository<Article, Integer> {
}

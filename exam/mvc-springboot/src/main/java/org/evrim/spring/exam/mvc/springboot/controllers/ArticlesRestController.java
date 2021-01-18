package org.evrim.spring.exam.mvc.springboot.controllers;

import org.evrim.spring.exam.mvc.springboot.dao.ArticleDao;
import org.evrim.spring.exam.mvc.springboot.ds.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping(value = "api", headers = "my-header")
public class ArticlesRestController {

    @Autowired
    ArticleDao articleDao;

    @GetMapping("articles")
    public Iterable<Article> index() {
        return articleDao.findAll();
    }

    /* curl -I -H "my-header:a" localhost:8080/api/articles2 */
    @GetMapping("articles2")
    public ResponseEntity<Iterable<Article>> index2() {
        Iterable<Article> articles = articleDao.findAll();
        long count = StreamSupport.stream(articles.spliterator(),false).count();
        return  ResponseEntity.ok()
                .header("item-count", String.valueOf(count))
                .body(articles);
    }

    @GetMapping("/articles/{id}")
    public ResponseEntity<Article> getArticle(@PathVariable("id") int id) {
        Optional<Article> article = articleDao.findById(id);
        if(article.isPresent()) {
            return ResponseEntity.ok()
                    .body(article.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/articles/ids/{ids}")
    public ResponseEntity<Iterable<Article>> getArticle(@PathVariable(value = "ids") List<Integer> ids) {
        Iterable<Article> articles = articleDao.findAllById(ids);
        long count = StreamSupport.stream(articles.spliterator(),false).count();

        if(count > 0) {
            return ResponseEntity.ok()
                    .body(articles);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

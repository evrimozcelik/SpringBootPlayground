package org.evrim.spring.exam.mvc.springboot.controllers;

import org.evrim.spring.exam.mvc.springboot.dao.ArticleDao;
import org.evrim.spring.exam.mvc.springboot.data.ArticlePart1;
import org.evrim.spring.exam.mvc.springboot.data.ArticlePart2;
import org.evrim.spring.exam.mvc.springboot.ds.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Optional;

@Controller
@SessionAttributes({"articlePart1","articlePart2"})
public class ArticlesController {

    @Autowired
    ArticleDao articleDao;

    @GetMapping("articles")
    public String index(Model model) {
        model.addAttribute("articles", articleDao.findAll());
        return "articles-index";
    }

    @PostMapping("delete-article")
    public String deleteArticle(@RequestParam int id) {
        Optional<Article> article = articleDao.findById(id);
        if(article.isPresent()) {
            articleDao.delete(article.get());
        }
        return "redirect:/articles";
    }

    @GetMapping("articles/new")
    public String newArticle() {
        return "redirect:/articles/new/step1";
    }

    @GetMapping("articles/new/step1")
    public String newArticle1(@ModelAttribute("articlePart1") ArticlePart1 articlePart1) {
        return "articles-new-step1";
    }

    @PostMapping("articles/new/step1")
    public String newArticle1(@ModelAttribute("articlePart1") @Valid ArticlePart1 articlePart1, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "articles-new-step1";
        } else {
            return "redirect:/articles/new/step2";
        }
    }

    @GetMapping("articles/new/step2")
    public String newArticle1(@ModelAttribute("articlePart2") ArticlePart2 articlePart2) {
        return "articles-new-step2";
    }

    @PostMapping("articles/new/step2")
    public String newArticle2(@ModelAttribute("articlePart2") @Valid ArticlePart2 articlePart2, BindingResult bindingResult, @ModelAttribute("articlePart1") ArticlePart1 articlePart1, SessionStatus sessionStatus) {
        if(bindingResult.hasErrors()) {
            return "articles-new-step2";
        } else {
            Article article = new Article(0, articlePart1.getTitle(),articlePart2.getBody());
            articleDao.save(article);
            sessionStatus.setComplete();
            return "redirect:/articles";
        }
    }

    // Called before executing any Controller method and if session attribute is not present
    @ModelAttribute("articlePart1")
    public ArticlePart1 articlePart1() {
        return new ArticlePart1("");
    }

    // Called before executing any Controller method and if session attribute is not present
    @ModelAttribute("articlePart2")
    public ArticlePart2 articlePart2() {
        return new ArticlePart2("");
    }
}

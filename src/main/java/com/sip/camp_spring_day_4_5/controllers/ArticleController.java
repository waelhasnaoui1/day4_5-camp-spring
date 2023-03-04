package com.sip.camp_spring_day_4_5.controllers;

import com.sip.camp_spring_day_4_5.entities.Article;
import com.sip.camp_spring_day_4_5.entities.Provider;
import com.sip.camp_spring_day_4_5.repositories.ArticleRepository;
import com.sip.camp_spring_day_4_5.repositories.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/article")
public class ArticleController {

    private final ArticleRepository articleRepository;

    private final ProviderRepository providerRepository;

    @Autowired
    public ArticleController(ArticleRepository articleRepository, ProviderRepository providerRepository){
        this.articleRepository = articleRepository;
        this.providerRepository = providerRepository;
    }

    @GetMapping("list")
    public String listArticle(Model model){
        model.addAttribute("articles",articleRepository.findAll());
        return "article/listArticles";
    }

    @GetMapping("add")
    public String showAddArticleForm( Model model){
        model.addAttribute("article",new Article());
        model.addAttribute("providers",providerRepository.findAll());
        return "article/addArticle";
    }

    @PostMapping("add")
    public String addArticle(
            @Valid Article article,
            BindingResult result,
            Model model,
            @RequestParam(name="providerId",required = false) Long id){
        if(result.hasErrors()){
            return "article/add";
        }
        Provider provider = providerRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("provider with this id doesn't exist:" + id)
        );
        article.setProvider(provider);
        articleRepository.save(article);
        return "article/list";
    }

    @GetMapping("delete/{id}")
    public String deleteArticle(@PathVariable("id") Long id, Model model){
        Article article = articleRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("Invalid id"+id)
        );
        articleRepository.delete(article);
        return "redirect:../list";
    }

    @GetMapping("edit/{id}")
    public String showArticleFormToUpdate(@PathVariable("id") Long id,Model model){
        Article article = articleRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("Invalid provider Id:" + id)
        );

        model.addAttribute("article",article);
        model.addAttribute("providers",providerRepository.findAll());

        return "article/updateArticle";
    }

    @PostMapping("edit/{id}")
    public String updateArticle(
            @Valid Article article,
            @RequestParam(name="providerId",required = false) Long providerId,
            @PathVariable("id") Long articleId,BindingResult result){
        if(result.hasErrors()){
            return "article/updateArticles";
        }
        Provider provider = providerRepository.findById(providerId).orElseThrow(
                () -> new IllegalArgumentException("Invalid provider Id:" +providerId));

        article.setProvider(provider);
        articleRepository.save(article);

        return "article/listArticles";
    }




}

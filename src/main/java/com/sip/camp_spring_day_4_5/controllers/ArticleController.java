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
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/article")
public class ArticleController {

    public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/uploads";
    static private  List<Article> articles;
    static private int index = 0;

    static private boolean isDeleting = false;
    private String messageToListView = "";
    private final ArticleRepository articleRepository;

    private final ProviderRepository providerRepository;

    @Autowired
    public ArticleController(ArticleRepository articleRepository, ProviderRepository providerRepository){
        this.articleRepository = articleRepository;
        this.providerRepository = providerRepository;
        this.articles = articleRepository.findAll();
    }

    @GetMapping("list")
    public String listArticle( Model model){
        List<Article> articles = articleRepository.findAll();
        if(articles.size() == 0 ){
            articles = null;
        }
        model.addAttribute("articles",articles);
        model.addAttribute("deleteMessage",isDeleting ? messageToListView : "");
        model.addAttribute("index",index);
        isDeleting=false;
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
            @RequestParam(name="providerId",required = false) Long id,
            @RequestParam("files")MultipartFile[] files
            ){

        if(result.hasErrors()){
            return "article/addArticle";
        }
        Provider provider = providerRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("provider with this id doesn't exist:" + id)
        );
        article.setProvider(provider);

        ///upload image to server
        MultipartFile file = files[0];
        Path fileNameAndPath = Paths.get(uploadDirectory,file.getOriginalFilename());
        try{
            Files.write(fileNameAndPath,file.getBytes());
        }catch(IOException ex){
            ex.printStackTrace();
        }

        ///save image name to database

        StringBuilder fileName = new StringBuilder();
        fileName.append(file.getOriginalFilename());
        article.setImage(fileName.toString());

        articleRepository.save(article);
        return "redirect:list";
    }

    @GetMapping("delete/{id}")
    public String deleteArticle(@PathVariable("id") Long id, Model model){
        this.articles = articleRepository.findAll();
        Optional<Article> article = articleRepository.findById(id);
        isDeleting = true;

        if(article.isPresent()){
            articleRepository.delete(article.get());
            messageToListView= "message Deleted";
            index=-1;
            return "redirect:../list";
        }
        else if (index == -1) {
            messageToListView = "item already deleted";
            index=0;
        }
        else{
            messageToListView="item doesn't exist in the database";
        }

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

        return "redirect:../list";
    }

}

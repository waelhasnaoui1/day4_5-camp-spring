package com.sip.camp_spring_day_4_5.controllers;

import com.sip.camp_spring_day_4_5.entities.Article;
import com.sip.camp_spring_day_4_5.entities.Provider;
import com.sip.camp_spring_day_4_5.repositories.ProviderRepository;
import com.sip.camp_spring_day_4_5.services.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/provider")
public class ProviderController {

    private final ProviderRepository providerRepository;

    private final ProviderService providerService;
    @Autowired
    public ProviderController(ProviderRepository providerRepository,ProviderService providerService){
        this.providerRepository = providerRepository;
        this.providerService = providerService;
    }

    @GetMapping("/list")
    public String listProviders(Model model){
        List<Provider> providers = providerService.getAllProviders();

        if(providers.size() == 0){
            providers = null;
        }
        model.addAttribute("providers", providers);
        return "provider/listProvider";
    }

    @GetMapping("/add")
    public String showAddProviderForm (Model model){

        model.addAttribute("provider",new Provider());
        return "provider/addProvider";
    }

    @PostMapping("/add")
    public String addProvider(@Valid Provider provider, BindingResult result, Model model){
        System.out.println(provider);
        if(result.hasErrors()){
            return "provider/addProvider";
        }
        providerRepository.save(provider);
        return "redirect:list";
    }

    @GetMapping("/delete/{id}")
    public String deleteProvider(@PathVariable("id") long id){
        Provider provider = providerRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Invalid Provider Id : " + id)
        );
        providerRepository.delete(provider);
        return "redirect:../list";
    }

    @GetMapping("/edit/{id}")
    public String showProvideFormToUpdate(@PathVariable("id") long id, Model model){
        Provider provider = providerRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Invalid Provider Id : " + id)
        );
        model.addAttribute("provider",provider);
        providerRepository.save(provider);
        return "provider/updateProvider";
    }

    @GetMapping("show/{id}")
    public String showProvider(@PathVariable("id") long id, Model model) {
        Provider provider = providerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid provider Id:" + id));
        List<Article> articles = providerRepository.findArticlesByProvider(id);
        for (Article a : articles)
            System.out.println("Article = " + a.getLabel());

        model.addAttribute("articles", articles);
        model.addAttribute("provider", provider);
        return "provider/showProvider";
    }


}

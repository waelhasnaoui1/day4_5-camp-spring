package com.sip.camp_spring_day_4_5.controllers;

import com.sip.camp_spring_day_4_5.entities.Provider;
import com.sip.camp_spring_day_4_5.repositories.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/provider")
public class ProviderController {

    final ProviderRepository providerRepository;

    @Autowired
    public ProviderController(ProviderRepository providerRepository){
        this.providerRepository = providerRepository;
    }

    @GetMapping("/")
    public String listProviders(Model model){
        model.addAttribute("providers", providerRepository.findAll());
        return "provider/listProvider";
    }

    @GetMapping("/add")
    public String showAddProviderForm (Model model){

        model.addAttribute("provider",new Provider());
        return "provider/addProvider";
    }

    

}

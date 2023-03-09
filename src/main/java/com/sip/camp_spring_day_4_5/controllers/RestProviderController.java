package com.sip.camp_spring_day_4_5.controllers;

import com.sip.camp_spring_day_4_5.entities.Provider;
import com.sip.camp_spring_day_4_5.repositories.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/providers")
@CrossOrigin(origins = "*")
public class RestProviderController {

    @Autowired
    private ProviderRepository providerRepository;

    @GetMapping("/list")
    public List<Provider> getAllProviders(){
        System.out.println(providerRepository.findAll());
        return (List<Provider>) providerRepository.findAll();
    }

    @PostMapping("/add")
    public Provider addProvider(@Valid @RequestBody Provider provider){
        return providerRepository.save(provider);
    }

    @PutMapping("/{providerId}")
    public Provider updateProvider(@PathVariable("providerId") Long providerId, @Valid @RequestBody Provider providerRequest){
        return providerRepository.findById(providerId).map(provider -> {
           provider.setName(providerRequest.getName());
           provider.setAddress(providerRequest.getAddress());
           provider.setEmail(providerRequest.getEmail());
           return providerRepository.save(provider);
        }).orElseThrow(()-> new IllegalArgumentException("ProviderId"+ providerId + "not found"));
    }

    @GetMapping("/{providerId}")
    public Provider getProvider(@PathVariable("providerId") Long providerId){
        Optional<Provider> provider = providerRepository.findById(providerId);
        return provider.get();
    }

    @DeleteMapping("{/providerId}")
    public ResponseEntity<?> deleteProvider(@PathVariable("providerId") Long providerId){
        return providerRepository.findById(providerId).map((provider)->{
            providerRepository.delete(provider);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new IllegalArgumentException("ProviderId"+ providerId + "not found"));
    }
}

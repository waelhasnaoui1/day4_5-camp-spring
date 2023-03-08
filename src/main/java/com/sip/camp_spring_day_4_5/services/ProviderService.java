package com.sip.camp_spring_day_4_5.services;

import com.sip.camp_spring_day_4_5.entities.Provider;
import com.sip.camp_spring_day_4_5.repositories.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.websocket.server.ServerEndpoint;
import java.util.List;

@Service
public class ProviderService {

    final private ProviderRepository providerRepository;

    @Autowired
    public ProviderService (ProviderRepository providerRepository){
        this.providerRepository = providerRepository;
    }

    public List<Provider> getAllProviders (){
        return (List<Provider>) providerRepository.findAll();
    }
}

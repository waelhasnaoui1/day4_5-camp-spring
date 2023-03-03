package com.sip.camp_spring_day_4_5.repositories;

import com.sip.camp_spring_day_4_5.entities.Provider;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends CrudRepository<Provider,Long > {
}

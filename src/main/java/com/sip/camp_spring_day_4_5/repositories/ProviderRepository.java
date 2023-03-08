package com.sip.camp_spring_day_4_5.repositories;

import com.sip.camp_spring_day_4_5.entities.Article;
import com.sip.camp_spring_day_4_5.entities.Provider;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProviderRepository extends CrudRepository<Provider,Long> {
    @Query("FROM Article a WHERE a.provider.id = ?1")
    List<Article> findArticlesByProvider(long id);

}

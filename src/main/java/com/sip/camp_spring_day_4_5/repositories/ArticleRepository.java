package com.sip.camp_spring_day_4_5.repositories;

import com.sip.camp_spring_day_4_5.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article,Long> {
}

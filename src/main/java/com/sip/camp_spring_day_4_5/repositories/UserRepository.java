package com.sip.camp_spring_day_4_5.repositories;

import com.sip.camp_spring_day_4_5.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmail(String email);
}

package com.sip.camp_spring_day_4_5.repositories;

import com.sip.camp_spring_day_4_5.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findByRole(String role);
}

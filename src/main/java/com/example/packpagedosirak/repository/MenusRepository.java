package com.example.packpagedosirak.repository;

import com.example.packpagedosirak.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenusRepository extends JpaRepository<Menu, Long> {
    List<Menu> findBycategoryid(Long categoryid);
}

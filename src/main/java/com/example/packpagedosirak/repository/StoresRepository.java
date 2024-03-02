package com.example.packpagedosirak.repository;

import com.example.packpagedosirak.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StoresRepository extends JpaRepository<Store, Long> {

    List<Store> findByfoodcategory(String foodcategory);

    List<Store> findBystoresname(String storesname);

    List<Store> findBycategoryid(Long categoryid);
}

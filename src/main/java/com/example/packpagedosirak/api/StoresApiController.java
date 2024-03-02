package com.example.packpagedosirak.api;


import com.example.packpagedosirak.entity.Store;
import com.example.packpagedosirak.repository.StoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/store")
public class StoresApiController {
    private final StoresRepository storesRepository;

    @Autowired
    public StoresApiController(StoresRepository storesRepository) {
        this.storesRepository = storesRepository;
    }

    @GetMapping
    public Iterable<Store> list() {
        return storesRepository.findAll();
    }

    @PostMapping
    public Store storeInsert(@RequestBody Store store) {
        return storesRepository.save(store);
    }

    @GetMapping("/category={foodcategory}")
    public List<Store> getStoresByCategory(@PathVariable String foodcategory) {
        return storesRepository.findByfoodcategory(foodcategory);
    }

    @GetMapping("/search")
    public List<Store> searchStores(@RequestParam String storesname) {
        return storesRepository.findBystoresname(storesname);
    }

    @GetMapping("/{storesid}")
    public Optional<Store> getMenuById(@PathVariable Long storesid) {
        return storesRepository.findById(storesid);
    }

    @PutMapping("/{storesid}")
    public Store updateStore(@PathVariable Long storesid, @RequestBody Store updatedStore) {
        Optional<Store> optionalStore = storesRepository.findById(storesid);
        if (optionalStore.isPresent()) {
            Store store = optionalStore.get();
            store.setStoresname(updatedStore.getStoresname());
            store.setDistance(updatedStore.getDistance());
            store.setFoodcategory(updatedStore.getFoodcategory());
            store.setStoresaddress(updatedStore.getStoresaddress());
            store.setStoresphone(updatedStore.getStoresphone());
            store.setOperationhour(updatedStore.getOperationhour());
            store.setLikescount(updatedStore.getLikescount());
            return storesRepository.save(store);
        } else {
            throw new IllegalArgumentException("Store not found with ID: " + storesid);
        }
    }

    @DeleteMapping("/{storesid}")
    public void deleteStore(@PathVariable Long storesid) {
        storesRepository.deleteById(storesid);
    }

    @GetMapping("/category/{categoryid}")
    public List<Store> getMenuByCategory(@PathVariable Long categoryid) {
        List<Store> storeList = storesRepository.findBycategoryid(categoryid);
        return storeList;
    }






}

package com.example.packpagedosirak.api;


import com.example.packpagedosirak.entity.Menu;
import com.example.packpagedosirak.repository.MenusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/menu")
public class MenusApiController {
    private final MenusRepository menusRepository;

    @Autowired
    public MenusApiController(MenusRepository menusRepository) {
        this.menusRepository = menusRepository;
    }

    @GetMapping
    public Iterable<Menu> list() {
        return menusRepository.findAll();
    }

    @PostMapping("/{storesid}")
    public Menu menuInsert(@RequestBody Menu menu) {
        return menusRepository.save(menu);
    }


    @GetMapping("/{menusid}")
    public Optional<Menu> getMenuById(@PathVariable Long menusid) {
        return menusRepository.findById(menusid);
    }

    @PutMapping("/{menusid}")
    public Menu updateMenu(@PathVariable Long menusid, @RequestBody Menu updatedMenu) {
        Optional<Menu> optionalMenu = menusRepository.findById(menusid);
        if (optionalMenu.isPresent()) {
            Menu menu = optionalMenu.get();
            menu.setStoresid(updatedMenu.getStoresid());
            menu.setMenusname(updatedMenu.getMenusname());
            menu.setPrices(updatedMenu.getPrices());
            return menusRepository.save(menu);
        } else {
            throw new IllegalArgumentException("Menu not found with ID: " + menusid);
        }
    }

    @DeleteMapping
    public void deleteMenu(@PathVariable Long menusid) {
        menusRepository.deleteById(menusid);
    }

    @GetMapping("/category/{categoryid}")
    public List<Menu> getMenuByCategory(@PathVariable Long categoryid) {
        List<Menu> menuList = menusRepository.findBycategoryid(categoryid);
        return menuList;
    }





}

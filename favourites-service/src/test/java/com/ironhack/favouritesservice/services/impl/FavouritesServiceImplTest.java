package com.ironhack.favouritesservice.services.impl;

import com.ironhack.favouritesservice.dao.Favourite;
import com.ironhack.favouritesservice.dto.FavouriteDTO;
import com.ironhack.favouritesservice.repositories.FavouriteRepository;
import com.ironhack.favouritesservice.services.FavouritesService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FavouritesServiceImplTest {

    @Autowired
    private FavouriteRepository favouriteRepository;
    @Autowired
    private FavouritesService favouritesService;

    Favourite testFav1;
    Favourite testFav2;
    Favourite testFav3;
    Favourite testFav4;

    @BeforeEach
    void setUp() {
        testFav1 = new Favourite(1L, 1L);
        testFav2 = new Favourite(1L, 2L);
        testFav3 = new Favourite(2L, 3L);
        testFav4 = new Favourite(3L, 4L);
        favouriteRepository.saveAll(List.of(testFav1,testFav2,testFav3,testFav4));
    }

    @AfterEach
    void tearDown() {
        favouriteRepository.deleteAll();
    }

    @Test
    void findAll() {
        var repoSize = favouritesService.findAll().size();
        assertEquals(4, repoSize);
    }

    @Test
    void findById() {
        Favourite favourite = favouritesService.findById(1L);
        assertEquals(testFav1.getRecipeId(), favourite.getRecipeId());
        assertEquals(testFav1.getUserId(), favourite.getUserId());
    }

    @Test
    void findById_Throws() {
        assertThrows(ResponseStatusException.class, () -> favouritesService.findById(testFav1.getId() - 50L));
    }

    @Test
    void findByUserId() {
        var repoSize = favouritesService.findByUserId(testFav1.getId()).size();
        assertEquals(2, repoSize);
    }

    @Test
    void getAllRecipesByUserId() {
        var recipeListDTO = favouritesService.getAllRecipesByUserId(1L);
        assertEquals(2, recipeListDTO.getFavouriteRecipes().size());
        assertNotNull(recipeListDTO.getFavouriteRecipes().get(0).getName());
        assertNotNull(recipeListDTO.getFavouriteRecipes().get(0).getIngredients());
        assertNotNull(recipeListDTO.getFavouriteRecipes().get(0).getDiets());
        assertNotNull(recipeListDTO.getFavouriteRecipes().get(0).getAuthorId());
        assertNotNull(recipeListDTO.getFavouriteRecipes().get(0).getCookingTime());
        assertNotNull(recipeListDTO.getFavouriteRecipes().get(0).getPrepTime());
    }

    @Test
    void addToFavourites() {
        var repoSizeBefore = favouritesService.findAll().size();
        Favourite favourite = favouritesService.addToFavourites(new FavouriteDTO(1L, 1L));
        var repoSizeAfter = favouritesService.findAll().size();
        assertEquals(repoSizeBefore + 1, repoSizeAfter);
        assertNotNull(favourite.getUserId());
        assertNotNull(favourite.getRecipeId());
    }

    @Test
    void removeFromFavourites_Valid() {
        var repoSizeBefore = favouritesService.findAll().size();
        favouritesService.removeFromFavourites(testFav1.getId());
        var repoSizeAfter = favouritesService.findAll().size();
        assertEquals(repoSizeBefore - 1, repoSizeAfter);
        assertThrows(ResponseStatusException.class, () -> favouritesService.findById(testFav1.getId()));
    }
}
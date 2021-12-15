package com.ironhack.reviewservice.controller;

import com.ironhack.reviewservice.dao.Review;
import com.ironhack.reviewservice.dto.ReviewResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

public interface ReviewController {

    @GetMapping("/recipeid/{recipeId}")
    @ResponseStatus(HttpStatus.OK)
    List<ReviewResponse> getByRecipeId(@PathVariable(name = "recipeId") Long recipeId);

    @GetMapping("/userid/{userid}")
    @ResponseStatus(HttpStatus.OK)
    List<ReviewResponse> getByUserId(@PathVariable(name = "userId") Long userId);

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    Review getById(@PathVariable(name = "id") Long id);
}

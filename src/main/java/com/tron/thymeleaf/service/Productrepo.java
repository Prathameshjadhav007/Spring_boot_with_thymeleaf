package com.tron.thymeleaf.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tron.thymeleaf.models.Product;

public interface Productrepo extends JpaRepository<Product, Integer> {

}

package com.example.productservice.service;

import com.example.productservice.DTO.Product1DTO;
import com.example.productservice.DTO.ProductDTO;
import com.example.productservice.DTO.SolarDTO;
import com.example.productservice.entity.Product;
import java.util.List;

public interface ProductServiceInterface {

    ProductDTO addOrUpdateProduct(Product product);
    Boolean deleteProduct(String productId);
    ProductDTO getSingleProduct(String productId);
    List<ProductDTO> getProductByCategory(String CategoryId);
    List<Product1DTO> getAll();
    List<Product> getProductBySearch(String searchTerm);
    List<SolarDTO> getAllSolar();
    List<Product> listOfProductsByCategoryName(String name);
}
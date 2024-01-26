package com.example.productservice.service.impl;

import com.example.productservice.DTO.CategoryDTO;
import com.example.productservice.DTO.Product1DTO;
import com.example.productservice.DTO.ProductDTO;
import com.example.productservice.DTO.SolarDTO;
import com.example.productservice.entity.Product;
import com.example.productservice.repository.CustomProductRepository;
import com.example.productservice.repository.ProductRepository;
import com.example.productservice.service.CategoryServiceInterface;
import com.example.productservice.service.ProductServiceInterface;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.*;

@Service
public class ProductServiceImplementation implements ProductServiceInterface {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryServiceInterface categoryService;

    @Autowired
    CustomProductRepository customProductRepository;

    @Override
    public ProductDTO addOrUpdateProduct(Product product) {
        ProductDTO productDTO = new ProductDTO();
        //Product product = new Product();
        CategoryDTO category = new CategoryDTO();

        //BeanUtils.copyProperties(productDTO, product);
        product.setProductID(product.getProductCategory().getCategoryName().toUpperCase().substring(0, 3) + product.getProductName().toUpperCase());

        BeanUtils.copyProperties(product.getProductCategory(), category);
        product.getProductCategory().setCategoryId(category.getCategoryName().toUpperCase().substring(0, 3));

        if (!categoryService.existsById(product.getProductCategory().getCategoryId())) {
            categoryService.addCategory(product.getProductCategory());
        }
        BeanUtils.copyProperties(productRepository.save(product), productDTO);
        return productDTO;
    }

    @Override
    public Boolean deleteProduct(String productId) {
        if (productRepository.existsById(productId)) {
            productRepository.deleteById(productId);
            return true;
        }
        return false;
    }

    @Override
    public ProductDTO getSingleProduct(String productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            ProductDTO productDTO = new ProductDTO();
            BeanUtils.copyProperties(optionalProduct.get(), productDTO);
            return productDTO;
        }
        return null;
    }

    @Override
    public List<ProductDTO> getProductByCategory(String categoryId) {
        List<ProductDTO> productByCategory = new ArrayList<>();
//        for(Product product : productRepository.findAll()){
//            if(product.getProductCategory().getCategoryId().equals(categoryId)){
//                ProductDTO productDTO = new ProductDTO();
//                BeanUtils.copyProperties(product, productDTO);
//                productByCategory.add(productDTO);
//            }
//        }

        for (Product product : productRepository.findByProductCategory_CategoryId(categoryId)) {
            ProductDTO productDTO = new ProductDTO();
            BeanUtils.copyProperties(product, productDTO);
            productByCategory.add(productDTO);
        }

        return productByCategory;
    }

    public List<Product1DTO> getAll() {
//
//        LinkedHashMap<String,Product> products=new LinkedHashMap<>();
//        int i=0;
//        for(Product product:productRepository.findAll()){
//            if (!products.containsKey(product.getProductID())) {
//                products.put(product.getProductID(),product);
//                i+=1;
//            }
//        }
//        LinkedHashSet<Product1DTO> products=new LinkedHashSet<>();
//        for(Product product:productRepository.findAll()){
//            Product1DTO product1DTO=new Product1DTO();
//            product1DTO.setAptX(product.getAptX());
//            product1DTO.setProductName(product);
//            product1DTO.setProductBrand();
//            product1DTO.setProductDescription();
//            product1DTO.setImageURL();
//            product1DTO.setHighBass();
//            product1DTO.setProductCategory();
//            product1DTO.setProductID();
//
//          products.add(product);
//        }
//
//        List<Product> list=new ArrayList<>();
//         Iterator<Product> i=products.iterator();
//         while(i.hasNext()){
//             list.add(i.next());
//         }
        List<Product1DTO> list = new ArrayList<>();
        for (Product product : productRepository.findAll()) {
            Product1DTO product1DTO = new Product1DTO();
            BeanUtils.copyProperties(product, product1DTO);
            list.add(product1DTO);
        }
        return list;
    }

    public List<Product> getProductBySearch(String searchTerm) {
        return customProductRepository.searchByRegex(searchTerm);
    }

    public List<SolarDTO> getAllSolar() {
        List<SolarDTO> solarDTOS = new ArrayList<>();
        for (Product product : productRepository.findAll()) {
            SolarDTO solarDTO = new SolarDTO();
            BeanUtils.copyProperties(product, solarDTO);
            solarDTOS.add(solarDTO);
        }
        return solarDTOS;
    }


    public List<Product> listOfProductsByCategoryName(String name) {
        Query query = new Query();
        query.addCriteria(new Criteria().where("productCategory.categoryName").regex(name, "i"));
        query.with(Sort.by(Sort.Direction.DESC, "createdDate"));
        List<Product> list = mongoTemplate.find(query, Product.class);
        return list;
    }

}

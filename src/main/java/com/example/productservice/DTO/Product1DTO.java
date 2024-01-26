package com.example.productservice.DTO;

import com.example.productservice.entity.Category;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Date;


@Data
@ToString
public class Product1DTO {
    @Indexed(unique = true)
    private String productID;
    private String productName;
    private String productBrand;
    private String highBass;
    private String waterResistant;
    private String aptX;
    private String imageURL;
    private Category productCategory;
    private String productDescription;
}

package ua.hoienko.webstorespring.mapper.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private String name;
    private double price;
    private int totalAmount;
    private String color;
    private long productId;
}

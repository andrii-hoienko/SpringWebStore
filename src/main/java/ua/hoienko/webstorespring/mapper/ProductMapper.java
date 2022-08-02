package ua.hoienko.webstorespring.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ua.hoienko.webstorespring.mapper.dto.ProductDto;
import ua.hoienko.webstorespring.model.Product;

import java.time.LocalDateTime;

@AllArgsConstructor
@Component
public class ProductMapper {

    public Product map(Product product, ProductDto productDto) {
        product.setColor(productDto.getColor());
        product.setPrice(productDto.getPrice());
        product.setName(productDto.getName());
        product.setTotalAmount(productDto.getTotalAmount());
        product.setDate(LocalDateTime.now());
        return product;
    }
}

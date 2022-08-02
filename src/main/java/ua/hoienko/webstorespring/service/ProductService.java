package ua.hoienko.webstorespring.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.hoienko.webstorespring.mapper.dto.ProductDto;
import ua.hoienko.webstorespring.mapper.ProductMapper;
import ua.hoienko.webstorespring.model.Product;
import ua.hoienko.webstorespring.repository.ProductRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public void createProduct(ProductDto productDto) {
        Product product = new Product();
        productMapper.map(product, productDto);
        productRepository.save(product);
    }

    public void updateProduct(ProductDto productDto) {
        Optional<Product> product = productRepository.findById(productDto.getProductId());
        productMapper.map(product.orElseThrow(),productDto);
        productRepository.save(product.get());
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(long productId) {
        return productRepository.findById(productId).get();
    }

    public void fillTableWithData() {
        for (int i = 0; i < 10; i++) {
            Product product = new Product();
            product.setDate(LocalDateTime.now());
            product.setName("prod" + i);
            product.setPrice(10 + i);
            product.setTotalAmount(100 + i);
            product.setColor("red");
            productRepository.save(product);
        }
    }
}

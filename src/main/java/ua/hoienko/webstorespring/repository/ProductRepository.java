package ua.hoienko.webstorespring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.hoienko.webstorespring.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}

package ua.hoienko.webstorespring.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Cart {
    Map<Product, Integer> products;

    public String getFullPrice() {
        return String.format("%.2f", products.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum());
    }

    public Cart() {
        this.products = new HashMap<>();
    }

    public String getTotalAmount() {
        return Integer.toString(products.values().stream().mapToInt(i -> i).sum());
    }

    public void addProduct(Integer amount, Product product) {
        products.put(product, amount);
    }

    public void deleteByProductId(long productId) {
        products.remove(products.keySet().stream().filter(key -> key.getId().equals(productId)).findAny().orElseThrow());
    }

    public boolean isPresent(Product product) {
        return products.get(product) != null;
    }

    public List<Product> getProductList() {
        return new ArrayList<>(products.keySet());
    }

    public List<Integer> getAmountList() {
        return new ArrayList<>(products.values());
    }

    public void clear() {
        products.clear();
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

}

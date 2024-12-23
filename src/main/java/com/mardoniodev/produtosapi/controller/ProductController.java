package com.mardoniodev.produtosapi.controller;

import com.mardoniodev.produtosapi.mode.Product;
import com.mardoniodev.produtosapi.repository.ProductRepository;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<Product> findAll(@RequestParam("name") String name) {
        return productRepository.findByName(name);
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable String id) {
        return productRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Product saveProduct(@RequestBody Product product) {
        System.out.println("Produto recebido: " + product);
        var id = UUID.randomUUID().toString();
        product.setId(id);
        productRepository.save(product);
        return product;
    }

    @PutMapping("/{id}")
    public void updateProduct(@PathVariable String id, @RequestBody Product product) {
        product.setId(id);
        productRepository.save(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable String id) {
        productRepository.deleteById(id);
    }

}

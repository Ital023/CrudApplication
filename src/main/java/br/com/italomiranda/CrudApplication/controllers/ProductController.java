package br.com.italomiranda.CrudApplication.controllers;

import br.com.italomiranda.CrudApplication.domain.product.Product;
import br.com.italomiranda.CrudApplication.domain.product.ProductRepository;
import br.com.italomiranda.CrudApplication.domain.product.RequestProduct;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    //Retrieve a list of all data.
    @GetMapping
    public ResponseEntity getAllProducts(){
        return ResponseEntity.ok(productRepository.findAll());
    }

    //Register a new data.
    @PostMapping
    public ResponseEntity registerProduct(@RequestBody @Valid RequestProduct requestProduct){
        Product newProduct = new Product(requestProduct);
        productRepository.save(newProduct);

        return ResponseEntity.ok(requestProduct);
    }

    //Alter Data
    @PutMapping
    @Transactional
    public ResponseEntity updateProduct(@RequestBody @Valid RequestProduct data){
        Optional<Product> optionalProduct = productRepository.findById(data.id());
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setName(data.name());
            product.setPrice_in_cents(data.price_in_cents());
            return ResponseEntity.ok(product);
        } else {
            throw new EntityNotFoundException();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable String id){
        productRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

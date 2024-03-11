package br.com.italomiranda.CrudApplication.controllers;

import br.com.italomiranda.CrudApplication.domain.product.Product;
import br.com.italomiranda.CrudApplication.domain.product.ProductRepository;
import br.com.italomiranda.CrudApplication.domain.product.RequestProduct;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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



}

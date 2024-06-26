package com.aryahamrah.app.product;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService=null;
    private final SearchService searchService=null;

    @PostMapping
    public ResponseEntity<String> save(
            @RequestBody Product product
    ){
        return  ResponseEntity.ok(productService.save(product));
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{product-id}")
    public ResponseEntity<Product> findById(
            @PathVariable("product-id") String productId
    ){
        return ResponseEntity.ok(productService.findById(productId));
    }

    @DeleteMapping("/{product-id}")
    public ResponseEntity<Void> delete(
            @PathVariable("product-id") String productId
    ){
        productService.delete(productId);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/search/is")
    public ResponseEntity<List<Product>> searchByName(
            @RequestParam("name") String name
    ){
        return ResponseEntity.ok(searchService.searchByName(name));
    }

    @GetMapping("/search/starts-with")
    public ResponseEntity<List<Product>> searchByNameStartingWith(
            @RequestParam("name") String name
    ){
        return ResponseEntity.ok(searchService.searchByNameStartingWith(name));
    }

    @GetMapping("/search/ends-with")
    public ResponseEntity<List<Product>> searchByNameEndWith(
            @RequestParam("name") String name
    ){
        return ResponseEntity.ok(searchService.searchByNameEndWith(name));
    }

    @GetMapping("/search/lt")
    public ResponseEntity<List<Product>> searchByNamePriceLt(
            @RequestParam("price") BigDecimal price
    ){
        return ResponseEntity.ok(searchService.searchByNamePriceLt(price));
    }

    @GetMapping("/search/gt")
    public ResponseEntity<List<Product>> searchByNamePriceGt(
            @RequestParam("price") BigDecimal price
    ){
        return ResponseEntity.ok(searchService.searchByNamePriceGt(price));
    }

    @GetMapping("/sort/asc")
    public ResponseEntity<List<Product>> sortAscByField(
            @RequestParam("field") String field
    ){
        return ResponseEntity.ok(searchService.sortAscByField(field));
    }

    @GetMapping("/sort/page")
    public ResponseEntity<List<Product>> sortAndPageByField(
            @RequestParam("field") String field
    ){
        return ResponseEntity.ok(searchService.sortAndPageByField(field));
    }
}

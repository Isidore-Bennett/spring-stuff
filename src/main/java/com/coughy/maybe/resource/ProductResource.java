package com.coughy.maybe.resource;

import com.coughy.maybe.entity.Product;
import com.coughy.maybe.service.ProductService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import static com.coughy.maybe.util.PageUtility.setPageRequest;

@RestController
@RequestMapping("/api/product")
public class ProductResource {

    private final ProductService productService;

    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("")
//    @RolesAllowed("")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        HttpHeaders headers = new HttpHeaders();
        try {
            return new ResponseEntity<>(productService.saveProduct(product), headers, HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/user")
    public ResponseEntity<Set<Product>> getAllProducts() {
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(productService.getAllByVisibleTrue(), headers, HttpStatus.OK);
    }

    @GetMapping("/admin")
    public ResponseEntity<Page<Product>> getProducts(
            @RequestHeader Map<String, String> headers
//            @RequestHeader("page") Long page,
//            @RequestHeader("size") Long size,
//            @RequestHeader("sort") String sort,
//            @RequestHeader("dir") String dir
    ) {
//    public ResponseEntity<Page<Product>> getProducts(Pageable pageable) {
        Pageable pageable = setPageRequest(headers);
        return new ResponseEntity<>(productService.getProducts(pageable), HttpStatus.OK);
    }

    @GetMapping("/toggle/{id}")
    public ResponseEntity<Product> toggle(@PathVariable String id) {
        try {
            return new ResponseEntity<>(productService.toggle(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

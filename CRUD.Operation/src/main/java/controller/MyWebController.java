package controller;

import com.example.crud.product.Product;
import com.example.crud.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class MyWebController {
    private final ProductService productService;

    @Autowired
    public MyWebController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public Product createProduct(Product product) {
        return productService.create(product.getName(), product.getPrice());

    }

    @GetMapping
    public List<Product> readAllProducts() {
        return productService.readAll();
    }

    @GetMapping("/{id}")
    public Product readProductById(@PathVariable Long id) {
        return productService.getReadId(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, Product updateProduct) {
        return productService.update(id, updateProduct.getName(), updateProduct.getPrice());

    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.delete(id);
    }


}

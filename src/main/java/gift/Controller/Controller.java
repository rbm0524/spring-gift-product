package gift.Controller;

import gift.Model.Product;
import gift.Service.ProductService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    private final ProductService productService;

    @Autowired
    public Controller(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/api/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/api/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        //product 객체가 존재하면 HTTP 200과 product 객체를 포함한 ResponseEntity 객체 반환
        //product 객체가 비어있으면 HTTP 404 응답 생성, build()로 본문이 없는 ResponseEntity 객체 반환
    }

    @PostMapping("/api/products/create")
    public ResponseEntity createProduct(@RequestBody Product product) {
        Product createdProduct = productService.saveProduct(product);
        if(createdProduct == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미 존재하는 상품입니다.");
        }
        return ResponseEntity.ok(createdProduct);
    }

    @PutMapping("/api/products/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id,
        @RequestBody Product productDetails) {
        Optional<Product> optionalProduct = productService.getProductById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setName(productDetails.getName());
            product.setPrice(productDetails.getPrice());
            return ResponseEntity.ok(productService.saveProduct(product));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/api/products/delete/{id}")
    public List<Product> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return productService.getAllProducts(); //삭제한 후 남은 내용들 반환
    }
}
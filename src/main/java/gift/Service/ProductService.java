package gift.Service;

import gift.Model.Product;
import gift.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final Map<Long, Product> products = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong(); //thread-safe

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAllProduct();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product saveProduct(Product product) {
        product.setId(idCounter.incrementAndGet());
        return productRepository.saveProduct(product);
    }

    public void updateProduct(Long id, Product productDetails) {
        productRepository.updateProduct(id, productDetails);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteProductById(id);
        idCounter.decrementAndGet();
    }
}
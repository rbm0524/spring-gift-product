package gift.Service;

import gift.Model.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
@Service
public class ProductService {
    private final Map<Long, Product> products = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong(); //thread-safe

    @PostConstruct
    public void init() {
        Product product = new Product();
        product.setName("아이스 카페 아메리카노 T");
        product.setPrice(4500);
        product.setImageUrl("https://img1.kakaocdn.net/thumb/C320x320@2x.fwebp.q82/?fname=https%3A%2F%2Fst.kakaocdn.net%2Fproduct%2Fgift%2Fproduct%2F20231010111814_9a667f9eccc943648797925498bdd8a3.jpg");
        saveProduct(product);
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    public Optional<Product> getProductById(Long id) {
        return Optional.ofNullable(products.get(id));
    }

    public Product saveProduct(Product product) {
        if (product.getId() != null && products.containsKey(product.getId())) {
            return null;
        }

        if (product.getId() == null) {
            product.setId(idCounter.incrementAndGet());
        }
        products.put(product.getId(), product);
        return product;
    }

    public void deleteProduct(Long id) {
        if (products.containsKey(id)) {
            products.remove(id);
        }
    }
}
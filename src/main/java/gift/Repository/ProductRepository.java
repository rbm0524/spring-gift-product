package gift.Repository;

import gift.Model.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    private void createProductTable() {
        String sql = """
            create table if not exists products (
                id bigint auto_increment,
                name varchar(255),
                price double,
                imageUrl varchar(1000),
                primary key (id)
            )
            """;
        jdbcTemplate.execute(sql);
    }

    public List<Product> findAllProduct() {
        String query = "SELECT * FROM products";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Product.class));
    }

    public Optional<Product> findById(Long id) {
        String query = "SELECT * FROM products WHERE id = ?";
        List<Product> products = jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Product.class), id);
        if (products.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(products.get(0));
        }
    }

    public Product saveProduct(Product product) {
        String query = "INSERT INTO products (name, price, imageUrl) VALUES (?, ?, ?)";
        jdbcTemplate.update(query, product.getName(), product.getPrice(), product.getImageUrl());
        return product;
    }

    public void updateProduct(Long id, Product productDetails) {
        String query = "UPDATE products SET name = ?, price = ?, imageUrl = ? WHERE id = ?";
        jdbcTemplate.update(query, productDetails.getName(), productDetails.getPrice(), productDetails.getImageUrl(), id);
    }

    public void deleteProductById(Long id) {
        String query = "DELETE FROM products WHERE id = ?";
        jdbcTemplate.update(query, id);
    }
}
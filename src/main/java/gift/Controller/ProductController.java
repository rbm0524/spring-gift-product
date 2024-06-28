package gift.Controller;

import gift.Model.Product;
import gift.Service.ProductService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

@Controller
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/api/products")
    public String getAllProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/api/products/create")
    public String showCreateProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "product_form";
    }

    @PostMapping("/api/products/create")
    public String createProduct(@ModelAttribute Product product) { //form의 데이터를 처리하기 위해
        productService.saveProduct(product);
        return "redirect:/api/products"; //redirection
    }

    @GetMapping("/api/products/update/{id}")
    public String showUpdateProductForm(@PathVariable Long id, Model model) {
        Optional<Product> optionalProduct = productService.getProductById(id);
        model.addAttribute("product", optionalProduct.get());
        return "update_form";
    }

    @PostMapping("/api/products/update/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute Product productDetails) {
        Optional<Product> optionalProduct = productService.getProductById(id);
        if (optionalProduct.isPresent()) {
            productService.updateProduct(id, productDetails);
        }
        return "redirect:/api/products";
    }

    @PostMapping("/api/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/api/products";  // 제품 목록 페이지로 리디렉션
    }
}
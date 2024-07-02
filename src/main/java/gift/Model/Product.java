package gift.Model;

public class Product {

    private Long id;
    private String name;
    private double price;
    private String imageUrl;
    private boolean isDeleted;

    public Product() {

    }

    public Product(Long id, String name, double price, String imageUrl, boolean isDeleted) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.isDeleted = isDeleted;
    }

    public String getName() {

        return name;
    }

    public double getPrice() {

        return price;
    }

    public String getImageUrl() {

        return imageUrl;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

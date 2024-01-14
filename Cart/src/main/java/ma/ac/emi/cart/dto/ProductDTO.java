package ma.ac.emi.cart.dto;



public class ProductDTO {
    private String title;
    private double price;
    private boolean disponibility;
    private Category category;
    private String imgUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isDisponibility() {
        return disponibility;
    }

    public void setDisponibility(boolean disponibility) {
        this.disponibility = disponibility;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public ProductDTO() {
    }

    public ProductDTO(String title, double price, boolean disponibility, Category category, String imgUrl) {
        this.title = title;
        this.price = price;
        this.disponibility = disponibility;
        this.category = category;
        this.imgUrl = imgUrl;
    }
}

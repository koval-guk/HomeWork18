import java.time.LocalDate;
import java.util.Objects;

public class Product {
    private ProductType type;
    private double price;
    private boolean isDiscount;
    private LocalDate date;

    public Product(ProductType type, double price) {
        this.type = type;
        this.price = price;
        this.date = LocalDate.now();
    }

    public Product(ProductType type, double price, boolean isDiscount) {
        this.type = type;
        this.price = price;
        this.isDiscount = isDiscount;
        this.date = LocalDate.now();
    }

    public ProductType getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public boolean isDiscount() {
        return isDiscount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.price, price) == 0 && isDiscount == product.isDiscount && type == product.type && Objects.equals(date, product.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, price, isDiscount, date);
    }

    @Override
    public String toString() {
        return "[" + type + ", price=" + price + "]";
    }
}

import java.time.LocalDate;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        products.add(new Product(ProductType.BOOK, 50));
        products.add(new Product(ProductType.BOOK, 300));
        products.add(new Product(ProductType.BOOK, 10000));
        products.add(new Product(ProductType.BOOK, 500));
        products.add(new Product(ProductType.BOOK, 250));
        products.add(new Product(ProductType.TOY, 1700));
        products.add(new Product(ProductType.BOOK, 500, true));
        products.add(new Product(ProductType.BOOK, 350));
        products.add(new Product(ProductType.BOOK, 666, true));

        System.out.println(listOfTypePrice(products, ProductType.BOOK, 250));

        System.out.println(listOfTypeDiscount(products, ProductType.BOOK, 10));

        System.out.println(minPriceProduct(products, ProductType.BOOK));

        List<Product> tempProducts = products.stream()
                .skip(products.size() / 2)
                .peek(p -> p.setDate(LocalDate.parse("2020-01-01")))
                .collect(Collectors.toList());
        tempProducts.addAll(products);
        products = tempProducts.stream()
                .limit(products.size())
                .collect(Collectors.toList());

        System.out.println("year: " + listOfTypeMaxPriceThisYear(products, ProductType.BOOK, 75));

        System.out.println("map: " + toMapOfType(products));
    }

    public static List<Product> listOfTypePrice(List<Product> list, ProductType type, double price) {
        return list.stream()
                .filter(p -> p.getType().equals(type))
                .filter(p -> p.getPrice() > price)
                .collect(Collectors.toList());
    }

    public static List<Product> listOfTypeDiscount(List<Product> list, ProductType type, int discount) {
        return list.stream()
                .filter(p -> p.getType() == type)
                .filter(Product::isDiscount)
                .map(p -> new Product(p.getType(),p.getPrice()*(100-discount)/100, p.isDiscount()))
                .collect(Collectors.toList());
    }

    public static Product minPriceProduct(List<Product> list, ProductType type) {
        try {
            return list.stream()
                    .filter(p -> p.getType().equals(type))
                    .min(Comparator.comparingDouble(Product::getPrice))
                    .orElseThrow(() -> new Exception(("Product [ type: " + type + " ] not found")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Product> listOfTypeMaxPriceThisYear(List<Product> list, ProductType type, double price) {
        LocalDate dateNow = LocalDate.now();
        return list.stream()
                .filter(p -> p.getType().equals(type))
                .filter(p -> p.getPrice() <= price)
                .filter(p -> p.getDate().getYear() == dateNow.getYear())
                .collect(Collectors.toList());
    }

    public static Map<ProductType, List<Product>> toMapOfType(List<Product> list) {
        return list.stream()
                .collect(Collectors.groupingBy(Product::getType));
    }

}

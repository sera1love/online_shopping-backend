package ShopSystem.interface_OJnS;

import ShopSystem.Product;
import java.util.function.Predicate;

// функциональный интерфейс для фильтра товаров
@FunctionalInterface
public interface ProductFilter extends Predicate<Product> {
    // наследуем test(Product p) из Predicate
    default ProductFilter and(ProductFilter other) {
        return p -> this.test(p) && other.test(p);
    }
}
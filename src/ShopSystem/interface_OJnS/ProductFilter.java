package ShopSystem.interface_OJnS;
import ShopSystem.Product;
import java.util.function.Predicate;

@FunctionalInterface
public interface ProductFilter extends Predicate<Product> {
    default ProductFilter and(ProductFilter other) {
        return p -> this.test(p) && other.test(p);
    }
    default ProductFilter or(ProductFilter other) {
        return p -> this.test(p) || other.test(p);
    }
}
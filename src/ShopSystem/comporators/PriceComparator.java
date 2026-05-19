package ShopSystem.comporators;
import ShopSystem.Product;
import java.util.Comparator;

public class PriceComparator implements Comparator<Product> {
    private final boolean ascending;
    public PriceComparator(boolean ascending) { this.ascending = ascending; }
    @Override public int compare(Product c1, Product c2) {
        int result = Double.compare(c1.getPrice(), c2.getPrice());
        return ascending ? result : -result;
    }
}
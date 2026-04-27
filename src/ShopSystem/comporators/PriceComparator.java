package ShopSystem.comporators;

import ShopSystem.Category;
import java.util.Comparator;

public class PriceComparator implements Comparator<Category> {
    private final boolean ascending;

    public PriceComparator(boolean ascending) {
        this.ascending = ascending;
    }

    @Override
    public int compare(Category c1, Category c2) {
        int result = Double.compare(c1.getPrice(), c2.getPrice());
        return ascending ? result : -result;
    }
}

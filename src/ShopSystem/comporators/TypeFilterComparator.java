package ShopSystem.comporators;
import ShopSystem.Product;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TypeFilterComparator implements Comparator<Product> {
    private final Class<?> filterType;
    private final boolean includeSubtypes;
    public TypeFilterComparator(Class<?> filterType, boolean includeSubtypes) {
        this.filterType = filterType; this.includeSubtypes = includeSubtypes;
    }
    @Override public int compare(Product c1, Product c2) {
        boolean c1Match = matches(c1); boolean c2Match = matches(c2);
        if (c1Match && !c2Match) return -1;
        if (!c1Match && c2Match) return 1;
        return 0;
    }
    private boolean matches(Product c) { return includeSubtypes ? filterType.isInstance(c) : c.getClass() == filterType; }
    public static List<Product> filterByType(List<Product> source, Class<?> type, boolean includeSubtypes) {
        List<Product> result = new ArrayList<>();
        for (Product c : source) {
            boolean matches = includeSubtypes ? type.isInstance(c) : c.getClass() == type;
            if (matches) result.add(c);
        }
        return result;
    }
}
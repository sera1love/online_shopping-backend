package ShopSystem.comporators;

import ShopSystem.Category;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TypeFilterComparator implements Comparator<Category> {
    private final Class<?> filterType;
    private final boolean includeSubtypes;

    public TypeFilterComparator(Class<?> filterType, boolean includeSubtypes) {
        this.filterType = filterType;
        this.includeSubtypes = includeSubtypes;
    }

    @Override
    public int compare(Category c1, Category c2) {
        boolean c1Match = matches(c1);
        boolean c2Match = matches(c2);

        if (c1Match && !c2Match) return -1;
        if (!c1Match && c2Match) return 1;
        return 0;
    }

    // метод для проверки соответствия типу
    private boolean matches(Category c) {
        return includeSubtypes ? filterType.isInstance(c) : c.getClass() == filterType;
    }

    // метод фильтрации
    public static List<Category> filterByType(List<Category> source, Class<?> type, boolean includeSubtypes) {
        List<Category> result = new ArrayList<>();
        for (Category c : source) {
            boolean matches = includeSubtypes ? type.isInstance(c) : c.getClass() == type;
            if (matches) {
                result.add(c);
            }
        }
        return result;
    }
}
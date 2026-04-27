package ShopSystem.comporators;

import ShopSystem.MobileDevice;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MemoryComparator implements Comparator<MobileDevice> {
    private final boolean ascending;

    public MemoryComparator(boolean ascending) {
        this.ascending = ascending;
    }

    private int extractMemoryGB(String description) {
        Pattern pattern = Pattern.compile("(\\d+)\\s*[Гг]б");
        Matcher matcher = pattern.matcher(description);
        return matcher.find() ? Integer.parseInt(matcher.group(1)) : 0;
    }

    @Override
    public int compare(MobileDevice m1, MobileDevice m2) {
        int mem1 = extractMemoryGB(m1.getDescription());
        int mem2 = extractMemoryGB(m2.getDescription());
        int result = Integer.compare(mem1, mem2);
        return ascending ? result : -result;
    }
}

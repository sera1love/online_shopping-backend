public class GardenItem extends Category {
    public GardenItem(String title, double price, String description) {
        super(title, price, description);
    }

    @Override
    public boolean isSubCategory() {
        return true;
    }

    @Override
    public void printInfo() {
        System.out.println("[Сад] - " + getTitle() + "\n"
                + " Цена - " + getPrice() + "\n"
                + " (ID: " + getID() + ")" + "\n"
                + " Описание: " + getDescription() + "\n");
    }
}

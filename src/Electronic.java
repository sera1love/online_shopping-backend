public class Electronic extends Category {
    public Electronic(String title, double price, String description) {
        super(title, price, description);
    }

    @Override
    public boolean isSubCategory() {
        return true;
    }

    @Override
    public void printInfo() {
        System.out.println("[Умный дом] - " + getTitle() + "\n"
                + " Цена - " + getPrice() + "\n"
                + " (ID: " + getID() + ")" + "\n"
                + " Описание: " + getDescription() + "\n");
    }
}

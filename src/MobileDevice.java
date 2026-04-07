public class MobileDevice extends Electronic {
    public MobileDevice(String title, double price, String description) {
        super(title, price, description);
    }

    @Override
    public void printInfo() {
        System.out.println("[Смартфоны] - " + getTitle() + "\n"
                + " Цена - " + getPrice() + "\n"
                + " (ID: " + getID() + ")" + "\n"
                + " Описание: " + getDescription() + "\n");
    }
}

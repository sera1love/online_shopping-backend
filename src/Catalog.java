public class Catalog {
    // TODO 1 поле - ID, 2 поле - Title, 3 поле - Price.

    private int id;
    private String title;
    private double price;

    public Catalog() {}
    public Catalog(int id, String title, double price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    // получение id
    public int getID() {
        return this.id;
    }
    public void setID(int id) {
        this.id = id;
    }

    // получение названия
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    // получение цены
    public double getPrice() {
        return this.price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
}

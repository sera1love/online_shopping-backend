package ShopSystem.ClientSystem;

public class Person {
    private static int idGen = 1;
    private final int id;
    private String name;
    private String phone;

    public Person(String name, String phone) {
        this.id = idGen++;
        this.name = name;
        this.phone = phone;
    }

    // геттеры и сеттеры
    public int getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    @Override public String toString() {
        return String.format("#%d | %s | %s", id, name, phone);
    }
}
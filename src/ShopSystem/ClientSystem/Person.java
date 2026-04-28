package ShopSystem.ClientSystem;

public class Person {
    private static int idGen = 1;
    private int id;
    private String name;
    private String phone;

    public Person(String name, String phone) {
        this.id = idGen++;
        this.name = name;
        this.phone = phone;
    }

    // геттеры и сеттеры
    // получение id
    public int getId() { return id; }
    public void setId(int id) { this.id = id;}

    // получение имени
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    // получение номера
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    @Override
    public String toString() {
        return String.format(" #%d | %s |  %s", id, name, phone);
    }
}

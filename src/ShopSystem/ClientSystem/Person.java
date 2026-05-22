package ShopSystem.ClientSystem;

import java.util.Objects;

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

    public int getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return id == person.id &&
                Objects.equals(name, person.name) &&
                Objects.equals(phone, person.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phone);
    }

    @Override
    public String toString() {
        return String.format("#%d | %s | %s", id, name, phone);
    }
}
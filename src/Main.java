public class Main {
    public static void main(String[] args) {
    // TODO catalog - Сделать новый каталог для магазина, 1 поле - ID будет идти в формате int, 2 Title - в формате String,
    //  3 Price - в формате double. Сделать полную инкапсуляцию и конструктор, в каталоге создаем 2 объекта(каталог1/2)

    Catalog catalog1 = new Catalog(1, "Хлеб", 30.0);
    System.out.println("ID товара - " + catalog1.getID() +
            ", Название - " + catalog1.getTitle() + ", Цена - " + catalog1.getPrice());

    Catalog catalog2 = new Catalog(2, "Колбаса", 350.0);
    System.out.println("ID товара - " + catalog2.getID() +
            ", Название - " + catalog2.getTitle() + ", Цена - " + catalog2.getPrice());
    }
}
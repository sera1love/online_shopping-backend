package ShopSystem.interface_OJnS.OrderStatus;

public enum OrderStatus {
    NEW("Новый"),
    IN_PROGRESS("В процессе"),
    SHIPPED("Отправлен"),
    DELIVERED("Получен"),
    CANCELLED("Отменён");

    private final String label;

    OrderStatus(String label) { this.label = label; }
    public String getLabel() { return label; }
}
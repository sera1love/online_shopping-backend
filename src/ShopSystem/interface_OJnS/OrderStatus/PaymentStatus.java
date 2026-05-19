package ShopSystem.interface_OJnS.OrderStatus;

public enum PaymentStatus {
    NEW("Новый"),
    PAID("Оплачен"),
    REFUNDED("Возврат товара"),
    FAILED("Ошибка оплаты");

    private final String label;

    PaymentStatus(String label) { this.label = label; }
    public String getLabel() { return label; }
}
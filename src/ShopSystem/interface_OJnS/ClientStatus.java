package ShopSystem.interface_OJnS;

public enum ClientStatus {
    ACTIVE("Активен"),
    BLOCKED("Заблокирован"),
    VIP("VIP-клиент"),
    INACTIVE("Неактивен");

    private final String label;

    ClientStatus(String label) { this.label = label; }
    public String getLabel() { return label; }
}
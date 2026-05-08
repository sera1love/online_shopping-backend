package ShopSystem.interface_OJnS;

// функциональный интерфейс для проверки статуса
@FunctionalInterface
public interface StatusValidator<T extends Enum<T>> {
    boolean validate(T status);

    // Метод по умолчанию для комбинации валидаторов
    default StatusValidator<T> or(StatusValidator<T> other) {
        return status -> this.validate(status) || other.validate(status);
    }
}
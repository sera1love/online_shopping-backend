package ShopSystem.interface_OJnS;

@FunctionalInterface
public interface StatusValidator<T extends Enum<T>> {
    boolean validate(T status);
    default StatusValidator<T> or(StatusValidator<T> other) {
        return status -> this.validate(status) || other.validate(status);
    }
    default StatusValidator<T> and(StatusValidator<T> other) {
        return status -> this.validate(status) && other.validate(status);
    }
}
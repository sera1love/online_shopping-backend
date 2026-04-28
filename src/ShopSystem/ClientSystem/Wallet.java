package ShopSystem.ClientSystem;

import ShopSystem.interface_OJnS.Finansable;

public class Wallet implements Finansable {
    private double balance;
    private final String currency = "р";

    public Wallet(double initialBalance) {
        this.balance = Math.max(0, initialBalance);
    }

    @Override
    public double checkBalance() {
        return balance;
    }

    @Override
    public boolean hasAmountMoney(double amount) {
        return balance >= amount && amount > 0;
    }

    @Override
    public String getFinalStatus() {
        return String.format("Баланс: %.2f %s", balance, currency);
    }

    // пополнение счёта
    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Зачислено: " + amount + "р");
            return true;
        }
        System.out.println("Сумма должна быть положительной");
        return false;
    }

    // списание средств
    public boolean withdraw(double amount) {
        if (amount <= 0) return false;
        if (hasAmountMoney(amount)) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public void setBalance(double balance) {
        if (balance >= 0) this.balance = balance;
    }
}

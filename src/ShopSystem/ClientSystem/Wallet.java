package ShopSystem.ClientSystem;

import ShopSystem.Exception.*;
import ShopSystem.interface_OJnS.Finansable;
import java.util.Objects;

public class Wallet implements Finansable {
    private double balance;
    private final String currency = "р";

    public Wallet(double initialBalance) {
        if (initialBalance < 0) {
            throw new InvalidAmountException(initialBalance);
        }
        this.balance = initialBalance;
    }

    @Override
    public double checkBalance() {
        return balance;
    }

    @Override
    public boolean hasAmountMoney(double amount) {
        if (amount <= 0) {
            throw new InvalidAmountException(amount);
        }
        return balance >= amount;
    }

    @Override
    public String getFinalStatus() {
        return String.format("Баланс: %.2f %s", balance, currency);
    }

    public boolean deposit(double amount) {
        boolean success = false;
        try {
            if (amount <= 0) {
                throw new InvalidAmountException(amount);
            }
            balance += amount;
            success = true;
            return true;
        } catch (InvalidAmountException e) {
            System.out.println("Ошибка пополнения: " + e.getMessage());
            return false;
        } finally {
            // finally выполняется всегда. Здесь удобно писать в лог/консоль факт операции
            System.out.println("[ЛОГ Wallet] Попытка пополнения на " + amount + "р. Успех: " + success);
        }
    }

    public boolean withdraw(double amount) {
        boolean success = false;
        try {
            if (amount <= 0) {
                throw new InvalidAmountException(amount);
            }
            if (!hasAmountMoney(amount)) {
                throw new InsufficientFundsException(balance, amount);
            }
            balance -= amount;
            success = true;
            return true;
        } catch (ShopSystemException e) {
            System.out.println("Ошибка списания: " + e.getMessage());
            return false;
        } finally {
            System.out.println("[ЛОГ Wallet] Попытка списания " + amount + "р. Успех: " + success);
        }
    }

    public void setBalance(double balance) {
        if (balance < 0) {
            throw new InvalidAmountException(balance);
        }
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Wallet)) return false;
        Wallet wallet = (Wallet) o;
        return Double.compare(wallet.balance, balance) == 0 &&
                Objects.equals(currency, wallet.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(balance, currency);
    }

    @Override
    public String toString() {
        return String.format("Wallet[balance=%.2f, currency=%s]", balance, currency);
    }
}
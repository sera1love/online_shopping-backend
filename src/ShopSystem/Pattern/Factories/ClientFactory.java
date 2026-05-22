package ShopSystem.Pattern.Factories;

import ShopSystem.ClientSystem.Client;
import ShopSystem.ClientSystem.Wallet;

public class ClientFactory {
    public static Client createClient(String name, String phone, double initialBalance) {
        return new Client(name, phone, initialBalance);
    }

    public static Wallet createWallet(double initialBalance) {
        return new Wallet(initialBalance);
    }
}
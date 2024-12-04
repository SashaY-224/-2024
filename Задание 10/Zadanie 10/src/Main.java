// Подсистема: Счет
class Account {
    private double balance;

    public Account(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Снятие " + amount + " успешно.");
        } else {
            System.out.println("Недостаточно средств.");
        }
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Пополнение на " + amount + " успешно.");
    }
}

// Подсистема: Банковская карта
class BankCard {
    private final String cardNumber;
    private final Account account;

    public BankCard(String cardNumber, Account account) {
        this.cardNumber = cardNumber;
        this.account = account;
    }

    public boolean checkPin(int pin) {
        // Проверка PIN-кода (упрощенно)
        return pin == 1234;
    }

    public Account getAccount() {
        return account;
    }
}

// Подсистема: Диспенсер наличных
class CashDispenser {
    public void dispenseCash(double amount) {
        System.out.println("Выдача " + amount + " наличными.");
    }
}

// Пример использования:
public class Main {
    public static void main(String[] args) {
        Account account = new Account(1000);
        BankCard card = new BankCard("1234567890", account);
        CashDispenser dispenser = new CashDispenser();
        ATMFacade atm = new ATMFacade(card, dispenser);

        atm.checkBalance(1234);     // Проверка баланса
        atm.withdrawCash(1234, 200); // Снятие
        atm.depositCash(500);        // Пополнение
        atm.checkBalance(1234);
    }
}
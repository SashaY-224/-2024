// Фасад: Банкомат
public class ATMFacade {
    private final BankCard card;
    private final CashDispenser cashDispenser;

    public ATMFacade(BankCard card, CashDispenser cashDispenser) {
        this.card = card;
        this.cashDispenser = cashDispenser;
    }

    // Снятие наличных
    public void withdrawCash(int pin, double amount) {
        if (card.checkPin(pin)) {
            card.getAccount().withdraw(amount);
            cashDispenser.dispenseCash(amount);
        } else {
            System.out.println("Неверный PIN-код.");
        }
    }

    // Проверка баланса
    public void checkBalance(int pin) {
        if (card.checkPin(pin)) {
            System.out.println("Баланс: " + card.getAccount().getBalance());
        } else {
            System.out.println("Неверный PIN-код.");
        }
    }

    // Пополнение счета
    public void depositCash(double amount) {
        card.getAccount().deposit(amount);
    }
}

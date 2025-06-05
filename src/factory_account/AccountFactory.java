package factory_account;

import accounts.CheckingAccountImpl;
import accounts.SavingsAccountImpl;
import accounts.interfaces.IAccount;
import observer.interfaces.Observer;

import java.util.ArrayList;
import java.util.List;

public class AccountFactory {
    private int counterNumberAccount = 0;

    private final List<Observer> observers = new ArrayList<>();

    static private AccountFactory instance = null;

    private AccountFactory() {
    }

    public static AccountFactory getInstance() {
        if (instance == null) {
            instance = new AccountFactory();
        }
        return instance;
    }

    public IAccount createAccount(AccountType accountType, String name) {
        int numberAccount = generatorNumberAccount();
        if (accountType == AccountType.checking) {
            return new CheckingAccountImpl(name, numberAccount, accountType);
        } else {
            return new SavingsAccountImpl(name, numberAccount, accountType);
        }
    }

    private int generatorNumberAccount() {
        counterNumberAccount++;
        return counterNumberAccount;
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    protected void notifyObservers(String mensagem) {
        for (Observer observer : observers) {
            observer.update(mensagem);
        }
    }
}

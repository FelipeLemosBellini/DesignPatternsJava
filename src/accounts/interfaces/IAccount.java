package accounts.interfaces;

import factory_account.AccountType;
import observer.interfaces.Observer;

import java.util.ArrayList;
import java.util.List;

public abstract class IAccount {
    public String name;
    public int idAccount;
    public double balance;
    public boolean exist;
    public AccountType accountType;

    private List<Observer> observers = new ArrayList<>();

    public IAccount(String name, int idAccount, AccountType accountType) {
        this.name = name;
        this.idAccount = idAccount;
        this.balance = 0;
        this.exist = true;
        this.accountType = accountType;
    }

    public abstract void deposit(double value);

    public abstract void withdraw(double value);

    public abstract double getBalance();

    public abstract void transfer(double value, int accountDestiny);

    protected abstract boolean checkIfBalanceIsSufficient(double value);

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
    public void printAccount() {
        System.out.println("Id: " + idAccount);
        System.out.println("nome: " + name);
        System.out.println();
    }
}

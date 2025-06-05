package accounts;

import factory_account.AccountType;
import my_bank.MyBank;
import accounts.interfaces.IAccount;

public class CheckingAccountImpl extends IAccount {

    public CheckingAccountImpl(String name, int idAccount, AccountType accountType) {
        super(name, idAccount, accountType);
    }

    @Override
    public void deposit(double value) {
        super.balance += value;
        System.out.println("->Deposito de " + value + " na conta " + idAccount + "\n");
        notifyObservers("Houve um depósito para a conta no valor de: " + value);
    }

    @Override
    public void withdraw(double value) {
        if (checkIfBalanceIsSufficient(value)) {
            super.balance -= value;
            System.out.println("->Saque de " + value + " na conta " + idAccount + "\n");
            notifyObservers("Houve um saque da conta de: " + value);
        } else {
            System.out.println("!!!--------Falha--------!!!");
            System.out.println("saldo insuficiente");
        }
    }

    @Override
    public double getBalance() {
        return super.balance;
    }

    @Override
    public void transfer(double value, int accountDestiny) {
        if (checkIfBalanceIsSufficient(value)) {
            MyBank bank = MyBank.getInstance();
            if (bank.accountMap.get(accountDestiny).exist) {
                super.balance -= value;
                IAccount account = bank.accountMap.get(accountDestiny);
                account.balance += value;
                bank.accountMap.put(accountDestiny, account);
                notifyObservers("Houve uma transferência para a conta: " + accountDestiny);
                System.out.println("->Transferencia de " + value + " da conta " + idAccount + " para a conta " + accountDestiny + "\n");
            } else {
                System.out.println("!!!--------Falha--------!!!");
                System.out.println("Falha ao trasferir para a conta numero: " + accountDestiny);
                System.out.println("verifique a conta de destino\n");
            }
        } else {
            System.out.println("!!!--------Falha--------!!!");
            System.out.println("saldo insuficiente\n");
        }
    }

    @Override
    protected boolean checkIfBalanceIsSufficient(double value) {
        return super.balance >= value;
    }

    public void chargeMonthlyRate() {
        MyBank bank = MyBank.getInstance();
        super.balance -= bank.getChangeMonthlyRate();
    }
}

package accounts;

import accounts.interfaces.IAccount;
import factory_account.AccountType;
import my_bank.MyBank;

public class SavingsAccountImpl extends IAccount {
    public SavingsAccountImpl(String name, int numberAccount, AccountType accountType) {
        super(name, numberAccount, accountType );
    }

    @Override
    public void deposit(double value) {
        super.balance += value;
        System.out.println("->Deposito de " + value + " na conta " + idAccount + "\n");
    }

    @Override
    public void withdraw(double value) {
        if (checkIfBalanceIsSufficient(value)) {
            super.balance -= value;
            System.out.println("->Saque de " + value + " na conta " + idAccount + "\n");
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

    public void showYieldByDay() {
        MyBank bank = MyBank.getInstance();
        System.out.println("Juros ganhos por dia: " + bank.getYieldByDay());
    }
}

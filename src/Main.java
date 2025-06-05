import accounts.CheckingAccountImpl;
import accounts.SavingsAccountImpl;
import accounts.interfaces.IAccount;
import factory_account.AccountFactory;
import factory_account.AccountType;
import my_bank.MyBank;
import observer.SendEmailNotifier;
import observer.interfaces.Observer;

public class Main {
    public static void main(String[] args) {
        //criando a fábrica de contas
        AccountFactory accountFactory = AccountFactory.getInstance();

        // criando contas
        IAccount newAccount1 = accountFactory.createAccount(AccountType.checking, "Felipe");
        IAccount newAccount2 = accountFactory.createAccount(AccountType.checking, "Felipe2");
        IAccount newAccount3 = accountFactory.createAccount(AccountType.savings, "Felipe3");

        //criando a instancia do Banco
        MyBank bank = MyBank.getInstance();

        //adicionando as contas geradas pela fábrica no banco
        bank.addAccount(newAccount1);
        bank.addAccount(newAccount2);
        bank.addAccount(newAccount2);
        bank.addAccount(newAccount3);

        //criando o Observer
        Observer sendEmailNotifier = new SendEmailNotifier();

        //inscrevendo a conta 1 para observar as transações
        bank.accountMap.get(1).addObserver(sendEmailNotifier);

        //criando transações
        bank.accountMap.get(1).deposit(10);
        bank.accountMap.get(1).transfer(2, 2);
        bank.accountMap.get(2).withdraw(1);

        //removendo observer
        bank.accountMap.get(1).removeObserver(sendEmailNotifier);
        //mais uma transação
        bank.accountMap.get(1).deposit(10);

        // mostrando saldo após transações
        System.out.println("Saldo da conta 1: " + bank.accountMap.get(1).getBalance());


        //Fazendo cast das contas para executar funções especificas de cada uma
        IAccount account1 = bank.accountMap.get(1);
        if (account1 instanceof CheckingAccountImpl checkingAccount) {
            checkingAccount.chargeMonthlyRate();
        }
        IAccount account3 = bank.accountMap.get(3);
        if (account3 instanceof SavingsAccountImpl savingsAccount) {
            savingsAccount.showYieldByDay();
        }

        //listagem de contas
        bank.listAccounts();
    }
}
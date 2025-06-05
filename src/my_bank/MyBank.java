package my_bank;

import accounts.interfaces.IAccount;

import java.util.HashMap;
import java.util.Map;

public class MyBank {
    static private MyBank instance = null;

    public Map<Integer, IAccount> accountMap = new HashMap<Integer, IAccount>();

    private final double yieldByDay = 0.000033;
    private final double changeMonthlyRate = 5.5;

    private MyBank() {
    }

    public static MyBank getInstance() {
        if (instance == null) {
            System.out.println("-----------Banco criado-----------");
            instance = new MyBank();
        }
        return instance;
    }

    public void addAccount(IAccount account) {
        if (accountMap.containsKey(account.idAccount)) {
            System.out.println("!!!--------Falha--------!!!");
            System.out.println("A conta já existe");
            account.printAccount();
        } else {
            accountMap.put(account.idAccount, account);
            System.out.println("Conta "+ account.idAccount + " criada com sucesso!");
        }
    }

    public void listAccounts() {
        System.out.println("-----------Listagem de contas-----------");
        accountMap.forEach((key, iAccount) -> {
            System.out.println("Numero da conta: " + key);
            System.out.println("    name user: " + iAccount.name);
            System.out.println("    balance: " + iAccount.balance + "\n");
        });
    }

    public double getYieldByDay(){
        return yieldByDay;
    }
    public double getChangeMonthlyRate(){
        return changeMonthlyRate;
    }
}

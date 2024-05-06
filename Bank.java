package com.jiahao;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private static Bank instance;
    private List<Account> accounts;
    private int accountCount;

    public Bank() {
        accounts = new ArrayList<>();
        accountCount = 0;
    }

    public static synchronized Bank getInstance(){
        if (instance == null){
            instance = new Bank();
        }
        return instance;
    }

    // 用户开户方法
    public Account register(Long id, String password, String confirmPassword, String name, String personId, String email, int accountType) {
        if (id <= 0 || password.equals(confirmPassword) == false) {
            System.out.println("Invalid input");
            return null;
        }

        Account account;
        if (accountType == 1) { // 储蓄账户
            account = new SavingAccount(id, password, name, personId, email, 0.0);
        } else { // 信用账户
            account = new CreditAccount(id, password, name, personId, email, 0.0, 0.0);
        }
        accounts.add(account);
        accountCount++;
        return account;
    }

    // 用户登录方法
    public Account login(Long id, String password) {
        for (Account account : accounts) {
            if (account.getId() == id && account.getPassword().equals(password)) {
                return account;
            }
        }
        System.out.println("Invalid login");
        return null;
    }

    // 用户存款方法
    public Account deposit(Long id, double amount) {
        Account account = login(id, "password"); // 仅为示例，实际应用中应使用安全的方法来验证密码或其他身份验证方式。
        if (account != null) {
            account.deposit(amount);
            return account;
        } else {
            System.out.println("Account not found");
            return null;
        }
    }

    // 用户取款方法
    public Account withdraw(Long id, double amount) {
        Account account = login(id, "password"); // 仅为示例，实际应用中应使用安全的方法来验证密码或其他身份验证方式。
        if (account != null) {
            account.withdraw(amount);
            return account;
        } else {
            System.out.println("Account not found");
            return null;
        }
    }


    // 设置透支额度方法（仅适用于信用账户）
    public Account setCeiling(Long id, double ceiling) {
        Account account = login(id, "password"); // 仅为示例，实际应用中应使用安全的方法来验证密码或其他身份验证方式。
        if (account != null && account instanceof CreditAccount) { // 确保是信用账户类型
            ((CreditAccount) account).setCeiling(ceiling);
            return account;
        } else {
            System.out.println("Invalid operation");
            return null;
        }
    }

    // 统计银行所有账户余额总数
    public double getTotalBalance() {
        double totalBalance = 0.0;
        for (Account account : accounts) {
            totalBalance += account.getBalance();
        }
        return totalBalance;
    }

    // 统计所有信用账户透支额度总数（由于您没有要求提供透支额度的计算方法，这里假设所有信用账户的透支额度都相同）
    public double getTotalCeiling() {
        double totalCeiling = 0.0;
        for (Account account : accounts) {
            if (account instanceof CreditAccount) { // 仅统计信用账户的透支额度
                totalCeiling += ((CreditAccount) account).getCeiling();
            }
        }
        return totalCeiling;
    }

    // 贷款方法，参数为账户ID和贷款额，返回修改过的Account对象
    public Account loan(String id, double loanAmount) {
        for (Account account : accounts) {
            if (account.getId().equals(id)) {
                if (account instanceof LoanCreditAccount) {
                    ((LoanCreditAccount) account).applyLoan(loanAmount);
                    return account;
                } else {
                    System.out.println("This account does not support loan.");
                    return null;
                }
            }
        }
        System.out.println("Invalid account ID");
        return null;
    }

    // 还贷款方法，参数为账户ID和还款额，返回修改过的Account对象
    public Account repayLoan(String id, double repayAmount) {
        for (Account account : accounts) {
            if (account.getId().equals(id)) {
                if (account instanceof LoanCreditAccount) {
                    ((LoanCreditAccount) account).repayLoan(repayAmount);
                    return account;
                } else {
                    System.out.println("This account does not support loan repayment.");
                    return null;
                }
            }
        }
        System.out.println("Invalid account ID");
        return null;
    }

    // 统计所有账户贷款的总数方法
    public double getTotalLoanAmount() {
        double total = 0.0;
        for (Account account : accounts) {
            if (account instanceof LoanCreditAccount) {
                total += ((LoanCreditAccount) account).getLoan();
            }
        }
        return total;
    }

    //
    public void addAccount(Account account) {
        String sid = account.getIdCard();
        if (!accounts.containsKey(sid)) {
            accounts.put(sid, new ArrayList<>());
        }
        accounts.get(sid).add(account);
    }

    public void printTotalAssetRanking() {
        List<Map.Entry<String, ArrayList<Account>>> sortedAccounts = new ArrayList<>(accounts.entrySet());
        sortedAccounts.sort(Comparator.comparing(e -> e.getValue().stream().mapToDouble(Account::getBalance).sum())); // 根据总资产排序
        int rank = 1;
        for (Map.Entry<String, ArrayList<Account>> entry : sortedAccounts) {
            System.out.println("Rank " + rank + ": " + entry.getKey() + ", Total Asset: " + entry.getValue().stream().mapToDouble(Account::getBalance).sum()); // 打印排名和总资产
            rank++;
        }
    }
}
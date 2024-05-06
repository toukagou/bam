package com.jiahao;

public abstract class LoanCreditAccount extends CreditAccount implements Loanable {
    private double loanAmount; // 贷款额

    // 构造方法，生成新的银行用户账号
    protected LoanCreditAccount(String password) {
        super(password);
        this.loanAmount = 0.0;
    }

    // 存款方法，不允许子类修改
    @Override
    public void deposit(double amount) {
        super.deposit(amount);
    }

    // 抽象的取款方法，允许子类修改实现
    @Override
    public abstract void withdraw(double amount);

    // 贷款方法，允许用户贷款
    public void applyLoan(double loanAmount) {
        if (loanAmount <= 0) {
            System.out.println("Invalid loan amount");
            return;
        }
        this.loanAmount += loanAmount;
        System.out.println("Loan successful. Loan amount: " + loanAmount);
    }

    // 透支方法，允许用户透支
    public void overdraft(double amount) {
        if (amount <= 0 || amount > loanAmount) {
            System.out.println("Invalid overdraft amount");
            return;
        }
        balance -= amount;
        System.out.println("Overdraft successful. New balance: " + balance);
    }

    // 还款方法，允许用户还款
    @Override
    public void repayLoan(double amount) {
        if (amount <= 0 || amount > getBalance()) {
            System.out.println("Invalid repay amount");
            return;
        }
        balance += amount;
        loanAmount -= amount;
        System.out.println("Repayment successful. New balance: " + balance);
    }

    @Override
    public void requestLoan(double loanAmount) {
        this.loanAmount += loanAmount;
        System.out.println("Loan successful. Loan amount: " + loanAmount);
    }

    @Override
    public double getLoan() {
        return loanAmount;
    }
}
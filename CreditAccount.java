package com.jiahao;

import java.util.Objects;
// 信用账户类
public class CreditAccount extends Account {
    private double ceiling; // 透支额度

    // 无参构造方法
    public CreditAccount(String password) {
        super();
    }

    // 有参构造方法
    public CreditAccount(long id, String password, String name, String personId, String email, double balance, double ceiling) {
        super(id, password, name, personId, email, balance);
        this.ceiling = ceiling;
    }

    // 设置透支额度的方法
    public void setCeiling(double ceiling) {
        this.ceiling = ceiling;
    }

    // 获取透支额度的方法
    public double getCeiling() {
        return ceiling;
    }

    // 重写父类的 withdraw 方法，允许透支，但不超过透支额度
    @Override
    public void withdraw(double amount) {
        if (balance >= amount || amount <= ceiling) {
            balance -= amount;
        } else {
            System.out.println("Insufficient funds");
        }
    }

    // 改写方法
    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) &&
                Objects.equals(name, account.name) &&
                Objects.equals(balance, account.balance);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, name, balance);
    }
}
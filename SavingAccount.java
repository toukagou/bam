package com.jiahao;

import java.util.Objects;
// 储蓄账户类
public class SavingAccount extends Account {
    // 无参构造方法
    public SavingAccount(String password) {
        super();
    }

    // 有参构造方法
    public SavingAccount(long id, String password, String name, String personId, String email, double balance) {
        super(id, password, name, personId, email, balance);
    }

    // 重写父类的 withdraw 方法，不允许透支
    @Override
    public void withdraw(double amount) {
        if (balance >= amount) {
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

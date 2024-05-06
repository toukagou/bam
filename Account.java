package com.jiahao;

import java.util.Objects;

public abstract class Account {
    public static long sid = 100000;
    public Long id;
    public String password;
    public String name;
    public String personId;
    public String email;
    public double balance;

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

    // 无参构造方法
    public Account() {
    }

    // 有参构造方法
    public Account(long id, String password, String name, String personId, String email, double balance) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.personId = personId;
        this.email = email;
        this.balance = balance;
    }

    // 构造方法，生成新的银行用户账号
    protected Account(String password) {
        this.id = sid++; // 自动生成银行用户账号，初始值为100000
        this.password = password;
        this.balance = 0.0;
    }

    // 存款方法
    public void deposit(double amount) {
        if (amount <= 0){
            System.out.println("存款金额不能为0或小于0");
            return;
        }
        balance += amount;
        System.out.println("存款成功！余额为：" + balance);
    }

    // 取款方法
//    public void withdraw(double amount) {
//        if (balance >= amount) {
//            balance -= amount;
//        } else {
//            System.out.println("Insufficient funds");
//        }
//    }
    // 抽象取款方法，允许子类修改
    public abstract void withdraw(double amount);

    // Getter 和 Setter 方法（只提供必要的）
    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
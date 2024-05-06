package com.jiahao;

public interface Loanable {
    // 贷款方法
    void requestLoan(double loanAmount);

    // 还款方法
    void repayLoan(double amount);

    // 获取用户贷款总额
    double getLoan();
}
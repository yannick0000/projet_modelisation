package com.example;

import java.util.*;

class Payroll implements PayrollInterface {
    @Override
    public void printPay(List<PayInfo> payInfos) {
        for (PayInfo payInfo : payInfos) {
            System.out.println("Pay Info: " + payInfo);
        }
    }

    @Override
    public double netFromBrute(double bruteSalary) {
        return bruteSalary * 0.6;
    }

    @Override
    public String DeductionsReport() {
        return "Deductions Report";
    }
}

package com.example;

import java.util.*;
interface PayrollInterface {
    void printPay(List<PayInfo> payInfos);
    double netFromBrute(double bruteSalary);
    String DeductionsReport();
}
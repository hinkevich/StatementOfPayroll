package by.bsu.statementofpayroll;

import static java.lang.Math.round;

public class PayrollAction {

    //константы на 2019год
    final static int PERCENT_INCOME_TAX = 13;
    final static double INCOME = 665.;
    final static double DEDUCTION_FOR_EMPLOYEE = 110.;
    final static double DEDUCTION_FOR_ONE_CHILDREN = 32.;
    final static double DEDUCTION_FOR_TWO_AND_MORE_CHILDREN = 61.;
    final static int PERCENT_PENSION_FOUND = 1;
    final static int PERCENT_FSZN = 35;
    final static double PERCENT_BELGOSSTRAH = 0.6;
//начислено по окладу
    public static double accruedBySalary(double staffList, int daysPerMonth, double fulfilledDays) {
        double salaryByStaffList = (staffList / daysPerMonth) * fulfilledDays;
        salaryByStaffList=convertMoney(salaryByStaffList);
        return salaryByStaffList;
    }
//всего начислено
    public static double totalCharged(double salaryByStaffList, double bonus, double vocationPay,
                                      double sickList) {
        double totalChargedSalary = salaryByStaffList + bonus + vocationPay + sickList;
        totalChargedSalary=convertMoney(totalChargedSalary);
        return totalChargedSalary;
    }
//подоходный налог
    public static double incomeTax(double totalChargedSalary, int fullTime, int quantityChildren) {
        double incomeTax;
        if ((fullTime == 1) && (totalChargedSalary < INCOME) && (quantityChildren == 0)) {
            incomeTax = ((totalChargedSalary - DEDUCTION_FOR_EMPLOYEE) * PERCENT_INCOME_TAX) / 100;
        } else if ((fullTime == 1) && (totalChargedSalary < INCOME) && (quantityChildren == 1)) {
            incomeTax = ((totalChargedSalary - DEDUCTION_FOR_EMPLOYEE - DEDUCTION_FOR_ONE_CHILDREN)
                    * PERCENT_INCOME_TAX) / 100;
        } else if ((fullTime == 1) && (totalChargedSalary < INCOME) && (quantityChildren >1)) {
            incomeTax = (((totalChargedSalary - DEDUCTION_FOR_EMPLOYEE - (quantityChildren * DEDUCTION_FOR_TWO_AND_MORE_CHILDREN))
                    * PERCENT_INCOME_TAX) / 100);
        } else if ((fullTime == 1) && (totalChargedSalary > INCOME) && (quantityChildren == 0)) {
            incomeTax = (totalChargedSalary * PERCENT_INCOME_TAX) / 100;
        } else if ((fullTime == 1) && (totalChargedSalary > INCOME) && (quantityChildren == 1)) {
            incomeTax = ((totalChargedSalary - DEDUCTION_FOR_ONE_CHILDREN) * PERCENT_INCOME_TAX) / 100;
        } else if ((fullTime == 1) && (totalChargedSalary > INCOME) && (quantityChildren > 1)) {
            incomeTax = (((totalChargedSalary - (quantityChildren * DEDUCTION_FOR_TWO_AND_MORE_CHILDREN))
                    * PERCENT_INCOME_TAX) / 100);
        } else {
            incomeTax = (totalChargedSalary * PERCENT_INCOME_TAX) / 100;
        }
        if (incomeTax<0){incomeTax=0;}
        else{
        incomeTax= convertMoney(incomeTax);}
        return incomeTax;
    }
//пенсионный фонд
    public static double pensionFund(double totalChargedSalary, double sickList) {
        double pensionFund = (totalChargedSalary - sickList) * PERCENT_PENSION_FOUND/100;
        pensionFund= convertMoney(pensionFund);
        return pensionFund;
    }
//всего удержано
    public static double totaWithheld(double incomeTax,double pensionFund){
        double totaWithheld=incomeTax+pensionFund;
        totaWithheld=convertMoney(totaWithheld);
        return totaWithheld;
    }
//к выдаче
    public static double toIssuePayroll(double totalChargedSalary,double totaWithheld){
        double toIssuePayroll=totalChargedSalary-totaWithheld;
        toIssuePayroll=convertMoney(toIssuePayroll);
        return toIssuePayroll;
    }
    public static double convertMoney (double money){
        money=round(money*100);
        money=money/100;
        return  money;
    }

    public static String transferredToBudget(double summaTotalCharge, double summaIncomeTax) {
        String transferredToBudget="Подлежит перечислению в бюджет: ";
        String fsznStr="ФСЗН - ";
        double fszn= convertMoney((summaTotalCharge*PERCENT_FSZN)/100);
        String bgsStr="Белгосстрах - ";
        double bds= convertMoney((summaTotalCharge*PERCENT_BELGOSSTRAH)/100);
        String podohodnyStr = "Подоходный налог - ";
        double podohodny = summaIncomeTax;
        transferredToBudget=transferredToBudget+"\n"+fsznStr+fszn+"\n"+bgsStr+bds+"\n"+
                podohodnyStr+podohodny;
        return transferredToBudget;


    }
}


package ArrayDemo;

public class bankvalue {
    public static void main(String[] args) {
        // Initial Balance
        int initialBalance = 25000;
        
        // Amount deposited by Amar
        int depositAmount = 15000;
        
        // Amount transferred to Surya
        int transferAmount = 11000;
        
        // Interest rate and time period (in years)
        int interestRate = 2; // 2% interest rate
        int timePeriod = 1;     // 12 months = 1 year
        
        // Calculate current balance
        double currentBalance = initialBalance + depositAmount - transferAmount;
        
        // Calculate interest on current balance
        double interest = (currentBalance * interestRate * timePeriod) / 100;
        
        // Output results
        System.out.println("Current Balance: ₹" + currentBalance);
        System.out.println("Interest for 12 months at 2%: ₹" + interest);
    }
}

package exception;

class CountryNotValidException extends Exception {
    public CountryNotValidException(String message) {
        super(message);
    }
}

class EmployeeNameInvalidException extends Exception {
    public EmployeeNameInvalidException(String message) {
        super(message);
    }
}

class TaxNotEligibleException extends Exception {
    public TaxNotEligibleException(String message) {
        super(message);
    }
}

class TaxCalculator {
    public double calculateTax(String empName, boolean isIndian, double empSal) throws CountryNotValidException, EmployeeNameInvalidException, TaxNotEligibleException {
        if (!isIndian) {
            throw new CountryNotValidException("The employee should be an Indian citizen for calculating tax.");
        }
        if (empName == null || empName.isEmpty()) {
            throw new EmployeeNameInvalidException("The employee name cannot be empty.");
        }
        if (empSal > 100000) {
            return empSal * 0.08;
        } else if (empSal >= 50000 && empSal <= 100000) {
            return empSal * 0.06;
        } else if (empSal >= 30000 && empSal < 50000) {
            return empSal * 0.05;
        } else if (empSal >= 10000 && empSal < 30000) {
            return empSal * 0.04;
        } else {
            throw new TaxNotEligibleException("The employee does not need to pay tax.");
        }
    }
}

public class WithdrawAmount {
    public static void main(String[] args) {
        TaxCalculator taxCalculator = new TaxCalculator();
        try {
            System.out.println("Tax amount is " + taxCalculator.calculateTax("Rounak", false, 34000));
        } catch (CountryNotValidException | EmployeeNameInvalidException | TaxNotEligibleException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        try {
            System.out.println("Tax amount is " + taxCalculator.calculateTax("Akash", true, 1000));
        } catch (CountryNotValidException | EmployeeNameInvalidException | TaxNotEligibleException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        try {
            System.out.println("Tax amount is " + taxCalculator.calculateTax("Adarsh", true, 55000));
        } catch (CountryNotValidException | EmployeeNameInvalidException | TaxNotEligibleException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        try {
            System.out.println("Tax amount is " + taxCalculator.calculateTax("Ashish", true, 30000));
        } catch (CountryNotValidException | EmployeeNameInvalidException | TaxNotEligibleException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}


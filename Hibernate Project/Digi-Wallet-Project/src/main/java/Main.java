import DAO.WalletDAO;

import DAOImpl.TransactionDAOImpl;
import DAOImpl.UserDAOImpl;
import DAOImpl.WalletDAOImpl;
import DAO.TransactionDAO;
import Service.ReportService;
import beans.*;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Setting up Hibernate SessionFactory
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session s = sf.openSession();

        try {
            UserDAOImpl userDAO = new UserDAOImpl();
            boolean running = true;

            User user = null;
            while (running) {
                System.out.println("1. Log In");
                System.out.println("2. Register");
                System.out.println("3. Exit");

                System.out.print("Welcome to your Digital Wallet! Please choose an option (1,2 or 3): ");
                int choice = sc.nextInt();
                sc.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1:
                        // Login
                        boolean loggedIn = false;

                        while (!loggedIn) {
                            System.out.print("Enter Username: ");
                            String username = sc.nextLine();
                            System.out.print("Enter Password: ");
                            String password = sc.nextLine();

                            user = userDAO.findUserByUsername(username);
                            if (user != null && user.getPassword().equals(password)) {
                                loggedIn = true;
                                System.out.println("Login successful!\n");

                                // After Login: Menu for choosing an action
                                System.out.println("1. Check Your Wallet Balance");
                                System.out.println("2. Perform a Transaction");
                                System.out.println("3. Display Last Transactions");
                                System.out.println("4. Exit/Logout");
                                System.out.println("5. View Spending Summary");
                                System.out.println("6. Filter Transactions by Type");
                                System.out.println("7. Enter your New password: ");                                        
                                System.out.println("8. Display spending by category: "); 
                                System.out.println("9. Delete Your Account");




                                System.out.print("What would you like to do? Choose from the above menu: ");
                                int action = sc.nextInt();
                                sc.nextLine();

                                switch (action) {
                                    case 1:
                                        // Checking Wallet Information
                                        WalletDAO walletInfo = new WalletDAOImpl();
                                        Wallet userWallet = walletInfo.findWalletByUserId(user.getId());
                                        if (userWallet != null) {
                                            System.out.println("Wallet Balance: " + userWallet.getBalance());
                                            System.out.println("Wallet ID: " + userWallet.getId());
                                        } else {
                                            System.out.println("No wallet information found for the user.");
                                        }
                                        break;

                                    case 2:
                                        // Transaction process
                                        Transaction tx = s.beginTransaction();
                                        Category category = new Category();

                                        // Create and save Category
                                        System.out.print("Enter Category of Expense: ");
                                        String c = sc.nextLine();
                                        category.setName(c);
                                        s.save(category);

                                        Transactions transaction = new Transactions();
                                        System.out.print("Enter Transaction Amount: ");
                                        double a = sc.nextDouble();
                                        sc.nextLine(); // Consume the newline character
                                        transaction.setAmount(a);
                                        transaction.setDate(new java.util.Date()); // Set to current date/time
                                        transaction.setDescription(c); // Transaction Description

                                        System.out.print("Receiver's Name: ");
                                        String madeTo = sc.nextLine();
                                        transaction.setMadeTo(madeTo);

                                        System.out.print("Is this a Credit or Debit?: ");
                                        String t = sc.nextLine();
                                        transaction.setType(t);

                                        // Fetch wallet from DB and initialize transactions collection
                                        Wallet walletFromDB = s.get(Wallet.class, user.getWallet().getId());
                                        Hibernate.initialize(walletFromDB.getTransactions());

                                        // Handling Credit/Debit transaction
                                     // Handling Credit/Debit transaction
                                        if (t.equalsIgnoreCase("credit")) {
                                            walletFromDB.setBalance(walletFromDB.getBalance() + a); // Add amount to wallet
                                        } else if (t.equalsIgnoreCase("debit")) {
                                            // Fetch spending limit
                                            SpendingLimit limit = s.createQuery("FROM SpendingLimit WHERE wallet.id = :walletId AND (category.id = :categoryId OR category IS NULL)", SpendingLimit.class)
                                                                   .setParameter("walletId", walletFromDB.getId())
                                                                   .setParameter("categoryId", category.getId())
                                                                   .uniqueResult();

                                            // Check if the transaction exceeds the spending limit
                                            if (limit != null && a > limit.getLimitAmount()) {
                                                System.out.println("Transaction denied! Spending limit exceeded.");
                                                tx.rollback();
                                                return;
                                            }

                                            // Check if sufficient balance is available
                                            if (walletFromDB.getBalance() >= a) {
                                                walletFromDB.setBalance(walletFromDB.getBalance() - a); // Subtract amount from wallet
                                            } else {
                                                System.out.println("Insufficient balance for this transaction.");
                                                tx.rollback();
                                                return; // Exit if not enough balance
                                            }
                                        }


                                        // Set transaction details and save
                                        transaction.setCategory(category);
                                        transaction.setWallet(walletFromDB);
                                        s.save(transaction);
                                        s.update(walletFromDB);
                                        tx.commit();
                                        
                                        

                                        System.out.println("Transaction saved successfully!");
                                        ReportService rs = new ReportService();
                                        rs.generateReport(user.getId());
                                        break;

                                    case 3:
                                        // Displaying Last Transactions
                                        TransactionDAO transactionDAO = new TransactionDAOImpl();
                                        List<Transactions> lastTransactions = transactionDAO.getTransactionByWallet(user.getWallet().getId());

                                        if (lastTransactions == null || lastTransactions.isEmpty()) {
                                            System.out.println("No transactions found for this wallet.");
                                        } else {
                                            System.out.println("Last Transactions:");
                                            for (Transactions tx1 : lastTransactions) {
                                                System.out.println(tx1);
                                            }
                                        }
                                        break;

                                    case 4:
                                        System.out.println("Exiting...");
                                        running = false;
                                        break;
                                        
                                    case 5:
                                        // View Spending Summary
                                        System.out.print("Enter start date (yyyy-MM-dd): ");
                                        String startDateInput = sc.nextLine();
                                        System.out.print("Enter end date (yyyy-MM-dd): ");
                                        String endDateInput = sc.nextLine();
                                        
                                        try {
                                            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
                                            java.util.Date startDate = sdf.parse(startDateInput);
                                            java.util.Date endDate = sdf.parse(endDateInput);

                                            List<Object[]> summary = s.createQuery(
                                                "SELECT t.category.name, SUM(t.amount) FROM Transactions t " +
                                                "WHERE t.wallet.id = :walletId AND t.date BETWEEN :startDate AND :endDate " +
                                                "GROUP BY t.category.name", Object[].class)
                                                .setParameter("walletId", user.getWallet().getId())
                                                .setParameter("startDate", startDate)
                                                .setParameter("endDate", endDate)
                                                .list();

                                            System.out.println("Spending Summary:");
                                            for (Object[] row : summary) {
                                                System.out.println("Category: " + row[0] + ", Total Spent: " + row[1]);
                                            }
                                        } catch (Exception e) {
                                            System.out.println("Invalid date format. Please try again.");
                                        }
                                        break;
                                        
                                    case 6:
                                        // Filter Transactions by Type
                                        System.out.print("Enter transaction type (credit/debit): ");
                                        String filterType = sc.nextLine();

                                        if (!filterType.equalsIgnoreCase("credit") && !filterType.equalsIgnoreCase("debit")) {
                                            System.out.println("Invalid transaction type! Please enter 'credit' or 'debit'.");
                                        } else {
                                            List<Transactions> filteredTransactions = s.createQuery(
                                                "FROM Transactions WHERE wallet.id = :walletId AND type = :type", Transactions.class)
                                                .setParameter("walletId", user.getWallet().getId())
                                                .setParameter("type", filterType)
                                                .list();

                                            if (filteredTransactions.isEmpty()) {
                                                System.out.println("No transactions found for the selected type.");
                                            } else {
                                                System.out.println("Filtered Transactions:");
                                                for (Transactions tx1 : filteredTransactions) {
                                                    System.out.println(tx1);
                                                }
                                            }
                                        }
                                        break;
                                        
                                    case 7:
                                        // Change Password
                                        String currentPassword = sc.nextLine();
                                        if (user.getPassword().equals(currentPassword)) {
                                            System.out.print("Enter new password: ");
                                            String newPassword = sc.nextLine();
                                            userDAO.updateUserPassword(user.getId(), newPassword);
                                            System.out.println("Password updated successfully!");
                                        } else {
                                            System.out.println("Incorrect current password.");
                                        }
                                        break;
                                        
                                    case 8:
                                        // Display a breakdown of spending by category
                                        List<Object[]> categorySpendingSummary = s.createQuery(
                                            "SELECT t.category.name, SUM(t.amount) FROM Transactions t " +
                                            "WHERE t.wallet.id = :walletId " +
                                            "GROUP BY t.category.name", Object[].class)
                                            .setParameter("walletId", user.getWallet().getId())
                                            .list();

                                        if (categorySpendingSummary.isEmpty()) {
                                            System.out.println("No transactions found for this wallet.");
                                        } else {
                                            System.out.println("Category-wise Spending Overview:");
                                            for (Object[] row : categorySpendingSummary) {
                                                System.out.println("Category: " + row[0] + ", Total Spent: " + row[1]);
                                            }
                                        }
                                        break;

                                    case 9:
                                        // Delete User Account
                                        System.out.print("Are you sure you want to delete your account? This action is irreversible. (yes/no): ");
                                        String confirmDelete = sc.nextLine();

                                        if (confirmDelete.equalsIgnoreCase("yes")) {
                                            Transaction txDelete = s.beginTransaction();
                                            
                                            try {
                                                // Get the user's wallet
                                                Wallet Wallet = user.getWallet();

                                                // Remove associated transactions
                                                Query transactionQuery = s.createQuery("DELETE FROM Transactions WHERE wallet.id = :walletId");
                                                transactionQuery.setParameter("walletId", Wallet.getId());
                                                transactionQuery.executeUpdate();

                                                // Remove associated spending limits
                                                Query spendingLimitQuery = s.createQuery("DELETE FROM SpendingLimit WHERE wallet.id = :walletId");
                                                spendingLimitQuery.setParameter("walletId", Wallet.getId());
                                                spendingLimitQuery.executeUpdate();

                                                // Delete wallet
                                                s.delete(Wallet);
                                                
                                                // Delete user
                                                s.delete(user);

                                                txDelete.commit();
                                                System.out.println("Account deleted successfully!");

                                                // Logout the user
                                                running = false; // This will exit the login loop and end the program

                                            } catch (Exception e) {
                                                System.out.println("Error deleting account. Please try again.");
                                                e.printStackTrace();
                                                txDelete.rollback();
                                            }
                                        } else {
                                            System.out.println("Account deletion cancelled.");
                                        }
                                        break;

                                    
                                        
                                        

                                        


                                    default:
                                        System.out.println("Invalid action. Please try again.");
                                }
                            } else {
                                System.out.println("Invalid credentials. Please try again.");
                                System.out.print("Would you like to try again or go back to the main menu? (try/back): ");
                                String choiceAgain = sc.nextLine();
                                if (choiceAgain.equalsIgnoreCase("back")) {
                                    break;
                                }
                            }
                        }
                        break;

                    case 2:
                        // Register new user
                        Transaction tx2 = s.beginTransaction();
                        System.out.print("Enter Username: ");
                        String newUsername = sc.nextLine();
                        System.out.print("Enter Password: ");
                        String newPassword = sc.nextLine();
                        System.out.print("Enter Email: ");
                        String newEmail = sc.nextLine();

                        // Register user
                        int userId = userDAO.register(newUsername, newPassword, newEmail);
                        System.out.println("Registration successful! Your unique ID is: " + userId);

                        // Create Wallet and Spending Limit for the new user
                        Wallet wallet = new Wallet();
                        System.out.print("Enter Wallet Balance: ");
                        double initialBalance = sc.nextDouble();
                        sc.nextLine(); // Consume the newline character

                        if (initialBalance <= 0) {
                            System.out.println("Invalid balance! Setting balance to 0.0");
                            initialBalance = 0.0; // Set to default value if invalid
                        }

                        wallet.setBalance(initialBalance);
                        wallet.setUser(userDAO.findUserById(userId)); // Link wallet to user

                        // Save wallet and spending limit
                        s.save(wallet);

                        SpendingLimit spendingLimit = new SpendingLimit();
                        System.out.print("Enter Spending Limit: ");
                        double limitAmount = sc.nextDouble();
                        sc.nextLine(); // Consume the newline character
                        spendingLimit.setLimitAmount(limitAmount);
                        spendingLimit.setWallet(wallet);
                        s.save(spendingLimit);

                        System.out.println("Spending Limit saved successfully!");

                        // Update the user with wallet
                        User registeredUser = userDAO.findUserById(userId);
                        registeredUser.setWallet(wallet); // Link wallet to the user
                        s.update(registeredUser);

                        tx2.commit();
                        System.out.println("User, Wallet, and Spending Limit saved successfully!");
                        break;

                    case 3:
                        // Exit the application
                        System.out.println("Exiting...");
                        running = false;
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            s.close();
            sf.close();
        }
    }
}

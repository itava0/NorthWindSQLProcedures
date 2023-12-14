package org.pluralsight;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.SQLException;
import java.util.Scanner;


public class App {
    public static void main(String[] args) {
        // Set up the DataSource for database connection
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/northwind");
        dataSource.setUsername(System.getenv("MY_DB_USERNAME"));
        dataSource.setPassword(System.getenv("MY_DB_PASSWORD"));
        NorthwindDataManager dataManager = new NorthwindDataManager(dataSource);

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("What do you want to do?");
            System.out.println("1) Search for customer order history by ID");
            System.out.println("2) Search for sales by year");
            System.out.println("3) Search for sales by category");
            System.out.println("0) Exit program");
            int userOption = scanner.nextInt();
            scanner.nextLine();
            switch (userOption) {
                case 1: {
                    System.out.println("Enter the customer ID: ");
                    String id = scanner.nextLine();
                    dataManager.getCustomerOrderHist(id);
                    break;
                }
                case 2: {
                    System.out.println("Enter start date: ex. 2023-12-01 : ");
                    String startDate = scanner.nextLine();
                    System.out.println("Enter end date: ex. 2023-12-14 : ");
                    String endDate = scanner.nextLine();
                    dataManager.getSalesByYear(startDate,endDate);
                    break;
                }
                case 3: {
                    System.out.println("Enter the category: ");
                    String category = scanner.nextLine();
                    System.out.println("Enter the year: ");
                    String year = scanner.nextLine();
                    dataManager.salesByCategory(category,year);
                    break;
                }
                case 0:
                    scanner.close();
                    try {
                        dataSource.close();
                    } catch (SQLException e) {
                        System.out.println("Error occurred while closing the dataSource");
                    }
                    System.out.println("Exiting program...");
                    System.exit(0);
                default:
                    System.out.println("ERROR: please choose a valid input");
                    break;
            }
        }
    }
}

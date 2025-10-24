
package com.mycompany.logistics_management_system01;

import java.util.Scanner;


public class Logistics_Management_System01 {
    
    static String[] cities = new String[30];
    static int[][] distances = new int[30][30];
    static int cityCount = 0;
    static final int FUEL_PRICE = 310;

    public static void main(String[] args) {
       displayMainMenu();
    }
    
   
    public static void displayMainMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        
        do {
            System.out.println("\n=== LOGISTICS MANAGEMENT SYSTEM ===");
            System.out.println("1. City Management");
            System.out.println("2. Distance Management");
            System.out.println("3. Vehicle Information");
            System.out.println("4. Process Delivery");
            System.out.println("5. Find Least-Cost Route");
            System.out.println("6. Performance Reports");
            System.out.println("7. Exit");
            System.out.print("Enter your choice (1-7): ");
            
            choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    cityManagement();
                    break;
                case 2:
                    distanceManagement();
                    break;
                case 3:
                    showVehicleInfo();
                    break;
                case 4:
                    processDelivery();
                    break;
                case 5:
                    findLeastCostRoute();
                    break;
                case 6:
                    showReports();
                    break;
                case 7:
                    System.out.println("Thank you for using Logistics Management System!");
                    break;
                default:
                    System.out.println("Invalid choice! Please enter 1-7.");
            }
        } while (choice != 7);
        
        scanner.close();
    }

    public static void cityManagement() {
    }

    public static void distanceManagement() {
    }

    public static void showVehicleInfo() {
    }

    public static void processDelivery() {
    }

    public static void findLeastCostRoute() {
    }

    public static void showReports() {
    }

    
}

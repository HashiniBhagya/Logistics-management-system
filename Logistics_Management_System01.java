
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
        
        Scanner scanner = new Scanner(System.in);
        int choice;
        
        do {
            System.out.println("\n=== CITY MANAGEMENT ===");
            System.out.println("1. Add New City");
            System.out.println("2. View All Cities");
            System.out.println("3. Rename City");
            System.out.println("4. Remove City");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");
            
            choice = scanner.nextInt();
            scanner.nextLine(); 
            
            switch (choice) {
                case 1:
                    addCity(scanner);
                    break;
                case 2:
                    viewCities();
                    break;
                case 3:
                    renameCity(scanner);
                    break;
                case 4:
                    removeCity(scanner);
                    break;
                case 5:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 5);

    }
    public static void addCity(Scanner scanner) {
        if (cityCount >= 30) {
            System.out.println("Cannot add more cities! Maximum limit reached.");
            return;
        }
        
        System.out.print("Enter city name: ");
        String cityName = scanner.nextLine();
        
        for (int i = 0; i < cityCount; i++) {
            if (cities[i].equalsIgnoreCase(cityName)) {
                System.out.println("City already exists!");
                return;
            }
        }
        
        cities[cityCount] = cityName;
        cityCount++;
        System.out.println("City '" + cityName + "' added successfully!");
    }
    

    public static void viewCities() {
        if (cityCount == 0) {
            System.out.println("No cities available!");
            return;
        }
        
        System.out.println("\n=== LIST OF CITIES ===");
        for (int i = 0; i < cityCount; i++) {
            System.out.println((i + 1) + ". " + cities[i]);
        }
    }
    

    public static void renameCity(Scanner scanner) {
        viewCities();
        if (cityCount == 0) return;
        
        System.out.print("Enter city number to rename: ");
        int cityNum = scanner.nextInt();
        scanner.nextLine(); 
        
        if (cityNum < 1 || cityNum > cityCount) {
            System.out.println("Invalid city number!");
            return;
        }
        
        System.out.print("Enter new name: ");
        String newName = scanner.nextLine();
        
        String oldName = cities[cityNum - 1];
        cities[cityNum - 1] = newName;
        System.out.println("City '" + oldName + "' renamed to '" + newName + "'");

    }

    public static void removeCity(Scanner scanner) {
        viewCities();
        if (cityCount == 0) return;
        
        System.out.print("Enter city number to remove: ");
        int cityNum = scanner.nextInt();
        
        if (cityNum < 1 || cityNum > cityCount) {
            System.out.println("Invalid city number!");
            return;
        }
        
        String removedCity = cities[cityNum - 1];
        
        for (int i = cityNum - 1; i < cityCount - 1; i++) {
            cities[i] = cities[i + 1];
        }
        cityCount--;
        
        System.out.println("City '" + removedCity + "' removed successfully!");
    }

    public static void distanceManagement() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        
        do {
            System.out.println("\n=== DISTANCE MANAGEMENT ===");
            System.out.println("1. Add/Edit Distance");
            System.out.println("2. View Distance Table");
            System.out.println("3. Back to Main Menu");
            System.out.print("Enter your choice: ");
            
            choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    addEditDistance(scanner);
                    break;
                case 2:
                    viewDistanceTable();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 3);
    }
    public static void addEditDistance(Scanner scanner) {
      
 }
    public static void viewDistanceTable() {
      
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

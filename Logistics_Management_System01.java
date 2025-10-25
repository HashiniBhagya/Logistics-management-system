package com.mycompany.logistics_management_system01;

import java.util.Scanner;
import java.io.*;

public class Logistics_Management_System01 {
    
    static String[] cities = new String[30];
    static int[][] distances = new int[30][30];
    static int cityCount = 0;
    static final int FUEL_PRICE = 310;
    
    static String[] vehicleTypes = {"Van", "Truck", "Lorry"};
    static int[] capacities = {1000, 5000, 10000};
    static int[] ratesPerKm = {30, 40, 80};
    static int[] avgSpeeds = {60, 50, 45};
    static int[] fuelEfficiency = {12, 6, 4};
    
    static DeliveryRecord[] deliveries = new DeliveryRecord[50];
    static int deliveryCount = 0;
    
    // File names for data persistence
    static final String CITIES_FILE = "cities.txt";
    static final String DISTANCES_FILE = "distances.txt";
    static final String DELIVERIES_FILE = "deliveries.txt";
    
    static class DeliveryRecord {
        int sourceCity;
        int destCity;
        double weight;
        int vehicleType;
        double distance;
        double customerCharge;
        double deliveryTime;
        
        DeliveryRecord(int source, int dest, double weight, int vehicle, double dist, double charge, double time) {
            this.sourceCity = source;
            this.destCity = dest;
            this.weight = weight;
            this.vehicleType = vehicle;
            this.distance = dist;
            this.customerCharge = charge;
            this.deliveryTime = time;
        }
    }

    public static void main(String[] args) {
        loadDataFromFiles();
        displayMainMenu();
        saveDataToFiles();
    }
    
    public static void loadDataFromFiles() {
        System.out.println("Loading data from files...");
        loadCitiesFromFile();
        loadDistancesFromFile();
        loadDeliveriesFromFile();
        System.out.println("Data loaded successfully!");
    }
    
    public static void saveDataToFiles() {
        System.out.println("Saving data to files...");
        saveCitiesToFile();
        saveDistancesToFile();
        saveDeliveriesToFile();
        System.out.println("Data saved successfully!");
    }
    
    public static void loadCitiesFromFile() {
        try {
            File file = new File(CITIES_FILE);
            if (!file.exists()) {
                System.out.println("Cities file not found. Starting with empty cities.");
                return;
            }
            
            Scanner fileScanner = new Scanner(file);
            cityCount = 0;
            
            while (fileScanner.hasNextLine() && cityCount < 30) {
                String cityName = fileScanner.nextLine().trim();
                if (!cityName.isEmpty()) {
                    cities[cityCount] = cityName;
                    cityCount++;
                }
            }
            
            fileScanner.close();
            System.out.println("Loaded " + cityCount + " cities from file.");
            
        } catch (FileNotFoundException e) {
            System.out.println("Error loading cities file: " + e.getMessage());
        }
    }
    
    public static void loadDistancesFromFile() {
        try {
            File file = new File(DISTANCES_FILE);
            if (!file.exists()) {
                System.out.println("Distances file not found. Starting with empty distances.");
                return;
            }
            
            Scanner fileScanner = new Scanner(file);
            int row = 0;
            
            while (fileScanner.hasNextLine() && row < 30) {
                String line = fileScanner.nextLine();
                String[] distanceValues = line.split(",");
                
                for (int col = 0; col < distanceValues.length && col < 30; col++) {
                    try {
                        distances[row][col] = Integer.parseInt(distanceValues[col].trim());
                    } catch (NumberFormatException e) {
                        distances[row][col] = 0;
                    }
                }
                row++;
            }
            
            fileScanner.close();
            System.out.println("Loaded distance matrix from file.");
            
        } catch (FileNotFoundException e) {
            System.out.println("Error loading distances file: " + e.getMessage());
        }
    }
    
    public static void loadDeliveriesFromFile() {
        try {
            File file = new File(DELIVERIES_FILE);
            if (!file.exists()) {
                System.out.println("Deliveries file not found. Starting with empty delivery history.");
                return;
            }
            
            Scanner fileScanner = new Scanner(file);
            deliveryCount = 0;
            
            while (fileScanner.hasNextLine() && deliveryCount < 50) {
                String line = fileScanner.nextLine();
                String[] deliveryData = line.split(",");
                
                if (deliveryData.length >= 7) {
                    try {
                        int source = Integer.parseInt(deliveryData[0].trim());
                        int dest = Integer.parseInt(deliveryData[1].trim());
                        double weight = Double.parseDouble(deliveryData[2].trim());
                        int vehicleType = Integer.parseInt(deliveryData[3].trim());
                        double distance = Double.parseDouble(deliveryData[4].trim());
                        double charge = Double.parseDouble(deliveryData[5].trim());
                        double time = Double.parseDouble(deliveryData[6].trim());
                        
                        deliveries[deliveryCount] = new DeliveryRecord(source, dest, weight, vehicleType, distance, charge, time);
                        deliveryCount++;
                    } catch (NumberFormatException e) {
                        System.out.println("Error parsing delivery data: " + e.getMessage());
                    }
                }
            }
            
            fileScanner.close();
            System.out.println("Loaded " + deliveryCount + " deliveries from file.");
            
        } catch (FileNotFoundException e) {
            System.out.println("Error loading deliveries file: " + e.getMessage());
        }
    }
    
    public static void saveCitiesToFile() {
        try {
            PrintWriter writer = new PrintWriter(CITIES_FILE);
            
            for (int i = 0; i < cityCount; i++) {
                writer.println(cities[i]);
            }
            
            writer.close();
            
        } catch (FileNotFoundException e) {
            System.out.println("Error saving cities to file: " + e.getMessage());
        }
    }
    
    public static void saveDistancesToFile() {
        try {
            PrintWriter writer = new PrintWriter(DISTANCES_FILE);
            
            for (int i = 0; i < cityCount; i++) {
                for (int j = 0; j < cityCount; j++) {
                    writer.print(distances[i][j]);
                    if (j < cityCount - 1) {
                        writer.print(",");
                    }
                }
                writer.println();
            }
            
            writer.close();
            
        } catch (FileNotFoundException e) {
            System.out.println("Error saving distances to file: " + e.getMessage());
        }
    }
    
    public static void saveDeliveriesToFile() {
        try {
            PrintWriter writer = new PrintWriter(DELIVERIES_FILE);
            
            for (int i = 0; i < deliveryCount; i++) {
                DeliveryRecord delivery = deliveries[i];
                writer.printf("%d,%d,%.2f,%d,%.2f,%.2f,%.2f\n",
                    delivery.sourceCity,
                    delivery.destCity,
                    delivery.weight,
                    delivery.vehicleType,
                    delivery.distance,
                    delivery.customerCharge,
                    delivery.deliveryTime
                );
            }
            
            writer.close();
            
        } catch (FileNotFoundException e) {
            System.out.println("Error saving deliveries to file: " + e.getMessage());
        }
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
        
        saveCitiesToFile();
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

        saveCitiesToFile();
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
        
        saveCitiesToFile();
        saveDistancesToFile();
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
        viewCities();
        if (cityCount < 2) {
            System.out.println("Need at least 2 cities to manage distances!");
            return;
        }
        
        System.out.print("Enter first city number: ");
        int city1 = scanner.nextInt();
        System.out.print("Enter second city number: ");
        int city2 = scanner.nextInt();
        
        if (city1 < 1 || city1 > cityCount || city2 < 1 || city2 > cityCount) {
            System.out.println("Invalid city numbers!");
            return;
        }
        
        if (city1 == city2) {
            System.out.println("Distance between same city is always 0!");
            return;
        }
        
        System.out.print("Enter distance in km: ");
        int distance = scanner.nextInt();
        
        if (distance <= 0) {
            System.out.println("Distance must be positive!");
            return;
        }
        
        distances[city1 - 1][city2 - 1] = distance;
        distances[city2 - 1][city1 - 1] = distance;
        
        System.out.println("Distance between " + cities[city1 - 1] + " and " + cities[city2 - 1] + " set to " + distance + " km");
        
        saveDistancesToFile();
    }

    public static void viewDistanceTable() {
        if (cityCount == 0) {
            System.out.println("No cities available!");
            return;
        }
        
        System.out.println("\n=== DISTANCE TABLE (km) ===");
        System.out.print("        ");
        for (int i = 0; i < cityCount; i++) {
            System.out.printf("%-8s", cities[i].substring(0, Math.min(7, cities[i].length())));
        }
        System.out.println();
        
        for (int i = 0; i < cityCount; i++) {
            System.out.printf("%-8s", cities[i].substring(0, Math.min(7, cities[i].length())));
            for (int j = 0; j < cityCount; j++) {
                System.out.printf("%-8d", distances[i][j]);
            }
            System.out.println();
        }
    }
    public static void showVehicleInfo() {
        System.out.println("\n=== VEHICLE INFORMATION ===");
        System.out.println("Type   Capacity(kg) Rate/km(LKR) Speed(km/h) Fuel(km/l)");
        System.out.println("--------------------------------------------------------");
        
        for (int i = 0; i < vehicleTypes.length; i++) {
            System.out.printf("%-6s %-12d %-13d %-11d %-10d\n", 
                vehicleTypes[i], capacities[i], ratesPerKm[i], avgSpeeds[i], fuelEfficiency[i]);
        }
    }

    public static void processDelivery() {
        Scanner scanner = new Scanner(System.in);
        
        if (cityCount < 2) {
            System.out.println("Need at least 2 cities for delivery!");
            return;
        }
        
        viewCities();
        System.out.print("Enter source city number: ");
        int source = scanner.nextInt();
        System.out.print("Enter destination city number: ");
        int dest = scanner.nextInt();
        
        if (source < 1 || source > cityCount || dest < 1 || dest > cityCount) {
            System.out.println("Invalid city numbers!");
            return;
        }
        
        if (source == dest) {
            System.out.println("Source and destination cannot be same!");
            return;
        }
        
        int distance = distances[source - 1][dest - 1];
        if (distance == 0) {
            System.out.println("No route available between these cities!");
            return;
        }
        
        showVehicleInfo();
        System.out.print("Select vehicle type (1=Van, 2=Truck, 3=Lorry): ");
        int vehicleType = scanner.nextInt() - 1;
        
        if (vehicleType < 0 || vehicleType > 2) {
            System.out.println("Invalid vehicle type!");
            return;
        }
        
        System.out.print("Enter package weight (kg): ");
        double weight = scanner.nextDouble();
        
        if (weight <= 0) {
            System.out.println("Weight must be positive!");
            return;
        }
        
        if (weight > capacities[vehicleType]) {
            System.out.println("Weight exceeds vehicle capacity! Max: " + capacities[vehicleType] + "kg");
            return;
        }
        
        calculateDeliveryCost(source, dest, distance, vehicleType, weight);
        
        saveDeliveriesToFile();
    }
     public static void calculateDeliveryCost(int source, int dest, int distance, int vehicleType, double weight) {
        double baseCost = distance * ratesPerKm[vehicleType] * (1 + weight / 10000);
        
        double fuelUsed = (double) distance / fuelEfficiency[vehicleType];
        double fuelCost = fuelUsed * FUEL_PRICE;
        
        double deliveryTime = (double) distance / avgSpeeds[vehicleType];
        
        double operationalCost = baseCost + fuelCost;
        
        double profit = baseCost * 0.25;
        
        double customerCharge = operationalCost + profit;
        
        if (deliveryCount < 50) {
            deliveries[deliveryCount] = new DeliveryRecord(source - 1, dest - 1, weight, vehicleType,  distance, customerCharge, deliveryTime);
            deliveryCount++;
        }
        
        System.out.println("\n--- DELIVERY COST ESTIMATION ---");
        System.out.println("From: " + cities[source - 1]);
        System.out.println("To: " + cities[dest - 1]);
        System.out.println("Distance: " + distance + " km");
        System.out.println("Vehicle: " + vehicleTypes[vehicleType]);
        System.out.println("Weight: " + weight + " kg");
        System.out.println("---");
        System.out.printf("Base Cost: %.2f LKR\n", baseCost);
        System.out.printf("Fuel Used: %.2f L\n", fuelUsed);
        System.out.printf("Fuel Cost: %.2f LKR\n", fuelCost);
        System.out.printf("Operational Cost: %.2f LKR\n", operationalCost);
        System.out.printf("Profit: %.2f LKR\n", profit);
        System.out.printf("Customer Charge: %.2f LKR\n", customerCharge);
        System.out.printf("Estimated Time: %.2f hours\n", deliveryTime);
    }
    public static void findLeastCostRoute() {
        Scanner scanner = new Scanner(System.in);
        
        if (cityCount < 2) {
            System.out.println("Need at least 2 cities!");
            return;
        }
        
        viewCities();
        System.out.print("Enter source city number: ");
        int source = scanner.nextInt();
        System.out.print("Enter destination city number: ");
        int dest = scanner.nextInt();
        
        if (source < 1 || source > cityCount || dest < 1 || dest > cityCount) {
            System.out.println("Invalid city numbers!");
            return;
        }
        
        if (source == dest) {
            System.out.println("Source and destination are the same!");
            return;
        }
        
        int directDistance = distances[source - 1][dest - 1];
        
        if (directDistance > 0) {
            System.out.println("Direct route found:");
            System.out.println(cities[source - 1] + " → " + cities[dest - 1] + " : " + directDistance + " km");
        } else {
            System.out.println("No direct route found.");
            System.out.println("Searching for alternative routes...");
            
            findAlternativeRoutes(source - 1, dest - 1);
        }
    }
    public static void findAlternativeRoutes(int source, int dest) {
        boolean foundRoute = false;
        
        for (int i = 0; i < cityCount; i++) {
            if (i != source && i != dest) {
                int dist1 = distances[source][i];
                int dist2 = distances[i][dest];
                
                if (dist1 > 0 && dist2 > 0) {
                    int totalDistance = dist1 + dist2;
                    System.out.println("\nAlternative route via " + cities[i] + ":");
                    System.out.println(cities[source] + " → " + cities[i] + " → " + cities[dest] + 
                                     " : " + totalDistance + " km");
                    foundRoute = true;
                }
            }
        }
        
        if (!foundRoute) {
            System.out.println("No routes found between these cities!");
        }
    }
    
    public static void showReports() {
        System.out.println("\n=== PERFORMANCE REPORTS ===");
        
        if (deliveryCount == 0) {
            System.out.println("No deliveries completed yet!");
            return;
        }
        
        double totalDistance = 0;
        double totalRevenue = 0;
        double totalTime = 0;
        double longestDistance = 0;
        double shortestDistance = Double.MAX_VALUE;
        
        for (int i = 0; i < deliveryCount; i++) {
            DeliveryRecord delivery = deliveries[i];
            totalDistance += delivery.distance;
            totalRevenue += delivery.customerCharge;
            totalTime += delivery.deliveryTime;
            
            if (delivery.distance > longestDistance) {
                longestDistance = delivery.distance;
            }
            if (delivery.distance < shortestDistance) {
                shortestDistance = delivery.distance;
            }
        }
        
        System.out.println("a. Total Deliveries Completed: " + deliveryCount);
        System.out.printf("b. Total Distance Covered: %.2f km\n", totalDistance);
        System.out.printf("c. Average Delivery Time: %.2f hours\n", totalTime / deliveryCount);
        System.out.printf("d. Total Revenue: %.2f LKR\n", totalRevenue);
        System.out.printf("   Estimated Profit: %.2f LKR\n", totalRevenue * 0.2);
        System.out.printf("e. Longest Route: %.2f km\n", longestDistance);
        System.out.printf("   Shortest Route: %.2f km\n", shortestDistance);
    }
}

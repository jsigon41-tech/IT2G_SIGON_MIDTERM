import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    static class VehiclePassage {
        int id;
        String plateNo;
        String vehicleClass;
        double tollFee;
        String paymentMethod;

        public VehiclePassage(int id, String plateNo, String vehicleClass, double tollFee, String paymentMethod) {
            this.id = id;
            this.plateNo = plateNo;
            this.vehicleClass = vehicleClass;
            this.tollFee = tollFee;
            this.paymentMethod = paymentMethod;
        }
    }

    private static ArrayList<VehiclePassage> records = new ArrayList<>();
    private static int nextId = 1;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            displayMenu();
            try {
                System.out.print("Enter choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        recordPassage();
                        break;
                    case 2:
                        viewLogs();
                        break;
                    case 3:
                        System.out.println("\nExiting system... Thank you!");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice! Please try again.\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.\n");
                scanner.nextLine();
            }
        }
    }

    private static void displayMenu() {
        System.out.println("----------------------------------------");
        System.out.println("      EXPRESSWAY TOLLGATE REGISTRY");
        System.out.println("----------------------------------------");
        System.out.println("[1] Record Vehicle Passage");
        System.out.println("[2] View Passage Logs");
        System.out.println("[3] Exit System");
        System.out.println("----------------------------------------");
    }

    private static void recordPassage() {
        System.out.println("\n--- RECORD VEHICLE PASSAGE ---");

        System.out.print("Enter License Plate Number: ");
        String plateNo = scanner.nextLine().trim();
        while (plateNo.isEmpty()) {
            System.out.print("Plate number cannot be empty! Enter License Plate Number: ");
            plateNo = scanner.nextLine().trim();
        }

        String vehicleClass = "";
        while (true) {
            System.out.print("Enter Vehicle Class (CLASS_1 / CLASS_2 / CLASS_3): ");
            vehicleClass = scanner.nextLine().trim().toUpperCase();
            if (vehicleClass.equals("CLASS_1") || vehicleClass.equals("CLASS_2") || vehicleClass.equals("CLASS_3")) {
                break;
            }
            System.out.println("Invalid class! Please use: CLASS_1, CLASS_2, or CLASS_3");
        }

        double tollFee = 0;
        while (true) {
            try {
                System.out.print("Enter Toll Fee: ");
                tollFee = scanner.nextDouble();
                scanner.nextLine();
                if (tollFee >= 0) break;
                else System.out.println("Toll fee cannot be negative!");
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number!");
                scanner.nextLine();
            }
        }

        String paymentMethod = "";
        while (true) {
            System.out.print("Enter Payment Method (RFID / CASH): ");
            paymentMethod = scanner.nextLine().trim().toUpperCase();
            if (paymentMethod.equals("RFID") || paymentMethod.equals("CASH")) {
                break;
            }
            System.out.println("Invalid payment method! Please use: RFID or CASH");
        }

        VehiclePassage newRecord = new VehiclePassage(nextId++, plateNo, vehicleClass, tollFee, paymentMethod);
        records.add(newRecord);

        System.out.println("\nSUCCESS: Passage recorded successfully!\n");
    }

    private static void viewLogs() {
        System.out.println("\n----------------------------------------");
        System.out.println("       EXPRESSWAY PASSAGE LOGS");
        System.out.println("----------------------------------------");

        if (records.isEmpty()) {
            System.out.println("         No records found.");
            System.out.println("----------------------------------------\n");
            return;
        }

        System.out.println("+-----+------------+---------+----------+----------+");
        System.out.println("| ID  | PLATE NO   | CLASS   | TOLL FEE | PAYMENT  |");
        System.out.println("+-----+------------+---------+----------+----------+");

        for (VehiclePassage vp : records) {
            System.out.printf("| %-3d | %-10s | %-7s | %-8.2f | %-8s |%n",
                    vp.id,
                    vp.plateNo,
                    vp.vehicleClass,
                    vp.tollFee,
                    vp.paymentMethod);
        }

        System.out.println("+-----+------------+---------+----------+----------+");
        System.out.println("Total Records Displayed: " + records.size());
        System.out.println("----------------------------------------\n");
    }
}
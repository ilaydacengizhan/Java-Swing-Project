package pack1;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Controller {
    public static final String CUSTOMER_FILE_PATH = "C:\\Users\\Hp\\OneDrive\\Masaüstü\\customer_data.txt";
    public static final String TICKET_FILE_PATH = "C:\\Users\\Hp\\OneDrive\\Masaüstü\\ticket_data.txt";

    public boolean writeCustomerToFile(String data) {
        return writeToFile(data, CUSTOMER_FILE_PATH);
    }

    public boolean writeTicketToFile(String data) {
        return writeToFile(data, TICKET_FILE_PATH);
    }

    public boolean deleteCustomerById(String customerId) {
        List<String> lines = readFromFile(CUSTOMER_FILE_PATH);
        for (int i = 0; i < lines.size(); i++) {
            String[] parts = lines.get(i).split(",");
            if (parts.length > 2 && parts[2].equals(customerId)) {
                lines.remove(i);
                writeToFile(lines, CUSTOMER_FILE_PATH);
                return true; // Successfully deleted
            }
        }
        return false; //Customer not found
    }

    public void showMainMenu() {
        
    }

    private boolean writeToFile(String data, String filePath) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
            writer.println(data);
            return true; 
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Dosya yazma hatası: " + e.getMessage());
            return false; 
        }
    }

    private void writeToFile(List<String> lines, String filePath) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                writer.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Dosya yazma hatası: " + e.getMessage());
        }
    }

    public List<String> readFromFile(String filePath) {
        try {
            return Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            return List.of();
        }
    }
}

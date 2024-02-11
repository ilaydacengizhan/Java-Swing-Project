package pack1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Main {
    public static void main(String[] args) {
        // Controller oluştur
        Controller controller = new Controller();

        // CustomerInfoFrame penceresini oluştur
        CustomerInfoFrame customerFrame = new CustomerInfoFrame(controller);

        // TicketInfoFrame penceresini oluştur
        TicketInfoFrame ticketFrame = new TicketInfoFrame(controller);

        // PaymentInfoFrame penceresini oluştur
        PaymentInfoFrame paymentFrame = new PaymentInfoFrame(controller);

        // Ana menüyü oluştur
        JFrame mainFrame = new JFrame("Main Menu");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(400, 300);
        mainFrame.setLayout(new GridLayout(3, 1));

        // Customer Info butonu
        JButton customerButton = new JButton("Customer Information");
        customerButton.addActionListener((ActionEvent e) -> {
            customerFrame.setVisible(true);
        });

        // Ticket Info butonu
        JButton ticketButton = new JButton("Ticket Information");
        ticketButton.addActionListener((ActionEvent e) -> {
            ticketFrame.setVisible(true);
        });

        // Payment Info butonu
        JButton paymentButton = new JButton("Payment Information");
        paymentButton.addActionListener((ActionEvent e) -> {
            paymentFrame.setVisible(true);
        });

        mainFrame.add(customerButton);
        mainFrame.add(ticketButton);
        mainFrame.add(paymentButton);

        mainFrame.setVisible(true);
    }
}

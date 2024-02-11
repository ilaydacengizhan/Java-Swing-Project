package pack1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PaymentInfoFrame extends JFrame {
    private JTextField cardNumberField, cardHolderField, cvvField;
    private JComboBox<String> expirationMonthComboBox, expirationYearComboBox;
    private JButton payButton;

    private Controller controller;

    public PaymentInfoFrame(Controller controller) {
        this.controller = controller;

        setTitle("Payment Information");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        initUI();

        payButton.addActionListener(this::payButtonActionPerformed);
    }

    private void initUI() {
        setLayout(new GridLayout(5, 2, 5, 5));

        addLabelAndTextField("Card Number:", cardNumberField = new JTextField());
        addLabelAndTextField("Card Holder:", cardHolderField = new JTextField());

        add(new JLabel("Expiration Date:"));
        expirationMonthComboBox = new JComboBox<>(new String[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"});
        add(expirationMonthComboBox);

        expirationYearComboBox = new JComboBox<>(new String[]{"2023", "2024", "2025", "2026", "2027"}); // Daha fazla yıl eklenmeli
        add(expirationYearComboBox);

        addLabelAndTextField("CVV:", cvvField = new JTextField());

        payButton = new JButton("Pay");
        add(payButton);
    }

    private void addLabelAndTextField(String labelText, JTextField textField) {
        add(new JLabel(labelText));
        add(textField);
    }

    private void payButtonActionPerformed(ActionEvent evt) {
        String data = String.format("%s,%s,%s,%s,%s",
                cardNumberField.getText(), cardHolderField.getText(),
                expirationMonthComboBox.getSelectedItem(),
                expirationYearComboBox.getSelectedItem(),
                cvvField.getText());

        
        boolean success = true; 

        if (success) {
            JOptionPane.showMessageDialog(this, "Payment successful.");
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Payment failed.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        cardNumberField.setText("");
        cardHolderField.setText("");
        cvvField.setText("");
        expirationMonthComboBox.setSelectedIndex(0);
        expirationYearComboBox.setSelectedIndex(0);
    }
}

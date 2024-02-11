package pack1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CustomerInfoFrame extends JFrame {
    private JTextField nameField, surnameField, idField, emailField, ageField, phoneField;
    private JComboBox<String> genderComboBox, cityComboBox;
    private JButton saveButton, addButton, deleteButton, backButton;

    private Controller controller;

    public CustomerInfoFrame(Controller controller) {
        this.controller = controller;

        setTitle("Customer Information");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        initUI();

        saveButton.addActionListener(this::saveButtonActionPerformed);
        addButton.addActionListener(this::addButtonActionPerformed);
        deleteButton.addActionListener(this::deleteButtonActionPerformed);
        backButton.addActionListener(this::backButtonActionPerformed);
    }

    private void initUI() {
        setLayout(new GridLayout(10, 2, 5, 5));

        addLabelAndTextField("Name:", nameField = new JTextField());
        addLabelAndTextField("Surname:", surnameField = new JTextField());
        addLabelAndTextField("ID:", idField = new JTextField());
        addLabelAndTextField("Email:", emailField = new JTextField());
        addLabelAndTextField("Age:", ageField = new JTextField());
        addLabelAndTextField("Phone:", phoneField = new JTextField());

        add(new JLabel("Gender:"));
        genderComboBox = new JComboBox<>(new String[]{"Male", "Female"});
        add(genderComboBox);

        add(new JLabel("City:"));
        cityComboBox = new JComboBox<>(new String[]{"İstanbul", "Ankara", "İzmir", "Eskişehir" ,"Antalya", "Mersin", "Bursa" , "Artvin", "Tekirdağ", "Zonguldak"});
        add(cityComboBox);

        saveButton = new JButton("Save");
        addButton = new JButton("Add");
        deleteButton = new JButton("Delete");
        backButton = new JButton("Back");

        add(saveButton);
        add(addButton);
        add(deleteButton);
        add(backButton);
    }

    private void addLabelAndTextField(String labelText, JTextField textField) {
        add(new JLabel(labelText));
        add(textField);
    }

    private void saveButtonActionPerformed(ActionEvent evt) {
        String data = String.format("%s,%s,%s,%s,%s,%s,%s,%s",
                nameField.getText(), surnameField.getText(), idField.getText(),
                emailField.getText(), ageField.getText(), phoneField.getText(),
                genderComboBox.getSelectedItem(), cityComboBox.getSelectedItem());

        boolean success = controller.writeCustomerToFile(data);

        if (success) {
            JOptionPane.showMessageDialog(this, "Customer data added successfully.");
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Error adding customer data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addButtonActionPerformed(ActionEvent evt) {
        String data = String.format("%s,%s,%s,%s,%s,%s,%s,%s",
                nameField.getText(), surnameField.getText(), idField.getText(),
                emailField.getText(), ageField.getText(), phoneField.getText(),
                genderComboBox.getSelectedItem(), cityComboBox.getSelectedItem());

        boolean success = controller.writeCustomerToFile(data);

        if (success) {
            JOptionPane.showMessageDialog(this, "Customer data added successfully.");
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Error adding customer data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteButtonActionPerformed(ActionEvent evt) {
        String customerId = idField.getText();
        boolean success = controller.deleteCustomerById(customerId);

        if (success) {
            JOptionPane.showMessageDialog(this, "Customer data deleted successfully.");
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Error deleting customer data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void backButtonActionPerformed(ActionEvent evt) {
        this.setVisible(false); 
        controller.showMainMenu();
    }

    private void clearFields() {
        nameField.setText("");
        surnameField.setText("");
        idField.setText("");
        emailField.setText("");
        ageField.setText("");
        phoneField.setText("");
        genderComboBox.setSelectedIndex(0);
        cityComboBox.setSelectedIndex(0);
    }
}

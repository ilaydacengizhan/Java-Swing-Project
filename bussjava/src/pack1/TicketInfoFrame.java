package pack1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class TicketInfoFrame extends JFrame {
    private JTextField nameField, surnameField, travelTimeField, travelDateField, fromToField, priceField;
    private JButton addButton, viewButton, backButton;

    private Controller controller;

    public TicketInfoFrame(Controller controller) {
        this.controller = controller;

        setTitle("Ticket Information");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        initUI();

        addButton.addActionListener(this::addButtonActionPerformed);
        viewButton.addActionListener(this::viewButtonActionPerformed);
        backButton.addActionListener(this::backButtonActionPerformed);
    }

    private void initUI() {
        setLayout(new GridLayout(8, 2, 5, 5));

        addLabelAndTextField("Name:", nameField = new JTextField());
        addLabelAndTextField("Surname:", surnameField = new JTextField());
        addLabelAndTextField("Travel Time:", travelTimeField = new JTextField());
        addLabelAndTextField("Travel Date:", travelDateField = new JTextField());
        addLabelAndTextField("From - To:", fromToField = new JTextField());
        addLabelAndTextField("Price:", priceField = new JTextField());

        addButton = new JButton("Add");
        viewButton = new JButton("View");
        backButton = new JButton("Back");

        add(addButton);
        add(viewButton);
        add(backButton);
    }

    private void addLabelAndTextField(String labelText, JTextField textField) {
        add(new JLabel(labelText));
        add(textField);
    }

    private void addButtonActionPerformed(ActionEvent evt) {
        String data = String.format("%s,%s,%s,%s,%s,%s",
                nameField.getText(), surnameField.getText(),
                travelTimeField.getText(), travelDateField.getText(),
                fromToField.getText(), priceField.getText());

        boolean success = controller.writeTicketToFile(data);

        if (success) {
            JOptionPane.showMessageDialog(this, "Ticket data added successfully.");
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Error adding ticket data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void viewButtonActionPerformed(ActionEvent evt) {
        List<String> dataList = controller.readFromFile(Controller.TICKET_FILE_PATH);
        if (dataList.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No ticket data available.", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else {
            String currentTicketData = dataList.get(dataList.size() - 1);
            JTextArea textArea = new JTextArea(currentTicketData);
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            JOptionPane.showMessageDialog(this, scrollPane, "Current Ticket Data", JOptionPane.PLAIN_MESSAGE);
        }
    }

    private void backButtonActionPerformed(ActionEvent evt) {
        controller.showMainMenu();
        this.dispose();
    }

    private void clearFields() {
        nameField.setText("");
        surnameField.setText("");
        travelTimeField.setText("");
        travelDateField.setText("");
        fromToField.setText("");
        priceField.setText("");
    }
}

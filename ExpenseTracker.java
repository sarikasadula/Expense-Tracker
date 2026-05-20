import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.ArrayList;

public class ExpenseTracker extends JFrame {

    JTextField dateField, categoryField, amountField;
    JButton addBtn, totalBtn;
    JTable table;
    DefaultTableModel model;

    // Store expense data (without model class)
    ArrayList<String> dates = new ArrayList<>();
    ArrayList<String> categories = new ArrayList<>();
    ArrayList<Double> amounts = new ArrayList<>();

    ExpenseTracker() {

        setTitle("Expense Tracker");
        setSize(500, 400);
        setLayout(null);

        JLabel l1 = new JLabel("Date:");
        JLabel l2 = new JLabel("Category:");
        JLabel l3 = new JLabel("Amount:");

        dateField = new JTextField();
        categoryField = new JTextField();
        amountField = new JTextField();

        addBtn = new JButton("Add Expense");
        totalBtn = new JButton("Total");

        model = new DefaultTableModel(
                new String[]{"Date", "Category", "Amount"}, 0);
        table = new JTable(model);
        JScrollPane sp = new JScrollPane(table);

        l1.setBounds(20, 20, 100, 20);
        dateField.setBounds(120, 20, 120, 20);

        l2.setBounds(20, 50, 100, 20);
        categoryField.setBounds(120, 50, 120, 20);

        l3.setBounds(20, 80, 100, 20);
        amountField.setBounds(120, 80, 120, 20);

        addBtn.setBounds(260, 30, 120, 25);
        totalBtn.setBounds(260, 70, 120, 25);

        sp.setBounds(20, 120, 440, 200);

        add(l1); add(l2); add(l3);
        add(dateField); add(categoryField); add(amountField);
        add(addBtn); add(totalBtn);
        add(sp);

        addBtn.addActionListener(e -> addExpense());
        totalBtn.addActionListener(e -> calculateTotal());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Add expense
    void addExpense() {
        try {
            String date = dateField.getText();
            String category = categoryField.getText();
            double amount = Double.parseDouble(amountField.getText());

            dates.add(date);
            categories.add(category);
            amounts.add(amount);

            model.addRow(new Object[]{date, category, amount});

            FileWriter fw = new FileWriter("expenses.txt", true);
            fw.write(date + "," + category + "," + amount + "\n");
            fw.close();

            dateField.setText("");
            categoryField.setText("");
            amountField.setText("");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid Input!");
        }
    }

    // Calculate total
    void calculateTotal() {
        double total = 0;
        for (double amt : amounts) {
            total += amt;
        }
        JOptionPane.showMessageDialog(this, "Total Expense: ₹" + total);
    }

    public static void main(String[] args) {
        new ExpenseTracker();
    }
}
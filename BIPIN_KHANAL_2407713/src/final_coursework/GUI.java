package final_coursework;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GUI extends JFrame {
    private Transaction transferObject;
    private StringBuilder sbAllData;
    private LinkedList<Account> globalAccounts;
    private JLabel showAllData;
    private JButton showAllButton, depositButton, withdrawButton, transferButton;
    private JTextField accDeposit, accWithdraw, acc1Transfer, acc2Transfer, depositInput, withdrawInput, transferAmount;
    private JLabel lblNewLabel_2;
    private JLabel lblNewLabel_3;
    private JLabel lblNewLabel_4;
    private JLabel lblNewLabel_5;
    private JLabel lblNewLabel_6;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LinkedList<Account> accounts = new LinkedList<>();
                    accounts.add(new Account("Patel", "Hiran", 11, 1000));
                    accounts.add(new Account("Ting", "Jeffrey", 22, 10));
                    accounts.add(new Account("Paul", "Wilson", 33, 50000));
                    accounts.add(new Account("Adam", "Worrallo", 44, 99999));
                    GUI frame = new GUI(accounts);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public GUI(LinkedList<Account> accounts) {
        globalAccounts = accounts;
        sbAllData = new StringBuilder("Click 'Show All' to display all data."); // Initialize with message
        showAllData = new JLabel(sbAllData.toString()); // Initialize showAllData with the initial message
        initialize();
    }


    private void initialize() {
        setTitle("Banking System");
        setBounds(100, 100, 600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        showAllData = new JLabel(sbAllData.toString());
        showAllData.setBounds(20, 20, 545, 200);
        getContentPane().add(showAllData);

        showAllButton = new JButton("Show All");
        showAllButton.setBounds(20, 233, 100, 30);
        getContentPane().add(showAllButton);

        depositButton = new JButton("Deposit");
        depositButton.setBounds(20, 290, 100, 30);
        getContentPane().add(depositButton);

        withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(20, 340, 100, 30);
        getContentPane().add(withdrawButton);

        transferButton = new JButton("Transfer");
        transferButton.setBounds(20, 390, 100, 30);
        getContentPane().add(transferButton);

        accDeposit = new JTextField();
        accDeposit.setBounds(140, 290, 100, 20);
        getContentPane().add(accDeposit);
        accDeposit.setColumns(10);

        accWithdraw = new JTextField();
        accWithdraw.setBounds(140, 340, 100, 20);
        getContentPane().add(accWithdraw);
        accWithdraw.setColumns(10);

        acc1Transfer = new JTextField();
        acc1Transfer.setBounds(140, 390, 110, 30);
        getContentPane().add(acc1Transfer);
        acc1Transfer.setColumns(10);

        acc2Transfer = new JTextField();
        acc2Transfer.setBounds(260, 390, 111, 30);
        getContentPane().add(acc2Transfer);
        acc2Transfer.setColumns(10);

        depositInput = new JTextField();
        depositInput.setBounds(260, 290, 100, 20);
        getContentPane().add(depositInput);
        depositInput.setColumns(10);

        withdrawInput = new JTextField();
        withdrawInput.setBounds(260, 340, 100, 20);
        getContentPane().add(withdrawInput);
        withdrawInput.setColumns(10);

        transferAmount = new JTextField();
        transferAmount.setBounds(403, 390, 100, 30);
        getContentPane().add(transferAmount);
        transferAmount.setColumns(10);
        
        JLabel lblNewLabel = new JLabel("Account number");
        lblNewLabel.setBounds(140, 277, 100, 14);
        getContentPane().add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Amount");
        lblNewLabel_1.setBounds(261, 277, 85, 14);
        getContentPane().add(lblNewLabel_1);
        
        lblNewLabel_2 = new JLabel("Account number");
        lblNewLabel_2.setBounds(140, 326, 100, 14);
        getContentPane().add(lblNewLabel_2);
        
        lblNewLabel_3 = new JLabel("Amount");
        lblNewLabel_3.setBounds(259, 326, 46, 14);
        getContentPane().add(lblNewLabel_3);
        
        lblNewLabel_4 = new JLabel("sender account no");
        lblNewLabel_4.setBounds(130, 376, 120, 14);
        getContentPane().add(lblNewLabel_4);
        
        lblNewLabel_5 = new JLabel("receiver account no");
        lblNewLabel_5.setBounds(260, 376, 133, 14);
        getContentPane().add(lblNewLabel_5);
        
        lblNewLabel_6 = new JLabel("Amount");
        lblNewLabel_6.setBounds(403, 376, 100, 14);
        getContentPane().add(lblNewLabel_6);

        showAllButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateAllData();
            }
        });

        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int accountNum = Integer.parseInt(accDeposit.getText());
                    double amount = Double.parseDouble(depositInput.getText());

                    Account account = findAccount(accountNum);
                    if (account != null) {
                        account.deposit(amount);
                        updateAllData();
                    } else {
                        showAllData.setText("Account not found.");
                    }
                } catch (NumberFormatException ex) {
                    showAllData.setText("Invalid input. Please enter a valid account number and amount.");
                }
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int accountNum = Integer.parseInt(accWithdraw.getText());
                    double amount = Double.parseDouble(withdrawInput.getText());

                    Account account = findAccount(accountNum);
                    if (account != null) {
                        if (account.getBalance() >= amount) {
                            account.withdraw(amount);
                            updateAllData();
                        } else {
                            showAllData.setText("Insufficient balance.");
                        }
                    } else {
                        showAllData.setText("Account not found.");
                    }
                } catch (NumberFormatException ex) {
                    showAllData.setText("Invalid input. Please enter a valid account number and amount.");
                }
            }
        });

        transferButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int accountNum1 = Integer.parseInt(acc1Transfer.getText());
                    int accountNum2 = Integer.parseInt(acc2Transfer.getText());
                    double amount = Double.parseDouble(transferAmount.getText());

                    Account account1 = findAccount(accountNum1);
                    Account account2 = findAccount(accountNum2);
                    if (account1 != null && account2 != null) {
                        if (account1.getBalance() >= amount) {
                            transferObject = new Transaction();
                            transferObject.transfer(account1, account2, amount);
                            updateAllData();
                        } else {
                            showAllData.setText("Insufficient balance in account " + accountNum1);
                        }
                    } else {
                        showAllData.setText("One or both accounts not found.");
                    }
                } catch (NumberFormatException ex) {
                    showAllData.setText("Invalid input. Please enter valid account numbers and amount.");
                }
            }
        });
    }

    private Account findAccount(int accountNum) {
        for (Account account : globalAccounts) {
            if (account.getAccountNum() == accountNum) {
                return account;
            }
        }
        return null;
    }
    private void updateAllData() {
        sbAllData = new StringBuilder("<html><ul>");
        for (Account account : globalAccounts) {
            sbAllData.append("<li>Account Number: ").append(account.getAccountNum()).append(", Name: ")
                    .append(account.getFirstName()).append(" ").append(account.getLastName())
                    .append(", Balance: ").append(account.getBalance()).append("</li>");
        }
        sbAllData.append("</ul></html>");
        showAllData.setText(sbAllData.toString());
    }
}

package final_coursework;

import java.util.LinkedList;

public class Main {

public static void main(String[] args) {
ReadAccounts reader = new ReadAccounts("Account.csv");
LinkedList<Account> accounts = new LinkedList<>();

LinkedList<String> firstNames = reader.getFirstNames();
LinkedList<String> lastNames = reader.getLastNames();
LinkedList<Integer> accountNums = reader.getAccounts();
LinkedList<Integer> balances = reader.getBalances();

for (int i = 0; i < accountNums.size(); i++) {
accounts.add(new Account(firstNames.get(i), lastNames.get(i), accountNums.get(i), balances.get(i)));
System.out.println();
}

new GUI(accounts);
}
}
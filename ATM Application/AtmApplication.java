import java.io.File;
import java.util.*;

public class AtmApplication  {
    private static Scanner scan;
    static int arr[] = { 2, 2, 3, 4 };
    static users user[] = new users[5];
    static String bank = "Canara";
    static int res = 6000, index = 0, count = 0;

    static void createuser() {
        user[0] = new users("Arasu", "2108", 31000.00, "sb659", "12345", "uco", "", 0);
        user[1] = new users("siva", "2340", 10000.00, "iob731", "99999", "rbi", "", 0);
        user[2] = new users("ram", "0909", 60000.00, "can083", "88888", "canara", "", 0);
        user[3] = new users("dinesh", "0808", 20000.00, "tmb023", "77777", "uco", "", 0);
        user[4] = new users("vijay", "0707", 80000.00, "uco777", "66666", "uco", "", 0);
    }

    public static void showamount() {
        System.out.println("2000 => " + arr[0] + " ");
        System.out.println("500 => " + arr[1] + " ");
        System.out.println("200 => " + arr[2] + " ");
        System.out.println("100 => " + arr[3] + " ");
        System.out.println("Total amount present : " + res);
    }

    public static void add() {
        System.out.print("2000 => ");
        arr[0] += scan.nextInt();
        System.out.print("500 => ");
        arr[1] += scan.nextInt();
        System.out.print("200 => ");
        arr[2] += scan.nextInt();
        System.out.print("100 => ");
        arr[3] += scan.nextInt();
        System.out.println("Total amount present : "
                + (res += (arr[0] * 2000) + (arr[1] * 500) + (arr[2] * 200) + (arr[3] * 100)));
        System.out.println("Amount added Successfully");
    }

    private static boolean check(users[] user2, String id, String password) {
        for (int i = 0; i < 5; i++) {
            if (user2[i].name.equals(id) && user2[i].passwd.equals(password)) {
                index = i;
                return true;
            }
        }
        return false;
    }

    public static void deposit(int dep) {
        user[index].balance += +dep;
        res += dep;
        System.out.println("Amount deposited Successfully");
        user[index].miniStatement += "Deposit\t\t" + dep + "\t\t" + user[index].balance + "\t\t" + "Creditted" + "\n";
    }

    private static void withdraw() {
        System.out.println("Enter the withdraw amount :");
        int withdraw = scan.nextInt();
        if (withdraw > user[index].balance)
            System.out.println("Amount you entered is exceeds your balance");
        else if (withdraw > res)
            System.out.println("Amount you entered is Less than ATM Balance...:(");
        else {
            user[index].balance = user[index].balance - withdraw;
            res = res - withdraw;
            user[index].miniStatement += "WithDrawn\t" + withdraw + "\t\t" + user[index].balance + "\t\t" + "Debitted"
                    + "\n";
            int temp = 0, t = withdraw;
            temp = t / 2000;
            if (arr[0] < t) {
                temp -= arr[0];
                t = temp * 2000;
                arr[0] = 0;
            } else if (arr[0] >= t) {
                t = t % 2000;
                arr[0] -= temp;
            }
            System.out.print(temp + " ");
            temp = t / 500;
            if (arr[1] < t) {
                temp -= arr[1];
                t = temp * 500;
                arr[1] = 0;
            } else if (arr[1] >= t) {
                t = t % 2000;
                arr[1] -= temp;
            }
            System.out.print(temp + " ");
            temp = t / 200;
            if (arr[2] < t) {
                temp -= arr[2];
                t = temp * 200;
                arr[2] = 0;
            } else if (arr[2] >= t) {
                t = t % 200;
                arr[2] -= temp;
            }
            System.out.print(temp + " ");
            temp = t / 100;
            if (arr[3] < t) {
                temp -= arr[3];
                t = temp * 100;
                arr[3] = 0;
            } else if (arr[3] >= t) {
                t = t % 100;
                arr[3] -= temp;
            }
            System.out.print(temp + " ");
            System.out.println("Amount withdrawn successfully");
        }

    }

    public static void changepin() {
        System.out.println("Enter the old Pin");
        String oldpin = scan.next();
        if (oldpin.equals(user[index].passwd)) {
            System.out.println("Enter the NewPin");
            String newpass = scan.next();
            user[index].passwd = newpass;
        } else {
            System.out.println("Incorrect old pin");
        }
    }

    public static void transfer() {
        double currentbalance = user[index].balance;
        int count = 0;
        System.out.println("Enter the amount to be Transfer");
        double transferAmount = scan.nextDouble();
        if (currentbalance > transferAmount) {
            System.out.println("Enter the Account Number to be Transfer");
            String accountnumber = scan.next();
            for (int i = 0; i < 5; i++) {
                count++;
                if (accountnumber.equals(user[i].acc_no)) {
                    user[i].balance += transferAmount;
                    user[index].balance -= transferAmount;
                    System.out.println("Amount Transferred Successfully");
                    user[i].miniStatement += "Credited Amount\t" + transferAmount + "\t\t" + user[i].balance + "\t\t\t"
                            + "Credited by " + user[index].name + "\n\t";
                    user[index].miniStatement += "Debitted Amount\t" + transferAmount + "\t\t" + user[index].balance
                            + "\t\t\t" + "Debitted to  " + user[i].name + "\n\t";
                    break;
                } else if (count >= 5) {
                    System.out.println("Account not Found try again");
                }
            }
        } else {
            System.out.println("Transfer amount exceeds available balance");
        }

    }

    public static void transactionHistory() {
        String statement[] = user[index].miniStatement.split("\n");
        if (statement.length <= 6) {
            System.out.println(user[index].miniStatement);
        } else if (statement.length > 6) {
            int statelength = statement.length - 6;
            for (int i = statelength; i <= statement.length; i++) {
                System.out.println(user[index].miniStatement);
            }
        }
    }

    public static boolean passcheck() {
        if (count <= 3) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        scan = new Scanner(System.in);
        createuser();
        int ch = 0;
        do {
            System.out.println("---------ATM Machine--------");
            System.out.println("1.Admin Login");
            System.out.println("2.User Login");
            System.out.println("3.Exit");
            System.out.println("Enter your Choice");
            ch = scan.nextInt();
            System.out.print("\033[H\033[2J");
            System.out.flush();
            switch (ch) {
                case 1:
                    System.out.println("Enter the Admin ID: ");
                    String adminId = scan.next();
                    System.out.println("Enter the Admin password");
                    String pswd = scan.next();
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    if (adminId.equals("kalai") && pswd.equals("1234")) {
                        int n = 0;
                        do {
                            System.out.println("------Welcome Admin-----");
                            System.out.println("1.Show Amount");
                            System.out.println("2.Add Amount");
                            System.out.println("3.Exit");
                            System.out.println("Enter Your Choice:");
                            n = scan.nextInt();
                            System.out.print("\033[H\033[2J");
                            System.out.flush();
                            switch (n) {
                                case 1:
                                    showamount();
                                    System.out.println("\tpress enter to continue.....!!");
                                    scan.nextLine();
                                    String s0 = scan.nextLine();
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    break;
                                case 2:
                                    System.out.println("Enter the amount to be added");
                                    // int amt=scan.nextInt()
                                    add();
                                    System.out.println("\tpress enter to continue.....!!");
                                    scan.nextLine();
                                    String s = scan.nextLine();
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    break;
                                case 3:
                                    System.out.println("Thank you admin");
                                    System.out.println("\tpress enter to continue.....!!");
                                    scan.nextLine();
                                    String s9 = scan.nextLine();
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    break;
                                default:
                                    System.out.println("invalid input");
                                    break;
                            }

                        } while (n != 3);

                    } else {
                        System.out.println("Invalid Admin log in credencials....");
                        System.out.println("\tpress enter to continue.....!!");
                        scan.nextLine();
                        String s = scan.nextLine();
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                    }
                    break;

                case 2:
                    System.out.println("Enter the User ID");
                    String id = scan.next();
                    System.out.println("Enter the User Password");
                    String password = scan.next();
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    if (check(user, id, password) && passcheck()) {
                        int opt = 0;
                        do {
                            System.out.println("-----Welcome " + id + "-----");
                            System.out.println("1.Balance");
                            System.out.println("2.Withdraw Amount");
                            System.out.println("3.deposit Amount");
                            System.out.println("4.Change Account Pin ");
                            System.out.println("5.Transfer Amount");
                            System.out.println("6.Transaction History");
                            System.out.println("7.Exit");
                            System.out.println("Enter Your Choice:");
                            opt = scan.nextInt();
                            switch (opt) {
                                case 1:
                                    System.out.println("Total amount of " + id + " : " + user[index].balance);
                                    System.out.println("\tpress enter to continue.....!!");
                                    scan.nextLine();
                                    String s = scan.nextLine();
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    break;
                                case 2: // withdraw
                                    withdraw();
                                    System.out.println("\tpress enter to continue.....!!");
                                    scan.nextLine();
                                    String s4 = scan.nextLine();
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    break;
                                case 3: // deposit
                                    System.out.println("Enter the amount to deposit");
                                    int depositAmt = scan.nextInt();
                                    deposit(depositAmt);
                                    System.out.println("\tpress enter to continue.....!!");
                                    scan.nextLine();
                                    String s3 = scan.nextLine();
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    break;
                                case 4: // change pin
                                    changepin();
                                    System.out.println("\tpress enter to continue.....!!");
                                    scan.nextLine();
                                    String s9 = scan.nextLine();
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    break;
                                case 5: // transfer
                                    transfer();
                                    System.out.println("\tpress enter to continue.....!!");
                                    scan.nextLine();
                                    String lo = scan.nextLine();
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    break;
                                case 6: // transaction History
                                    System.out.println("Transaction History of " + user[index].name);
                                    System.out.println(
                                            "Process\t\t" + "Amount\t\t" + "Available Balance\t\t" + "Credit/Debit");
                                    transactionHistory();
                                    System.out.println("\tpress enter to continue.....!!");
                                    scan.nextLine();
                                    String po = scan.nextLine();
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    break;
                                case 7:
                                    System.out.println("Thank you " + user[index].name + "...:)");
                                    System.out.println("\tpress enter to continue.....!!");
                                    scan.nextLine();
                                    String s2 = scan.nextLine();
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    break;
                                default:
                                    System.out.println("invaid input");
                                    break;
                            }
                        } while (opt != 7);
                    } else {
                        count++;
                        user[index].lock++;
                        if (user[index].lock >= 3) {
                            System.out.println("Too many attemps to login....:(  \n Please Contact NearBy Branch !!!");
                            count = 0;
                        }
                        System.out.println("Invalid user Id or Password");
                        System.out.println("\tpress enter to continue.....!!");
                        scan.nextLine();
                        String s6 = scan.nextLine();
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                    }
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Invalid input");
                    break;
            }
        } while (ch != 3);

    }

}

class users {
    String name, passwd, ifsc, miniStatement, acc_no, bank;
    double balance;
    int lock;

    users(String name, String passwd, double balance, String ifsc, String acc_no, String bank, String miniStatement,
            int lock) {
        this.name = name;
        this.passwd = passwd;
        this.balance = balance;
        this.ifsc = ifsc;
        this.acc_no = acc_no;
        this.bank = bank;
        this.miniStatement = miniStatement;
        this.lock = lock;
    }
}
    


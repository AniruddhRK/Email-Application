import java.util.ArrayList;
import java.util.Scanner;
interface IMessage{
    void viewInbox(ArrayList<user> users, Scanner sc);
    void viewSentMails(ArrayList<user> users,Scanner sc);
}
abstract class Account{
    abstract void setMail(ArrayList users,Scanner sc);
}
public class runApp {
    public static void main(String[] args) throws Exception {
        ArrayList<user> users = new ArrayList<>();
        ArrayList<group> groups = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int choice;
        while (true) {
            System.out.println("Enter the choice from the options Listed below: ");
            System.out.println("1.Create User\n2.Create Group\n3.Add User to a Group");
            System.out.println("4.Compose Mail\n5.Read Inbox\n6.Read Sent Mails\n7.Exit");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    user User = new user();
                    User.setMail(users,sc);
                    users.add(User);
                    System.out.println(User);
                    break;
                case 2:
                    group Group = new group();
                    Group.setMail(groups,sc);
                    groups.add(Group);
                    System.out.println(Group);
                    break;
                case 3:
                    new group().addUser(users,groups,sc);
                    break;
                case 4:
                    new user().composeMail(users,groups,sc);
                    break;
                case 5:
                    IMessage i = new user();
                    i.viewInbox(users,sc);
                    break;
                case 6:
                    IMessage s = new user();
                    s.viewSentMails(users,sc);
                    break;
                case 7:
                    System.exit(0);
            }
        }
    }
}
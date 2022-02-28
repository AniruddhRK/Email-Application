import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
public class user extends Account implements IMessage{
    protected String name,mailId,password;
    ArrayList<Message> inboxMails = new ArrayList<Message>();
    static HashMap<String,String> val = new HashMap<>(10,0.5f);
    ArrayList<Message> sentMails = new ArrayList<Message>();
    @Override
    public void setMail(ArrayList users,Scanner sc) {
        System.out.println("Enter your Name: ");
        String tempName = sc.nextLine();
        System.out.println("Enter the Mail Id: ");
        String tempMail = sc.nextLine();
        boolean flag = false;
        ArrayList<user> pam = users;
        if(!val.containsKey(tempName)&&!val.containsValue(tempMail)) {
            flag = true;
        }
        if(!tempMail.matches("\\w*@[a-zA-Z]*[.]com")) {
            System.out.println("Invalid Email Format!!");
            return;
        }
        if(pam.size()==0) flag = true;
        if(!flag){
            System.out.println("Mail Id Already Exists!!!");
            return;
        }
        System.out.println("Enter the password: ");
        password = sc.next();
        name = tempName;
        mailId = tempMail;
        val.put(tempName,tempMail);
    }
    public void composeMail(ArrayList<user> users,ArrayList<group> groups,Scanner sc){
        String from,to,subject,content;
        boolean fromFlag=false,toFlagUser=false,toFlagGroup=false;
        user fromUser = new user();
        user toUser = new user();
        group toUsers = new group();
        System.out.println("Enter the Sender Mail Id: ");
        from = sc.next();
        System.out.println("Enter the Receiver Mail Id: ");
        to = sc.next();
        for(user U:users){
            if(U.mailId.equals(from)){
                fromFlag = true;
                fromUser = U;
            }
            if(U.mailId.equals(to)){
                toFlagUser = true;
                toUser = U;
            }
        }
        for(group G:groups){
            if(G.mailId.equals(to)){
                toUsers = G;
                toFlagGroup = true;
                break;
            }
        }
        if(!(fromFlag && toFlagGroup || fromFlag && toFlagUser)){
            System.out.println("From or To Mail Id is Invalid");
            return;
        }
        sc.nextLine();
        System.out.println("Enter the Subject: ");
        subject = sc.nextLine();
        System.out.println("Enter the Message: ");
        content = sc.nextLine();
        if(toFlagUser){
            fromUser.sentMails.add(new Message(fromUser.sentMails.size()+1,fromUser.mailId,toUser.mailId,subject,content));
            toUser.inboxMails.add(new Message(toUser.inboxMails.size()+1,fromUser.mailId,toUser.mailId,subject,content));
            return;
        }
        if(toFlagGroup){
            fromUser.sentMails.add(new Message(fromUser.sentMails.size()+1,fromUser.mailId,toUsers.mailId,subject,content));
            for(user UG:toUsers.usersInGroup){
                if(!UG.name.equals(fromUser.name))
                    UG.inboxMails.add(new Message(UG.inboxMails.size()+1,fromUser.mailId,toUsers.mailId,subject,content));
            }
        }
    }
    @Override
    public void viewInbox(ArrayList<user> users,Scanner sc) {
        String name;
        System.out.println("Enter the User Name: ");
        name = sc.nextLine();
        boolean flag=false;
        user currUser = new user();
        for(user U:users){
            if(U.name.equals(name)){
                currUser = U;
                flag = true;
                break;
            }
        }
        if(!flag){
            System.out.println("Invalid UserName!!!");
            return;
        }
        for(Message msg: currUser.inboxMails) {
            System.out.println(msg.ind +"   "+ msg.from + "     " + msg.to + "      " + msg.subject + "     " + msg.content);
        }
    }
    @Override
    public void viewSentMails(ArrayList<user> users,Scanner sc) {
        String name;
        System.out.println("Enter the User Name: ");
        name = sc.nextLine();
        boolean flag = false;
        user currUser = new user();
        for(user U:users){
            if(U.name.equals(name)){
                currUser = U;
                flag = true;
                break;
            }
        }
        if(!flag){
            System.out.println("Invalid UserName!!!");
            return;
        }
        for(Message msg: currUser.sentMails){
            System.out.println(msg.ind+"    "+msg.from+"    "+msg.to+"     "+msg.subject+"      "+msg.content);
        }
    }
    public String toString(){
        return "Name "+name+" MailID "+mailId+" Password: "+password;
    }
}

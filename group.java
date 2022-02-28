import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
public class group extends Account {
    protected String name,mailId,password;
    static HashMap<String,String> val = new HashMap<>(10,0.5f);
    protected ArrayList<user> usersInGroup = new ArrayList<>();
    public void setMail(ArrayList groups, Scanner sc){
        String tempName,tempMail;
        System.out.println("Enter the Group Name: ");
        tempName = sc.nextLine();
        System.out.println("Enter the Group Mail Id: ");
        tempMail = sc.nextLine();
        ArrayList<group> pam = groups;
        boolean flag = false;
        if(!val.containsKey(tempName)&&!val.containsValue(tempMail)) {
            flag = true;
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
    public void addUser(ArrayList<user> users,ArrayList<group> groups,Scanner sc){
        String groupName,userName;
        user currUser = new user();
        group currGroup = new group();
        boolean uFlag=false,gFlag=false;
        System.out.println("Enter the Group Name: ");
        groupName = sc.nextLine();
        System.out.println("Enter the User Name: ");
        userName = sc.nextLine();
        for(user U:users){
            if(U.name.equals(userName)){
                uFlag = true;
                currUser = U;
                break;
            }
        }
        for(group G:groups){
            if(G.name.equals(groupName)){
                currGroup = G;
                gFlag = true;
                break;
            }
        }
        if(!(gFlag&&uFlag)){
            System.out.println("Group Name or User Name is Invalid!!!\nCannot proceed Further!!");
            return;
        }
        for(user UG:currGroup.usersInGroup){
            if(currUser.name.equals(UG.name)){
                System.out.println("User "+currUser.name+" already exists in "+currGroup.name);
                return;
            }
        }
        currGroup.usersInGroup.add(currUser);
        System.out.println("User: "+currUser.name+" is successfully added to the group: "+currGroup.name);
    }
    public String toString(){
        return "Name "+name+" MailID "+mailId+" Password: "+password;
    }
}

import java.io.*;
import java.util.*;
public class BankFile {
    String acno="",dacno="",name="",fname="",mname="",phno="";
    int ob,option;
    char choice;
    public void input()throws IOException{

            do{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter A/c Number");
            acno = br.readLine();
            System.out.println("Enter Your Name");
            name = br.readLine();
            System.out.println("Enter Father's Name");
            fname = br.readLine();
            System.out.println("Enter Mother's Name");
            mname = br.readLine();
            System.out.println("Enter Phone Number");
            phno = br.readLine();
            System.out.println("Enter Your Opening Balance");
            ob = Integer.parseInt(br.readLine());
            write();

            System.out.println("Do You Wand To Open Another Account Press Y for Yes And N for No");
            choice = (char) br.read();
        }
        while (choice=='y'||choice=='Y');
        userchoice();
    }
    public void write() throws IOException {
        FileOutputStream op = new FileOutputStream("CustomerData", true);
        DataOutputStream dop = new DataOutputStream(op);

        if (ob >= 5000) {
        dop.writeChars("Customer Name:" + name + "\n");
        dop.writeChars("Father's Name:" + fname + "\n");
        dop.writeChars("Mother's Name:" + mname + "\n");
        dop.writeChars("Customer's Phone Number:" + phno + "\n\n");
        System.out.println("Your Account Opened Successfully:");
        FileOutputStream aop = new FileOutputStream(acno);
        DataOutputStream adp = new DataOutputStream(aop);
        adp.writeInt(ob);
        } else {
            System.out.println("Please Enter the Minimum Account Opening Balance");
        }
        dop.close();
    }

    public void userchoice()throws IOException{
        System.out.println("Enter Your Choice");
        System.out.println("Press 1 For Account Opening"+"\n"+"Press 2 For Amount Depositing"+"\n"+"Press 3 For Amount withdrawn"+"\n"+"Press 4 for Check Balance");
        BufferedReader br = new BufferedReader( new InputStreamReader(System.in));
        option=Integer.parseInt(br.readLine());
        switch (option){
            case 1:
                input();
                break;
            case 2:
                validate('d');
                break;
            case 3:
                validate('w');
                break;
            case 4:
                validate('c');
                break;
        }
    }

    public void validate(char ch) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter Account Number");
        dacno= br.readLine();
        File f = new File(dacno);
        if (f.exists()){
            switch(ch)
            {
                case 'd':
                    deposit();
                    break;
                case 'w':
                    withdrawn();
                    break;
                case 'c':
                    check();
                    break;
            }
        }
        else
        {
            System.out.println("Account not exists");
        }
    }

    public void deposit() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter Amount To Deposit");
        int amount=Integer.parseInt(br.readLine());
        FileInputStream fis = new FileInputStream(dacno);
        DataInputStream dis = new DataInputStream(fis);
        int amt=dis.readInt();
        dis.close();
        fis.close();
        amount=amount+amt;
        FileOutputStream op = new FileOutputStream(dacno);
        DataOutputStream dop = new DataOutputStream(op);
        dop.writeInt(amount);
        System.out.println("Total Avail Balance:"+" "+amount);
        userchoice();
    }

    public void withdrawn() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter Withdrawn Amount");
        int amount=Integer.parseInt(br.readLine());
        FileInputStream fis = new FileInputStream(dacno);
        DataInputStream dis = new DataInputStream(fis);
        int amt=dis.readInt();
        dis.close();
        fis.close();
        amount=amt-amount;
        if (amount>=5000){
            FileOutputStream op = new FileOutputStream(dacno);
            DataOutputStream dop = new DataOutputStream(op);
            dop.writeInt(amount);
        }
        else {
            System.out.println("Can't Withdrawn Hold Minimum Balance");
        }

        System.out.println("Total Avail Balance:" +amount);
        userchoice();
    }

    public void check() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        File f = new File(dacno);
        if (f.exists()) {
            FileInputStream fis = new FileInputStream(dacno);
            DataInputStream dis = new DataInputStream(fis);
            int balance = dis.readInt();
            dis.close();
            fis.close();
            System.out.println("Account Balance: " + balance);
        } else {
            System.out.println("Account does not exist.");
        }
        userchoice();
    }




    public static void main(String args[]) throws IOException {
        BankFile bf = new BankFile();
        bf.userchoice();
    }
}

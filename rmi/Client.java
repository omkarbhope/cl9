import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Client {
    public Client() throws RemoteException {
        super();
    }

    public static void main(String[] args) throws RemoteException {
        try {
            DTR stub = (DTR)Naming.lookup("rmi://localhost:5000/calculate");
            Scanner sc = new Scanner(System.in);

            int a,choice;
			while(true){
				System.out.println("\nEnter your choice");
				System.out.println("1. Degree to Radian");
				System.out.println("2. EXIT");
				choice= sc.nextInt();
				switch(choice)
				{
					case 1: 	System.out.println("Enter 1st number");
								a = sc.nextInt();
								System.out.println("Value of "+a+" in radian is "+stub.dtr(a));  
								break;
					case 2: 	System.exit(0);

				}
        } 
    }
    catch (Exception e) {
        // TODO Auto-generated catch block
        System.out.println(e);
    } 
}

}

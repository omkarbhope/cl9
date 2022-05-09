import calc_val.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import java.io.*;
public class calc_client
{
    static calc calcimpl;
    public static void main(String args[])
    {
      try
      {
        double result=0.0,num1=0.0,num2=0.0;

         // create and initialize the ORB	
        ORB orb=ORB.init(args,null);
        // get the root naming context
        org.omg.CORBA.Object objref=orb.resolve_initial_references("NameService");
        // Use NamingContextExt which is part of the Interoperable Naming Service (INS) specification.
        NamingContextExt ncref=NamingContextExtHelper.narrow(objref);

        String pathname="calc";
        calcimpl=calcHelper.narrow(ncref.resolve_str(pathname));


       int ch=1;

       while(ch!=0)
       {
        System.out.println("1. Home Loan");
        System.out.println("2. Personal Loan");
        System.out.println("3. Car Loan");
        System.out.println("4. Gold Loan");
        System.out.println("0. Exit");

        BufferedReader in1=new BufferedReader(new InputStreamReader(System.in));

        System.out.println("enter your choice: ");
        ch=Integer.parseInt(in1.readLine());

        if(ch==0)
          break;

        switch(ch)
        {
          case 1:
              result=calcimpl.getHomeLoan();
              break;

          case 2:
              result=calcimpl.getPersonalLoan();
              break;

          case 3:
              result=calcimpl.getCarLoan();
              break;

          case 4:
              result=calcimpl.getGoldLoan();
              break;

        }
              System.out.println("interest is: "+result + "%");

        }

      }
      catch(Exception e)
      {
         System.out.println(e);
      }
    }

}

import calc_val.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;

import java.io.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;

class serverimpl extends calcPOA
{
   private ORB orb;
   public void setorb(ORB orb_val)
   {
      orb=orb_val;
   }

   public float getHomeLoan()
   {
     return (float) 7.8;
   }
   public float getPersonalLoan()
   {
     return (float) 9.6;
   }
   public float getCarLoan()
   {
     return (float) 8.6;
   }
   public float getGoldLoan()
   {
     return (float) 8.4;
   }


}
public class calc_server
{
   public static void main(String args[])
   {
     try
     {
         //Create and initialize the ORB
         ORB orb=ORB.init(args,null);

          // get reference to rootpoa & activate the POAManager
         org.omg.CORBA.Object objref1=orb.resolve_initial_references("RootPOA");
         POA rootpoa=POAHelper.narrow(objref1);
         rootpoa.the_POAManager().activate();

          //create servant and register it with the ORB
         serverimpl serverobj=new serverimpl();
         serverobj.setorb(orb);
         
          // get object reference from the servant
         org.omg.CORBA.Object objref2=rootpoa.servant_to_reference(serverobj);
         calc href=calcHelper.narrow(objref2);

         // get the root naming context
         org.omg.CORBA.Object objref3= orb.resolve_initial_references("NameService");

          // Use NamingContextExt which is part of the Interoperable Naming Service (INS) specification.
         NamingContextExt ncref=NamingContextExtHelper.narrow(objref3);
         String pathname="calc";
         NameComponent path[]=ncref.to_name(pathname);

         // bind the Object Reference in Naming
         ncref.rebind(path,href);


         System.out.println("server ready and waiting...");
         // wait for invocations from clients
         orb.run();


     }
     catch(Exception e)
     {
       System.out.println(e);
     }
   }


}
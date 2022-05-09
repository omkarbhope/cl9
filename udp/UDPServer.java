import java.io.*;
import java.net.*;

class UDPServer
{
   public static void main(String args[]) throws Exception
      {
         DatagramSocket serverSocket = new DatagramSocket(9876);
            byte[] receiveData = new byte[1024];
            byte[] sendData = new byte[1024];
            System.out.println("SERVER started. Waiting for client to connect...");
            while(true)
               {
                  DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                  serverSocket.receive(receivePacket);
                  String sentence = new String( receivePacket.getData());
                  InetAddress IPAddress = receivePacket.getAddress();
                  System.out.println("RECEIVED received from "+IPAddress+": " + sentence);
                  int port = receivePacket.getPort();
                  String capitalizedSentence = sentence.toUpperCase();
                  System.out.println("SENDING result: " + capitalizedSentence);
                  sendData = capitalizedSentence.getBytes();
                  DatagramPacket sendPacket =
                  new DatagramPacket(sendData, sendData.length, IPAddress, port);
                  serverSocket.send(sendPacket);
               }
      }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interface;

// this class to recieve message from the tcp client

import static Interface.ClientFrame.OnlineUsersList;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import Interface.MessagePacket ;
import static Interface.TCPFrame.ServerOnlineUsers;
import static Interface.TCPFrame.ServerStatus;
import static Interface.TCPFrame.list1;

import java.util.HashSet;
/**
 *
 * @author Administrator
 */
public class ServerTCP extends Thread{
    int Server_port ;
    ServerSocket Server_S ;
    String Message_recieved ; 
    String online_Users_message;
    ArrayList<Socket> UserSockets = new ArrayList<Socket>();         // to store the sockets from the users
    public static ArrayList<MessagePacket> OnlineUsers = new ArrayList<MessagePacket>() ; // to store all the Messages data
    public ServerTCP(int Port){
        this.Server_port = Port ;
        try
        {
            Server_S = new ServerSocket(Server_port);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    
    @Override
    public void run(){
        try{
            while(true){
                online_Users_message = "" ;
                Socket Con_Soc = Server_S.accept();
                UserSockets.add(Con_Soc);
                BufferedReader Client_input = new BufferedReader (new InputStreamReader(Con_Soc.getInputStream()));
                DataOutputStream MSG_To_Client = new DataOutputStream(Con_Soc.getOutputStream());
                
                Message_recieved = Client_input.readLine();
                String []Message = Message_recieved.split(",");
                
                if(Message.length == 4)
                {
                    String []Users = ServerOnlineUsers.getItems();
                    int index = 0 ;
                    for(int i = 0 ; i < Users.length; i++)
                    {
                        String []part=Users[i].split(",", 3);
                        if(part[0].equals(Message[0]))
                        {
                            index = i ;
                        }
                        else 
                            continue;
                    }
                    ServerOnlineUsers.remove(index);
                    OnlineUsers.remove(index);
                    for (Socket X : UserSockets)
                    {
                        System.out.println(X);
                        DataOutputStream Send = new DataOutputStream(X.getOutputStream());
                        Send.writeBytes(index + "booboo\n");
                    }
                    //MSG_To_Client.writeBytes(index+"\n");
                    //ServerStatus.setText("User with IP: " + Message[1] + " and Port: " + Message[2] + " is now offline");
               list1.add( Message[0]+"  with IP: " + Message[1] + " and Port: " + Message[2] + " is now offline");
                for(int j=0;j<list1.getItemCount();j++){
                       String s=list1.getItem(j);
                       if(s.equals(Message[0]+" with IP: " + Message[1] + " and Port: " + Message[2] + " is now online")){
                           list1.remove(j);
                       }
                   }
                } // Logout request 
                
                else 
                {
                   // ServerStatus.setText("User with IP: " + Message[1] + " and Port: " + Message[2] + " is now online");
                   list1.add( Message[0]+" with IP: " + Message[1] + " and Port: " + Message[2] + " is now online");
                   for(int j=0;j<list1.getItemCount();j++){
                       String s=list1.getItem(j);
                       if(s.equals( Message[0]+"  with IP: " + Message[1] + " and Port: " + Message[2] + " is now offline")){
                           list1.remove(j);
                       }
                   }
                    ServerOnlineUsers.add(Message[0]+","+Message[1]+","+Message[2]);
                    MessagePacket M = new MessagePacket(Message[0] , Message[1] , Message[2]);
                    OnlineUsers.add(M);
                    
                    for (MessagePacket MP : OnlineUsers){
                        online_Users_message += MP.username + "," + MP.IP + "," + MP.PORT + "---" ;
                    }
                    System.out.println(online_Users_message);
                    for (Socket X : UserSockets){
                        System.out.println(X);
                        DataOutputStream Send = new DataOutputStream(X.getOutputStream());
                   Send.writeBytes(online_Users_message + "\n");
                    }
                    //MSG_To_Client.writeBytes(Message_recieved+"\n");
                } // Login request
                    HashSet<String> set = new HashSet<>();
        for (int i = 0; i < list1.getItemCount(); i++) {
            String item = list1.getItem(i);
            if (!set.contains(item)) {
                set.add(item);
            }
        }
        list1.removeAll();
        for (String item : set) {
            list1.add(item);
        }
        /////////////////////////////////////////////////
           HashSet<String> set1 = new HashSet<>();
        for (int i = 0; i <  ServerOnlineUsers.getItemCount(); i++) {
            String item =  ServerOnlineUsers.getItem(i);
            if (!set1.contains(item)) {
                set1.add(item);
            }
        }
         ServerOnlineUsers.removeAll();
        for (String item : set1) {
             ServerOnlineUsers.add(item);
        }
            }
        }catch(Exception e){
            System.out.println("error");
            e.printStackTrace();
        }
    }
    
}
// send all the online users to the last user has logged in and then send the online users by udp from the last users to all the connected users he can see
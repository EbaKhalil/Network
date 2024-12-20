/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interface;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import Interface.* ;
import static Interface.ClientFrame.OnlineUsersList;
import static Interface.ClientFrame.UsernameTF;
import static Interface.ClientFrame.chatbox;
import static Interface.ClientFrame.messagesBox;
import static Interface.ClientFrame.model;
import static Interface.ClientFrame.online_User_Name;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import javax.swing.JList;
//import static Interface.ClientFrame.Peer_online_Users;
//import static Interface.ClientFrame.online_User_Name ;
/**
 *
 * @author Administrator
 */
public class LoginThread extends Thread {
    String Message ;
    String TCP_IP ;
    int TCP_Port ;
    //String USER ;
    public LoginThread(String Mess ,String IP , int Port) {
        this.TCP_IP = IP ;
        this.TCP_Port = Port ;
        this.Message = Mess ;
    }

    @Override
    public void run() {
        try 
        {
            Socket Peer_send = new Socket(TCP_IP, TCP_Port); 
            DataOutputStream MSG_TO_Server = new DataOutputStream(Peer_send.getOutputStream());
            BufferedReader Server_input = new BufferedReader(new InputStreamReader(Peer_send.getInputStream()));
                
            MSG_TO_Server.writeBytes(Message.trim() + "\n");
//            System.out.println("hiiiiiiiiiiiiiiii");
            
            online_User_Name = new ArrayList<MessagePacket>() ;
            String MSG_From_server = Server_input.readLine();
            System.out.println(MSG_From_server);
            String []Message_server = MSG_From_server.split("---");
            OnlineUsersList.removeAll();
            for(String Mess : Message_server){
                String []Online = Mess.split(",");
                MessagePacket MP = new MessagePacket(Online[0] , Online[1] , Online[2]);
                //online_User_Name.add(MP);
              //  String o=MP.username+","+MP.IP+","+MP.PORT;
               OnlineUsersList.add(MP.username+","+MP.IP+","+MP.PORT);
         
   
               
                
            }
            
            
         HashSet<String> set = new HashSet<>();
        for (int i = 0; i < OnlineUsersList.getItemCount(); i++) {
            String item = OnlineUsersList.getItem(i);
            if (!set.contains(item)) {
                set.add(item);
            }
        }
        OnlineUsersList.removeAll();
        for (String item : set) {
            String []part=item.split(",",3);
            MessagePacket f=new MessagePacket(part[0],part[1],part[2]);
            OnlineUsersList.add(item);
            online_User_Name.add(f);
        }
            
          
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
  
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interface;

import static Interface.ClientFrame.OnlineUsersList;
import static Interface.ClientFrame.online_User_Name;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashSet;

/**
 *
 * @author Administrator
 */
public class LogoutThread extends Thread {
    String Message ;
    String TCP_IP ;
    int TCP_Port ;
    boolean LOGOUT;
    public LogoutThread(String Mess ,String IP , int Port , boolean Logout){
        this.TCP_IP = IP ;
        this.TCP_Port = Port ;
        this.Message = Mess ;
        this.LOGOUT = Logout;
    }

    @Override
    public void run() {
         try {
            Socket Peer_send = new Socket(TCP_IP, TCP_Port); 
            DataOutputStream MSG_TO_Server = new DataOutputStream(Peer_send.getOutputStream());
            BufferedReader Server_input = new BufferedReader(new InputStreamReader(Peer_send.getInputStream()));
                
            MSG_TO_Server.writeBytes(Message + "," + LOGOUT + "\n");
            String msg_Received = Server_input.readLine();
            int index = Integer.parseInt(msg_Received);
            online_User_Name.remove(index);
            OnlineUsersList.remove(index);
            
              HashSet<String> set = new HashSet<>();
        for (int i = 0; i < OnlineUsersList.getItemCount(); i++) {
            String item = OnlineUsersList.getItem(i);
            if (!set.contains(item)) {
                set.add(item);
            }
        }
        OnlineUsersList.removeAll();
        online_User_Name.clear();
        for (String item : set) {
            String []part=item.split(",",3);
            MessagePacket f=new MessagePacket(part[0],part[1],part[2]);
            OnlineUsersList.add(item);
            online_User_Name.add(f);
        }
            Peer_send.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
}

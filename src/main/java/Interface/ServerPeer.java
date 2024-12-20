/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interface;

import static Interface.ClientFrame.OnlineUsersList;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import static Interface.ClientFrame.Status;  
import static Interface.ClientFrame.UsernameTF;            // import the label that will indicate the user fromwhich user data has been recieved
// import the label that will indicate the user fromwhich user data has been recieved
import static Interface.ClientFrame.chatbox;
import static Interface.ClientFrame.model;
import static Interface.ClientFrame.messagesBox;

 import static Interface.ClientFrame.remotePort;
import java.awt.Color;
import java.awt.Component;
import static java.awt.SystemColor.text;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
/**
 *
 * @author Administrator
 */
public class ServerPeer extends Thread{
    int Loc_port ;
    String IP_ADD ;
    byte[] Client_msg = new byte[1024]; 
    int Client_PORT ;
    String chat_box_msg ;
     DatagramSocket Server_DS;
     
 
    
    
    public ServerPeer(String Server_IP , int Server_Port){ // Runnable method of the class to pass the parameter to the thread
        this.IP_ADD = Server_IP ;
        this.Loc_port = Server_Port;
       
        try {
            Server_DS  = new DatagramSocket(Server_Port);    // initalize a socket to recieve the data from any client
        } catch (Exception e) {
            e.printStackTrace();
        }   
    }

    @Override
    public void run() {
        try{
            while(true){
            
          
                            

                 String Username = UsernameTF.getText();
                DatagramPacket Receieved_Client = new DatagramPacket(Client_msg , Client_msg.length);
                Server_DS.receive(Receieved_Client);
          
                chat_box_msg = new String(Receieved_Client.getData()).trim();
                Client_msg = new byte[1024];
                if(chat_box_msg!=null){
                   
                    // MESSAGE RECIEVED 
                                  String username=" ";

                    String [] NET_DATA = chat_box_msg.split(" , ");
                    if(NET_DATA.length==5){
                    Status.setText("Data has been recieved from => IP: " +NET_DATA[0]+", Port: "+NET_DATA[1]);
                    System.out.println("This is the server side");
           
                           model.addElement( NET_DATA[4]+ " : " + NET_DATA[2] +"     "+NET_DATA[3]+"\n");
                                 chatbox.setCellRenderer(new CustomListCellRenderer());
                 
                    }
                    else if(NET_DATA.length==4){
                         Status.setText("Data has been recieved from => IP: " +NET_DATA[0]+", Port: "+NET_DATA[1]);
                    System.out.println("This is the server side");
           
                           model.addElement( "rem"+ " : " + NET_DATA[2] +"     "+NET_DATA[3]+"\n");
                                 chatbox.setCellRenderer(new CustomListCellRenderer());
                 
                    }
                    else if(NET_DATA.length==7){
                     int i=  Integer.parseInt(NET_DATA[6]);
                         model.removeElementAt(i);

                        
                     }
                     else if(NET_DATA.length==6){
                         
                         model.clear();
                     }
                     
                    
                
                     else if(NET_DATA.length==3){
                 OnlineUsersList.remove(NET_DATA[2]);
                     } 
                    else if(NET_DATA.length==8){
                 OnlineUsersList.add(NET_DATA[7]);
                     } 
             
            }
           
          
            
            
          
          

            
            }
        }catch(Exception e){
            e.printStackTrace();
        }  
    }
    
    
    
   
    
    
    public String Retrieve_IP(){
        return IP_ADD ;
    }
    public int Retrieve_Port(){
        return Client_PORT ;
    }
    public String Retrieve_MSG(){
        return chat_box_msg ;
    }
    
    
    
    
     
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interface;

/**
 *
 * @author Administrator
 */
public class MessagePacket {
    String username ;
    String IP ;
    String PORT ;
    MessagePacket(String name , String ip , String port){
        this.username = name ;
        this.IP = ip ;
        this.PORT = port ;
    }
      public  void set_username(String username){
          this.username=username;
    }
        public  void set_ip(String ip){
          this.IP=ip;
    }
          public  void set_port(String port){
          this.PORT=port;
    }
}

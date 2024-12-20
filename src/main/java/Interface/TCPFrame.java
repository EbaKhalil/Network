/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interface;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Enumeration;
import Interface.MessagePacket;
import javax.swing.DefaultListModel;

/**
 *  
 * @author Administrator
 */
public class TCPFrame extends javax.swing.JFrame {
    
    public TCPFrame() {
        initComponents();
        ListNets();
    }
    
    public void ListNets(){     
        String ip ;
        ArrayList<String> Net_interfaces = new ArrayList<String>();     // use to store the activated interfaces with there ipv4
        try{
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) 
            {
                NetworkInterface Net_int = interfaces.nextElement();
                if (Net_int.isLoopback() || !Net_int.isUp())  // filters out localhost and any inactive interfaces
                    continue;

                Enumeration<InetAddress> addresses = Net_int.getInetAddresses();
                while(addresses.hasMoreElements())
                {
                    InetAddress addr = addresses.nextElement();

                    if (addr instanceof Inet6Address) continue; // remove the IPv6 from the address (network interface)

                    ip = addr.getHostAddress();
                    if(Net_int.getName().contains("wlan"))
                    {     // if the hostname contains wlanx such x is number equal 1 or larger then it will be wifi interface so host nname will be change
                        String wlan = "Wifi";               
                        Net_interfaces.add(wlan);           // add the interface new hostname 
                        Net_interfaces.add(ip);             // add the interface ip
                    }
                }
            }
            for(int i = 0 ; i < Net_interfaces.size() ; i+=2)
            {  // loop on each 2 consecutive values, and concatenate them to represent the network interface
                String Name = Net_interfaces.get(i);
                String ipv4 = Net_interfaces.get(i+1);
                String Net_INT = Name + " : " + ipv4 ;
                Server_IP_List.addItem(Net_INT);        // add the network interface to combobox
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        StartListeningButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        Server_Port = new javax.swing.JTextField();
        Server_IP_List = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ServerStatus = new javax.swing.JLabel();
        ServerOnlineUsers = new java.awt.List();
        list1 = new java.awt.List();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TCP Server");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 204, 204));
        jPanel1.setForeground(new java.awt.Color(51, 51, 51));
        jPanel1.setPreferredSize(new java.awt.Dimension(649, 579));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        StartListeningButton.setBackground(new java.awt.Color(255, 204, 204));
        StartListeningButton.setFont(new java.awt.Font("Lucida Bright", 0, 15)); // NOI18N
        StartListeningButton.setText("Start Listening");
        StartListeningButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        StartListeningButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StartListeningButtonActionPerformed(evt);
            }
        });
        jPanel1.add(StartListeningButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 180, 40));

        jLabel1.setBackground(new java.awt.Color(51, 51, 51));
        jLabel1.setFont(new java.awt.Font("Lucida Bright", 0, 15)); // NOI18N
        jLabel1.setText("Port:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 20, 40, 40));

        Server_Port.setBackground(new java.awt.Color(255, 204, 204));
        Server_Port.setFont(new java.awt.Font("Lucida Bright", 0, 15)); // NOI18N
        Server_Port.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Server_Port.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Server_PortActionPerformed(evt);
            }
        });
        jPanel1.add(Server_Port, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 20, 210, 40));

        Server_IP_List.setBackground(new java.awt.Color(255, 204, 204));
        Server_IP_List.setFont(new java.awt.Font("Lucida Bright", 0, 16)); // NOI18N
        Server_IP_List.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(Server_IP_List, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 460, 40));

        jLabel2.setBackground(new java.awt.Color(51, 51, 51));
        jLabel2.setFont(new java.awt.Font("Lucida Bright", 0, 14)); // NOI18N
        jLabel2.setText("Online Users:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 140, 200, 30));

        jLabel3.setBackground(new java.awt.Color(51, 51, 51));
        jLabel3.setFont(new java.awt.Font("Lucida Bright", 0, 15)); // NOI18N
        jLabel3.setText("Status : ");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 520, 60, 40));

        ServerStatus.setBackground(new java.awt.Color(255, 204, 204));
        ServerStatus.setFont(new java.awt.Font("Lucida Bright", 0, 15)); // NOI18N
        jPanel1.add(ServerStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 520, 450, 40));

        ServerOnlineUsers.setBackground(new java.awt.Color(255, 204, 204));
        ServerOnlineUsers.setForeground(new java.awt.Color(0, 0, 0));
        ServerOnlineUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ServerOnlineUsersActionPerformed(evt);
            }
        });
        jPanel1.add(ServerOnlineUsers, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 170, 230, 340));

        list1.setBackground(new java.awt.Color(255, 204, 204));
        list1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                list1ActionPerformed(evt);
            }
        });
        jPanel1.add(list1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 350, 340));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, 590));

        setSize(new java.awt.Dimension(649, 593));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void StartListeningButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StartListeningButtonActionPerformed
 String s=Server_IP_List.getItemAt(Server_IP_List.getSelectedIndex());
        String []part=s.split(" : ",2);
        ServerStatus.setText("Address: "+part[1]+" port: "+Server_Port.getText());        
        String Ser_port = Server_Port.getText();
        int Server_port = Integer.parseInt(Ser_port);
        
        ServerTCP ST = new ServerTCP(Server_port);
        ST.start();
    
    }//GEN-LAST:event_StartListeningButtonActionPerformed

    private void Server_PortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Server_PortActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_Server_PortActionPerformed

    private void ServerOnlineUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ServerOnlineUsersActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ServerOnlineUsersActionPerformed

    private void list1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_list1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_list1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) { 
       
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TCPFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TCPFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TCPFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TCPFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TCPFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static java.awt.List ServerOnlineUsers;
    public static javax.swing.JLabel ServerStatus;
    private javax.swing.JComboBox<String> Server_IP_List;
    private static javax.swing.JTextField Server_Port;
    private javax.swing.JButton StartListeningButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    public static java.awt.List list1;
    // End of variables declaration//GEN-END:variables
}

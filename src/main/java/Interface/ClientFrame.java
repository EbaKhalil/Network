/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interface;
import java.util.HashSet;

import javax.swing.Timer;
import static Interface.TCPFrame.list1;
import static Interface.TCPFrame.ServerOnlineUsers;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;



public class ClientFrame extends javax.swing.JFrame {
     static DefaultListModel<String> model = new DefaultListModel<>();

    Scanner scanner=new Scanner(System.in);
    public static ArrayList<MessagePacket> online_User_Name = new ArrayList<MessagePacket>() ; 
    public static ArrayList<MessagePacket> listWithoutDuplicates;
    String Server_IP ;
    String Client_IP ;
    int Server_port ;
    int Client_port ;
   ServerPeer SP ;
    int counter=0;
    //servepeer SP2;
    public ClientFrame() {
        initComponents();
        chatbox.setModel(model);
                ListNets();
         

         //corlist();

      
    
    }
    
    public void ListNets(){     
        String ip ;
        ArrayList<String> Net_interfaces = new ArrayList<String>();     // use to store the activated interfaces with there ipv4
        try{
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
            NetworkInterface Net_int = interfaces.nextElement();
       
            if (Net_int.isLoopback() || !Net_int.isUp())  // filters out localhost and any inactive interfaces
                continue;

            Enumeration<InetAddress> addresses = Net_int.getInetAddresses();
                while(addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();

                    if (addr instanceof Inet6Address) continue; // remove the IPv6 from the address (network interface)

                    ip = addr.getHostAddress();
                    if(Net_int.getName().contains("wlan")){     // if the hostname contains wlanx such x is number equal 1 or larger then it will be wifi interface so host nname will be change
                        String wlan = "Wifi";               
                        Net_interfaces.add(wlan);           // add the interface new hostname 
                        Net_interfaces.add(ip);             // add the interface ip
                    }
                    else if(Net_int.getName().contains("eth")){ // if the hostname contains ethx such x is number equal 1 or larger then it will be ethernet interface so host nname will be change
                        String eth = "Ethernet";
                        Net_interfaces.add(eth);            // add the interface new hostname
                        Net_interfaces.add(ip);             // add the interface ip
                    }
                    else if(Net_int.getName().contains("enp")){ // if the hostname contains ethx such x is number equal 1 or larger then it will be ethernet interface so host nname will be change
                        String enp = "VM_Wifi";
                        Net_interfaces.add(enp);            // add the interface new hostname
                        Net_interfaces.add(ip);             // add the interface ip
                    }
                }
            }
            for(int i = 0 ; i < Net_interfaces.size() ; i+=2){  // loop on each 2 consecutive values, and concatenate them to represent the network interface
                String Name = Net_interfaces.get(i);
                String ipv4 = Net_interfaces.get(i+1);
                String Net_INT = Name + " : " + ipv4 ;
                Interface_List.addItem(Net_INT);        // add the network interface to combobox
                
//                Local_port.setText(Integer.toString(local_port));
//                Local_port.setEditable(false);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    } 
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Main_frame = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        UsernameTF = new javax.swing.JTextField();
        logoutbutton = new javax.swing.JButton();
        testConnectionButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        messagesBox = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tcpPort = new javax.swing.JTextField();
        remotePort = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        Interface_List = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        tcpIP = new javax.swing.JTextField();
        localIP = new javax.swing.JTextField();
        localPort = new javax.swing.JTextField();
        remoteIP = new javax.swing.JTextField();
        loginbutton = new javax.swing.JButton();
        sendMessageButton = new javax.swing.JButton();
        Status = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        OnlineUsersList = new java.awt.List(20 , false);
        Send_to_all = new javax.swing.JButton();
        update = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        chatbox = new javax.swing.JList<>();
        delete_all = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        PasswordTF = new javax.swing.JPasswordField();
        showPassword = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Client Frame");
        setBackground(new java.awt.Color(51, 51, 51));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Main_frame.setBackground(new java.awt.Color(255, 204, 204));
        Main_frame.setForeground(new java.awt.Color(204, 204, 204));
        Main_frame.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(255, 204, 204));
        jLabel1.setFont(new java.awt.Font("Lucida Bright", 0, 14)); // NOI18N
        jLabel1.setText("User Name : ");
        Main_frame.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 90, 30));

        UsernameTF.setBackground(new java.awt.Color(255, 204, 204));
        UsernameTF.setFont(new java.awt.Font("Lucida Bright", 0, 14)); // NOI18N
        UsernameTF.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Main_frame.add(UsernameTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 190, 30));

        logoutbutton.setBackground(new java.awt.Color(255, 204, 204));
        logoutbutton.setFont(new java.awt.Font("Lucida Bright", 0, 14)); // NOI18N
        logoutbutton.setText("Logout");
        logoutbutton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        logoutbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutbuttonActionPerformed(evt);
            }
        });
        Main_frame.add(logoutbutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 20, 110, 30));

        testConnectionButton.setBackground(new java.awt.Color(255, 204, 204));
        testConnectionButton.setFont(new java.awt.Font("Lucida Bright", 0, 14)); // NOI18N
        testConnectionButton.setText("Test Connection");
        testConnectionButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        testConnectionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Test_Connection_Action(evt);
            }
        });
        Main_frame.add(testConnectionButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 410, 140, 50));

        messagesBox.setBackground(new java.awt.Color(255, 204, 204));
        messagesBox.setColumns(20);
        messagesBox.setFont(new java.awt.Font("Lucida Bright", 0, 15)); // NOI18N
        messagesBox.setRows(3);
        messagesBox.setText("enter your text ..");
        messagesBox.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        messagesBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                messagesBoxMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                messagesBoxMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(messagesBox);

        Main_frame.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 570, 90));

        jLabel2.setBackground(new java.awt.Color(255, 204, 204));
        jLabel2.setFont(new java.awt.Font("Lucida Bright", 0, 14)); // NOI18N
        jLabel2.setText("Status : ");
        Main_frame.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 460, 60, 40));

        jSeparator1.setBackground(new java.awt.Color(51, 51, 51));
        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Main_frame.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 3, -1, 550));

        jLabel3.setBackground(new java.awt.Color(255, 204, 204));
        jLabel3.setFont(new java.awt.Font("Lucida Bright", 0, 15)); // NOI18N
        jLabel3.setText("Available Interface : ");
        Main_frame.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 100, -1, 30));

        jLabel4.setBackground(new java.awt.Color(255, 204, 204));
        jLabel4.setFont(new java.awt.Font("Lucida Bright", 0, 15)); // NOI18N
        jLabel4.setText("Remote Port : ");
        Main_frame.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 370, -1, 30));

        tcpPort.setBackground(new java.awt.Color(255, 204, 204));
        tcpPort.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tcpPort.setPreferredSize(new java.awt.Dimension(112, 19));
        Main_frame.add(tcpPort, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 60, 170, 30));

        remotePort.setBackground(new java.awt.Color(255, 204, 204));
        remotePort.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        remotePort.setPreferredSize(new java.awt.Dimension(112, 19));
        remotePort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                remotePortActionPerformed(evt);
            }
        });
        Main_frame.add(remotePort, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 370, 170, 30));

        jLabel5.setBackground(new java.awt.Color(255, 204, 204));
        jLabel5.setFont(new java.awt.Font("Lucida Bright", 0, 15)); // NOI18N
        jLabel5.setText("TCP Server Port : ");
        Main_frame.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 60, -1, 30));

        Interface_List.setBackground(new java.awt.Color(255, 204, 204));
        Interface_List.setFont(new java.awt.Font("Lucida Bright", 0, 16)); // NOI18N
        Interface_List.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Interface_List.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Interface_ListActionPerformed(evt);
            }
        });
        Main_frame.add(Interface_List, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 140, 300, 50));

        jLabel6.setBackground(new java.awt.Color(255, 204, 204));
        jLabel6.setFont(new java.awt.Font("Lucida Bright", 0, 15)); // NOI18N
        jLabel6.setText("Online Users :");
        Main_frame.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 20, -1, 30));

        jLabel7.setBackground(new java.awt.Color(255, 204, 204));
        jLabel7.setFont(new java.awt.Font("Lucida Bright", 0, 15)); // NOI18N
        jLabel7.setText("Local IP : ");
        Main_frame.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 220, -1, 30));

        jLabel8.setBackground(new java.awt.Color(255, 204, 204));
        jLabel8.setFont(new java.awt.Font("Lucida Bright", 0, 15)); // NOI18N
        jLabel8.setText("Local Port : ");
        Main_frame.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 260, -1, 30));

        jLabel9.setBackground(new java.awt.Color(255, 204, 204));
        jLabel9.setFont(new java.awt.Font("Lucida Bright", 0, 15)); // NOI18N
        jLabel9.setText("Remote IP : ");
        Main_frame.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 330, -1, 30));

        tcpIP.setBackground(new java.awt.Color(255, 204, 204));
        tcpIP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tcpIP.setPreferredSize(new java.awt.Dimension(112, 19));
        Main_frame.add(tcpIP, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 20, 170, 30));

        localIP.setBackground(new java.awt.Color(255, 204, 204));
        localIP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        localIP.setEnabled(false);
        localIP.setPreferredSize(new java.awt.Dimension(112, 19));
        localIP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                localIPActionPerformed(evt);
            }
        });
        Main_frame.add(localIP, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 220, 170, 30));

        localPort.setBackground(new java.awt.Color(255, 204, 204));
        localPort.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        localPort.setPreferredSize(new java.awt.Dimension(112, 19));
        Main_frame.add(localPort, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 260, 170, 30));

        remoteIP.setBackground(new java.awt.Color(255, 204, 204));
        remoteIP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        remoteIP.setPreferredSize(new java.awt.Dimension(112, 19));
        Main_frame.add(remoteIP, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 330, 170, 30));

        loginbutton.setBackground(new java.awt.Color(255, 204, 204));
        loginbutton.setFont(new java.awt.Font("Lucida Bright", 0, 14)); // NOI18N
        loginbutton.setText("Login");
        loginbutton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        loginbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginbuttonActionPerformed(evt);
            }
        });
        Main_frame.add(loginbutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, 110, 30));

        sendMessageButton.setBackground(new java.awt.Color(255, 204, 204));
        sendMessageButton.setFont(new java.awt.Font("Lucida Bright", 0, 16)); // NOI18N
        sendMessageButton.setText("Send Message");
        sendMessageButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        sendMessageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendMessageButtonActionPerformed(evt);
            }
        });
        Main_frame.add(sendMessageButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 410, 150, 50));

        Status.setBackground(new java.awt.Color(255, 204, 204));
        Status.setFont(new java.awt.Font("Lucida Bright", 0, 14)); // NOI18N
        Main_frame.add(Status, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 460, 500, 40));

        jLabel11.setBackground(new java.awt.Color(255, 204, 204));
        jLabel11.setFont(new java.awt.Font("Lucida Bright", 0, 15)); // NOI18N
        jLabel11.setText("TCP Server IP : ");
        Main_frame.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 20, -1, 30));

        OnlineUsersList.setBackground(new java.awt.Color(255, 204, 204));
        OnlineUsersList.setFont(new java.awt.Font("Lucida Bright", 0, 15)); // NOI18N
        OnlineUsersList.setForeground(new java.awt.Color(0, 0, 0));
        OnlineUsersList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                OnlineUsersListMouseClicked(evt);
            }
        });
        OnlineUsersList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OnlineUsersListActionPerformed(evt);
            }
        });
        Main_frame.add(OnlineUsersList, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 60, 170, 440));

        Send_to_all.setBackground(new java.awt.Color(255, 204, 204));
        Send_to_all.setFont(new java.awt.Font("Lucida Bright", 0, 15)); // NOI18N
        Send_to_all.setText("Send to all");
        Send_to_all.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Send_to_all.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Send_to_allActionPerformed(evt);
            }
        });
        Main_frame.add(Send_to_all, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 470, 210, 30));

        update.setBackground(new java.awt.Color(255, 204, 204));
        update.setFont(new java.awt.Font("Lucida Bright", 0, 14)); // NOI18N
        update.setText("update");
        update.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });
        Main_frame.add(update, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 510, 110, 30));

        delete.setBackground(new java.awt.Color(255, 204, 204));
        delete.setFont(new java.awt.Font("Lucida Bright", 0, 14)); // NOI18N
        delete.setText("delete");
        delete.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });
        Main_frame.add(delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 510, 110, 30));

        jScrollPane1.setBackground(new java.awt.Color(240, 204, 204));

        chatbox.setBackground(new java.awt.Color(255, 204, 204));
        chatbox.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        chatbox.setFont(new java.awt.Font("Lucida Bright", 0, 15)); // NOI18N
        chatbox.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        chatbox.setVisibleRowCount(30);
        jScrollPane1.setViewportView(chatbox);

        Main_frame.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 570, 210));

        delete_all.setBackground(new java.awt.Color(255, 204, 204));
        delete_all.setFont(new java.awt.Font("Lucida Bright", 0, 14)); // NOI18N
        delete_all.setText("delete all");
        delete_all.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        delete_all.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_allActionPerformed(evt);
            }
        });
        Main_frame.add(delete_all, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 510, 110, 30));

        jLabel10.setBackground(new java.awt.Color(255, 204, 204));
        jLabel10.setFont(new java.awt.Font("Lucida Bright", 0, 14)); // NOI18N
        jLabel10.setText("Password :");
        Main_frame.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 90, 30));

        PasswordTF.setBackground(new java.awt.Color(255, 204, 204));
        PasswordTF.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        PasswordTF.setEchoChar('\u23FA');
        Main_frame.add(PasswordTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 62, 190, 30));

        showPassword.setBackground(new java.awt.Color(255, 204, 204));
        showPassword.setFont(new java.awt.Font("Lucida Bright", 0, 14)); // NOI18N
        showPassword.setText("show password");
        showPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showPasswordMouseClicked(evt);
            }
        });
        showPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showPasswordActionPerformed(evt);
            }
        });
        Main_frame.add(showPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 70, -1, -1));

        getContentPane().add(Main_frame, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1150, 550));

        setSize(new java.awt.Dimension(1166, 586));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void Interface_ListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Interface_ListActionPerformed
        // TODO add your handling code here:
        String network_int = Interface_List.getSelectedItem().toString(); 
        String []separate = network_int.split(" : ");   // separate the interface host name from its own ipv4
        System.out.println(separate[0]+"\n"+separate[1]);
        localIP.setText(separate[1]);  // set the IP address in the local IP text field
        localIP.setForeground(new Color(0,0,0));
    }//GEN-LAST:event_Interface_ListActionPerformed

    private void messagesBoxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_messagesBoxMouseClicked
        messagesBox.setText("");      // clear the data found
    }//GEN-LAST:event_messagesBoxMouseClicked

    private void sendMessageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendMessageButtonActionPerformed
if(OnlineUsersList.getItemCount()!=0){

         int f=0;
  
for(int i=0;i<OnlineUsersList.getItemCount();i++){
String s= OnlineUsersList.getItem(i);
           String []part=s.split(",", 3);
    if(part[0].equals(UsernameTF.getText())){
        f=1;
        break;
    }
    }
if(f==1){


     
        try
        {
            String locport=localPort.getText();
     int loc=Integer.parseInt( locport);
     String locip=localIP.getText();
     SP = new ServerPeer(locip ,loc);
           SP.start();
            LocalDateTime localDateTime = LocalDateTime.now();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
            String formatterDateTime = dateTimeFormatter.format(localDateTime);
            String sent_message = messagesBox.getText();
            String client_port = remotePort.getText();
            Client_IP = remoteIP.getText();
            Client_port = Integer.parseInt(client_port);

            String Message = Server_IP + " , " + Server_port + " , " + sent_message + " , " + formatterDateTime;
            ClientPeer CP = new ClientPeer(Message , Client_IP , Client_port);
            CP.start();
           
            String s="ME" +" : "+ sent_message + "     "+formatterDateTime+"\n";
           // chatbox.setModel(model);
              model.addElement(s);
         chatbox.setCellRenderer(new CustomListCellRenderer());
                      repaint();
   
            sent_message = "";   // to reset the message to null
            
        }
        catch(Exception ex)
        {
            System.out.println(counter++);
            ex.printStackTrace();
        }
    
                  

 messagesBox.setText("");
}
else{
       JOptionPane.showMessageDialog(null, "can't send message the user logout");


}
}
else{
    
        try
        {
            String locport=localPort.getText();
     int loc=Integer.parseInt( locport);
     String locip=localIP.getText();
     SP = new ServerPeer(locip ,loc);
           SP.start();
            LocalDateTime localDateTime = LocalDateTime.now();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
            String formatterDateTime = dateTimeFormatter.format(localDateTime);
            String sent_message = messagesBox.getText();
            String client_port = remotePort.getText();
            Client_IP = remoteIP.getText();
            Client_port = Integer.parseInt(client_port);

            String Message = Server_IP + " , " + Server_port + " , " + sent_message + " , " + formatterDateTime;
            ClientPeer CP = new ClientPeer(Message , Client_IP , Client_port);
            CP.start();
           
            String s="ME" +" : "+ sent_message + "     "+formatterDateTime+"\n";
           // chatbox.setModel(model);
              model.addElement(s);
         chatbox.setCellRenderer(new CustomListCellRenderer());
                      repaint();
   
            sent_message = "";   // to reset the message to null
            
        }
        catch(Exception ex)
        {
            System.out.println(counter++);
            ex.printStackTrace();
        }
    
                  

 messagesBox.setText("");

    
}


    }//GEN-LAST:event_sendMessageButtonActionPerformed

    private void Test_Connection_Action(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Test_Connection_Action
        // this method is used to run the server peer thread, so it can communicate with the other peer when connect.
            repaint();

        String Ser_port = localPort.getText();
        Server_port = Integer.parseInt(Ser_port);
        Server_IP = localIP.getText() ;
 
       SP = new ServerPeer(Server_IP , Server_port);
        SP.start();
                            repaint();

    }//GEN-LAST:event_Test_Connection_Action

    private void loginbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginbuttonActionPerformed
                   repaint();
        try 
        {
            // this button is for login (send TCP data)
            if(!tcpIP.getText().isEmpty())
            {
                String Ser_port = localPort.getText();
                Server_port = Integer.parseInt(Ser_port);
                Server_IP = localIP.getText() ;
               SP = new ServerPeer(Server_IP , Server_port);
                SP.start();
            }
            File file;
            ArrayList<String> Data=new ArrayList<String>();
            
            FileReader filereader;
            String[] myarray = null;
            ArrayList<String> Usernames=new ArrayList<String>();
            ArrayList<String> Password=new ArrayList<String>();

            String clientSentence;
            
            file = new File("HW2.txt");
            Scanner myReader = new Scanner(file);
            
            
            
            filereader = new FileReader("HW2.txt");
            
            while (myReader.hasNextLine()) {
                Data.add(myReader.nextLine());
            }
            
            myReader.close();
            
            for (int y = 0; y < Data.size(); y++) {
                myarray = Data.get(y).split(" ");
                Usernames.add(myarray[0]);
                 Password.add(myarray[1]);

            }
            int i;
            for(i=0;i<Usernames.size();i++)
            {
                if(Usernames.get(i).equals(UsernameTF.getText())&&Password.get(i).equals(String.valueOf(PasswordTF.getPassword())))
                {
                     JOptionPane.showMessageDialog(null, "Logged in successful");
                    // list1.add(UsernameTF.getText()+"login");
                    String Username = UsernameTF.getText();
                    String Loc_IP = localIP.getText();
                    String Loc_port = localPort.getText();
                    String TCP_Server_IP = tcpIP.getText();
                    String TCP_Server_Port = tcpPort.getText();
                    int Server_port = Integer.parseInt(TCP_Server_Port);
                    String MSG_From_server ;
                    String Message = Username + "," + Loc_IP + "," + Loc_port;
                    LoginThread peer_send = new LoginThread(Message, TCP_Server_IP, Server_port);
                    peer_send.start();
                   ///////////////////////////////////////////////////////////////////////////
                      
      
        
                    break;
                }
            }
            if(i==(Usernames.size()))
                JOptionPane.showMessageDialog(null, "Invalid Credentials");
            
        } 
    
catch (FileNotFoundException ex) {
            Logger.getLogger(ClientFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
///////////////////////////////////////////

    
   
      
                 
  
    }//GEN-LAST:event_loginbuttonActionPerformed

    private void logoutbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutbuttonActionPerformed
    
          //list1.add(UsernameTF.getText()+"logout");

        String Username = UsernameTF.getText();
        
        String Loc_IP = localIP.getText();
        String Loc_port = localPort.getText();
        String TCP_Server_IP = tcpIP.getText();
        String TCP_Server_Port = tcpPort.getText();
        int Server_port = Integer.parseInt(TCP_Server_Port);
        String MSG_From_server ;
        String Message = Username + "," + Loc_IP + "," + Loc_port;
        OnlineUsersList.remove(Message);
         JOptionPane.showMessageDialog(null, "logout succesful ");

        LogoutThread peer_send = new LogoutThread(Message, TCP_Server_IP, Server_port , true);
        peer_send.start();
       remoteIP.setText("");
       remotePort.setText("");
       ///////////////////////////////////////////////////////
          
        ArrayList<MessagePacket> data = new ArrayList<MessagePacket>();   
        for(int i=0;i<OnlineUsersList.getItemCount();i++){
           String s= OnlineUsersList.getItem(i);
           String []part=s.split(",", 3);
           if(part[0].equals(UsernameTF.getText())){
               continue;
           }
           else{
               MessagePacket d= new MessagePacket(part[0],part[1],part[2]);
               data.add(d);
           }
        }
        String locport=localPort.getText();
     int loc=Integer.parseInt( locport);
     String locip=localIP.getText();
     SP = new ServerPeer(locip ,loc);
           SP.start();
          
             
             String sent_message ="ss";
            for (MessagePacket User : data){
            if(!User.username.equals(UsernameTF.getText())){
    
           
          String client_port = User.PORT;
            Client_IP = User.IP;
            Client_port = Integer.parseInt(client_port);
            String Message1 = locip + " , " + locport + " , " +Message;
            ClientPeer CP = new ClientPeer(Message1 , Client_IP , Client_port);
            CP.start();
           
    
        }
            
            }
   
      
                 

        
    }//GEN-LAST:event_logoutbuttonActionPerformed

    private void OnlineUsersListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OnlineUsersListActionPerformed
       
    }//GEN-LAST:event_OnlineUsersListActionPerformed

    private void Send_to_allActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Send_to_allActionPerformed
int f=0;
       
for(int i=0;i<OnlineUsersList.getItemCount();i++){
String s= OnlineUsersList.getItem(i);
           String []part=s.split(",", 3);
    if(part[0].equals(UsernameTF.getText())){
        f=1;
        break;
    }
    }

if(f==1){
        
        ArrayList<MessagePacket> data = new ArrayList<MessagePacket>();   
        for(int i=0;i<OnlineUsersList.getItemCount();i++){
           String s= OnlineUsersList.getItem(i);
           String []part=s.split(",", 3);
           if(part[0].equals(UsernameTF.getText())){
               continue;
           }
           else{
               MessagePacket d= new MessagePacket(part[0],part[1],part[2]);
               data.add(d);
           }
        }
      
   
        String locport=localPort.getText();
     int loc=Integer.parseInt( locport);
     String locip=localIP.getText();
     SP = new ServerPeer(locip ,loc);
           SP.start();
          
             LocalDateTime localDateTime = LocalDateTime.now();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
            String formatterDateTime = dateTimeFormatter.format(localDateTime);
             String sent_message = messagesBox.getText();
            for (MessagePacket User : data){
            if(!User.username.equals(UsernameTF.getText())){
    
           
          String client_port = User.PORT;
            Client_IP = User.IP;
            Client_port = Integer.parseInt(client_port);
            String Message = locip + " , " + locport + " , " + sent_message + " , " + formatterDateTime+" , " +UsernameTF.getText();
            ClientPeer CP = new ClientPeer(Message , Client_IP , Client_port);
            CP.start();
           
    
     // to reset the message to null
        }
            
            }
             String s="ME"+" : " + sent_message + "     "+formatterDateTime+"\n";
             chatbox.setModel(model);
              model.addElement(s);
       chatbox.setCellRenderer(new CustomListCellRenderer());
      
                    repaint();
messagesBox.setText("");
        
        }
        else{
               JOptionPane.showMessageDialog(null, "can't send message the user logout");

        }
       

    }//GEN-LAST:event_Send_to_allActionPerformed

    private void localIPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_localIPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_localIPActionPerformed

    private void messagesBoxMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_messagesBoxMousePressed
        // TODO add your handling code here:
        messagesBox.setText("");
    }//GEN-LAST:event_messagesBoxMousePressed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
              repaint();
        try 
        {
            // this button is for login (send TCP data)
            if(!tcpIP.getText().isEmpty())
            {
                String Ser_port = localPort.getText();
                Server_port = Integer.parseInt(Ser_port);
                Server_IP = localIP.getText() ;
                   SP = new ServerPeer(Server_IP , Server_port);
                SP.start();
            }
            File file;
            ArrayList<String> Data=new ArrayList<String>();
            
            FileReader filereader;
            String[] myarray = null;
            ArrayList<String> Usernames=new ArrayList<String>();
            ArrayList<String> Password=new ArrayList<String>();

            String clientSentence;
            
            file = new File("HW2.txt");
            Scanner myReader = new Scanner(file);
            
            
            
            filereader = new FileReader("HW2.txt");
            
            while (myReader.hasNextLine()) {
                Data.add(myReader.nextLine());
            }
            
            myReader.close();
            
            for (int y = 0; y < Data.size(); y++) {
                myarray = Data.get(y).split(" ");
                Usernames.add(myarray[0]);
                 Password.add(myarray[1]);

            }
            int i;
            for(i=0;i<Usernames.size();i++)
            {
                if(Usernames.get(i).equals(UsernameTF.getText())&&Password.get(i).equals(String.valueOf(PasswordTF.getPassword())))
                {
                    String Username = UsernameTF.getText();
                    String Loc_IP = localIP.getText();
                    String Loc_port = localPort.getText();
                    String TCP_Server_IP = tcpIP.getText();
                    String TCP_Server_Port = tcpPort.getText();
                    int Server_port = Integer.parseInt(TCP_Server_Port);
                    String MSG_From_server ;
                    String Message = Username + "," + Loc_IP + "," + Loc_port;
                    LoginThread peer_send = new LoginThread(Message, TCP_Server_IP, Server_port);

                    peer_send.start();
                   ///////////////////////////////////////////////////////////////////////////
                   
                    break;
                }
            }
           
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ClientFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
                     repaint();        
    }//GEN-LAST:event_updateActionPerformed

    private void OnlineUsersListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OnlineUsersListMouseClicked
        // TODO add your handling code here:
         String User_Name = OnlineUsersList.getSelectedItem();
        String []part=User_Name.split(",",3);
        if(part[1].equals(localIP.getText())&&part[2].equals(localPort.getText())){
                     JOptionPane.showMessageDialog(null, "you can't contact yourself");

        }
        else{
        for(MessagePacket x : online_User_Name){
            if(x.username.equals(part[0])){
                remoteIP.setText(x.IP);
                remotePort.setText(x.PORT);
            }
        }
        }
    }//GEN-LAST:event_OnlineUsersListMouseClicked

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        // TODO add your handling code here:
    
   int f=0;
       
for(int i=0;i<OnlineUsersList.getItemCount();i++){
String s= OnlineUsersList.getItem(i);
           String []part=s.split(",", 3);
    if(part[0].equals(UsernameTF.getText())){
        f=1;
        break;
    }
    }



     
        try
        {
            String locport=localPort.getText();
     int loc=Integer.parseInt( locport);
     String locip=localIP.getText();
     SP = new ServerPeer(locip ,loc);
           SP.start();
            LocalDateTime localDateTime = LocalDateTime.now();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
            String formatterDateTime = dateTimeFormatter.format(localDateTime);
            String sent_message = messagesBox.getText();
            String client_port = remotePort.getText();
            Client_IP = remoteIP.getText();
            Client_port = Integer.parseInt(client_port);
            String Message = Server_IP + " , " + Server_port+ " , "+"ss"+" , "+Server_port+ " , "+Server_port+ " , "+Server_port+ " , "+String.valueOf(chatbox.getSelectedIndex());
            ClientPeer CP = new ClientPeer(Message , Client_IP , Client_port);
            CP.start();
           
              model.removeElement(chatbox.getSelectedValue());
   
            sent_message = "";   // to reset the message to null
            
        }
        catch(Exception ex)
        {
            System.out.println(counter++);
            ex.printStackTrace();
        }
    
                  

 messagesBox.setText("");


                
        

    }//GEN-LAST:event_deleteActionPerformed

    private void delete_allActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_allActionPerformed
            
    
   int f=0;
       
for(int i=0;i<OnlineUsersList.getItemCount();i++){
String s= OnlineUsersList.getItem(i);
           String []part=s.split(",", 3);
    if(part[0].equals(UsernameTF.getText())){
        f=1;
        break;
    }
    }



     
        try
        {
            String locport=localPort.getText();
     int loc=Integer.parseInt( locport);
     String locip=localIP.getText();
     SP = new ServerPeer(locip ,loc);
           SP.start();
            LocalDateTime localDateTime = LocalDateTime.now();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
            String formatterDateTime = dateTimeFormatter.format(localDateTime);
            String sent_message = messagesBox.getText();
            String client_port = remotePort.getText();
            Client_IP = remoteIP.getText();
            Client_port = Integer.parseInt(client_port);

            String Message = Server_IP + " , " + Server_port+" , " + Server_port+ " , " + Server_port+" , " + Server_port+" , "+"ss" ;
            ClientPeer CP = new ClientPeer(Message , Client_IP , Client_port);
            CP.start();
           
              model.clear();
   
            sent_message = "";   // to reset the message to null
            
        }
        catch(Exception ex)
        {
            System.out.println(counter++);
            ex.printStackTrace();
        }
    
                  

 messagesBox.setText("");


                
        

    }//GEN-LAST:event_delete_allActionPerformed

    private void showPasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showPasswordMouseClicked
        // TODO add your handling code here:
        if(showPassword.isSelected())
        {
            PasswordTF.setEchoChar((char)0);
        }
        else
        PasswordTF.setEchoChar('\u23FA');
    }//GEN-LAST:event_showPasswordMouseClicked

    private void showPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_showPasswordActionPerformed

    private void remotePortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_remotePortActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_remotePortActionPerformed

    /**
     *  
     * 
     * @param args the command line arguments
     */
    
    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                   new ClientFrame().setVisible(true);


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Interface_List;
    private javax.swing.JPanel Main_frame;
    public static java.awt.List OnlineUsersList;
    HashSet<String> set = new HashSet<>();
    private javax.swing.JPasswordField PasswordTF;
    private javax.swing.JButton Send_to_all;
    public static javax.swing.JLabel Status;
    public static javax.swing.JTextField UsernameTF;
    public static javax.swing.JList<String> chatbox;
    private javax.swing.JButton delete;
    private javax.swing.JButton delete_all;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField localIP;
    private javax.swing.JTextField localPort;
    private javax.swing.JButton loginbutton;
    private javax.swing.JButton logoutbutton;
    public static javax.swing.JTextArea messagesBox;
    private javax.swing.JTextField remoteIP;
    public static javax.swing.JTextField remotePort;
    private javax.swing.JButton sendMessageButton;
    private javax.swing.JCheckBox showPassword;
    private javax.swing.JTextField tcpIP;
    private javax.swing.JTextField tcpPort;
    private javax.swing.JButton testConnectionButton;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables
 
    public void update(){
         this.repaint();

    }
 
    
}


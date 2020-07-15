import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
/*<appletcode ='Client_udp' width=400 height=400></applet>*/
public class Client_udp extends Frame implements ActionListener
{FlowLayout fl;
 static Button start,stop;
String s=new String(" ");
DatagramPacket packet;
byte[] sendData;
//byte[] receiveData;
static DatagramSocket ClientSocket;
InetAddress IpAddress;
Client_udp(String title)
{
super(title);
try
{
fl=new FlowLayout(FlowLayout.CENTER,15,15);
setLayout(fl);
 start =new Button("start");
stop =new Button("stop");
add(start);
add(stop);
start.addActionListener(this);
stop.addActionListener(this);
ClientSocket=new DatagramSocket();
IpAddress=InetAddress.getByName("localhost");
System.out.println(IpAddress);
sendData=new byte[1024];
//receive Data=new byte[1024];
}
catch(Exception e)
{
System.out.println(e);
}
}
public void actionPerformed(ActionEvent ae)
{
try
{
Object o =ae.getSource();
if(o==start)
{
s="start";
System.out.println(s);
sendData=s.getBytes();
System.out.println(IpAddress);
packet=new DatagramPacket(sendData,sendData.length,IpAddress,9877);
ClientSocket.send(packet);
}
if(o==stop)
{
s="stop"; 
System.out.println(s);
sendData=s.getBytes();
packet=new DatagramPacket(sendData,sendData.length,IpAddress,9877);
ClientSocket.send(packet);
}
}
catch(Exception e)
{System.out.println("here");
System.out.println(e);
}
}
public static void main(String arg[]) throws Exception
{Client_udp c=new Client_udp("client");
c.setSize(400,400);
c.setVisible(true);
c.addWindowListener(new WindowAdapter(){
public void windowClosing(WindowEvent w)
{
System.exit(0);
ClientSocket.close();
}});
}}

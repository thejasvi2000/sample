import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
/*<appletcode='Server_udp' width=400 height=400> </applet>*/
class Server_udp extends Frame implements Runnable
{static int x,y,w;
 static String st;
static boolean stopflag;
DatagramPacket packet;
byte[] receiveData;
static DatagramSocket serverSocket;
Thread t;
Server_udp(String title)
{super(title);
x=20; y=30;w=700;
stopflag=false;
try
{
this.setSize(800,700);
this.setVisible(true);
t=new Thread(this);
serverSocket=new DatagramSocket(9877);
receiveData=new byte[1024];
packet=new DatagramPacket(receiveData,receiveData.length);
serverSocket.receive(packet);
st=new String(packet.getData());
System.out.println(st);

t.start();
}
catch(Exception e)
{
System.out.println(e);
}
}
public void paint(Graphics g)
{setBackground(Color.cyan);
w=700;
Color c1=new Color(20,160,200);
Color c2=new Color(200,60,200);
g.setColor(c1);
g.drawLine(0,y+75,w,y+75);
g.setColor(c2);
g.fillRoundRect(x,y+20,100,40,5,5);
        g.fillArc(x+90,y+20,20,40,270,180);
 g.setColor(c1);
       

        g.fillRoundRect(x+10,y,70,25,10,10);
        g.setColor(Color.white);
                g.fillRect(x+20,y+5,20,25);
        g.fillRect(x+50,y+5,20,25);
        g.setColor(Color.black);
        g.fillRoundRect(x+55,y+10,10,20,10,10);
                  g.fillOval(x+10,y+50,25,25);
        g.fillOval(x+60,y+50,25,25);
        g.setColor(Color.white);
                  g.fillOval(x+15,y+55,10,10);
        g.fillOval(x+65,y+55,10,10);
         x=x+10; slep();

}
void slep()
{
try{
Thread.sleep(200);
}
catch(Exception ex)
{
}
}
public static void main(String arg[]) throws IOException
{
Server_udp s=new Server_udp("server2");
s.addWindowListener(new WindowAdapter()
{
public void windowClosing(WindowEvent w1)
{serverSocket.close();
System.exit(0);

}
});
}
public void run()
{
try
{
while(true)
{
if(st.equals("stop")==true)
{
stopflag=true;
}
if(st.equals("start")==true)
{
stopflag=false;
}
if(!stopflag)
{
if(x+100<700)
{
repaint();
}
else
{
x=20;
y+=30;
repaint();
}}
/*packet=new DatagramPacket(receiveData,receiveData.length);
serverSocket.receive(packet);
st=new String(packet.getData());
System.out.println(st);*/
}}
catch(Exception e)
{
System.out.println(e);

}
}
}

package com.example.eltamimy.tictactoe;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class The_Game extends AppCompatActivity {

    String state;
    TextView b1,b2,b3,b4,b5,b6,b7,b8,b9;

    int[] index={0,0,0,0,0,0,0,0,0};

    WifiManager wifiManager;

    WifiP2pManager mWifiP2pManager;
    WifiP2pManager.Channel mChannel;

    BroadcastReceiver mReceiver;
    IntentFilter mIntentFilter;

    static final int MESSAGE_READ=1;

    Serverclass serverclass;
    Clientclass clientclass;
    SendRecieve sendRecieve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the__game);
        Toast.makeText(getApplicationContext(),state,Toast.LENGTH_LONG).show();

        mReceiver=new WIFIDirectBroadcastReciever(mWifiP2pManager,mChannel,this);
        mIntentFilter=new IntentFilter();

        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);


        b1=(TextView)findViewById(R.id.btn_1);
        b2=(TextView)findViewById(R.id.btn_2);
        b3=(TextView)findViewById(R.id.btn_3);
        b4=(TextView)findViewById(R.id.btn_4);
        b5=(TextView)findViewById(R.id.btn_5);
        b6=(TextView)findViewById(R.id.btn_6);
        b7=(TextView)findViewById(R.id.btn_7);
        b8=(TextView)findViewById(R.id.btn_8);
        b9=(TextView)findViewById(R.id.btn_9);
    }

    WifiP2pManager.ConnectionInfoListener connectionInfoListener=new WifiP2pManager.ConnectionInfoListener() {
        @Override
        public void onConnectionInfoAvailable(WifiP2pInfo info) {

            final InetAddress groupOwnerAddress=info.groupOwnerAddress;
            if (info.groupFormed&&info.isGroupOwner)
            {
                state="Host";
                serverclass=new Serverclass();
                serverclass.start();
            }
            else if (info.groupFormed)
            {
                state="Client";
                clientclass=new Clientclass(groupOwnerAddress);
                clientclass.start();
            }

        }
    };
    Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what)
            {
                case MESSAGE_READ:
                    byte[] readBuff=(byte[])msg.obj;
                    String tempMsg=new String(readBuff,0,msg.arg1);
                    if (tempMsg.equals("0"))
                    {
                        if (state.equals("Host"))
                        {
                            b1.setBackground(getResources().getDrawable(R.drawable.o));
                            index[0]=2;
                        }
                        else if (state.equals("Client"))
                        {
                            b1.setBackground(getResources().getDrawable(R.drawable.x));
                            index[0]=1;
                        }
                    }
                    if (tempMsg.equals("1"))
                    {
                        if (state.equals("Host"))
                        {
                            index[1]=2;
                            b2.setBackground(getResources().getDrawable(R.drawable.o));
                        }
                        else if (state.equals("Client"))
                        {
                            index[1]=1;
                            b2.setBackground(getResources().getDrawable(R.drawable.x));
                        }
                    }
                    if (tempMsg.equals("2"))
                    {
                        if (state.equals("Host"))
                        {
                            index[2]=2;
                            b3.setBackground(getResources().getDrawable(R.drawable.o));
                        }
                        else if (state.equals("Client"))
                        {
                            index[2]=1;
                            b3.setBackground(getResources().getDrawable(R.drawable.x));
                        }
                    }
                    if (tempMsg.equals("3"))
                    {
                        if (state.equals("Host"))
                        {
                            index[3]=2;
                            b4.setBackground(getResources().getDrawable(R.drawable.o));
                        }
                        else if (state.equals("Client"))
                        {
                            index[3]=1;
                            b4.setBackground(getResources().getDrawable(R.drawable.x));
                        }
                    }
                    if (tempMsg.equals("4"))
                    {
                        if (state.equals("Host"))
                        {
                            index[4]=2;
                            b5.setBackground(getResources().getDrawable(R.drawable.o));
                        }
                        else if (state.equals("Client"))
                        {
                            index[4]=1;
                            b5.setBackground(getResources().getDrawable(R.drawable.x));
                        }
                    }
                    if (tempMsg.equals("5"))
                    {
                        if (state.equals("Host"))
                        {
                            index[5]=2;
                            b6.setBackground(getResources().getDrawable(R.drawable.o));
                        }
                        else if (state.equals("Client"))
                        {
                            index[5]=1;
                            b6.setBackground(getResources().getDrawable(R.drawable.x));
                        }
                    }
                    if (tempMsg.equals("6"))
                    {
                        if (state.equals("Host"))
                        {
                            index[6]=2;
                            b7.setBackground(getResources().getDrawable(R.drawable.o));
                        }
                        else if (state.equals("Client"))
                        {
                            index[6]=1;
                            b7.setBackground(getResources().getDrawable(R.drawable.x));
                        }
                    }
                    if (tempMsg.equals("7"))
                    {
                        if (state.equals("Host"))
                        {
                            index[7]=2;
                            b8.setBackground(getResources().getDrawable(R.drawable.o));
                        }
                        else if (state.equals("Client"))
                        {
                            index[7]=1;
                            b8.setBackground(getResources().getDrawable(R.drawable.x));
                        }
                    }
                    if (tempMsg.equals("8"))
                    {
                        if (state.equals("Host"))
                        {
                            index[8]=2;
                            b9.setBackground(getResources().getDrawable(R.drawable.o));
                        }
                        else if (state.equals("Client"))
                        {
                            index[8]=1;
                            b9.setBackground(getResources().getDrawable(R.drawable.x));
                        }
                    }

                    break;

            }
            return true;
        }
    });

    public class Serverclass extends Thread
    {
        Socket socket;
        ServerSocket serverSocket;

        @Override
        public void run() {
            try {
                serverSocket =new ServerSocket(8888);
                socket=serverSocket.accept();
                sendRecieve=new SendRecieve(socket);
                sendRecieve.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public class SendRecieve extends Thread
    {
        private Socket socket;
        private InputStream inputStream;
        private OutputStream outputStream;

        public SendRecieve(Socket skt)
        {
            socket=skt;
            try {
                inputStream=socket.getInputStream();
                outputStream=socket.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            byte[] buffer= new byte[1024];
            int bytes;

            while (socket!=null)
            {
                try {
                    bytes=inputStream.read(buffer);
                    if (bytes>0)
                    {
                        handler.obtainMessage(MESSAGE_READ,bytes,-1,buffer).sendToTarget();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        public void write(byte[] bytes)
        {
            try {
                outputStream.write(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public class Clientclass extends Thread
    {
        Socket socket;
        String hostAdd;

        public Clientclass(InetAddress hostAddress)
        {
            hostAdd=hostAddress.getHostAddress();
            socket=new Socket();
        }

        @Override
        public void run() {
            try {
                socket.connect(new InetSocketAddress(hostAdd,8888),500);
                sendRecieve=new SendRecieve(socket);
                sendRecieve.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
String  i;
    public void btn_1(View view) {
    if (index[0]==0)
    {
        if (state.equals("Host"))
        {

            b1.setBackground(getResources().getDrawable(R.drawable.x));
            index[0]=1;

        }
         else if (state.equals("Client"))
        {
            b1.setBackground(getResources().getDrawable(R.drawable.o));
            index[0]=2;
        }
        i="0";
        sendRecieve.write(i.getBytes());
    }
    }
    public void btn_2(View view) {
        if (index[1]==0)
        {
        if (state.equals("Host"))
        {
            index[1]=1;
            b2.setBackground(getResources().getDrawable(R.drawable.x));
        }
        else if(state.equals("Client"))
        {
            index[1]=2;
            b2.setBackground(getResources().getDrawable(R.drawable.o));
        }
        }
        i="1";
        sendRecieve.write(i.getBytes());
    }
    public void btn_3(View view) {
        if (index[2]==0)
        {
        if (state.equals("Host"))
        {
            index[2]=1;
            b3.setBackground(getResources().getDrawable(R.drawable.x));
        }
        else if(state.equals("Client"))
        {
            index[2]=2;
            b3.setBackground(getResources().getDrawable(R.drawable.o));
        }
        }
        i="2";
        sendRecieve.write(i.getBytes());
    }
    public void btn_4(View view) {
        if (index[3]==0)
        {
        if (state.equals("Host"))
        {
            index[3]=1;
            b4.setBackground(getResources().getDrawable(R.drawable.x));
        }
        else if(state.equals("Client"))
        {
            index[3]=2;
            b4.setBackground(getResources().getDrawable(R.drawable.o));
        }
        }
        i="3";
        sendRecieve.write(i.getBytes());
    }
    public void btn_5(View view) {
        if (index[4]==0)
        {
        if (state.equals("Host"))
        {
            index[4]=1;
            b5.setBackground(getResources().getDrawable(R.drawable.x));
        }
        else if(state.equals("Client"))
        {
            index[4]=2;
            b5.setBackground(getResources().getDrawable(R.drawable.o));
        }
        }
        i="4";
        sendRecieve.write(i.getBytes());
    }
    public void btn_6(View view) {
        if (index[5]==0)
        {
        if (state.equals("Host"))
        {
            index[5]=1;
            b6.setBackground(getResources().getDrawable(R.drawable.x));
        }
        else if(state.equals("Client"))
        {
            index[5]=2;
            b6.setBackground(getResources().getDrawable(R.drawable.o));
        }
        }
        i="5";
        sendRecieve.write(i.getBytes());
    }
    public void btn_7(View view) {
        if (index[6]==0)
        {
        if (state.equals("Host"))
        {
            index[6]=1;
            b7.setBackground(getResources().getDrawable(R.drawable.x));
        }
        else if(state.equals("Client"))
        {
            index[6]=2;
            b7.setBackground(getResources().getDrawable(R.drawable.o));
        }
        }
        i="6";
        sendRecieve.write(i.getBytes());
    }
    public void btn_8(View view) {
        if (index[7]==0)
        {
        if (state.equals("Host"))
        {
            index[7]=1;
            b8.setBackground(getResources().getDrawable(R.drawable.x));
        }
        else if(state.equals("Client"))
        {
            index[7]=2;
            b8.setBackground(getResources().getDrawable(R.drawable.o));
        }
        }
        i="7";
        sendRecieve.write(i.getBytes());
    }
    public void btn_9(View view) {
        if (index[8]==0)
        {
        if (state.equals("Host"))
        {
            index[8]=1;
            b9.setBackground(getResources().getDrawable(R.drawable.x));
        }
        else if(state.equals("Client"))
        {
            index[8]=2;
            b9.setBackground(getResources().getDrawable(R.drawable.o));
        }
        }
        i="8";
        sendRecieve.write(i.getBytes());
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mReceiver,mIntentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mReceiver);
    }
}

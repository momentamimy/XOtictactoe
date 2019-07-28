package com.example.eltamimy.tictactoe;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.wifi.WifiManager;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import br.com.bloder.magic.view.MagicButton;

public class MainActivity extends AppCompatActivity {

    Dialog dialog;
    ListView listPeers;

    WifiManager wifiManager;

    WifiP2pManager mWifiP2pManager;
    WifiP2pManager.Channel mChannel;

    BroadcastReceiver mReceiver;
    IntentFilter mIntentFilter;

    List<WifiP2pDevice> peers=new ArrayList<WifiP2pDevice>();
    String[] deviceNameArray;
    WifiP2pDevice[] deviceArray;

    static final int MESSAGE_READ=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dialog=new Dialog(this);
        wifiManager=(WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        mWifiP2pManager=(WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
        mChannel=mWifiP2pManager.initialize(this,getMainLooper(),null);

        mReceiver=new WIFIDirectBroadcastReciever(mWifiP2pManager,mChannel,this);
        mIntentFilter=new IntentFilter();

        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);

    }


   /* Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what)
            {
                case MESSAGE_READ:
                    byte[] readBuff=(byte[])msg.obj;
                    String tempMsg=new String(readBuff,0,msg.arg1);
                    message.setText(tempMsg);
                    break;

            }
            return true;
        }
    });*/

    WifiP2pManager.PeerListListener peerListListener=new WifiP2pManager.PeerListListener() {
        @Override
        public void onPeersAvailable(WifiP2pDeviceList peerslist) {
            if (!peerslist.getDeviceList().equals(peers))
            {
                peers.clear();
                peers.addAll(peerslist.getDeviceList());

                deviceNameArray=new String[peerslist.getDeviceList().size()];
                deviceArray=new WifiP2pDevice[peerslist.getDeviceList().size()];

                int index=0;
                for (WifiP2pDevice device : peerslist.getDeviceList())
                {
                    deviceNameArray[index]=device.deviceName;
                    deviceArray[index]=device;
                    index++;
                }

                ArrayAdapter<String> adapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,deviceNameArray);
                listPeers.setAdapter(adapter);

            }
            if (peers.size()==0)
            {
                Toast.makeText(getApplicationContext(),"no device found",Toast.LENGTH_LONG).show();
                return;
            }
        }
    };

    public void play(View view) {


        dialog.setContentView(R.layout.lan);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        listPeers=dialog.findViewById(R.id.list_peers);

        mWifiP2pManager.discoverPeers(mChannel, new WifiP2pManager.ActionListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(getApplicationContext(),"Discovery Started",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(int reason) {
                Toast.makeText(getApplicationContext(),"Discovery Failed",Toast.LENGTH_LONG).show();
            }
        });
        listPeers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                final WifiP2pDevice device=deviceArray[i];
                WifiP2pConfig config=new WifiP2pConfig();
                config.deviceAddress=device.deviceAddress;

                mWifiP2pManager.connect(mChannel, config, new WifiP2pManager.ActionListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(getApplicationContext(),"device connected"+device.deviceName,Toast.LENGTH_LONG).show();
                        finish();
                        startActivity(new Intent(getApplicationContext(),The_Game.class));
                    }

                    @Override
                    public void onFailure(int reason) {
                        Toast.makeText(getApplicationContext(),"not connected",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        dialog.show();
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

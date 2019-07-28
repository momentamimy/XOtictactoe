package com.example.eltamimy.tictactoe;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.widget.Toast;

/**
 * Created by eltamimy on 4/27/2018.
 */

public class WIFIDirectBroadcastReciever extends BroadcastReceiver {

    private WifiP2pManager mWifiP2pManager;
    private WifiP2pManager.Channel mChannel;
    private MainActivity mMainActivity;
    private The_Game mThe_Game;

    public WIFIDirectBroadcastReciever(WifiP2pManager wifiP2pManager, WifiP2pManager.Channel channel, MainActivity mainActivity)
    {
        this.mWifiP2pManager=wifiP2pManager;
        this.mChannel=channel;
        this.mMainActivity=mainActivity;
    }
    public WIFIDirectBroadcastReciever(WifiP2pManager wifiP2pManager, WifiP2pManager.Channel channel, The_Game the_game)
    {
        this.mWifiP2pManager=wifiP2pManager;
        this.mChannel=channel;
        this.mThe_Game=the_game;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action=intent.getAction();
        if (WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action))
        {
            int state=intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE,-1);
            if (state==WifiP2pManager.WIFI_P2P_STATE_ENABLED)
            {

            }
            else
            {

            }
        }
        else if (WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action))
        {
            if (mWifiP2pManager!=null)
            {
                mWifiP2pManager.requestPeers(mChannel,mMainActivity.peerListListener);
            }

        }
        else if (WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action))
        {
            if (mWifiP2pManager==null)
            {
                return;
            }
            NetworkInfo networkInfo=intent.getParcelableExtra(WifiP2pManager.EXTRA_NETWORK_INFO);
            if (networkInfo.isConnected())
            {
                mWifiP2pManager.requestConnectionInfo(mChannel,mThe_Game.connectionInfoListener);
            }
            else
            {
          //      mMainActivity.connection_status.setText("Device disconnected");
            }
        }
        else if (WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION.equals(action))
        {

        }
    }
}

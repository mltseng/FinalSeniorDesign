package com.example.android.smartlock;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.WriterException;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_qr) {
            // Handle the camera action
            startActivity(new Intent(MainActivity.this, QRActivity.class));
        } else if (id == R.id.nav_nfc) {
            startActivity(new Intent(MainActivity.this, NFCActivity.class));
        } else if (id == R.id.nav_fingerprint) {
            startActivity(new Intent(MainActivity.this, FingerprintActivity.class));
        } else if (id == R.id.nav_bluetooth) {
            startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

/*    public void BTConnect(String msg){
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        BluetoothSocketWrapper socket = null;
        final BluetoothConnector b;
        BluetoothDevice d = null;
        String server_response;
        Bitmap bit;

        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        if(pairedDevices.size() > 0){
            for(BluetoothDevice device: pairedDevices){
                if(device.getName().equals("raspberrypi")){
                    d = device;
                    break;
                }
            }
            b = new BluetoothConnector(d, false, mBluetoothAdapter, null);
            try {
                socket = b.connect();
                if(msg.equals("q")){
                    server_response = sendBtMsg(msg, socket);
                    QRActivity qr = new QRActivity();
                    bit = qr.generateQR(server_response);
                    img.setImageBitmap(bit);
                    text.setText(server_response);
                }
                else{
                    server_response = sendBtMsg(msg, socket);
                    text.setText(server_response);
                }
                socket.close();
            }catch(IOException | WriterException e){
                e.printStackTrace();
            }
        }
    }
    public String sendBtMsg(String msg2send, BluetoothSocketWrapper socket){
        byte[] buffer = new byte[256];
        int bytes;
        try{
            OutputStream mmOut = socket.getOutputStream();
            mmOut.write(msg2send.getBytes());
            InputStream mmIn = socket.getInputStream();
            DataInputStream in = new DataInputStream(mmIn);
            bytes = in.read(buffer);
            String readMess = new String(buffer, 0, bytes);
            return readMess;
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    } */
}

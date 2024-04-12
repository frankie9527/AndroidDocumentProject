package android.bluetooth.Practice

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.bluetooth.Practice.chat.ChatActivity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {
    val REQUEST_ENABLE_BT = 0
    val permission = arrayOf(Manifest.permission.BLUETOOTH_CONNECT,Manifest.permission.BLUETOOTH_SCAN);
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
    fun checkPermission() {
        if(!hasPermission(android.Manifest.permission.BLUETOOTH_CONNECT)){
            ActivityCompat.requestPermissions(this, permission, REQUEST_ENABLE_BT)
            return
        }
        if(!hasPermission(android.Manifest.permission.BLUETOOTH_SCAN)){
            ActivityCompat.requestPermissions(this, permission, REQUEST_ENABLE_BT)
            return
        }
    }
    fun checkBluetooth(view: View) {
        // Check to see if the Bluetooth classic feature is available.
        val bluetoothAvailable = packageManager.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH)

        // Check to see if the BLE feature is available.
        val bluetoothLEAvailable =
            packageManager.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)
        val bleAvailable = packageManager.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)
        Log.e("jyh_bt", "bluetoothAvailable=" + bluetoothAvailable);
        Log.e("jyh_bt", "bluetoothLEAvailable=" + bluetoothLEAvailable);
        Log.e("jyh_bt", "bleAvailable=" + bleAvailable);
    }


    fun openBluetooth(view: View) {
        val bluetoothManager: BluetoothManager = getSystemService(BluetoothManager::class.java)
        val bluetoothAdapter: BluetoothAdapter? = bluetoothManager.getAdapter()
        if (bluetoothAdapter == null) {
            // Device doesn't support Bluetooth
            return
        }
        if (bluetoothAdapter.isEnabled == false) {
            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            // todo  申请权限没做监听，以后做
            checkPermission()
            // todo
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT)
        }
    }
    fun findBluetooth(view: View){
        startActivity(Intent(this,FindDevicesBluetoothActivity::class.java))
    }
    fun chatBluetooth(view: View){
        startActivity(Intent(this, ChatActivity::class.java))
    }
    fun bleBluetooth(view: View){
        startActivity(Intent(this, BleActivity::class.java))
    }
    private fun hasPermission(permission: String): Boolean{
        return checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
    }
}
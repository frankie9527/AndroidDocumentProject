package android.bluetooth.Practice

import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
@SuppressLint("MissingPermission")
class FindDevicesBluetoothActivity : AppCompatActivity() {
    val REQUEST_ENABLE_BT = 0
    val permission = arrayOf(Manifest.permission.BLUETOOTH_CONNECT,Manifest.permission.BLUETOOTH_SCAN);
    lateinit var bluetoothManager: BluetoothManager
    lateinit var bluetoothAdapter: BluetoothAdapter
    var myOnePlus: BluetoothDevice?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find)
        // Register for broadcasts when a device is discovered.
        val filter = IntentFilter()
        filter.addAction(BluetoothDevice.ACTION_FOUND)
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED)
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)
        registerReceiver(receiver, filter)

        bluetoothManager= getSystemService(BluetoothManager::class.java);
        bluetoothAdapter=bluetoothManager.getAdapter();

        checkPermission()
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

    fun pairedDevices(view: View) {
        val pairedDevices: Set<BluetoothDevice>? = bluetoothAdapter.bondedDevices
        pairedDevices?.forEach { device ->
            val deviceName = device.name
            val deviceHardwareAddress = device.address // MAC address
            Log.e("jyh_bt", "pairedDevices deviceName=" + deviceName);
            Log.e("jyh_bt", "pairedDevices deviceHardwareAddress=" + deviceHardwareAddress);

        }
    }

    fun startDiscover(view: View) {
        Log.e("jyh_bt", "startDiscover");
        if(!hasPermission(android.Manifest.permission.BLUETOOTH_SCAN)){
            return
        }

        bluetoothAdapter?.startDiscovery()
    }
    // Create a BroadcastReceiver for ACTION_FOUND.
    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {

            Log.e("jyh_bt", "onReceive ");
            val action: String? = intent.action
            when (action) {
                BluetoothAdapter.ACTION_DISCOVERY_STARTED ->{
                    Log.e("jyh_bt", "onReceive ACTION_DISCOVERY_STARTED");
                }
                BluetoothAdapter.ACTION_DISCOVERY_FINISHED ->{
                    Log.e("jyh_bt", "onReceive ACTION_DISCOVERY_FINISHED");
                    // 配对
                    myOnePlus?.createBond()
                }
                BluetoothDevice.ACTION_FOUND -> {
                    // Discovery has found a device. Get the BluetoothDevice
                    // object and its info from the Intent.
                    val device: BluetoothDevice? =
                        intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
                    val deviceName = device?.name
                    val deviceHardwareAddress = device?.address // MAC address
                    Log.e("jyh_bt", "receiver deviceName=" + deviceName);
                    Log.e("jyh_bt", "receiver deviceHardwareAddress=" + deviceHardwareAddress);
                    if (deviceName != null) {
                        if (deviceName.contains("OnePlus")){
                            myOnePlus=device;
                        }

                    }
                }
            }
        }
    }
    // 设置设备在300秒内可以被发现
    fun canDiscoverable(view: View){
        val requestCode = 1;
        val discoverableIntent: Intent = Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE).apply {
            putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300)
        }
        startActivityForResult(discoverableIntent, requestCode)
    }

    override fun onDestroy() {
        super.onDestroy()
        // Don't forget to unregister the ACTION_FOUND receiver.
        unregisterReceiver(receiver)
    }


    private fun hasPermission(permission: String): Boolean{
        return checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
    }


}
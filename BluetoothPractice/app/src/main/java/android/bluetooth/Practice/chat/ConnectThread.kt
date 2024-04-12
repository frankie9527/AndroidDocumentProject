package android.bluetooth.Practice.chat

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.util.Log
import java.io.IOException

@SuppressLint("MissingPermission")
class ConnectThread(val device: BluetoothDevice, val bluetoothAdapter: BluetoothAdapter?) :
    Thread() {
    val TAG = "JYh_ConnectThread";
    lateinit var interFace: ChatInterFace

    fun setChatInterFace(interFace: ChatInterFace) {
        this.interFace = interFace;
        ChatService.mState = STATE_CONNECTING
    }

    private val mmSocket: BluetoothSocket? by lazy(LazyThreadSafetyMode.NONE) {
        device.createRfcommSocketToServiceRecord(MY_UUID_INSECURE)
    }

    override fun run() {
        // Cancel discovery because it otherwise slows down the connection.
        bluetoothAdapter?.cancelDiscovery()

        mmSocket?.let { socket ->

            // Make a connection to the BluetoothSocket
            try {
                // This is a blocking call and will only return on a
                // successful connection or an exception
                mmSocket!!.connect()
            } catch (e: IOException) {
                // Close the socket
                try {
                    mmSocket!!.close()
                } catch (e2: IOException) {
                    Log.e(
                        TAG, "unable to close() socket during connection failure", e2
                    )
                }
                interFace.connectionFailedInter(true)
                return
            }
            // Reset the ConnectThread because we're done


            // Start the connected thread
            interFace.connectedInter(mmSocket, device, true)
        }
    }


    // Closes the client socket and causes the thread to finish.
    fun cancel() {
        try {
            mmSocket?.close()
        } catch (e: IOException) {
            Log.e(TAG, "Could not close the client socket", e)
        }
    }
}
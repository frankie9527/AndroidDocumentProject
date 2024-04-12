package android.bluetooth.Practice.chat

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothServerSocket
import android.bluetooth.BluetoothSocket
import android.util.Log
import java.io.IOException

@SuppressLint("MissingPermission")
class AcceptThread(val bluetoothAdapter: BluetoothAdapter) : Thread() {
    val TAG = "JYh_AcceptThread";
    lateinit var interFace: ChatInterFace

    fun setChatInterFace(interFace: ChatInterFace) {
        this.interFace = interFace;
        ChatService.mState=STATE_LISTEN
    }

    private val mmServerSocket: BluetoothServerSocket? by lazy(LazyThreadSafetyMode.NONE) {
        bluetoothAdapter.listenUsingInsecureRfcommWithServiceRecord(NAME_INSECURE, MY_UUID_INSECURE)
    }

    override fun run() {
        // Keep listening until exception occurs or a socket is returned.
        while (ChatService.mState != STATE_CONNECTED) {
            val socket: BluetoothSocket? = try {
                mmServerSocket?.accept()
            } catch (e: IOException) {


                null
            }
            socket?.also {
                synchronized(this) {
                    when (ChatService.mState) {
                        STATE_LISTEN, STATE_CONNECTING -> {
                            interFace.connectedInter(socket, socket.remoteDevice,false)
                        }

                        STATE_NONE, STATE_CONNECTED -> {
                            try {
                                socket.close()
                            } catch (e: IOException) {
                                Log.e(TAG, "Could not close unwanted socket", e)
                            }
                        }
                    }
                }
            }
        }
    }

    // Closes the connect socket and causes the thread to finish.
    fun cancel() {
        try {
            mmServerSocket?.close()
        } catch (e: IOException) {
            Log.e(TAG, "Could not close the connect socket", e)
        }
    }
}
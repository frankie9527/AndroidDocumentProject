package android.bluetooth.Practice.chat

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothSocket
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log


@SuppressLint("MissingPermission")
class ChatService(val context: Context, val handler: Handler) : ChatInterFace {
    val TAG = "JYh_ChatService";
    val bluetoothManager by lazy { context.getSystemService(BluetoothManager::class.java) }
    val bluetoothAdapter by lazy { bluetoothManager?.adapter }

    private var mNewState = 0
    private var mInsecureAcceptThread: AcceptThread? = null
    private var mConnectThread: ConnectThread? = null
    private var mConnectedThread: ConnectedThread? = null

    companion object {
        var mState = 0
    }

    @Synchronized
    fun start() {
        Log.d(TAG,"start")
        // Cancel any thread attempting to make a connection
        if (mConnectThread != null) {
            mConnectThread?.cancel()
            mConnectThread = null
        }
        if (mConnectedThread != null) {
            mConnectedThread?.cancel()
            mConnectedThread = null
        }
//        if (mInsecureAcceptThread == null) {
//            mInsecureAcceptThread = bluetoothAdapter?.let { AcceptThread(it) }
//            mInsecureAcceptThread?.setChatInterFace(this)
//            mInsecureAcceptThread?.start()
//        }
        // Update UI title
        updateUserInterfaceTitle()
    }

    @Synchronized
    fun stop() {
        Log.d(TAG, "stop")
        if (mConnectThread != null) {
            mConnectThread!!.cancel()
            mConnectThread = null
        }
        if (mConnectedThread != null) {
            mConnectedThread!!.cancel()
            mConnectedThread = null
        }
        if (mInsecureAcceptThread != null) {
            mInsecureAcceptThread!!.cancel()
            mInsecureAcceptThread = null
        }
        mState = STATE_NONE
        // Update UI title
        updateUserInterfaceTitle()
    }

    @Synchronized
    fun connect(device: BluetoothDevice) {
        Log.d(TAG, "connect to: $device")
        // Cancel any thread attempting to make a connection
        if (mState == STATE_CONNECTING) {
            if (mConnectThread != null) {
                mConnectThread!!.cancel()
                mConnectThread = null
            }
        }
        // Cancel any thread currently running a connection
        if (mConnectedThread != null) {
            mConnectedThread!!.cancel()
            mConnectedThread = null
        }

        // Start the thread to connect with the given device
        mConnectThread = ConnectThread(device, bluetoothAdapter)
        mConnectThread?.setChatInterFace(this)
        mConnectThread!!.start()

        // Update UI title
        updateUserInterfaceTitle()
    }

    @Synchronized
    fun connected(socket: BluetoothSocket?, device: BluetoothDevice) {
        Log.d(TAG, "connected, Socket Type")

        // Cancel the thread that completed the connection
        if (mConnectThread != null) {
            mConnectThread!!.cancel()
            mConnectThread = null
        }

        // Cancel any thread currently running a connection
        if (mConnectedThread != null) {
            mConnectedThread!!.cancel()
            mConnectedThread = null
        }


        if (mInsecureAcceptThread != null) {
            mInsecureAcceptThread
            mInsecureAcceptThread!!.cancel()
            mInsecureAcceptThread = null
        }

        // Start the thread to manage the connection and perform transmissions
        mConnectedThread = ConnectedThread(socket!!, handler)
        mConnectedThread?.setChatInterFace(this)
        mConnectedThread!!.start()

        // Send the name of the connected device back to the UI Activity
        val msg: Message = handler.obtainMessage(MESSAGE_DEVICE_NAME)
        val bundle = Bundle()
        bundle.putString(DEVICE_NAME, device.getName())
        msg.data = bundle
        handler.sendMessage(msg)
        // Update UI title
        updateUserInterfaceTitle()
    }

    /**
     * Indicate that the connection attempt failed and notify the UI Activity.
     */
    fun connectionFailed(isFailed: Boolean) {
        Log.d(TAG, "connectionFailed isFailed"+isFailed)
        // Send a failure message back to the Activity
        val msg: Message = handler.obtainMessage(MESSAGE_TOAST)
        val bundle = Bundle()
        if (isFailed) {
            bundle.putString(TOAST, "Unable to connect device")
        } else {
            bundle.putString(TOAST, "Device connection was lost")
        }
        msg.data = bundle
        handler.sendMessage(msg)
        mState = STATE_NONE
        // Update UI title
        updateUserInterfaceTitle()

        // Start the service over to restart listening mode
        start()
    }

    fun write(message: String) {
        // Create temporary object
        var r: ConnectedThread
        // Synchronize a copy of the ConnectedThread
        synchronized(this) {
            if (mState != STATE_CONNECTED) return
            r = mConnectedThread!!
        }
        // Perform the write unsynchronized
        r.write(message.toByteArray())
    }

    @Synchronized
    fun getState(): Int {
        return mState
    }

    @Synchronized
    private fun updateUserInterfaceTitle() {
        mState = getState()
        Log.d(TAG, "updateUserInterfaceTitle() $mNewState -> $mState")
        mNewState = mState
        // Give the new state to the Handler so the UI Activity can update
        handler.obtainMessage(MESSAGE_STATE_CHANGE, mNewState, -1).sendToTarget()
    }

    override fun connectedInter(
        socket: BluetoothSocket?,
        device: BluetoothDevice,
        resetData: Boolean
    ) {
        // Reset the ConnectThread because we're done
        if (resetData) {
            synchronized(this@ChatService) {
                mConnectThread = null
            }
        }

        connected(socket, device)
    }

    override fun connectionFailedInter(isFailed: Boolean) {
        connectionFailed(isFailed)
    }
}
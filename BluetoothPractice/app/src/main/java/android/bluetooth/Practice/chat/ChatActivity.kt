package android.bluetooth.Practice.chat

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.bluetooth.Practice.R
import android.os.Bundle
import android.os.Handler
import android.os.Message

import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

@SuppressLint("MissingPermission")
class ChatActivity : AppCompatActivity() {
    private val bluetoothManager by lazy { applicationContext.getSystemService(BluetoothManager::class.java) }
    private val bluetoothAdapter by lazy { bluetoothManager?.adapter }
    var ed_input: EditText? = null;
    var service: ChatService? = null;
    private val handler by lazy {
        object : Handler(mainLooper) {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                when (msg.what) {
                    MESSAGE_STATE_CHANGE -> when (msg.arg1) {
                        STATE_CONNECTED -> {
                            showMessage("connect")
                        }

                        STATE_CONNECTING ->{
                            showMessage("connecting")
                        }
                        STATE_LISTEN,  STATE_NONE -> {
                            showMessage("not connect")
                        }
                    }

                    MESSAGE_WRITE -> {
                        val writeBuf = msg.obj as ByteArray
                        // construct a string from the buffer
                        val writeMessage = String(writeBuf)
                        showMessage(writeMessage)
                    }

                    MESSAGE_READ -> {
                        val readBuf = msg.obj as ByteArray
                        // construct a string from the valid bytes in the buffer
                        val readMessage = String(readBuf, 0, msg.arg1)
                        showMessage(readMessage)
                    }

                    MESSAGE_DEVICE_NAME -> {
                        showMessage("Connected to "+msg.getData().getString(DEVICE_NAME))
                    }

                    MESSAGE_TOAST -> {
                        showMessage(msg.data.getString(TOAST))
                    }
                }
            }
        }
    };

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        init()
    }

    override fun onResume() {
        super.onResume()

        // Performing this check in onResume() covers the case in which BT was
        // not enabled during onStart(), so we were paused to enable it...
        // onResume() will be called when ACTION_REQUEST_ENABLE activity returns.
        if (service != null) {
            // Only if the state is STATE_NONE, do we know that we haven't started already
            if (service!!.getState() === STATE_NONE) {
                // Start the Bluetooth chat services
                service!!.start()
            }
        }
    }

    fun init() {
        ed_input = findViewById(R.id.ed_input)
        service = ChatService(this, handler);

    }

    fun linkDevices(view: View) {
        val pairedDevices: Set<BluetoothDevice>? = bluetoothAdapter?.bondedDevices
        pairedDevices?.forEach { device ->
            val deviceName = device.name
            val deviceHardwareAddress = device.address // MAC address
            if (deviceName.contains("OneP")) {
                service?.connect(device)
            }
        }
    }

    fun sendMessage(view: View) {
        if (TextUtils.isEmpty(getInputMessage())) {
            Toast.makeText(this, "plz input text", Toast.LENGTH_SHORT).show()
            return
        }
        // Check that we're actually connected before trying anything
        if (service?.getState() !== STATE_CONNECTING) {
            Toast.makeText(this, "not_connected", Toast.LENGTH_SHORT).show()
            return
        }
        service?.write(getInputMessage())
        ed_input?.setText("")
    }

    fun getInputMessage(): String {
        return ed_input?.text.toString().trim();
    }

    fun showMessage(str: String?) {
        Toast.makeText(
            baseContext, str,
            Toast.LENGTH_SHORT
        ).show()
    }
    public override fun onDestroy() {
        super.onDestroy()
        if (service != null) {
            service!!.stop()
        }
    }
}
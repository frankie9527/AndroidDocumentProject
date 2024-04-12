package android.bluetooth.Practice.chat

import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket

interface ChatInterFace {
    fun connectedInter(socket: BluetoothSocket?, device: BluetoothDevice,resetData:Boolean)
    fun connectionFailedInter(isFailed:Boolean);
}
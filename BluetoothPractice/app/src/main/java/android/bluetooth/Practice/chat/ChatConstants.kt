package android.bluetooth.Practice.chat

import java.util.UUID

const val STATE_NONE: Int = 0 // we're doing nothing

const val STATE_LISTEN: Int = 1 // now listening for incoming connections

const val STATE_CONNECTING: Int = 2 as Int// now initiating an outgoing connection

const val STATE_CONNECTED: Int = 3 // now connected to a remote device

const val NAME_INSECURE = "BluetoothChatInsecure"


// handler message
const val MESSAGE_STATE_CHANGE = 1
const val MESSAGE_READ = 2
const val MESSAGE_WRITE = 3
const val MESSAGE_DEVICE_NAME = 4
const val MESSAGE_TOAST = 5

// Unique UUID for this application

const val DEVICE_NAME = "device_name"
const val TOAST = "toast"

val MY_UUID_INSECURE = UUID.fromString("8ce255c0-200a-11e0-ac64-0800200c9a66")
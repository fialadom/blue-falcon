package dev.bluefalcon

expect class BlueFalcon {

    val delegates: MutableList<BlueFalconDelegate>

    fun connect(bluetoothPeripheral: BluetoothPeripheral)
    fun disconnect(bluetoothPeripheral: BluetoothPeripheral)
    fun scan()

}
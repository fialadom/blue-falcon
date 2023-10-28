package dev.bluefalcon.kotlinmp_example.viewmodels

import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import dev.bluefalcon.BlueFalcon
import dev.bluefalcon.BluetoothPeripheral
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DevicesViewModel(private val blueFalcon: BlueFalcon) : ViewModel() {

    private val _devices: MutableStateFlow<List<BluetoothPeripheral>> = MutableStateFlow(emptyList())

    @NativeCoroutinesState
    val devices: StateFlow<List<BluetoothPeripheral>> = _devices.asStateFlow()
    init {
        CoroutineScope(Dispatchers.IO).launch {
            blueFalcon.peripherals.collect {
                _devices.tryEmit(it.toList())
            }
        }
    }

    fun scan() {
        blueFalcon.scan()
    }
}

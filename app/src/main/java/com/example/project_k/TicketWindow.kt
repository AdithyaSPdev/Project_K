package com.example.project_k


import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity

class TicketWindow : AppCompatActivity() {
    private var adminComponent: ComponentName? = null
    private var devicePolicyManager: DevicePolicyManager? = null
    private var cameraSwitch: Switch? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        devicePolicyManager = getSystemService(DEVICE_POLICY_SERVICE) as DevicePolicyManager
        adminComponent = ComponentName(packageName, "$packageName.DeviceAdministrator")

        // Request device admin activation if not enabled.
        if (!devicePolicyManager!!.isAdminActive(adminComponent!!)) {
            val activateDeviceAdmin = Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN)
            activateDeviceAdmin.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, adminComponent)
            startActivityForResult(activateDeviceAdmin, Companion.DPM_ACTIVATION_REQUEST_CODE)
        }
        setContentView(com.example.project_k.R.layout.ticket_windwos) // This must be called before initializing our switch!
        cameraSwitch = findViewById<View>(com.example.project_k.R.id.onbutton) as Switch
        cameraSwitch!!.setOnCheckedChangeListener { compoundButton, isEnabled ->
            try {
                if (isEnabled) {
                    devicePolicyManager!!.setCameraDisabled(
                        adminComponent!!,
                        true
                    ) // Enable camera.
                } else {

                    devicePolicyManager!!.setCameraDisabled(
                        adminComponent!!,
                        true
                    ) // Disable camera.
                }

            } catch (securityException: SecurityException) {
                Log.i(
                    "Device Administrator",
                    "Error occurred while disabling/enabling camera - " + securityException.message
                )
            }
        }
        if (devicePolicyManager!!.getCameraDisabled(adminComponent)) {
            cameraSwitch!!.isChecked = false
        } else {
            cameraSwitch!!.isChecked = true
        }
    }

    companion object {
        const val DPM_ACTIVATION_REQUEST_CODE = 100
    }
}
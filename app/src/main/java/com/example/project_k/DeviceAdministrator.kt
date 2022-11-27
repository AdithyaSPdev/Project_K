package com.example.project_k

import android.app.admin.DeviceAdminReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

/**
 * Created by Daniel Kreider on 01/02/18.
 */
class DeviceAdministrator : DeviceAdminReceiver() {
    override fun onDisabled(context: Context, intent: Intent) {
        super.onDisabled(context, intent)
        Toast.makeText(context, "Sample Device Admin has been disabled.", Toast.LENGTH_SHORT).show()

    }

    override fun onEnabled(context: Context, intent: Intent) {
        super.onEnabled(context, intent)
        Toast.makeText(context, "Sample Device Admin has been enabled.", Toast.LENGTH_SHORT).show()
    }
}
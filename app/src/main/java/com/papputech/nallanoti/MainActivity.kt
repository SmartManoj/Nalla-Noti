package com.papputech.nallanoti
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
fun MainScreen() {
    Scaffold(
        topBar = { AppTopBar() },
        content = { it
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(46.dp)
        ) {
            TextButton(onClick = {
                if (!hasNotificationAccess(this@MainActivity)) {
                    openNotificationAccessSettings(this@MainActivity)
                } else {
                    // Handle case where permission is already granted
                }
            }) {
                Text("Enable Notification Access")
            }
        }
    })
}

    private fun hasNotificationAccess(context: Context): Boolean {
        val enabledListeners = Settings.Secure.getString(context.contentResolver, "enabled_notification_listeners")
        val packageName = context.packageName
        return enabledListeners?.contains(packageName) == true
    }

    private fun openNotificationAccessSettings(context: Context) {
        val intent = Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS)
        context.startActivity(intent)
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun AppTopBar() {
        TopAppBar(
            title = { Text("Nalla Notifications") }
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        MainScreen()
    }
}

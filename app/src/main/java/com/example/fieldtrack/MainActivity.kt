package com.example.fieldtrack

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.fieldtrack.data.repository.Repository
import com.example.fieldtrack.navigation.MyApp
import com.example.fieldtrack.ui.components.MainScreen
import com.example.fieldtrack.ui.theme.FieldTrackTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var repository: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        repository.getLogEntries()
            .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach {
                if (it.isNotEmpty()) {
                    Log.i("Temp", "Foreground location: ${it[it.size - 1].aid}")
                }
            }
            .launchIn(lifecycleScope)

        enableEdgeToEdge()
        setContent {
            Log.d("Sara", "MainActivity")
            FieldTrackTheme {
                MainScreen()
            }
        }
    }
}
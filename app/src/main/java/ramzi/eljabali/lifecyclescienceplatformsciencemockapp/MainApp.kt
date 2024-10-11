@file:SuppressLint("LogNotTimber")

package ramzi.eljabali.lifecyclescienceplatformsciencemockapp

import android.annotation.SuppressLint
import android.app.Application
import android.content.ComponentName
import android.content.Intent
import android.util.Log
import com.pltsci.ipc.core.IPsCoreIpcApiServices
import com.pltsci.ipc.core.listener.OnConnectListener
import com.pltsci.pscoresdk.UserProfile
import com.pltsci.pscoresdk.activity.ActivityStarterClient.startActivity
import com.pltsci.pscoresdk.vehicle.data.EngineEventData
import com.pltsci.vehicledatasdk.EngineDataClient
import com.pltsci.vehicledatasdk.EngineDataSubscriptionClient
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ramzi.eljabali.lifecyclescienceplatformsciencemockapp.data.EngineData
import ramzi.eljabali.lifecyclescienceplatformsciencemockapp.data.UserProfileManagerKt

class MainApp : Application() {
    private lateinit var psCoreApi: IPsCoreIpcApiServices
    val userObject = UserProfileManagerKt

    private val connectListener = OnConnectListener {
        Log.i("PS-Core", "OnConnectListener ${psCoreApi.isConnected}")
        userObject.init(psCoreApi)
    }

    private val engineDataClient: EngineDataClient by lazy {
        EngineDataClient(this.applicationContext)
    }
    private val engineDataSubscriptionClient: EngineDataSubscriptionClient by lazy {
        EngineDataSubscriptionClient(GlobalScope, this)
    }
    var engineData: EngineEventData = EngineEventData()
    private var engineDataSubscription = MutableStateFlow(EngineData())
    val stateFlow = engineDataSubscription.asStateFlow()

    override fun onCreate() {
        super.onCreate()
        connectToPSCoreServices()
        GlobalScope.launch {
            subscribeToEngineClient()
        }
    }

    private fun connectToPSCoreServices() {
        psCoreApi = IPsCoreIpcApiServices.Builder(this)
            .addApi(UserProfile.API)
            .addOnConnectListener(connectListener)
            .build()

        psCoreApi.connect(true)
    }

    suspend fun getEngineData() {
        engineDataClient.withConnection { getEngineEventData() }
            .onSuccess { engineEventData ->
                if (engineEventData != null) {
                    engineData = engineEventData
                }
                Log.i("Vehicle-Data", engineEventData.toString())
            }
            .onFailure {
                Log.i("Vehicle-Data", it.message.toString())
                Log.i("Vehicle-Data", it.cause?.message.toString())
                Log.i("Vehicle-Data", it.toString())
            }
    }

    private suspend fun subscribeToEngineClient() {
        engineDataSubscriptionClient.engineData()
            .map { result ->
                result.map {
                    EngineData(
                        it.ignition,
                        it.wheelsInMotion,
                    )
                }
            }
            .distinctUntilChanged()
            .collect { result ->
                result
                    // TODO: Find what information we are in need of
                    .onSuccess { data ->
                        Log.i("Vehicle-Subscription", data.toString())
                        engineDataSubscription.update { data }
                    }
                    .onFailure {
                        Log.e("Vehicle-Data", it.message.toString())
                        Log.e("Vehicle-Data", it.cause?.message.toString())
                        Log.e("Vehicle-Data", it.toString())
                    }
            }
    }

    private fun launchActivity() {
        try {
            startActivity(
                this,
                ComponentName(
                    "ramzi.eljabali.lifecyclescienceplatformsciencemockapp",
                    "ramzi.eljabali.lifecyclescienceplatformsciencemockapp.AlertsActivity"
                ),
                Intent.ACTION_VIEW,
                null,
                Intent.FLAG_ACTIVITY_NEW_TASK,
                null
            )
            Log.i("Activity-Starter", "Activity Launched")
        } catch (e: Exception) {
            Log.e("Activity-Starter", "Failed to start activity")
        }
    }

}

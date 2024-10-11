package ramzi.eljabali.lifecyclescienceplatformsciencemockapp.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import com.pltsci.pscoresdk.vehicle.data.EngineEventData
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ramzi.eljabali.lifecyclescienceplatformsciencemockapp.MainApp
import ramzi.eljabali.lifecyclescienceplatformsciencemockapp.data.EngineData
import ramzi.eljabali.lifecyclescienceplatformsciencemockapp.data.UserProfileManagerKt
import ramzi.eljabali.lifecyclescienceplatformsciencemockapp.ui.theme.LifeCycleSciencePlatformScienceMockAppTheme
import kotlin.coroutines.EmptyCoroutineContext

class MainActivity : ComponentActivity() {
    private lateinit var psCoreApplication: MainApp

    @SuppressLint("LogNotTimber")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        psCoreApplication = application as MainApp
        setContent {
            val subscriptionDataState by
            psCoreApplication.stateFlow.collectAsState()

            var refreshedDataState by remember {
                mutableStateOf("")
            }
            val userID by psCoreApplication.userObject.userID.collectAsState()
            val userName by psCoreApplication.userObject.userName.collectAsState()
            val isDriver by psCoreApplication.userObject.isDriver.collectAsState()

            LifeCycleSciencePlatformScienceMockAppTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            text = "User Name \n$userName"
                        )
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            text = "User ID \n$userID"
                        )
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            text = "Is Driver \n$isDriver"
                        )
                        Text("Subscription Vehicle Info")
                        Text(subscriptionDataState.toString())
                        Button(
                            modifier = Modifier.padding(innerPadding),
                            onClick = {
                                Log.i("Vehicle-Data", "Refresh Button Clicked")
                                lifecycleScope.launch {
                                    psCoreApplication.getEngineData()
                                    refreshedDataState = psCoreApplication.engineData.toString()
                                }
                            }
                        ) {
                            Text("Click to refresh vehicle info")
                        }
                        Text("Button click Vehicle Info")
                        Text(
                            modifier = Modifier
                                .height(100.dp)
                                .verticalScroll(rememberScrollState(), true),
                            text = refreshedDataState
                        )
                    }
                }
            }
        }
    }
}


package ramzi.eljabali.lifecyclescienceplatformsciencemockapp.activities

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ramzi.eljabali.lifecyclescienceplatformsciencemockapp.R

class AlertsActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val packageName = applicationContext.packageName
        Log.i("Package-name-", packageName)
        setContent {
            Alert()
        }
    }
}

@Composable
fun Alert() {
    Column(
        modifier =
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Alert!",
            fontSize = 30.sp,
            modifier = Modifier.padding(
                bottom = 20.dp
            )
        )
        Image(
            modifier = Modifier.padding(bottom = 8.dp),
            painter = painterResource(id = R.mipmap.road_work_ahead),
            contentDescription = "Road Work Ahead"
        )
        IconButton(
            modifier = Modifier.size(150.dp),
            onClick = {

            }
        ) {
            Image(
                modifier = Modifier.padding(top = 8.dp),
                painter = painterResource(id = R.mipmap.tap_to_dismiss),
                contentDescription = "Tap To Dismiss"
            )
        }

    }
}

@Preview
@Composable
fun previewAlert() {
    Alert()
}
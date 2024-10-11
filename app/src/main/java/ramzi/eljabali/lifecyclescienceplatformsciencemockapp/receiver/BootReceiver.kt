package ramzi.eljabali.lifecyclescienceplatformsciencemockapp.receiver

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.pltsci.ipc.core.IPsCoreIpcApiServices
import ramzi.eljabali.lifecyclescienceplatformsciencemockapp.activities.MainActivity

class BootReceiver : BroadcastReceiver() {
    private lateinit var psCoreApi: IPsCoreIpcApiServices

    @SuppressLint("LogNotTimber")
    override fun onReceive(context: Context?, intent: Intent?) {
        val activityIntent = Intent(context, MainActivity::class.java)

        // Add FLAG_ACTIVITY_NEW_TASK to allow starting activity from non-activity context
        activityIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

        // Optionally, pass some extra data to the activity
        activityIntent.putExtra("key", "value")

        // Start the activity
        context?.startActivity(activityIntent)
    }
}
package ramzi.eljabali.lifecyclescienceplatformsciencemockapp.data

import android.content.Intent

enum class BroadcastActions(val action: String) {
    WAKE_UP_ON_INSTALL("com.pltsci.action.WAKE_UP_ON_INSTALL"),
    BOOT_COMPLETED(Intent.ACTION_BOOT_COMPLETED)
}
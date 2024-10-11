@file:Suppress("unused")
@file:SuppressLint("LogNotTimber")
package ramzi.eljabali.lifecyclescienceplatformsciencemockapp.data

import android.annotation.SuppressLint
import android.util.Log
import com.pltsci.ipc.core.IPsCoreIpcApiServices
import com.pltsci.ipc.core.Status
import com.pltsci.pscoresdk.UserProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

object UserProfileManagerKt {

    // Private mutable state flows
    private val _userID = MutableStateFlow(0L)
    private val _userName = MutableStateFlow("")
    private val _isDriver = MutableStateFlow(false)
    private val _secondUserID = MutableStateFlow(0L)

    // Public immutable state flows
    val userID: StateFlow<Long> = _userID.asStateFlow()
    val userName: StateFlow<String> = _userName.asStateFlow()
    val isDriver: StateFlow<Boolean> = _isDriver.asStateFlow()

    private lateinit var psCoreApi: IPsCoreIpcApiServices

    // Initialize the manager with the API service and retrieve the user profile
    fun init(psCoreApi: IPsCoreIpcApiServices) {
        UserProfileManagerKt.psCoreApi = psCoreApi

        UserProfile.getUserProfileListV10(psCoreApi) { result ->
            if (result.status == Status.OK) {
                result.data?.let { userProfileList ->
                    if (userProfileList.isNotEmpty()) {
                        val userProfile = userProfileList[0]
                        _userID.update { userProfile.id }
                        _userName.update { userProfile.username }
                        _isDriver.update { userProfile.isDriver }

                        if (userProfileList.size > 1) {
                            val secondProfile = userProfileList[1]
                            _secondUserID.update { secondProfile.id }
                        }
                    }
                }
                Log.i("PS-Core", "getUserProfile ${result.status}")
                Log.i("PS-Core", "data: ID:${_userID.value}, Username: ${_userName.value}, isDriver: ${_isDriver.value}")
            } else {
                Log.i("PS-Core", "getUserProfile failed: ${result.status}")
            }
        }
    }

    // Show login
    fun showLogin() {
        UserProfile.requestLogin(psCoreApi) { result ->
            if (result.status == Status.OK) {
                Log.i("PS-Core", "login ok")
            }
        }
    }
}

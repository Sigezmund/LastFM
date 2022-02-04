package teach.meskills.lastfm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.viewModels

class MainActivity : AppCompatActivity() {
//    private val viewModel: UserViewModel by viewModels()
//    private val pref by lazy {
//        CustomPreference(requireContext())
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        viewModel.userLiveData.observe() { user ->
//            if (user != null) {
//                pref.login = user.userName
//                pref.password = user.password
//                supportFragmentManager
//                    .beginTransaction()
//                    .replace(R.id.fragmentContainer, ResultFragment.newInstance())
//                    .commit()
//            }
//        }
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer, LoginFragment())
                .commit()
        }
    }
}
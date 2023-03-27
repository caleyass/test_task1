package com.mygdx.game.presentation

import android.content.Context
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.badlogic.gdx.backends.android.AndroidFragmentApplication
import com.mygdx.game.presentation.fragment.GameFragment
import com.mygdx.game.R
import com.mygdx.game.presentation.fragment.WebViewFragment
import com.mygdx.game.presentation.viewmodel.MyViewModel


class MainActivity : AppCompatActivity(),  AndroidFragmentApplication.Callbacks {
    
    private val viewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if( viewModel.writeToSharedPreference(getSharedPreferences("myPrefs", Context.MODE_PRIVATE))
        ) {
            val fragment = WebViewFragment()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, fragment, "MY_FRAGMENT_TAG")
                .addToBackStack(null)
                .commit()

        }
        else{

            // Create libgdx fragment
            val libgdxFragment =
                GameFragment()

            // Put it inside the framelayout (which is defined in the layout.xml file).
            supportFragmentManager.beginTransaction().add(R.id.fragment_container, libgdxFragment)
                .commit()
        }
    }

    override fun exit() {
    }

    /*@SuppressLint("ServiceCast")
    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }*/
}
package com.learning.exp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.learning.exp.databinding.WhatsappActivityBinding
import androidx.navigation.findNavController

class WhatsAppActivity : AppCompatActivity() {
    private lateinit var mBinding: WhatsappActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = WhatsappActivityBinding.inflate(layoutInflater)

        setContentView(mBinding.root)

        /* Recommended/1st Way to Load the Fragments*/

        // Obtain NavController from the NavHostFragment via the Navigation helper. This
        // is reliable from an Activity and ensures we have a controller even when the
        // buttons are outside the host fragment's view hierarchy.
        val navController = this.findNavController(R.id.nav_host_fragment)

        // Navigate directly to fragment destination IDs so the buttons work regardless
        // of which fragment is currently visible. Add guards to avoid redundant navigation.
        mBinding.callBtn.setOnClickListener {
            if (navController.currentDestination?.id != R.id.callFragment) {
                navController.navigate(R.id.callFragment)
            }
        }
        mBinding.statusBtn.setOnClickListener {
            if (navController.currentDestination?.id != R.id.statusFragment) {
                navController.navigate(R.id.statusFragment)
            }
        }
        mBinding.chatBtn.setOnClickListener {
            if (navController.currentDestination?.id != R.id.chatFragment) {
                navController.navigate(R.id.chatFragment)
            }
        }


        /* 2nd Way to Load the Fragments*/
        /*supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainerView, CallFragment())
            .commit()*/

        /* mBinding.callBtn.setOnClickListener {
             //startActivity(Intent(this, CallActivity::class.java)) // Activity
             // Opening CallFragment
             supportFragmentManager.beginTransaction()
                 .replace(R.id.fragmentContainerView, CallFragment::class.java, null)
                 .addToBackStack(null) // Optional: allows users to go back with the device back button
                 .commit()
         }

         mBinding.statusBtn.setOnClickListener {
             supportFragmentManager.beginTransaction()
                 .replace(R.id.fragmentContainerView, StatusFragment::class.java, null)
                 .addToBackStack(null) // Optional: allows users to go back with the device back button
                 .commit()
         }

         mBinding.chatBtn.setOnClickListener {
             supportFragmentManager.beginTransaction()
                 .replace(R.id.fragmentContainerView, ChatFragment::class.java, null)
                 .addToBackStack(null) // Optional: allows users to go back with the device back button
                 .commit()
         }*/

    }
}
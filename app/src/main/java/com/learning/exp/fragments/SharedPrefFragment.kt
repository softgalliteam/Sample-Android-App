package com.learning.exp.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.learning.exp.R
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

class SharedPrefFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.call_fragment, container, false)
    }


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Shared Preferences example code can be added here
        val sharedPref = activity?.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE)
        sharedPref?.edit {
            putString("User_Name", "Rahul")
            putString("User_Password", "123456")
            putLong("User_Phone_Number", 98767885)
            putBoolean("isLoggedIn", true)
        }



        // Retrieve data from Shared Preferences
        val userName = sharedPref?.getString("User_Name1", "No Data Found")
        val userPass = sharedPref?.getString("User_Password", "No Data Found")
        val userMobile = sharedPref?.getLong("User_Phone_Number", 0L)
        val isLoggedIn = sharedPref?.getBoolean("isLoggedIn", false)


        Log.d("SharedPrefFragment", "User Name: $userName")
        Log.d("SharedPrefFragment", "User Password: $userPass")
        Log.d("SharedPrefFragment", "User Mobile: $userMobile")
        Log.d("SharedPrefFragment", "Is Logged In: $isLoggedIn")

        val callFragmentTextView = view.findViewById<TextView>(R.id.callFragmentTextView)
        callFragmentTextView.text = "User Name: $userName\nUser Password: $userPass\nUser Mobile: $userMobile\nIs Logged In: $isLoggedIn"


        // Encrypted Shared Preferences example code can be added here (if needed)

        // Use the MasterKey.Builder to create or retrieve a key for encryption:
        val masterKey =  MasterKey.Builder(requireActivity())
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        // Create or retrieve an instance of EncryptedSharedPreferences:
        val encryptedSharedPref =
                EncryptedSharedPreferences.create(
                    requireActivity(),
                    "EncryptedSharedPref",
                    masterKey,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
                )

        //Safe data storage using EncryptedSharedPreferences
            encryptedSharedPref.edit {
                putString("User_Name1", "Manish")
                putString("User_Password1", "Manish@123")
            }


        // Retrieve data from EncryptedSharedPreferences
        val encryptedUserName = encryptedSharedPref.getString("User_Name1", "No Data Found")
        val encryptedUserPass = encryptedSharedPref.getString("User_Password1", "No Data Found")
        Log.d("SharedPrefFragment", "Encrypted User Name: $encryptedUserName")
        Log.d("SharedPrefFragment", "Encrypted User Password: $encryptedUserPass")

        callFragmentTextView.text = "User Name: $userName\nUser Password: $userPass\nUser Mobile: $userMobile\nIs Logged In: $isLoggedIn\n\nEncrypted User Name: $encryptedUserName\nEncrypted User Password: $encryptedUserPass"








    }

}
package com.learning.exp

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.learning.exp.databinding.ApiCallActivityBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request

class ApiCallActivity : AppCompatActivity() {
    companion object {
        const val TAG = "ApiCallActivity"
        const val BASE_URL = "https://api.restful-api.dev/objects"
        const val DELETE_URL = "https://api.restful-api.dev/objects?id=2"
    }

    private lateinit var mBinding: ApiCallActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ApiCallActivityBinding.inflate(layoutInflater)

        setContentView(mBinding.root)

        val userNameEt = mBinding.nameEt
        val userEmailEt = mBinding.emailEt
        val resultTv = mBinding.resultTv


        mBinding.addBtn.setOnClickListener {
            lifecycleScope.launch(IO) {
                val name = userNameEt.text.toString()
                val email = userEmailEt.text.toString()

                // Make API call to add user using name and email
                val client = OkHttpClient()
                val request = Request.Builder()
                    .addHeader("Content-Type", "application/json")
                    .url(BASE_URL).build()
                client.newCall(request).execute().use { response ->
                    Log.d(TAG, response.body?.string().toString())
                }
            }
        }
        mBinding.updateBtn.setOnClickListener {

        }
        var myResponse = ""
        mBinding.getBtn.setOnClickListener {
            Log.d(TAG, "Fetching Details from Rest API.....")
            lifecycleScope.launch(IO) {
                // Make API call to add user using name and email
                val client = OkHttpClient() // Or use Retrofit <Imp> or any other HTTP client library
                val request = Request.Builder().url(BASE_URL).build()
                client.newCall(request).execute().use { response ->
                    myResponse = response.body?.string().toString()
                    Log.d(TAG, myResponse)
                }
            }

            Handler().postDelayed({
                resultTv.text = myResponse
            }, 1000)
        }
        mBinding.deleteBtn.setOnClickListener {
            Log.d(TAG, "Deleting Details from Rest API.....")
            lifecycleScope.launch(IO) {
                // Make API call to add user using name and email
                val client = OkHttpClient()
                val request = Request.Builder().url(DELETE_URL).build()
                client.newCall(request).execute().use { response ->
                    myResponse = response.body?.string().toString()
                    Log.d(TAG, myResponse)
                    Log.d(TAG, "Deleted successfully..... :)")
                }
            }
            Handler().postDelayed({
                resultTv.text = myResponse
            }, 1000)
        }
    }
}
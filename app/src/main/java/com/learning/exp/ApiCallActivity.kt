package com.learning.exp

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.learning.exp.databinding.ApiCallActivityBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.HttpUrl
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

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

        val computerRV = mBinding.computerRV
        val userNameEt = mBinding.nameEt
        val cpu = mBinding.cpuEt
        val resultTv = mBinding.resultTv

        computerRV.layoutManager = LinearLayoutManager(this)
        // Set in recycler view adapter
        val adapter = ComputerRecyclerViewAdapter(arrayListOf(), {
            Toast.makeText(this, "Clicked: $it", Toast.LENGTH_SHORT).show()
        })
        // Setting the Adapter with the recyclerview
        computerRV.adapter = adapter

        mBinding.addBtn.setOnClickListener {
            lifecycleScope.launch(IO) {
                val name = userNameEt.text.toString()
                val cpu = cpu.text.toString()

                val requestBody = RequestBody(
                    name = name,
                    data = ComputerDetails(
                        cpuModel = cpu,
                        hardDiskSize = "2 TB",
                        year = 2026,
                        price = 25000.0
                    )
                )

                val requestBodyJson: String = Gson().toJson(requestBody)
                val body = requestBodyJson.toRequestBody("application/json".toMediaType())
                // Make API call to add user using name and email
                val client = OkHttpClient()
                val request = Request.Builder()
                    //.addHeader("Content-Type", "application/json")
                    .post(body)
                    .url(BASE_URL).build()
                client.newCall(request).execute().use { response ->
                    Log.d(TAG, response.body?.string().toString())
                }
            }
        }
        mBinding.updateBtn.setOnClickListener {
            lifecycleScope.launch(IO) {
                val name = userNameEt.text.toString()
                val cpu = cpu.text.toString()

                val requestBody = RequestBody(
                    name = name,
                    data = ComputerDetails(
                        cpuModel = cpu,
                        hardDiskSize = "2 TB",
                        year = 2026,
                        price = 25000.0
                    )
                )

                val requestBodyJson: String = Gson().toJson(requestBody)
                val body = requestBodyJson.toRequestBody("application/json".toMediaType())

                val client = OkHttpClient()
                val url = HttpUrl.Builder()
                    .scheme("https")
                    .host("api.restful-api.dev")
                    .addPathSegment("objects")
                    .addPathSegment("ff8081819d62221a019d80168a5b248d")
                    .build()

                val request = Request.Builder()
                    .url(url)
                    .addHeader("Content-Type", "application/json")
                    .put(body)
                    .build()
                client.newCall(request).execute().use { response ->
                    Log.d(TAG, response.body?.string().toString())
                }
            }
        }
        var myResponse = ""
        mBinding.getBtn.setOnClickListener {
            Log.d(TAG, "Fetching Details from Rest API.....")
            lifecycleScope.launch(IO) {
                // Make API call to add user using name and email
                val client =
                    OkHttpClient() // Or use Retrofit <Imp> or any other HTTP client library
                val request = Request.Builder().url(BASE_URL).build()
                client.newCall(request).execute().use { response ->
                    myResponse = response.body?.string().toString()
                    Log.d(TAG, myResponse)

                    Log.d(TAG, "Parsing response using Gson.....")
                    val computerList: ResponseData =
                        Gson().fromJson(myResponse, ResponseData::class.java)
                    Log.d(TAG, computerList.toString())
                    withContext(Main) {
                        adapter.updateData(computerList)
                    }
                }
            }
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

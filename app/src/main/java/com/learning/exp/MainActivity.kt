package com.learning.exp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.util.TableInfo
import com.learning.exp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(mBinding.root)

        mBinding.saveDataBtn.setOnClickListener {

        }

        mBinding.fetchDataBtn.setOnClickListener {

        }
    }
}



















    /*
    private val Context.dataStore by preferencesDataStore("My_Pref_Data")
    private suspend fun saveDataInDataStore(myData: String) {
        dataStore.edit {
            it[TOKEN] = myData
        }
    }
    private suspend fun fetchDataFromDataStore() {
        dataStore.data.collect { pref ->
            val fetchedData = pref[TOKEN] ?: ""
            mBinding.resultTv.text = "Data Fetched: $fetchedData"
            Log.d(TAG, "Fetched Data: $fetchedData")
        }
    }
    companion object {
        const val TAG = "MainActivity"
        val TOKEN = stringPreferencesKey("token")
    }
    */

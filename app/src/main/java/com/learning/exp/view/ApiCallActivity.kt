package com.learning.exp.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.learning.exp.databinding.ApiCallActivityBinding
import com.learning.exp.view.adapter.ComputerRecyclerViewAdapter
import com.learning.exp.viewmodel.ApiCallState
import com.learning.exp.viewmodel.ApiCallViewModel
import kotlinx.coroutines.delay

class ApiCallActivity : AppCompatActivity() {
    companion object {
        const val TAG = "ApiCallActivity"
    }

    val apiCallViewModel: ApiCallViewModel by viewModels() // 1st Way to initialize view model

    private lateinit var mBinding: ApiCallActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ApiCallActivityBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        val computerRV = mBinding.computerRV
        computerRV.layoutManager = LinearLayoutManager(this)
        // Set in recycler view adapter
        val adapter = ComputerRecyclerViewAdapter(arrayListOf(), {
            Toast.makeText(this, "Clicked: $it", Toast.LENGTH_SHORT).show()
        })
        // Setting the Adapter with the recyclerview
        computerRV.adapter = adapter

        apiCallViewModel.screenState.observe(this) { state ->
            when (state) {
                is ApiCallState.Loading -> {
                    // Show loading indicator
                    mBinding.loaderLl.visibility = android.view.View.VISIBLE
                    Snackbar.make(mBinding.root, "Loading...", Snackbar.LENGTH_LONG).show()
                }

                is ApiCallState.Success -> {

                    mBinding.loaderLl.visibility = android.view.View.INVISIBLE
                    // Update UI with the list of computers
                    val computerList = state.articles

                    Log.d(TAG, "Received computer list: $computerList")
                    Snackbar.make(mBinding.root, "Success", Snackbar.LENGTH_LONG).show()

                    // For example, you can set the articles to a RecyclerView adapter here
                    adapter.updateData(computerList)
                }

                is ApiCallState.Error -> {
                    mBinding.loaderLl.visibility = android.view.View.INVISIBLE
                    // Show error message
                    val errorMessage = state.message
                    // For example, you can show a Toast or a Snackbar with the error message
                    Snackbar.make(mBinding.root, errorMessage, Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }
}
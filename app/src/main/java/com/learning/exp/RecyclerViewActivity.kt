package com.learning.exp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.learning.exp.DataProviderUtils.getFruitList
import com.learning.exp.databinding.RecyclerviewActivityBinding

class RecyclerViewActivity : AppCompatActivity() {
    private lateinit var mBinding: RecyclerviewActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = RecyclerviewActivityBinding.inflate(layoutInflater)

        setContentView(mBinding.root)

        val dataFromMainActivity = intent.getStringExtra("EXTRA_DATA")
        val numberFromMainActivity = intent.getIntExtra("EXTRA_NUMBER", 0)

        Toast.makeText(this, "onCreate, $dataFromMainActivity", Toast.LENGTH_SHORT).show()
        Log.d("RecyclerViewActivity", "onCreate called, $dataFromMainActivity, $numberFromMainActivity")


        // getting the recyclerview by its id
        val recyclerview = mBinding.recyclerView

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        // this creates a grid layout Manager with 2 columns
        //recyclerview.layoutManager = GridLayoutManager(this, 2)

        val list = getFruitList()


        // This will pass the ArrayList to our Adapter
        val adapter = RecyclerViewAdapter(list, {
            /*Toast.makeText(
                this,
                "Clicked: $it",
                Toast.LENGTH_SHORT
            ).show()
*/
            Snackbar.make(recyclerview, "Clicked: $it", Snackbar.LENGTH_SHORT).show()
        })

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter
    }


    // Lifecycle all methods
    override fun onStart() {
        super.onStart()
        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show()
        Log.d("RecyclerViewActivity", "onStart called")

    }

    override fun onRestart() {

        super.onRestart()
        Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show()
        Log.d("RecyclerViewActivity", "onRestart called")
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show()
        Log.d("RecyclerViewActivity", "onResume called")
    }

    override fun onPause() {
        super.onPause()
        Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show()
        Log.d("RecyclerViewActivity", "onPause called")
    }

    override fun onStop() {
        super.onStop()
        Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show()
        Log.d("RecyclerViewActivity", "onStop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show()
        Log.d("RecyclerViewActivity", "onDestroy called")
    }




}
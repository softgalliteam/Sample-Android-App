package com.learning.exp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.learning.exp.DataProviderUtils.getFruitList
import com.learning.exp.databinding.ListviewActivityBinding

class ListActivity : AppCompatActivity() {
    private lateinit var mBinding: ListviewActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ListviewActivityBinding.inflate(layoutInflater)

        setContentView(mBinding.root)

        val stateListView = mBinding.listView

        /*
        Simple ListView using ArrayAdapter

        val list = ArrayList<String>()
        list.add("Jharkhand")
        list.add("Bihar")
        list.add("UP")
        list.add("Haryana")
        list.add("Chatisgarh")
        list.add("Maharastra")

            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
        stateListView.adapter = adapter
        */


        val list = getFruitList()

        val adapter = FruitListAdaptor(this, list)
        stateListView.adapter = adapter
    }

}

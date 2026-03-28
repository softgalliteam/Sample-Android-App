package com.learning.exp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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


        //Custom ListView using CustomAdapter


        val list = ArrayList<FruitListItem>()
        list.add(
            FruitListItem(
                "Apple",
                "Apple is best fruit which keeps us away from doctor",
                R.drawable.apple
            )
        )
        list.add(
            FruitListItem(
                "Mango",
                "Mango is the king of fruits and is loved by everyone for its sweet taste and juicy texture.",
                 R.drawable.mango
            )
        )
        list.add(
            FruitListItem(
                "Banana",
                "Bananas are a popular fruit known for their sweet flavor and versatility in various dishes.",
                R.drawable.logo
            )
        )
        list.add(
            FruitListItem(
                "Pine Apple",
                "Pineapple is a tropical fruit with a sweet and tangy flavor, known for its unique appearance and delicious taste.",
                R.drawable.logo
            )
        )
        list.add( FruitListItem(
            "Kiwi",
            "Kiwi is a small, fuzzy fruit with a sweet and tangy flavor, packed",
            R.drawable.logo
        ))


        val adapter = FruitListAdaptor(this, list)
        stateListView.adapter = adapter
    }

}

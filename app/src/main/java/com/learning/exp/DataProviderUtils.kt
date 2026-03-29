package com.learning.exp

object DataProviderUtils {
    fun getFruitList(): List<FruitListItem> {
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
        list.add(
            FruitListItem(
                "Kiwi",
                "Kiwi is a small, fuzzy fruit with a sweet and tangy flavor, packed",
                R.drawable.logo
            )
        )
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
        list.add(
            FruitListItem(
                "Kiwi",
                "Kiwi is a small, fuzzy fruit with a sweet and tangy flavor, packed",
                R.drawable.logo
            )
        )
        return list
    }
}
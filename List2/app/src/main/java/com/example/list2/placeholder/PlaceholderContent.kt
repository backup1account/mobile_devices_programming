package com.example.list2.placeholder

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.ArrayList
import kotlin.random.Random


object PlaceholderContent {
    val ITEMS: MutableList<CrimeItem> = ArrayList()
    private const val COUNT = 19

    init {
        for (i in 0..COUNT) {
            addItem(createCrimeItem(i))
        }
    }

    private fun addItem(item: CrimeItem) {
        ITEMS.add(item)
    }

    private fun createCrimeItem(position: Int): CrimeItem {
        return CrimeItem(position,
            title="Crime #$position",
            description="Here is clear, amazing description for $position student's crime",
            index=(300000..399999).random(),
            isSolved=Random.nextBoolean()
        )
    }

    @Parcelize
    data class CrimeItem(val id: Int, val title: String, val description: String,
                         val index: Int, val isSolved: Boolean) : Parcelable

}


package com.example.list2

import com.example.list2.placeholder.PlaceholderContent

interface OnClickHandler {
    fun onClickItem(crimeItem: PlaceholderContent.CrimeItem)
}
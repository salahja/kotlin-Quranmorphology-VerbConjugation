package com.example.compose
import androidx.compose.runtime.Immutable

@Immutable
data class ExpandableCardModel(val id: Int, val title: String,val vlist:ArrayList<String>)
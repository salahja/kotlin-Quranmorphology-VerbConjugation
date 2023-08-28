package sj.hisnul.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hcategory")
class hcategory(var title: String, @field:PrimaryKey(autoGenerate = true) var id: Int)
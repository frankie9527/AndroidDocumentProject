package android.kotlin.practice.data.dao

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null,
    @ColumnInfo(name = "userName") val userName: String?,
    @ColumnInfo(name = "phoneNum") val phoneNum: String

)
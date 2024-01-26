package android.room.practice.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "userName") val userName: String?,
    @ColumnInfo(name = "phoneNum") val phoneNum: String

)
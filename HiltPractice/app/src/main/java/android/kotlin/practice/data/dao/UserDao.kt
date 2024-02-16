package android.kotlin.practice.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE userName LIKE :name LIMIT 1")
    fun findByName(name: String): User

    @Insert
    fun insert(user: User)

    @Delete
    fun delete(user: User)
}
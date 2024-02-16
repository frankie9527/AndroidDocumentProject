package android.kotlin.practice.data.dao

import kotlinx.coroutines.flow.Flow


interface UserRepository {

    fun getAll():  List<User>

    suspend fun insert(user: User)

    suspend fun findByName(name: String): User

    suspend fun delete(user: User)
}
package android.kotlin.practice.data.dao

class UserRepositoryImpl constructor(private val dao: UserDao) : UserRepository {
    override fun getAll(): List<User> {
        return dao.getAll();
    }

    override suspend fun insert(user: User) {
        return dao.insert(user)
    }

    override suspend fun findByName(name: String): User {
        return dao.findByName(name);
    }

    override suspend fun delete(user: User) {
        dao.delete(user)
    }

}
package com.example.myapplicationasdfasdfasdfasdfasdf.DATA

import com.example.myapplicationasdfasdfasdfasdfasdf.DATA.UserDao.UserDao
import kotlinx.coroutines.flow.Flow

/**
 * Repository that provides insert, update, delete, and retrieve of [users] from a given data source.
 */
interface UserRepository {
    /**
     * Retrieve all the users from the the given data source.
     */
    fun getAllusersStream(): Flow<List<User>>

    /**
     * Retrieve an users from the given data source that matches with the [id].
     */
    fun getuserStream(id: Int): Flow<User?>

    /**
     * Insert users in the data source
     */
    suspend fun insertuser(users: User)

    /**
     * Delete users from the data source
     */
    suspend fun deleteuser(users: User)

    /**
     * Update users in the data source
     */
    suspend fun updateuser(users: User)
}

class OfflineUsersRepository(private val usersDao: UserDao) : UserRepository {
    override fun getAllusersStream(): Flow<List<User>> = usersDao.getAllusers()

    override fun getuserStream(id: Int): Flow<User?> = usersDao.getusers(id)

    override suspend fun insertuser(users: User) = usersDao.insert(users)

    override suspend fun deleteuser(users: User) = usersDao.delete(users)

    override suspend fun updateuser(users: User) = usersDao.update(users)

}
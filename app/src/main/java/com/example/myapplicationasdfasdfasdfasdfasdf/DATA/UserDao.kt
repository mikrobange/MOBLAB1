package com.example.myapplicationasdfasdfasdfasdfasdf.DATA

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

interface UserDao {
    abstract fun update(user: Any)


    @Dao
    interface UserDao {
        @Insert(onConflict = OnConflictStrategy.IGNORE)
        suspend fun insert(users: User)

        @Update
        suspend fun update(users: User)

        @Delete
        fun delete(users: User)

        @Query("SELECT * from users WHERE id = :id")
        fun getusers(id: Int): Flow<User>

        @Query("SELECT * from users WHERE mac = :id")
        fun Getdata(id: String): Flow<List<User>>





        @Query("""
    SELECT * FROM users 
    GROUP BY mac
    ORDER BY id DESC
    LIMIT 1 
""")
        suspend fun getThirdLatestUser(): User

        //@Query("SELECT * from users ORDER BY id ASC")
        @Query("""
    SELECT * FROM users 
    GROUP BY mac
    ORDER BY id ASC
""")
        fun getAllusers(): Flow<List<User>>




        @Query("""
    SELECT * FROM users 
""")
        fun A(): Flow<List<User>>
        @Query("DELETE FROM users") // Adjust the table name if necessary
        suspend fun deleteAllUsers()



    }

}
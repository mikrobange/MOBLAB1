package com.example.myapplicationasdfasdfasdfasdfasdf.ui.notifications

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplicationasdfasdfasdfasdfasdf.DATA.JReader
import com.example.myapplicationasdfasdfasdfasdfasdf.DATA.User
import com.example.myapplicationasdfasdfasdfasdfasdf.DATA.UserDatabase
import com.example.myapplicationasdfasdfasdfasdfasdf.Power.Power
import com.example.myapplicationasdfasdfasdfasdfasdf.Power.PowerDatabase
import com.example.myapplicationasdfasdfasdfasdfasdf.SIGNALS.MatavimasDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NotificationsViewModel(application: Application) : AndroidViewModel(application) {

    private val userDao = UserDatabase.getDatabase(application).usersDao()
    private val MatavimasDao = MatavimasDatabase.getDatabase(application).MatavimasDao()
    private val PowerDao = PowerDatabase.getDatabase(application).PowerDao()


    private val _userList = MutableLiveData<List<User>>()
    val userList: LiveData<List<User>> get() = _userList








    private val _userSaved = MutableLiveData<Boolean>()
    val userSaved: LiveData<Boolean> get() = _userSaved

    // idk ar sito reik
    init {
        // Initialize LiveData with data from the database
        loadUsers()
    }
    fun loadUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            userDao.getAllusers().collect { userList ->
                _userList.postValue(userList) // This should update the LiveData
            }
        }
    }




    fun loadUsersFromJson(fileName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            // Use the JReader to load users from JSON
            val users: List<User>? = JReader.Utilities.loadJsonFromFile(getApplication(), fileName, User::class.java)
            //clearUsers()
            // Insert users into the database if the list is not null
            users?.forEach { user ->
                userDao.insert(user)
            }


            loadUsers()
        }
    }
    fun fetchThirdLatestUser() = viewModelScope.launch {
        val thirdLatestUser = withContext(Dispatchers.IO) {
            userDao.getThirdLatestUser()
        }
        // Update LiveData or StateFlow with the result
    }
    suspend fun getSelected(): String {
        val user = userDao.getThirdLatestUser() // This should be a suspend function or Flow in your DAO
        return user.mac.toString()  // Assuming `id` is a String. Adjust based on your User model.
    }

    // Function to add a new user to the database
    fun saveUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userDao.insert(user)
            // Collect the Flow from getAllUsers and post the result to _userList
            loadUsers() // This will update _userList automatically
        }
    }
    fun Update() {
            _userSaved.postValue(true)
    }

    fun clearUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            userDao.deleteAllUsers()
            loadUsers() // Reload the list after clearing the database
        }
    }
    data class UiState(val itemList: List<User> = listOf())

    suspend fun saveItem() {

    }
    private val _text = MutableLiveData<String>().apply {
        value = "This is Tavo mama"
    }
    val text: LiveData<String> = _text
}

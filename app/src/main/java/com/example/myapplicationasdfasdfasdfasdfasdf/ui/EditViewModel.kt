package com.example.myapplicationasdfasdfasdfasdfasdf.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplicationasdfasdfasdfasdfasdf.DATA.User
import com.example.myapplicationasdfasdfasdfasdfasdf.DATA.UserDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditViewModel(application: Application) : AndroidViewModel(application)  {



    private val userDao = UserDatabase.getDatabase(application).usersDao()
    private val _userList = MutableLiveData<List<User>>()
    val userList: LiveData<List<User>> get() = _userList


    fun GetAUser(mac: String) {
        viewModelScope.launch(Dispatchers.IO) {
            userDao.Getdata(mac).collect { users ->
                _userList.postValue(users) // This should update the LiveData
            }
        }
    }
    fun UpdateAUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userDao.update(user)

        }
    }


}
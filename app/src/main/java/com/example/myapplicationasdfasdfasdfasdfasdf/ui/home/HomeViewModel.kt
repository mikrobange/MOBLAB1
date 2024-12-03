package com.example.myapplicationasdfasdfasdfasdfasdf.ui.home

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
import com.example.myapplicationasdfasdfasdfasdfasdf.SIGNALS.Matavimas
import com.example.myapplicationasdfasdfasdfasdfasdf.SIGNALS.MatavimasDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {


    private val MatavimasDao = MatavimasDatabase.getDatabase(application).MatavimasDao()
    val UserDao = UserDatabase.getDatabase(application).usersDao()
    val PowerDao = PowerDatabase.getDatabase(application).PowerDao()


   // var allSignals: Flow<List<Matavimas>> = this.MatavimasDao.getAllSignals()

    private val _usersigList = MutableLiveData<List<Matavimas>>()
    val usersigList: LiveData<List<Matavimas>> get() = _usersigList

    private val _sigList = MutableLiveData<List<Matavimas>>()
    val sigList: LiveData<List<Matavimas>> get() = _sigList

    private val _usrList = MutableLiveData<List<User>>()
    val usrList: LiveData<List<User>> get() = _usrList

    private val _pwrList = MutableLiveData<List<Power>>()
    val pwrList: LiveData<List<Power>> get() = _pwrList

    val USERS : List<User> get() = _usrList.value ?: listOf()
    val POWERS : List<Power> get() = _pwrList.value ?: listOf()

    fun updateSignals(){
        //allSignals = MatavimasDao.getAllSignals()
        viewModelScope.launch(Dispatchers.IO) {
            MatavimasDao.getAllSignals().collect { userList ->
                _sigList.postValue(userList) // This should update the LiveData
            }
        }



    }


    fun GetOthers(){
        viewModelScope.launch(Dispatchers.IO) {
            UserDao.A().collect { userList ->
                _usrList.postValue(userList) // This should update the LiveData
            }
        }
        viewModelScope.launch(Dispatchers.IO) {
            PowerDao.GetAll().collect { userList ->
                _pwrList.postValue(userList) // This should update the LiveData
            }
        }
    }



//    fun FindUserSignals() {
//        viewModelScope.launch(Dispatchers.IO) {
//            // Create mutable lists to collect users and signals
//            val USERS: MutableList<User> = mutableListOf()
//            val SIGNALS: MutableList<Matavimas> = mutableListOf()
//
//            // Collect users concurrently
//            val usersDeferred = async {
//                UserDao.A().collect { userList ->
//                    USERS.addAll(userList)  // Add users to the list
//                }
//            }
//            usersDeferred.await()
//
//            // Collect signals concurrently
//            val signalsDeferred = async {
//                MatavimasDao.getAllSignals().collect { signalList ->
//                    SIGNALS.addAll(signalList)  // Add signals to the list
//                }
//            }
//
//            // Wait for both collections to finish
//            signalsDeferred.await()
//
//            // Proceed with calculations
//            val MATHS: MutableList<Double> = mutableListOf()
//            var i = 0
//            while (i < USERS.size) {
//                val usr1 = USERS[i].stiprumas.toInt()
//                val usr2 = USERS[i + 1].stiprumas.toInt()
//                val usr3 = USERS[i + 2].stiprumas.toInt()
//                val math = Math.sqrt(
//                    (Math.pow(usr1.toDouble(), 2.0) + Math.pow(usr2.toDouble(), 2.0) + Math.pow(usr3.toDouble(), 2.0))
//                )
//                MATHS.add(math)
//                i += 3
//            }
//
//            val FOUND: MutableList<Matavimas> = mutableListOf()
//            var closest: Double = Double.MAX_VALUE
//            var sigmal: Matavimas? = null
//            SIGNALS.forEach { sig ->
//                MATHS.forEach { dist ->
//                    val a = Math.abs(sig.atstumas - dist)
//                    if (a < closest) {
//                        closest = a
//                        sigmal = sig
//                    }
//                }
//                sigmal?.let { FOUND.add(it) }
//            }
//
//            // Post the found list to LiveData
//            _sigList.postValue(FOUND)
//        }
//    }


    fun BOB(list : List<Int> ) {
        viewModelScope.launch(Dispatchers.IO) {
                MatavimasDao.GETBOBDATA(list).collect{ mat ->
                    if (mat != null) {
                        _usersigList.postValue(mat)
                    }
                }
        }
    }



    fun clearUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            MatavimasDao.deleteAllSignals()
        }
    }

    fun loadSignalsFromJson(fileName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            //clearUsers()
            val matavimai: List<Matavimas>? = JReader.Utilities.loadJsonFromFile(getApplication(), fileName, Matavimas::class.java)
            matavimai?.forEach { matavimas ->
                MatavimasDao.insert(matavimas)
            }
            println(matavimai)
        }
    }


}
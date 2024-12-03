package com.example.myapplicationasdfasdfasdfasdfasdf

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myapplicationasdfasdfasdfasdfasdf.DATA.UserDatabase
import com.example.myapplicationasdfasdfasdfasdfasdf.Power.PowerDatabase
import com.example.myapplicationasdfasdfasdfasdfasdf.SIGNALS.MDatabase
import com.example.myapplicationasdfasdfasdfasdfasdf.SIGNALS.Matavimas
import com.example.myapplicationasdfasdfasdfasdfasdf.SIGNALS.MatavimasDatabase
import com.example.myapplicationasdfasdfasdfasdfasdf.databinding.ActivityMainBinding
import com.example.myapplicationasdfasdfasdfasdfasdf.ui.home.HomeViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch

class MainActivity() : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_notifications, R.id.edit
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


    }

    override fun onStart() {
        super.onStart()
        val HomeView: HomeViewModel by viewModels()

        val MatavimasDao = MatavimasDatabase.getDatabase(applicationContext).MatavimasDao()
        val MDao = MDatabase.getDatabase(applicationContext).MatavimasDao()
        val UserDao = UserDatabase.getDatabase(applicationContext).usersDao()
        val PowerDao = PowerDatabase.getDatabase(applicationContext).PowerDao()


        lifecycleScope.launch {
//            MatavimasDao.deleteAllSignals()
//            UserDao.deleteAllUsers()


            val mute:MutableList<Matavimas> = emptyList<Matavimas>().toMutableList()
            val users = ReadUsersFromApi()
            val matavimai = ReadMatavimaiFromApi()
            val stiprumai = ReadPowersFromApi()


            if (users != null)
            {
                users.forEach { user ->
                    UserDao.insert(user)
                }
            }

            if (matavimai != null)
            {
                matavimai.forEach { matavimas ->
                    MatavimasDao.insert(matavimas)
                }
            }
            if (stiprumai != null)
            {
                stiprumai.forEach { stiprumas ->
                    PowerDao.insert(stiprumas)
                }
            }


            var list: MutableList<Double> = mutableListOf()

            if (users != null && stiprumai != null) {
                var i = 0
                while (i < users.count()) {

                    var usr1 = users[i].stiprumas.toInt()
                    var usr2 = users[i + 1].stiprumas.toInt()
                    var usr3 = users[i + 2].stiprumas.toInt()
                    var pwr1 = stiprumai[i].stiprumas.toInt()
                    var pwr2 = stiprumai[i + 1].stiprumas.toInt()
                    var pwr3 = stiprumai[i + 2].stiprumas.toInt()


//                    var math = Math.sqrt(
//                        (Math.multiplyExact(
//                            usr1 - pwr1,
//                            usr1 - pwr1
//                        ) + Math.multiplyExact(
//                            usr2 - pwr2,
//                            usr2 - pwr2
//                        ) + Math.multiplyExact(usr3 - pwr3, usr3 - pwr3)).toDouble()
//                    )
                    var math = Math.sqrt(
                        (Math.pow((usr1).toDouble(),2.0) + Math.pow((usr2).toDouble(),2.0) + Math.pow((usr3).toDouble(),2.0)))
                    list.add(math)


                    i += 3
                }
            }
            if (matavimai != null) {
                var closet: Double = Double.MAX_VALUE;
                var sigma: Matavimas = matavimai[0]
                list.forEach { yeet ->

                    matavimai.forEach() { op ->

                        var a = Math.abs(yeet - op.atstumas)

                        if (a < closet) {
                            closet = a
                            sigma = op
                        }

                    }

                    mute.add(sigma)

                }
            }

//            MatavimasDao.deleteAllSignals()
//            mute.forEach { matavimas ->
//                MatavimasDao.insert(matavimas)
////                Log.d("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB", matavimas.toString())
//
//            }














        }

    }
}

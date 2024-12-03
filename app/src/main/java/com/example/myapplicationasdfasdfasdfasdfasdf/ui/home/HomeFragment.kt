package com.example.myapplicationasdfasdfasdfasdfasdf.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.myapplicationasdfasdfasdfasdfasdf.DATA.User
import com.example.myapplicationasdfasdfasdfasdfasdf.Power.Power
import com.example.myapplicationasdfasdfasdfasdfasdf.R
import com.example.myapplicationasdfasdfasdfasdfasdf.databinding.FragmentHomeBinding
import com.example.myapplicationasdfasdfasdfasdfasdf.ui.notifications.NotificationsViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.math.pow

class HomeFragment : Fragment() {


    private lateinit var HomeView: HomeViewModel
    private lateinit var NotificationsView: NotificationsViewModel

    private var _binding: FragmentHomeBinding? = null
    private lateinit var butt: FloatingActionButton

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!










    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        HomeView = HomeViewModel(requireActivity().application)
        NotificationsView = NotificationsViewModel(requireActivity().application)
        butt = root.findViewById(R.id.butonsas)




        return inflater.inflate(R.layout.fragment_home, container, false)

        /*_binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome


        var HomeView = HomeViewModel(requireActivity().application);
        HomeView.loadSignalsFromJson("stiprumai.json")

        val gridView = findViewById<GridView>(R.id.gridView)
        gridView.coordinates = listOf(
            Pair(0, 0), // Top-left corner
            Pair(2, 3), // Position on grid (x=2, y=3)
            Pair(5, 5)  // Position on grid (x=5, y=5)
        )
        gridView.invalidate() // Request re-draw

        return root*/
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Use the view parameter to find the GridView
        val gridView = view.findViewById<GridView>(R.id.gridView)
        //HomeView.loadSignalsFromJson("matavimai.json")

        HomeView.updateSignals()
        HomeView.GetOthers()
//        gridView.Chng()
//        HomeView.FindUserSignals()


        HomeView.sigList.observe(viewLifecycleOwner, Observer { signals ->
            signals?.let {
                gridView.setSignals(signals)
            }
        })
//        NotificationsView.loadPowers()
//        NotificationsView.loadUsers()



        var vartotojai: List<User>? = null  // Adjust type as per your data model
        var stiprumai: List<Power>? = null    // Adjust type as per your data model

        var isVartotojaiUpdated = false
        var isStiprumaiUpdated = false
        var ONCE = true



        // Observer for vartotojai
        HomeView.usrList.observe(viewLifecycleOwner, Observer { signals ->
            signals?.let {
                vartotojai = HomeView.USERS
                if (vartotojai != null) {
                    isVartotojaiUpdated = true
                    if (isVartotojaiUpdated && isStiprumaiUpdated){
                        Log.d("VARTOTOJAI", vartotojai!!.size.toString())
                        Log.d("STIPRUMAI", stiprumai!!.size.toString())
                        Log.d("isStiprumaiUpdated", isStiprumaiUpdated.toString())
                        Log.d("isVartotojaiUpdated", isVartotojaiUpdated.toString())
                        if (vartotojai!!.size >= 3 && stiprumai!!.size >= 570) {
                            val result = executeIfReady(
                                vartotojai,
                                stiprumai,
                                isVartotojaiUpdated,
                                isStiprumaiUpdated
                            )
                            result?.let {
                                Log.d("Result", it.toString())  // Handle the returned list
                                if (it != null && it.size >= 3 && ONCE) {
                                    ONCE = false
                                    HomeView.BOB(it)
                                }
                            }
                        }
                    }
                }
                //executIfReady(vartotojai, stiprumai, isVartotojaiUpdated, isStiprumaiUpdated)
            }
        })

        // Observer for stiprumai
        HomeView.pwrList.observe(viewLifecycleOwner, Observer { signals ->
            signals?.let {
                stiprumai = HomeView.POWERS
                if (stiprumai != null)
                {




                        isStiprumaiUpdated = true

                        if (isVartotojaiUpdated && isStiprumaiUpdated){
                            Log.d("STIPRUMAI", stiprumai!!.size.toString())
                            Log.d("VARTOTOJAI", vartotojai!!.size.toString())
                            Log.d("isStiprumaiUpdated", isStiprumaiUpdated.toString())
                            Log.d("isVartotojaiUpdated", isVartotojaiUpdated.toString())
                            if (vartotojai!!.size >= 3 && stiprumai!!.size >= 570) {
                                val result = executeIfReady(
                                    vartotojai,
                                    stiprumai,
                                    isVartotojaiUpdated,
                                    isStiprumaiUpdated
                                )
                                result?.let {
                                    Log.d("Result", it.toString())  // Handle the returned list
                                    if (it != null && it.size >= 3 && ONCE) {
                                        ONCE = false
                                        HomeView.BOB(it)

                                    }
                                }
                            }
                        }

                }

            }
        })
        //gridView.setSignals(signals)

        HomeView.usersigList.observe(viewLifecycleOwner, Observer { signals ->
            signals?.let {
                if (!signals.isEmpty()){
                    gridView.setUsers(signals)

                }
            }
        })


    }

    private fun executeIfReady(
        vartotojai: List<User>?,
        stiprumai: List<Power>?,
        isVartotojaiUpdated: Boolean,
        isStiprumaiUpdated: Boolean
    ) : List<Int> {
        if (isVartotojaiUpdated && isStiprumaiUpdated) {
            // Proceed with your logic
            if (vartotojai != null && stiprumai != null) {
                val ToCOLOR = mutableListOf<Int>()
                var small = Float.MAX_VALUE
                var index: Int? = null

                var i = 0
                var j = 0
                while (i in 0 until vartotojai.size - 2) {
                    small = Float.MAX_VALUE
                    while(j in 0 until stiprumai.size - 2) {

                        if (stiprumai[j].matavimas != stiprumai[j + 1].matavimas &&
                            stiprumai[j].matavimas != stiprumai[j + 2].matavimas) {
                            Log.d("Pirmas", stiprumai[j].matavimas.toString())
                            break
                        }
                        if (vartotojai[i].mac != vartotojai[i + 1].mac &&
                            vartotojai[i].mac != vartotojai[i + 2].mac) {
                            Log.d("Pirmas", vartotojai[i].mac.toString())
                            break
                        }


                        val a = (vartotojai[i].stiprumas.toInt() - stiprumai[j].stiprumas).toFloat().pow(2) +
                                (vartotojai[i + 1].stiprumas.toInt() - stiprumai[j + 1].stiprumas).toFloat().pow(2) +
                                (vartotojai[i + 2].stiprumas.toInt() - stiprumai[j + 2].stiprumas).toFloat().pow(2)

                        if (a < small) {
                            small = a
                            index = stiprumai[j].matavimas
                        }
                        j+=3

                    }
                    j =0
                    index?.let { ToCOLOR.add(it) }
                    i+=3
                }

                Log.d("I BORKED", ToCOLOR.toString())
                return ToCOLOR

            }
        }
        Log.d("PASIKLYDAU", "WHEREAMI")
        return emptyList()
    }
    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}
package com.example.myapplicationasdfasdfasdfasdfasdf.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationasdfasdfasdfasdfasdf.DATA.User
import com.example.myapplicationasdfasdfasdfasdfasdf.R
import com.example.myapplicationasdfasdfasdfasdfasdf.databinding.FragmentNotificationsBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch


class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var userAdapter: SensorDataAdapter

    private var _binding: FragmentNotificationsBinding? = null
    //private val binding get() = _binding!!
    private lateinit var butt: FloatingActionButton
    //private lateinit var sensorDataAdapter: SensorDataAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        //val notificationsViewModel = ViewModelProvider(this).get(NotificationsViewModel::class.java)
        //var userViewModel = NotificationsViewModel(requireActivity().application);
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)


        //var users = loadJsonFromFile(requireContext(),"vartotojai.json", User::class.java)

        /*
        var recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        sensorDataAdapter = SensorDataAdapter(users!!)
        recyclerView.adapter = sensorDataAdapter
        */

        notificationsViewModel = NotificationsViewModel(requireActivity().application)
        recyclerView = root.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)


        notificationsViewModel.userList.observe(viewLifecycleOwner, Observer { users ->
            userAdapter.setUsers(users)
        })

        // notificationsViewModel.loadUsersFromJson("vartotojai.json")

        userAdapter = SensorDataAdapter(emptyList()) { mac ->
            val bundle = Bundle().apply {
                putString("MAC", mac) // Replace with the correct key if necessary
            }
            findNavController().navigate(R.id.edit, bundle)
        }
        recyclerView.adapter = userAdapter

        //val root: View = binding.root

        butt = root.findViewById(R.id.floatingActionButton)
        butt.setOnClickListener {
            //Log.d("NotificationsFragment", "Button clicked!")
            val charset = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789:"  // Customize this set as needed
            val randomMac = generateRandomMac()
            val newUser = User(0, randomMac, "Sensorius", "-1")
            notificationsViewModel.saveUser(newUser)
            notificationsViewModel.saveUser(newUser)
            notificationsViewModel.saveUser(newUser)
            notificationsViewModel.Update()

            notificationsViewModel.userSaved.observe(viewLifecycleOwner) {  isSaved ->
                if (isSaved) {

                    lifecycleScope.launch {
                        val mac = notificationsViewModel.getSelected()  // Call the suspend function
                        val bundle = Bundle().apply {
                            putString("MAC", mac)
                        }
                        findNavController().navigate(R.id.edit, bundle)
                    }
                }

            }
        }

        return root
    }
    fun generateRandomMac(): String {
        val hexChars = "0123456789ABCDEF"
        return (1..5)  // We need 5 pairs for XX:XX:XX:XX:XX
            .map { (1..2).map { hexChars.random() }.joinToString("") }  // Each pair has 2 hex digits
            .joinToString(":")
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
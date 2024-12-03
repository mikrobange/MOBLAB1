package com.example.myapplicationasdfasdfasdfasdfasdf.ui.notifications

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationasdfasdfasdfasdfasdf.DATA.User
import com.example.myapplicationasdfasdfasdfasdfasdf.R


class SensorDataAdapter(private var sensorList: List<User>,
                        private val onEditClick: (String) -> Unit) :
    RecyclerView.Adapter<SensorDataAdapter.SensorDataViewHolder>() {

    class SensorDataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val idTextView: TextView = itemView.findViewById(R.id.id)
        val matTextView: TextView = itemView.findViewById(R.id.mat)
        val sensTextView: TextView = itemView.findViewById(R.id.sens)
        val editButt: Button = itemView.findViewById(R.id.button)
        //val stiprumasTextView: TextView = itemView.findViewById(R.id.stiprumas)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SensorDataViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_sensor_data, parent, false)
        return SensorDataViewHolder(view)
    }

    override fun onBindViewHolder(holder: SensorDataViewHolder, position: Int) {
        val sensorData = sensorList[position]
        holder.idTextView.text = sensorData.id.toString()
        holder.matTextView.text = sensorData.mac
        //holder.sensTextView.text = sensorData.sensorius

        holder.editButt.setOnClickListener {
            onEditClick(sensorData.mac)
        }
        //holder.stiprumasTextView.text = sensorData.stiprumas
    }

    override fun getItemCount(): Int {
        return sensorList.size
    }

    fun setUsers(users: List<User>) {
        sensorList = users
        notifyItemInserted(users.lastIndex)
        notifyDataSetChanged()
    }

}
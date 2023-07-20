package com.example.rentmyproperty.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.rentmyproperty.R

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        val btnLandlords = view.findViewById<Button>(R.id.btnLandlords)
        btnLandlords.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_OwnersFragment)
        }

        val btnProperties = view.findViewById<Button>(R.id.btnProperties)
        btnProperties.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_PropertyFragment)
        }

        val btnPropertyListView = view.findViewById<Button>(R.id.btnPropertiesListView)
        btnPropertyListView.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_PropertiesListFragment)
        }
        val btncamera = view.findViewById<Button>(R.id.btncamera)
        btncamera.setOnClickListener{
            findNavController().navigate(R.id.action_mainFragment_to_cameraFragment)
        }

        return view
    }
}

package com.example.rentmyproperty.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.rentmyproperty.R

class TenantsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tenants, container, false)

        val btnSearch = view.findViewById<Button>(R.id.btnSearch)
        btnSearch.setOnClickListener{
            findNavController().navigate(R.id.action_tenantsFragment_to_TenantReview)
        }
        val btnLeaveReview = view.findViewById<Button>(R.id.btnLeaveReview)
        btnLeaveReview.setOnClickListener{
            findNavController().navigate(R.id.action_tenantsFragment_to_TenantReview)
        }
        return view
    }

}


package com.example.rentmyproperty.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rentmyproperty.databinding.FragmentPropertiesListBinding
import com.example.rentmyproperty.models.PropertyFilter
import com.example.rentmyproperty.models.PropertyType
import com.example.rentmyproperty.ui.adapters.PropertyAdapter
import com.example.rentmyproperty.viewmodel.PropertiesListViewModel
import java.util.*

class PropertiesListFragment : Fragment() {

    private lateinit var binding: FragmentPropertiesListBinding
    private lateinit var viewModel: PropertiesListViewModel
    private lateinit var adapter: PropertyAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentPropertiesListBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this)[PropertiesListViewModel::class.java]

        adapter = PropertyAdapter(emptyList())

        binding.propertiesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.propertiesRecyclerView.adapter = adapter

        binding.filterButton.setOnClickListener {
            val filter = PropertyFilter(
                binding.searchEditText.text.toString(),
                getPropertyTypeFromString(binding.propertyTypeSpinner.selectedItem as? String),
                binding.minPriceEditText.text.toString().toIntOrNull(),
                binding.maxPriceEditText.text.toString().toIntOrNull()
            )
            viewModel.applyFilter(filter)
        }

        viewModel.properties.observe(viewLifecycleOwner) {
            adapter.updateProperties(it)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun getPropertyTypeFromString(propertyTypeString: String?): PropertyType? {
        Log.d("getPropertyType", "propertyTypeString: $propertyTypeString")
        return when(propertyTypeString?.uppercase(Locale.getDefault())) {
            "HOUSE" -> PropertyType.HOUSE
            "APARTMENT" -> PropertyType.APARTMENT
            "ROOM" -> PropertyType.ROOM
            else -> null
        }
    }
}

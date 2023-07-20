package com.example.rentmyproperty.ui.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.rentmyproperty.database.RentMyPropertyDatabase
import com.example.rentmyproperty.databinding.FragmentPropertyDetailsBinding
import com.example.rentmyproperty.domain.FavoritePlaces
import com.example.rentmyproperty.models.ReservationDTO
import com.example.rentmyproperty.viewmodel.PropertyDetailsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.util.*

class PropertyDetailsFragment : Fragment() {

    private lateinit var binding: FragmentPropertyDetailsBinding
    private lateinit var viewModel: PropertyDetailsViewModel
    private var propertyId: Long? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPropertyDetailsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(PropertyDetailsViewModel::class.java)
        propertyId = arguments?.getLong("propertyId")
        viewModel.getPropertyById(propertyId!!)

        viewModel.propertyDTO.observe(viewLifecycleOwner) { property ->
            binding.propertyTitle.text = property.title
            binding.propertyDescription.text = property.description
            binding.propertyPrice.text = property.price.toString()
            binding.propertyType.text = property.propertyType?.name
            // set other property details views here
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fromDateEditText.setOnClickListener {
            showDatePickerDialog(binding.fromDateEditText)
        }
        binding.toDateEditText.setOnClickListener {
            showDatePickerDialog(binding.toDateEditText)
        }
        binding.reserveButton.setOnClickListener {
            val fromDate = binding.fromDateEditText.text.toString()
            val toDate = binding.toDateEditText.text.toString()

            if (fromDate.isNotEmpty() && toDate.isNotEmpty()) {
                val reservation = ReservationDTO()
                reservation.id = 0
                reservation.fromDate = LocalDate.parse(fromDate, ReservationDTO.formatter)
                reservation.toDate = LocalDate.parse(toDate, ReservationDTO.formatter)
                reservation.tenant = 10015 // replace later
                reservation.property = 10014 // replace later

                viewModel.reserveProperty(fromDate, toDate)
            } else {
                Toast.makeText(requireContext(), "Please enter dates", Toast.LENGTH_SHORT).show()
            }
        }

        val favoritePlacesDao = RentMyPropertyDatabase.getDatabase(requireContext()).favoritePlacesDAO()

        binding.favoriteButton.setOnClickListener {
            lifecycleScope.launch {
                withContext(Dispatchers.IO) {
                    val favoritePlace = FavoritePlaces(
                        0,
                        binding.propertyTitle.text.toString(),
                        "Breda"
                    )
                    favoritePlacesDao.addFavoritePlace(favoritePlace)
                }
                Toast.makeText(requireContext(), "Added to favorites", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showDatePickerDialog(textView: TextView) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                // Update the textView with the selected date
                val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
                textView.text = selectedDate
            },
            year,
            month,
            dayOfMonth
        )
        datePickerDialog.show()
    }
}

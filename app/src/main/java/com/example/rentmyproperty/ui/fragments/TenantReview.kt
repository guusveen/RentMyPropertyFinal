package com.example.rentmyproperty.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.rentmyproperty.R
import com.example.rentmyproperty.database.RentMyPropertyDatabase
import com.example.rentmyproperty.databinding.FragmentTenantReviewBinding
import com.example.rentmyproperty.domain.Review
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TenantReview : Fragment() {

    private var _binding: FragmentTenantReviewBinding? = null
    private val binding get() = _binding!!

    //override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
      //  return inflater.inflate(R.layout.fragment_tenant_review, container, false)

    //}
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTenantReviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var ratingBar : RatingBar = view.findViewById(R.id.ratingBar)
        var comment : TextView = view.findViewById(R.id.commentEditText)
        val db = RentMyPropertyDatabase.getDatabase(requireContext())
        //val rating = binding.ratingBar.rating
        //val remark = binding.commentEditText.text.toString()
        binding.submitButton.setOnClickListener {
            val rating = ratingBar.rating
            val remark = comment.text.toString()
            if (rating > 0 && remark.isNotEmpty()) {
                lifecycleScope.launch {

                    withContext(Dispatchers.IO) {
                        val myReview = Review(null, rating, remark)
                        db.reviewDAO().insertReview(myReview)
                    }
                    // Update UI or perform other operations after the insert operation is complete
                }
                Toast.makeText(requireContext(), "Review submitted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Please enter a rating and comment", Toast.LENGTH_SHORT).show()
            }
        }

    }
}



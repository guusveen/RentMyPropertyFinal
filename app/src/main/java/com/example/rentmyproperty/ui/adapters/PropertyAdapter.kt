package com.example.rentmyproperty.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.rentmyproperty.R
import com.example.rentmyproperty.models.PropertyDTO
import com.example.rentmyproperty.ui.fragments.PropertiesListFragmentDirections
import com.squareup.picasso.Picasso

class PropertyAdapter(private var properties: List<PropertyDTO>) :
    RecyclerView.Adapter<PropertyAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.property_title)
        val description: TextView = view.findViewById(R.id.property_description)
        val price: TextView = view.findViewById(R.id.property_price)
        val propertyType: TextView = view.findViewById(R.id.property_type)
        val propertyDetailsButton: Button = view.findViewById(R.id.property_details_button)
        val propertyReviewButton: Button = view.findViewById(R.id.property_review_button)
        val headerImageView: ImageView = view.findViewById(R.id.headerImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.property_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load("https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fcloud.funda.nl%2Fvalentina_media%2F124%2F483%2F652_1440.jpg&f=1&nofb=1&ipt=fc00917378481a4759be7c9e9e5464bf33f1b6f8e94cf7b9ef44a8e5f45a7908&ipo=images").into(holder.headerImageView)
        val property = properties[position]
        holder.title.text = property.title
        holder.description.text = property.description
        holder.price.text = property.price?.toString()
        holder.propertyType.text = property.propertyType?.name
        holder.propertyDetailsButton.setOnClickListener {
            val action = PropertiesListFragmentDirections
                .actionPropertiesListFragmentToPropertyDetailFragment(property.id!!)
            val navController = Navigation.findNavController(holder.itemView)
            navController.navigate(action)
        }
        holder.propertyReviewButton.setOnClickListener {
            holder.itemView.findNavController().navigate(R.id.action_propertiesListFragment_to_TenantReview)
        }
    }

    override fun getItemCount() = properties.size

    fun updateProperties(newProperties: List<PropertyDTO>) {
        properties = newProperties
        notifyDataSetChanged()
    }
}

package com.example.rentmyproperty.ui.fragments

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.rentmyproperty.R
import com.example.rentmyproperty.databinding.FragmentPropertyBinding
import com.example.rentmyproperty.models.PropertyDTO
import com.example.rentmyproperty.models.PropertyFilter
import com.example.rentmyproperty.models.PropertyType
import com.example.rentmyproperty.viewmodel.PropertiesListViewModel
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.ClusterItem
import com.google.maps.android.clustering.ClusterManager
import java.util.*

class PropertyFragment : Fragment() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private lateinit var viewModel: PropertiesListViewModel

    private var _binding: FragmentPropertyBinding? = null
    private val binding get() = _binding!!

    private var currentLocation: Location? = null

    private var myGoogleMap: GoogleMap? = null

    //de marker beweegt mee met de realtime location, maar 1 marker toegevoegd met de markeroptions.
    private var currentLocationMarker: Marker? = null

    // Declare a variable for the cluster manager.
    private lateinit var clusterManager: ClusterManager<PropertyMarker>

    private lateinit var locationCallback: LocationCallback


    private val callback = OnMapReadyCallback { googleMap ->
        googleMap.setMinZoomPreference(10f)
        googleMap.setMaxZoomPreference(20f)

        val a: MarkerOptions = MarkerOptions().apply {
            position(LatLng(0.0, 0.0))
            title("You are here")
        }

        currentLocationMarker = googleMap.addMarker(a)
        setUpClusterer(googleMap)
        myGoogleMap = googleMap
    }

    // wordt aangeroepen bij de eerste aanmaak van t fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
// last known location
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
//gebruikt om code uit te voeren nadat de locatie is opgehaald
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                for (location in locationResult.locations) {
                        currentLocation = location
                        if (location != null) {
                            currentLocationMarker?.position = LatLng(
                                location.latitude,
                                location.longitude)
                            myGoogleMap?.moveCamera(
                                CameraUpdateFactory.newLatLng(
                                    LatLng(
                                        location.latitude,
                                        location.longitude
                                    )
                                )
                            )
                        }
                }
            }
        }
    }

    //wordt aangeroepen om de layout van het fragment op te blazen, grafische initialisatie vindt hier plaats
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        viewModel = ViewModelProvider(this)[PropertiesListViewModel::class.java]
        _binding = FragmentPropertyBinding.inflate(inflater, container, false)

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
            if(::clusterManager.isInitialized) {
                clusterManager.clearItems()
                it.forEach { property ->
                    if (property.latitude != null && property.longitude != null) {
                        addPropertyMarker(property)
                    }
                }
                //clustermanager wordt gedwongen om de clusters te laten zien
                clusterManager.cluster()
            }
        }
        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //genoemd naar oncreateview en zorgt ervoor dat de rootweergave van het fragment niet null is
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    override fun onResume() {
        super.onResume()
        startLocationUpdates()

    }

    // hieronder worden de permissies gedefinieerd
    @SuppressLint("MissingPermission")
    private fun startLocationUpdates() {
        if (isLocationPermissionGranted()) {
            Toast.makeText(requireContext(), "Start Location updates", Toast.LENGTH_SHORT).show()
            val locationRequest =
                LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 5000L).build()
            fusedLocationClient.requestLocationUpdates(locationRequest,
                locationCallback,
                Looper.getMainLooper())
        }
    }

    //permissie vragen bij opstarten locatie
    private fun isLocationPermissionGranted(): Boolean {
        val appContext = context?.applicationContext!!

        return if (ActivityCompat.checkSelfPermission(
                appContext,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                appContext,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                1
            )
            Toast.makeText(requireContext(), "Permissions not granted", Toast.LENGTH_LONG).show()
            false
        } else {
            true
        }
    }
//dit kan nog aangeroepen worden als de locatie gestopt wordt met updates
    private fun stopLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    inner class PropertyMarker(
        lat: Double,
        lng: Double,
        title: String,
        snippet: String,
    ) : ClusterItem {

        private val position: LatLng
        private val title: String
        private val snippet: String

        override fun getPosition(): LatLng {
            return position
        }

        override fun getTitle(): String {
            return title
        }

        override fun getSnippet(): String {
            return snippet
        }

        init {
            position = LatLng(lat, lng)
            this.title = title
            this.snippet = snippet
        }
    }

    private fun setUpClusterer(map: GoogleMap) {

        // Initialize the manager with the context and the map.
        clusterManager = ClusterManager(context, map)

        // Point the map's listeners at the listeners implemented by the cluster
        // manager.
        map.setOnCameraIdleListener(clusterManager)
        map.setOnMarkerClickListener(clusterManager)

    }

    private fun addPropertyMarker(property: PropertyDTO) {
        clusterManager.addItem(PropertyMarker(property.latitude!!,
            property.longitude!!,
            property.title ?: "",
            ""))
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
    companion object {
        private const val TAG = "Property Fragment"
    }
}

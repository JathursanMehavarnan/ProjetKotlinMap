package fr.epsi.projetkotlinmap

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.json.JSONObject

class StoreFragment : Fragment() {

    private lateinit var googleMap: GoogleMap

    private val callback = OnMapReadyCallback { googleMap ->
        this.googleMap = googleMap
        loadStoresFromApi()

        googleMap.setOnInfoWindowClickListener { marker ->
            val storeInformationsIntent = Intent(requireContext(), StoreInformationsActivity::class.java)
            val selectedStore = marker.tag as JSONObject
            storeInformationsIntent.putExtra("selectedStore", selectedStore.toString())
            startActivity(storeInformationsIntent)
        }
    }

    @SuppressLint("MissingPermission")
    val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                googleMap.isMyLocationEnabled = true
            }
            permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                googleMap.isMyLocationEnabled = true
            }
            else -> {
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_store, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val storeFragment = childFragmentManager.findFragmentById(R.id.store) as SupportMapFragment?
        storeFragment?.getMapAsync(callback)
    }

    private fun loadStoresFromApi() {
        val url = "https://www.ugarit.online/epsi/stores.json"
        val queue = Volley.newRequestQueue(requireContext())

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                val stores = response.getJSONArray("stores")
                for (i in 0 until stores.length()) {
                    val store = stores.getJSONObject(i)
                    val position = LatLng(
                        store.getDouble("latitude"),
                        store.getDouble("longitude")
                    )
                    val iconMarker = MarkerOptions()
                        .position(position)
                        .title(store.getString("name"))
                    val marker = googleMap.addMarker(iconMarker)
                    if (marker != null) {
                        marker.tag = store
                    }
                }
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(46.227638, 2.213749), 5f))
                Log.d("StoreFragment", "")
            },
            { error ->
                error.printStackTrace()
            }
        )
        queue.add(jsonObjectRequest)
    }
}

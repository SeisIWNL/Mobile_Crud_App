package pe.edu.cibertec.proyectofinalkotlin.fragmentsServices

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import pe.edu.cibertec.proyectofinalkotlin.R

class GeoFragment : Fragment(), OnMapReadyCallback,
    GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMyLocationClickListener {

    private lateinit var mGoogleMap: GoogleMap

    companion object {
        const val REQUEST_CODE_LOCATION = 0
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_geo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment = childFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mGoogleMap = googleMap

        // Configurar marcador en Plaza de Mayo, Buenos Aires
        val plazaDeMayo = LatLng(-34.6083, -58.3712)
        mGoogleMap.addMarker(MarkerOptions().position(plazaDeMayo).title("Plaza de Mayo"))

        // Mover la cámara del mapa al lugar con un zoom adecuado
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(plazaDeMayo, 15f))

        enableLocation()
    }

    private fun enableLocation() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED) {
            mGoogleMap.isMyLocationEnabled = true
        } else {
            requestPermissions(
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_CODE_LOCATION
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_LOCATION && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED) {
                mGoogleMap.isMyLocationEnabled = true
            }
        } else {
            Toast.makeText(requireContext(), "Permiso de ubicación denegado", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onMyLocationClick(location: Location) {
        Toast.makeText(
            requireContext(),
            "Estás en la coordenada: ${location.latitude}, ${location.longitude}",
            Toast.LENGTH_SHORT
        ).show()
        agregarMarcador(LatLng(location.latitude, location.longitude))
    }

    override fun onResume() {
        super.onResume()
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED) {
            mGoogleMap.isMyLocationEnabled = true
        } else {
            requestPermissions(
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_CODE_LOCATION
            )
        }
    }

    override fun onMyLocationButtonClick(): Boolean {
        val myLocation = mGoogleMap.myLocation
        if (myLocation != null) {
            agregarMarcador(LatLng(myLocation.latitude, myLocation.longitude))
        }
        return false
    }

    private fun agregarMarcador(latLng: LatLng) {
        mGoogleMap.clear()
        mGoogleMap.addMarker(MarkerOptions().position(latLng).title("Mi ubicación actual"))
    }
}
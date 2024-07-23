package com.bta.retotecnico.ui.view.activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import com.bta.retotecnico.R

import com.bta.retotecnico.domain.util.*
import com.bta.retotecnico.databinding.CartasActivityBinding
import com.bta.retotecnico.ui.viewmodel.CartasViewModel
import com.google.android.gms.location.*

import dagger.hilt.android.AndroidEntryPoint
import java.io.*
import java.util.Collections
import java.util.Locale

@AndroidEntryPoint
class CartasActivity : AppCompatActivity() {

    private lateinit var binding: CartasActivityBinding
    private val cartasViewModel: CartasViewModel by viewModels()
    var loading: TransparentProgressDialog? = null
    var className = "CartasActivity"
    var estadoJuego: Int = Constans.ESTADOS_JUEGO.CARTAS_VOLTEADAS

    var listaEstadosJuego: List<Int>? = null
    var regionLocalizacion: String = ""

    val PERMISSION_ID = 42
    lateinit var mFusedLocationClient: FusedLocationProviderClient
    var latitud: String = ""
    var longitud: String = ""

    var carta1Mostrada: Boolean = false
    var carta2Mostrada: Boolean = false
    var carta3Mostrada: Boolean = false
    var carta4Mostrada: Boolean = false

    var click1: Boolean = false
    var click2: Boolean = false
    var click3: Boolean = false
    var click4: Boolean = false

    var carta1 = ""
    var carta2 = ""
    var carta3 = ""
    var carta4 = ""

    var listaCartas: List<String>? = listOf(
        Constans.CARTAS.NAIPE_NEGRO,
        Constans.CARTAS.NAIPE_ROJO,
        Constans.CARTAS.NAIPE_NEGRO,
        Constans.CARTAS.NAIPE_ROJO
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            binding = CartasActivityBinding.inflate(layoutInflater)
            setContentView(binding.root)
            mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
            getLastLocation()
            initGame()
            observers()
            clickListener()
        } catch (e: Exception) {
            Log.e(className, e.message.toString())
        }
    }

    private fun initGame() {
        loading = TransparentProgressDialog(this)
        asignarCartas()
    }


    private fun asignarCartas() {
        Collections.shuffle(listaCartas);
        carta1 = listaCartas!![0]
        carta2 = listaCartas!![1]
        carta3 = listaCartas!![2]
        carta4 = listaCartas!![3]
    }


    private fun clickListener() {

        binding.btnConsultaEstadoServidor.setOnClickListener {
            asignarCartas()
            cartasVolteadasReset()
            //  consultaEstadoServidor()
            pruebaSinConexionApi()
        }

        binding.ivCartaUno.setOnClickListener {

            var cartaa1 = listaCartas!![0]
            var cartaa2 = listaCartas!![1]
            var cartaa3 = listaCartas!![2]
            var cartaa4 = listaCartas!![3]

            if (!carta1Mostrada) {
                carta1Mostrada = true
                if (!click1) {
                    click1 = true
                }

                if (carta2Mostrada) {
                    if (cartaa1 == cartaa2) {
                        estadoJuego = Constans.ESTADOS_JUEGO.PARES_ENCONTRADOS
                    }
                }

                if (carta3Mostrada) {
                    if (cartaa1 == cartaa3) {
                        estadoJuego = Constans.ESTADOS_JUEGO.PARES_ENCONTRADOS
                    }
                }

                if (carta4Mostrada) {
                    if (cartaa1 == cartaa4) {
                        estadoJuego = Constans.ESTADOS_JUEGO.PARES_ENCONTRADOS
                    }
                }


                loadImage(binding.ivCartaUno, cartaa1)

            }
        }

        binding.ivCartaDos.setOnClickListener {
            var cartaa1 = listaCartas!![0]
            var cartaa2 = listaCartas!![1]
            var cartaa3 = listaCartas!![2]
            var cartaa4 = listaCartas!![3]
            if (!carta2Mostrada) {
                carta2Mostrada = true

                if (!click2) {
                    click2 = true
                }

                if (carta1Mostrada) {
                    if (cartaa1 == cartaa2) {
                        estadoJuego = Constans.ESTADOS_JUEGO.PARES_ENCONTRADOS
                    }
                }

                if (carta3Mostrada) {
                    if (cartaa2 == cartaa3) {
                        estadoJuego = Constans.ESTADOS_JUEGO.PARES_ENCONTRADOS
                    }
                }

                if (carta4Mostrada) {
                    if (cartaa2 == cartaa4) {
                        estadoJuego = Constans.ESTADOS_JUEGO.PARES_ENCONTRADOS
                    }
                }
                loadImage(binding.ivCartaDos, cartaa2)
            }
        }

        binding.ivCartaTres.setOnClickListener {
            var cartaa1 = listaCartas!![0]
            var cartaa2 = listaCartas!![1]
            var cartaa3 = listaCartas!![2]
            var cartaa4 = listaCartas!![3]
            if (!carta3Mostrada) {
                carta3Mostrada = true

                if (!click3) {
                    click3 = true
                }

                if (carta1Mostrada) {
                    if (cartaa3 == cartaa1) {
                        estadoJuego = Constans.ESTADOS_JUEGO.PARES_ENCONTRADOS
                    }
                }

                if (carta2Mostrada) {
                    if (cartaa2 == cartaa3) {
                        estadoJuego = Constans.ESTADOS_JUEGO.PARES_ENCONTRADOS
                    }
                }

                if (carta4Mostrada) {
                    if (cartaa3 == cartaa4) {
                        estadoJuego = Constans.ESTADOS_JUEGO.PARES_ENCONTRADOS
                    }
                }
                loadImage(binding.ivCartaTres, cartaa3)
            }
        }

        binding.ivCartaCuatro.setOnClickListener {
            var cartaa1 = listaCartas!![0]
            var cartaa2 = listaCartas!![1]
            var cartaa3 = listaCartas!![2]
            var cartaa4 = listaCartas!![3]
            if (!carta4Mostrada) {
                carta4Mostrada = true

                if (!click4) {
                    click4 = true
                }

                if (carta1Mostrada) {
                    if (cartaa4 == cartaa1) {
                        estadoJuego = Constans.ESTADOS_JUEGO.PARES_ENCONTRADOS
                    }
                }

                if (carta2Mostrada) {
                    if (cartaa4 == cartaa2) {
                        estadoJuego = Constans.ESTADOS_JUEGO.PARES_ENCONTRADOS
                    }
                }

                if (carta3Mostrada) {
                    if (cartaa3 == cartaa4) {
                        estadoJuego = Constans.ESTADOS_JUEGO.PARES_ENCONTRADOS
                    }
                }
                loadImage(binding.ivCartaCuatro, cartaa4)
            }
        }

        binding.btnReiniciarJuego.setOnClickListener {
            asignarCartas()
            muestraCartas(Constans.ESTADOS_JUEGO.CARTAS_VOLTEADAS)
        }

        binding.btnEnviarEstadoServidor.setOnClickListener {
            Toast.makeText(this, "Estado enviado", Toast.LENGTH_SHORT).show()
        }

    }


    private fun pruebaSinConexionApi() {
        listaEstadosJuego = listOf(
            Constans.ESTADOS_JUEGO.CARTAS_VOLTEADAS,
            Constans.ESTADOS_JUEGO.PARES_ENCONTRADOS,
            Constans.ESTADOS_JUEGO.JUEGO_TERMINADO
        )
        estadoJuego = listaEstadosJuego!!.random()
        asignarCartas()
        muestraCartas(estadoJuego)
    }


    private fun muestraCartas(estado: Int) {
        when (estado) {
            Constans.ESTADOS_JUEGO.CARTAS_VOLTEADAS -> cartasVolteadas()
            Constans.ESTADOS_JUEGO.PARES_ENCONTRADOS -> paresEncontrados()
            Constans.ESTADOS_JUEGO.JUEGO_TERMINADO -> juegoTerminado()
            else -> { // Note the block
                Log.e(className, Constans.ERROR.ERROR)
            }
        }
    }


    fun cartasVolteadas() {
        loadImage(binding.ivCartaUno, Constans.CARTAS.DEFAULT)
        loadImage(binding.ivCartaDos, Constans.CARTAS.DEFAULT)
        loadImage(binding.ivCartaTres, Constans.CARTAS.DEFAULT)
        loadImage(binding.ivCartaCuatro, Constans.CARTAS.DEFAULT)

        carta1Mostrada = false
        carta2Mostrada = false
        carta3Mostrada = false
        carta4Mostrada = false
        click1 = false
        click2 = false
        click3 = false
        click4 = false
        carta1 = ""
        carta2 = ""
        carta3 = ""
        carta4 = ""

        estadoJuego = Constans.ESTADOS_JUEGO.CARTAS_VOLTEADAS

        Toast.makeText(this, "Cartas Volteadas", Toast.LENGTH_SHORT).show()
    }


    fun paresEncontrados() {
        for (data in listaCartas!!) {
            if (data.equals(Constans.CARTAS.NAIPE_NEGRO)) {
                loadImage(binding.ivCartaDos, data)
                loadImage(binding.ivCartaTres, data)
            }
        }
        estadoJuego = Constans.ESTADOS_JUEGO.PARES_ENCONTRADOS

        Toast.makeText(this, "Pares encontrados", Toast.LENGTH_SHORT).show()
    }


    fun juegoTerminado() {
        loadImage(binding.ivCartaUno, carta1)
        loadImage(binding.ivCartaDos, carta2)
        loadImage(binding.ivCartaTres, carta3)
        loadImage(binding.ivCartaCuatro, carta4)
        estadoJuego = Constans.ESTADOS_JUEGO.JUEGO_TERMINADO
        Toast.makeText(this, "Juego terminado", Toast.LENGTH_SHORT).show()
    }


    fun cartasVolteadasReset() {
        loadImage(binding.ivCartaUno, Constans.CARTAS.DEFAULT)
        loadImage(binding.ivCartaDos, Constans.CARTAS.DEFAULT)
        loadImage(binding.ivCartaTres, Constans.CARTAS.DEFAULT)
        loadImage(binding.ivCartaCuatro, Constans.CARTAS.DEFAULT)
        carta1Mostrada = false
        carta2Mostrada = false
        carta3Mostrada = false
        carta4Mostrada = false
        click1 = false
        click2 = false
        click3 = false
        click4 = false
        carta1 = ""
        carta2 = ""
        carta3 = ""
        carta4 = ""
        estadoJuego = Constans.ESTADOS_JUEGO.CARTAS_VOLTEADAS
    }


    fun loadImage(imageView: ImageView, urlImage: String) {
        GlideApp.with(baseContext)
            .load(urlImage)
            .placeholder(R.drawable.cartavolteada)
            .into(imageView)
    }


    private fun observers() {

        cartasViewModel.isLoading.observe(this, Observer {
            if (it) {
                if (!loading!!.isShowing) {
                    loading!!.show()
                }
            } else {
                if (loading!!.isShowing) {
                    loading!!.dismiss()
                }
            }
        })

        cartasViewModel.cartaState.observe(this, Observer {
            if (it != null) {
                muestraCartas(estadoJuego)
            } else {
                Log.e(className, Constans.ERROR.ERROR)
            }
        })
    }


    private fun consultaEstadoServidor() {
        try {
            cartasViewModel.consultaEstadoJuego()
        } catch (e: Exception) {
            Log.e(className, e.message.toString())
        }
    }


    override fun onBackPressed() {
    }

    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        if (checkPermissions()) {
            if (isLocationEnabled()) {

                mFusedLocationClient.lastLocation.addOnCompleteListener(this) { task ->
                    var location: Location? = task.result
                    if (location == null) {
                        requestNewLocationData()
                    } else {
                        latitud = location.latitude.toString()
                        longitud = location.longitude.toString()
                        regionLocalizacion = getCityName(latitud.toDouble(), longitud.toDouble())
                        binding.tvUbicacion.text =
                            "Lat: " + latitud + " - Long: " + longitud + " - " + regionLocalizacion
                    }
                }
            } else {
                Toast.makeText(this, "Activa tu ubicacion", Toast.LENGTH_LONG).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        } else {
            requestPermissions()
        }
    }

    @SuppressLint("MissingPermission")
    private fun requestNewLocationData() {
        var mLocationRequest = LocationRequest()
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = 0
        mLocationRequest.fastestInterval = 0
        mLocationRequest.numUpdates = 1

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        mFusedLocationClient!!.requestLocationUpdates(
            mLocationRequest, mLocationCallback,
            Looper.myLooper()
        )
    }

    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            var mLastLocation: Location = locationResult.lastLocation
            latitud = mLastLocation.latitude.toString()
            longitud = mLastLocation.longitude.toString()
            regionLocalizacion = getCityName(latitud.toDouble(), longitud.toDouble())
            binding.tvUbicacion.text =
                "Lat: " + latitud + " - Long: " + longitud + " - " + regionLocalizacion
        }
    }

    private fun isLocationEnabled(): Boolean {
        var locationManager: LocationManager =
            getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    private fun checkPermissions(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            PERMISSION_ID
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == PERMISSION_ID) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                getLastLocation()
            }
        }
    }

    private fun getCityName(lat: Double, long: Double): String {
        var cityName: String?
        val geoCoder = Geocoder(this, Locale.getDefault())
        val address = geoCoder.getFromLocation(lat, long, 1)
        cityName = address[0].adminArea
        if (cityName == null) {
            cityName = address[0].locality
            if (cityName == null) {
                cityName = address[0].subAdminArea
            }
        }
        return cityName
    }

}
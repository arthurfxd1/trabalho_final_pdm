package com.example.trabalho_final_pdm

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingClient
import com.google.android.gms.location.GeofencingRequest
import com.google.android.gms.location.LocationServices

class auxiliargeofence(private val contexto: Context) {

    private val cliente: GeofencingClient = LocationServices.getGeofencingClient(contexto)

    private val pi: PendingIntent by lazy {
        val intent = Intent(contexto, receptorgeofence::class.java)
        intent.action = "ALERTA_GPS"
        PendingIntent.getBroadcast(
            contexto, 
            0, 
            intent, 
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
        )
    }

    @SuppressLint("MissingPermission")
    fun add(t: tarefa) {
        val geo = Geofence.Builder()
            .setRequestId(t.id.toString())
            .setCircularRegion(t.latitude, t.longitude, 1000f) // 1km para garantir no emulador
            .setExpirationDuration(Geofence.NEVER_EXPIRE)
            .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER)
            .build()

        val req = GeofencingRequest.Builder()
            .setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER)
            .addGeofence(geo)
            .build()

        cliente.addGeofences(req, pi).addOnSuccessListener {
            android.util.Log.d("debug_geo", "geofence ativo em: ${t.latitude}, ${t.longitude}")
        }.addOnFailureListener {
            android.util.Log.e("debug_geo", "erro: ${it.message}")
        }
    }
}

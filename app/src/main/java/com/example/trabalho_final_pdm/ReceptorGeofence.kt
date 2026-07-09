package com.example.trabalho_final_pdm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class receptorgeofence : BroadcastReceiver() {

    override fun onReceive(contexto: Context, intent: Intent) {
        val evento = GeofencingEvent.fromIntent(intent) ?: return
        
        if (evento.hasError()) {
            val erro = "erro gps: ${evento.errorCode}"
            auxiliarnotificacao.avisar(contexto, 999, "erro", erro)
            return
        }

        // Verifica se entrou ou se esta na area
        val tipo = evento.geofenceTransition
        if (tipo == Geofence.GEOFENCE_TRANSITION_ENTER || tipo == Geofence.GEOFENCE_TRANSITION_DWELL) {
            val lista = evento.triggeringGeofences ?: return
            lista.forEach { g ->
                val id = g.requestId.toLongOrNull() ?: return@forEach
                chegou(contexto, id)
            }
        }
    }

    private fun chegou(contexto: Context, id: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            val db = bancodados.obter_instancia(contexto)
            val t = db.tdao().buscar_por_id(id)
            val nome = t?.titulo ?: "tarefa encontrada"
            
            // Manda a notificacao real
            auxiliarnotificacao.avisar(contexto, id.toInt(), "voce chegou!", "nao esqueca: $nome")
        }
    }
}

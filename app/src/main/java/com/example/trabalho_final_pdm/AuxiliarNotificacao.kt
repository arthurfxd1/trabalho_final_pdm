package com.example.trabalho_final_pdm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

object auxiliarnotificacao {

    private const val cid = "canal"

    fun criar(contexto: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val canal = NotificationChannel(cid, "alertas", NotificationManager.IMPORTANCE_HIGH)
            val ger = contexto.getSystemService(NotificationManager::class.java)
            ger.createNotificationChannel(canal)
        }
    }

    fun avisar(contexto: Context, id: Int, tit: String, txt: String) {
        val not = NotificationCompat.Builder(contexto, cid)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle(tit)
            .setContentText(txt)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()

        NotificationManagerCompat.from(contexto).notify(id, not)
    }
}

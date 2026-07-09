package com.example.trabalho_final_pdm

import android.app.Application

class appprincipal : Application() {
    override fun onCreate() {
        super.onCreate()
        auxiliarnotificacao.criar(this)
    }
}

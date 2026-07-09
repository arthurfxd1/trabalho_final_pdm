package com.example.trabalho_final_pdm

import android.Manifest
import android.location.Geocoder
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale

class mainactivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pedir_permissoes()

        auxiliarnotificacao.criar(this)

        val et_titulo = findViewById<EditText>(R.id.et_titulo)
        val et_endereco = findViewById<EditText>(R.id.et_endereco)
        val bt_salvar = findViewById<Button>(R.id.bt_salvar)
        val bt_painel = findViewById<Button>(R.id.bt_painel)
        val bt_teste_notif = findViewById<Button>(R.id.bt_teste_notif)
        val bt_limpar = findViewById<Button>(R.id.bt_limpar)
        val container = findViewById<FrameLayout>(R.id.container)

        val db = bancodados.obter_instancia(this)
        val aux_geo = auxiliargeofence(this)

        bt_salvar.setOnClickListener {
            val titulo = et_titulo.text.toString().trim()
            val endereco = et_endereco.text.toString().trim()
            if (titulo.isEmpty() || endereco.isEmpty()) {
                Toast.makeText(this, "preencha titulo e endereco", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                val geo = Geocoder(this@mainactivity, Locale.getDefault())
                val lista = withContext(Dispatchers.IO) {
                    try {
                        geo.getFromLocationName(endereco, 1)
                    } catch (e: Exception) {
                        null
                    }
                }

                if (lista.isNullOrEmpty()) {
                    Toast.makeText(this@mainactivity, "endereco nao encontrado", Toast.LENGTH_SHORT).show()
                    return@launch
                }

                val loc = lista[0]
                val lat = loc.latitude
                val lon = loc.longitude

                val nova = tarefa(titulo = titulo, descricao = endereco, latitude = lat, longitude = lon)
                val id = withContext(Dispatchers.IO) { db.tdao().inserir(nova) }
                val salva = tarefa(id = id, titulo = titulo, descricao = endereco, latitude = lat, longitude = lon)

                aux_geo.add(salva)

                Toast.makeText(this@mainactivity, "coordenadas: $lat, $lon", Toast.LENGTH_LONG).show()
            }
        }

        bt_painel.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragmentopainel())
                .commit()
        }

        bt_teste_notif.setOnClickListener {
            auxiliarnotificacao.avisar(this, 1, "TESTE", "notificacao de teste")
        }

        bt_limpar.setOnClickListener {
            lifecycleScope.launch {
                withContext(Dispatchers.IO) {
                    db.tdao().limpar_tudo()
                }
                Toast.makeText(this@mainactivity, "todas as tarefas removidas", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun pedir_permissoes() {
        val lista = mutableListOf<String>()
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != android.content.pm.PackageManager.PERMISSION_GRANTED) {
            lista.add(Manifest.permission.ACCESS_FINE_LOCATION)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != android.content.pm.PackageManager.PERMISSION_GRANTED) {
                lista.add(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
        if (lista.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, lista.toTypedArray(), 1)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_BACKGROUND_LOCATION) != android.content.pm.PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_BACKGROUND_LOCATION), 2)
            }
        }
    }
}

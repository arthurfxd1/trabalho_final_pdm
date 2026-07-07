package com.example.trabalho_final_pdm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class fragmentopainel : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val wv = WebView(requireContext())
        wv.settings.javaScriptEnabled = true
        
        lifecycleScope.launch {
            val db = bancodados.obter_instancia(requireContext())
            val lista = withContext(Dispatchers.IO) { db.tdao().buscar_todas_lista() }
            val total = lista.size
            
            val html = "<html><body style='text-align:center;font-family:sans-serif;padding-top:30px;'>" +
                       "<h1>painel web</h1>" +
                       "<p>voce tem <b>$total</b> tarefas salvas no gps</p>" +
                       "<div style='background:#f0f0f0;padding:10px;border-radius:10px;'>bom trabalho!</div>" +
                       "</body></html>"
            
            wv.loadDataWithBaseURL(null, html, "text/html", "UTF-8", null)
        }

        return wv
    }
}

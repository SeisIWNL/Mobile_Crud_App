package pe.edu.cibertec.proyectofinalkotlin.fragmentsServices

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pe.edu.cibertec.proyectofinalkotlin.R
import pe.edu.cibertec.proyectofinalkotlin.databinding.FragmentAboutBinding

class AboutFragment : Fragment(R.layout.fragment_about) {

    private var _binding: FragmentAboutBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        _binding = FragmentAboutBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        val root: View = binding.root


        // Setup button click listeners
        binding.button.setOnClickListener {
            openUrl("https://www.instagram.com/cc.compupalace")
        }
        binding.button2.setOnClickListener {
            openUrl("https://www.facebook.com/compupalace")
        }
        binding.button3.setOnClickListener {
            openUrl("https://x.com/atv_noticias")
        }
        binding.button4.setOnClickListener {
            openUrl("https://mail.google.com/mail")
        }

        return root
    }

    private fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }
}
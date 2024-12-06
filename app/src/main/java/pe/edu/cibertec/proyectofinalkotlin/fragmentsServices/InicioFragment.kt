package pe.edu.cibertec.proyectofinalkotlin.fragmentsServices

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pe.edu.cibertec.proyectofinalkotlin.R
import pe.edu.cibertec.proyectofinalkotlin.databinding.FragmentInicioBinding

class InicioFragment : Fragment(R.layout.fragment_inicio) {

    private var _binding: FragmentInicioBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        _binding = FragmentInicioBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentInicioBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Inflate the layout for this fragment
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
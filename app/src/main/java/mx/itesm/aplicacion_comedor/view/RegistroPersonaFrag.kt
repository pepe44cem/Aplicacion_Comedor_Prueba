package mx.itesm.aplicacion_comedor.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import mx.itesm.aplicacion_comedor.R
import mx.itesm.aplicacion_comedor.databinding.FragmentRegistroComidaBinding
import mx.itesm.aplicacion_comedor.databinding.FragmentRegistroPersonaBinding
import mx.itesm.aplicacion_comedor.viewmodel.RegistroPersonaVM

class RegistroPersonaFrag : Fragment() {

    companion object {
        fun newInstance() = RegistroPersonaFrag()
    }

    private lateinit var viewModel: RegistroPersonaVM
    private lateinit var binding: FragmentRegistroPersonaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistroPersonaBinding.inflate(layoutInflater)
        //registrarObservadores()
        registrarEventos()
        return binding.root

    }

    private fun registrarEventos() {
        binding.button.setOnClickListener {
            val accion = RegistroPersonaFragDirections.actionRegistroPersonaFragToRegistroPersonaExitosoFrag()
            findNavController().navigate(accion)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RegistroPersonaVM::class.java)
        // TODO: Use the ViewModel
    }

}
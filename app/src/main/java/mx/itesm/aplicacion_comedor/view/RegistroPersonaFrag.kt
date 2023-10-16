package mx.itesm.aplicacion_comedor.view

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import mx.itesm.aplicacion_comedor.R
import mx.itesm.aplicacion_comedor.databinding.FragmentRegistroComidaBinding
import mx.itesm.aplicacion_comedor.databinding.FragmentRegistroPersonaBinding
import mx.itesm.aplicacion_comedor.viewmodel.RegistroPersonaVM

class RegistroPersonaFrag : Fragment() {

    companion object {
        fun newInstance() = RegistroPersonaFrag()
    }

    private val viewModel : RegistroPersonaVM by viewModels()
    private lateinit var binding: FragmentRegistroPersonaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistroPersonaBinding.inflate(layoutInflater)
        registrarObservadores()
        registrarEventos()
        return binding.root
    }

    private fun registrarObservadores() {
        viewModel.id.observe(viewLifecycleOwner, Observer {codigo ->
            val accion = RegistroPersonaFragDirections.actionRegistroPersonaFragToRegistroPersonaExitosoFrag(codigo)
            findNavController().navigate(accion)
        })
    }

    private fun registrarEventos() {
        binding.button.setOnClickListener {
            val nombre =  binding.etNombre.text.toString()
            val apellido = binding.etApellido.text.toString()
            val curp = binding.etCURP.text.toString()
            val sexo = binding.etSexo.text.toString()
            val fecha = binding.etFecha.text.toString()

            viewModel.descargarServicios(nombre, apellido, curp, sexo, fecha)
        }
    }
}
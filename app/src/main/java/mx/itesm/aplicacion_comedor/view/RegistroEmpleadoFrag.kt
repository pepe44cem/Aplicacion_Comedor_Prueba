package mx.itesm.aplicacion_comedor.view

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.app.AlertDialog
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
import mx.itesm.aplicacion_comedor.databinding.FragmentRegistroEmpleadoBinding
import mx.itesm.aplicacion_comedor.viewmodel.RegistroEmpleadoVM

class RegistroEmpleadoFrag : Fragment() {

    companion object {
        fun newInstance() = RegistroEmpleadoFrag()
    }

    private val viewModel: RegistroEmpleadoVM by viewModels()
    private lateinit var binding: FragmentRegistroEmpleadoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistroEmpleadoBinding.inflate(layoutInflater)
        registrarEventos()
        return binding.root
    }

    private fun registrarEventos() {
        binding.button.setOnClickListener {//nombre, apellido, curp, sexo, fecha
            val nombre = binding.etNombre.text.toString()
            val apellido = binding.etApellido.text.toString()
            val curp = binding.etCURP.text.toString()
            val sexo = binding.etSexo.text.toString()
            val fecha = binding.etFecha.text.toString()

            viewModel.descargarServicios(nombre, apellido, curp, sexo, fecha)

            // Mostrar el AlertDialog
            val alertDialog = AlertDialog.Builder(requireContext())
                .setTitle("Confirmación")
                .setMessage("Registro completado")
                .setPositiveButton("OK") { dialog, _ ->
                    dialog.dismiss()
                }
                .create()
            alertDialog.show()
        }
    }
}
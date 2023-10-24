package mx.itesm.aplicacion_comedor.view

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.InputType
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import mx.itesm.aplicacion_comedor.R
import mx.itesm.aplicacion_comedor.databinding.FragmentRegistroEmpleadoBinding
import mx.itesm.aplicacion_comedor.model.others.Single
import mx.itesm.aplicacion_comedor.viewmodel.RegistroEmpleadoVM

/**
 * Autor : Jose Antonio Moreno Tahuilan
 * Clase que representa a la VISTA en la arquitectura MVVM
 * Se encarga de controlar los elementos de la vista,
 * ademas de llamar a las funciones que se necesiten para la logica
 * para poder registrar nuevo empleados o registrar el curp de los ya registrados.
 */

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
        registrarObservadores()
        registrarEventos()
        return binding.root
    }

    private fun registrarEventos() {
        binding.btnNuevoVol.setOnClickListener {
            val curp = binding.etCURP.text.toString()
            val nombre = binding.etNombre.text.toString()
            val apellido = binding.etApellido.text.toString()
            val telefono = binding.etTelefono.text.toString()
            viewModel.agregarEmpleado(nombre, apellido, curp, telefono)
        }

        binding.btnRegistrarVol.setOnClickListener {
            lanzarPopUp()
        }
    }

    private fun lanzarPopUp() {
        val miDialog = AlertDialog.Builder(requireActivity())
        val etCURP = EditText(requireActivity())

        miDialog.setTitle("Pasame tu CURP")
        miDialog.setView(etCURP)
        etCURP.setInputType(InputType.TYPE_CLASS_TEXT)

        miDialog.setPositiveButton("Listo", DialogInterface.OnClickListener { dialogInterface: DialogInterface, i: Int ->
            val texto = etCURP.text.toString()
            Log.d("QUE SACA?", texto)
            viewModel.agrgarAsistencia(Single.idComedor, texto)
        })
        miDialog.setNegativeButton("Cancelar", DialogInterface.OnClickListener { dialogInterface: DialogInterface, i: Int ->
            dialogInterface.cancel()
        })
        miDialog.show()
    }

    private fun registrarObservadores() {
        viewModel.id.observe(viewLifecycleOwner, Observer {id ->
            viewModel.agrgarAsistencia(Single.idComedor, id)
        })

        viewModel.idasistencia.observe(viewLifecycleOwner, Observer { idasis ->
            Toast.makeText(requireContext(), "SU ASISTENCIA A QUEDADO REGISTRADA", Toast.LENGTH_LONG)
            val accion = RegistroEmpleadoFragDirections.actionRegistroEmpleadoFragToMenuFrag()
            findNavController().navigate(accion)
        })
    }
}
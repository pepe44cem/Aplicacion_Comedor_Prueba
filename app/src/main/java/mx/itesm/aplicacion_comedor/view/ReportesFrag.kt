package mx.itesm.aplicacion_comedor.view

import android.os.Binder
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import mx.itesm.aplicacion_comedor.R
import mx.itesm.aplicacion_comedor.databinding.FragmentLogInBinding
import mx.itesm.aplicacion_comedor.databinding.FragmentReporteBinding
import mx.itesm.aplicacion_comedor.databinding.FragmentReportesBinding
import mx.itesm.aplicacion_comedor.model.others.Single
import mx.itesm.aplicacion_comedor.viewmodel.ReportesVM

/**
 * Autor : Jose Antonio Moreno Tahuilan
 * Clase que representa a la VISTA en la arquitectura MVVM
 * Se encarga de controlar los elementos de la vista,
 * ademas de llamar a las funciones que se necesiten para la logica
 * en la sección de reportes de la aplicación.
 */
class ReportesFrag : Fragment() {

    companion object {
        fun newInstance() = ReportesFrag()
    }

    private val viewModel: ReportesVM by viewModels()
    private lateinit var binding : FragmentReportesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReportesBinding.inflate(layoutInflater)

        registrarEventos()
        registrarObservadores()

        return binding.root
    }

    private fun registrarObservadores() {
        viewModel.id.observe(viewLifecycleOwner, Observer { idReporte ->
            Toast.makeText(requireContext(), "Reporte exitoso, muchas gracias.", Toast.LENGTH_LONG).show()
            val accion = ReportesFragDirections.actionReportesFragToMenuFrag()
            findNavController().navigate(accion)
        })

        viewModel.error.observe(viewLifecycleOwner) { error ->
            Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
        }
    }

    private fun registrarEventos() {
        binding.button3.setOnClickListener {
            val texto = binding.etReporte.text.toString()
            if (texto.isBlank()){
                Toast.makeText(requireContext(), "Por favor complete el reporte.", Toast.LENGTH_LONG).show()
            } else {
                viewModel.agrergarReporte(Single.idComedor, texto)
            }

        }
    }


}
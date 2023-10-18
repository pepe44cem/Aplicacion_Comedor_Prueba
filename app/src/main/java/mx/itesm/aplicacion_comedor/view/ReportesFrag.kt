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
    }

    private fun registrarEventos() {
        binding.button3.setOnClickListener {
            viewModel.agrergarReporte(Single.idComedor, binding.etReporte.text.toString())
        }
    }


}
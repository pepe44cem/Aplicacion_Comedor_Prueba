package mx.itesm.aplicacion_comedor.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import mx.itesm.aplicacion_comedor.viewmodel.DatosHoyVM
import mx.itesm.aplicacion_comedor.databinding.FragmentDatosHoyBinding

class DatosHoyFrag : Fragment() {

    companion object {
        fun newInstance() = DatosHoyFrag()
    }

    private val viewModel: DatosHoyVM by viewModels()
    private lateinit var binding: FragmentDatosHoyBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDatosHoyBinding.inflate(layoutInflater)
        return binding.root
        //return inflater.inflate(R.layout.fragment_datos_hoy, container, false)
    }

    override fun onStart() {
        super.onStart()
        viewModel.descargarDatosHoy()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registrarObservadores()
    }

    private fun registrarObservadores() {
        viewModel.comidasTotalesHoy.observe(viewLifecycleOwner, Observer { comidasTH ->
            binding.comidasTotalesValue.text = comidasTH.toString()
        })
        viewModel.comidasPagadasHoy.observe(viewLifecycleOwner, Observer { comidasPH ->
            binding.comidasPagadasValue.text = comidasPH.toString()
            binding.ingresoValue.text="$"+(comidasPH*13).toString()+".00"
        })
        viewModel.comidasDonadasHoy.observe(viewLifecycleOwner, Observer { comidasDH ->
            binding.comidasDonadasValue.text = comidasDH.toString()
        })
        viewModel.voluntariosAsistentesHoy.observe(viewLifecycleOwner, { voluntariosAH ->
            binding.voluntariosPresentesValue.text=voluntariosAH.toString()
        })
    }


}
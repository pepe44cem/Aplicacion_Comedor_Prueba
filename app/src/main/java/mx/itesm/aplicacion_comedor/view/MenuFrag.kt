package mx.itesm.aplicacion_comedor.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import mx.itesm.aplicacion_comedor.R
import mx.itesm.aplicacion_comedor.databinding.FragmentMenuBinding
import mx.itesm.aplicacion_comedor.viewmodel.MenuVM

/**
 * Autor : Jose Antonio Moreno Tahuilan
 * Clase que representa a la VISTA en la arquitectura MVVM
 * Se de mandar a otros fragmentos, es decir sirve de menu de opciones.
 */

class MenuFrag : Fragment() {

    companion object {
        fun newInstance() = MenuFrag()
    }

    private lateinit var viewModel: MenuVM
    private lateinit var binding: FragmentMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return inflater.inflate(R.layout.fragment_menu, container, false)
        binding = FragmentMenuBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MenuVM::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registrarEventos()
    }

    private fun registrarEventos() {
        binding.btnRegistrarComida.setOnClickListener{
            val accion = MenuFragDirections.actionMenuFragToRegistroComidaFrag()
            findNavController().navigate(accion)

        }
        binding.btnRegistrarVoluntario.setOnClickListener{
            val accion = MenuFragDirections.actionMenuFragToRegistroEmpleadoFrag()
            findNavController().navigate(accion)
        }
        binding.btnEstadisticas.setOnClickListener{
            val accion = MenuFragDirections.actionMenuFragToEstadisticaFrag()
            findNavController().navigate(accion)
        }
        binding.btnReporte.setOnClickListener{
            val accion = MenuFragDirections.actionMenuFragToReportesFrag()
            findNavController().navigate(accion)
        }
    }

}
package mx.itesm.aplicacion_comedor.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import mx.itesm.aplicacion_comedor.R
import mx.itesm.aplicacion_comedor.databinding.FragmentRegistroComidaExitosoBinding
//import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.IdComedor
import mx.itesm.aplicacion_comedor.model.others.Single
import mx.itesm.aplicacion_comedor.viewmodel.RegistroComidaExitosoVM

/**
 * Autor : Jose Antonio Moreno Tahuilan
 * Clase que representa a la VISTA en la arquitectura MVVM
 * Se encarga de controlar los elementos de la vista,
 * ademas de llamar a las funciones que se necesiten para la logica
 * para cuando se registra de manera correcta una persona.
 */

class RegistroComidaExitosoFrag : Fragment() {

    private val viewModel: RegistroComidaExitosoVM by viewModels()
    private lateinit var binding: FragmentRegistroComidaExitosoBinding
    val args: RegistroComidaExitosoFragArgs by navArgs()

    //val spinner = binding.spinner
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return inflater.inflate(R.layout.fragment_registro_comida_exitoso, container, false)
        binding = FragmentRegistroComidaExitosoBinding.inflate(layoutInflater)

        registrarEventos()
        registrarObservables()

        return  binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.listaVoluntarios(Single.idComedor)
    }

    private fun registrarObservables() {
        viewModel.flag.observe(viewLifecycleOwner, Observer {
            val accion = RegistroComidaExitosoFragDirections.actionRegistroComidaExitosoFragToRegistroComidaFrag()
            findNavController().navigate(accion)
        })

        viewModel.lista.observe(viewLifecycleOwner, Observer { l ->
            Log.d("TAG", "MENSAJE =====" + l.user.toString())
            val nlst = listOf<String>("Pagado") + l.user
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, nlst)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinner.adapter = adapter
            /*spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    val selectedItem = nlst[position]
                    val paraLlevar = binding.checkBox.isSelected

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Maneja el caso en el que no se haya seleccionado ningún elemento
                }
            }*/
        })


    }

    private fun registrarEventos() {
        binding.btn.setOnClickListener{
            var nombre = binding.spinner.selectedItem as String
            var racionpagada = false
            var idvisita = args.idVisita
            var parallevar = binding.checkBox.isChecked

            Log.d("TAG", "NOMBRE:    " + nombre)

            if(nombre == "Pagado"){
                racionpagada = true
            }
            viewModel.cambiarVisitas(nombre, racionpagada, idvisita, parallevar)
        }

    }


}
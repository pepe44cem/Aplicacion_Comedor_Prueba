package mx.itesm.aplicacion_comedor.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import mx.itesm.aplicacion_comedor.R
import mx.itesm.aplicacion_comedor.databinding.FragmentRegistroComidaExitosoBinding
import mx.itesm.aplicacion_comedor.viewmodel.RegistroComidaExitosoVM

class RegistroComidaExitosoFrag : Fragment() {

    private val viewModel: RegistroComidaExitosoVM by viewModels()
    private lateinit var binding: FragmentRegistroComidaExitosoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return inflater.inflate(R.layout.fragment_registro_comida_exitoso, container, false)
        binding = FragmentRegistroComidaExitosoBinding.inflate(layoutInflater)
        return  binding.root
    }

}
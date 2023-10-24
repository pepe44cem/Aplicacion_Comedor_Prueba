package mx.itesm.aplicacion_comedor.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import mx.itesm.aplicacion_comedor.databinding.FragmentLogInBinding
import mx.itesm.aplicacion_comedor.model.others.Single
import mx.itesm.aplicacion_comedor.viewmodel.LogInVM

/**
 * Autor : Jose Antonio Moreno Tahuilan
 * Clase que representa a la VISTA en la arquitectura MVVM
 * Se encarga de controlar los elementos de la vista,
 * ademas de llamar a las funciones que se necesiten para la logica en el Log In de la aplicacion.
 */

class LogInFrag : Fragment() {

    companion object {
        fun newInstance() = LogInFrag()
    }

    private val viewModel: LogInVM by viewModels()
    private lateinit var binding: FragmentLogInBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return inflater.inf   late(R.layout.fragment_log_in, container, false
        binding = FragmentLogInBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registrarEventos()
        registrarObservables()
    }
    private fun registrarObservables() {
        viewModel.error.observe(viewLifecycleOwner, Observer { error ->
            Toast.makeText(requireContext(), error, Toast.LENGTH_LONG)
        })

        viewModel.id.observe(viewLifecycleOwner, Observer { id ->
            if (id != null){
                Single.idComedor = id
                val accion = LogInFragDirections.actionLogInFragToMenuFrag()
                findNavController().navigate(accion)
            } else {
                Toast.makeText(requireContext(), "Su usuario o contraseña estan mal por favor vuelva a intentar", Toast.LENGTH_LONG).show()
            }
        })

        viewModel.error.observe(viewLifecycleOwner){error ->
            Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
        }
    }
    private fun registrarEventos() {
        binding.btnLogIn.setOnClickListener {
            val usuario = binding.etUsuario.text.toString()
            val contrasena = binding.etContrasena.text.toString()

            if (usuario.isBlank() && contrasena.isBlank()){
                binding.etUsuario.error = "No deje valores en blanco"
                binding.etContrasena.error = "No deje valores en blanco."
            } else if (usuario.isBlank()) {
                binding.etUsuario.error = "No deje valores en blanco"
            } else if (contrasena.isBlank()){
                binding.etContrasena.error = "No deje valores en blanco."
            } else {
                viewModel.descargarServicios(usuario, contrasena) //Verifica el usuario y la contraseña.
            }

        }
    }
}
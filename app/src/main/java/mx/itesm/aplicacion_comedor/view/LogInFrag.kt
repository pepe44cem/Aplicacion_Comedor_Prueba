package mx.itesm.aplicacion_comedor.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import mx.itesm.aplicacion_comedor.databinding.FragmentLogInBinding
import mx.itesm.aplicacion_comedor.model.others.Single
import mx.itesm.aplicacion_comedor.viewmodel.LogInVM

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
            Log.d("TAG", "ID-----: " + id.toString())
            if(id != null){
                Single.idComedor = id

                /*val sharedPreferences = requireContext().getSharedPreferences("MiPreferencia", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putInt("idComedor", id)
                editor.apply()*/
                /*val prefs = requireActivity().getSharedPreferences("datos", AppCompatActivity.MODE_PRIVATE)
                prefs.edit().apply{
                    putInt("idComedor", id)
                    commit()
                }*/
                val accion = LogInFragDirections.actionLogInFragToMenuFrag()
                findNavController().navigate(accion)
            }else{
                Toast.makeText(requireContext(), "Su usuario o contraseña estan mal por favor vuelva a intentar", Toast.LENGTH_LONG).show()
            }
        })
    }
    private fun registrarEventos() {
        binding.btnLogIn.setOnClickListener {
            //Toast.makeText(requireContext(), "Si paso", Toast.LENGTH_LONG).show()
            Log.d("TAG", "Si funciona esta cosa")
            val usuario = binding.etUsuario.text.toString()
            val contrasena = binding.etContrasena.text.toString()
            viewModel.descargarServicios(usuario, contrasena)
        }
    }
}
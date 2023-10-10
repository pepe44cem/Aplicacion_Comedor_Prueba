package mx.itesm.aplicacion_comedor.view

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.google.zxing.integration.android.IntentIntegrator
import mx.itesm.aplicacion_comedor.R
import mx.itesm.aplicacion_comedor.databinding.FragmentRegistroComidaBinding
import mx.itesm.aplicacion_comedor.viewmodel.RegistroComidaVM

class RegistroComidaFrag : Fragment(){

    private val viewModel: RegistroComidaVM by viewModels()
    private lateinit var binding: FragmentRegistroComidaBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentRegistroComidaBinding.inflate(layoutInflater)
        return binding.root

        registrarObservadores()
    }

    private fun registrarObservadores() {
        binding.ibEscanear.setOnClickListener{
            iniciarEscaneo()
        }
    }

    private fun iniciarEscaneo() {
        IntentIntegrator(requireActivity()).initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if(result != null){
            if(result.contents != null){
                Toast.makeText(requireContext(), "Cancelado", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(requireContext(), "El valor es: ${result.contents}", Toast.LENGTH_LONG).show()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}
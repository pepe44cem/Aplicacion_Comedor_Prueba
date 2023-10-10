package mx.itesm.aplicacion_comedor.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mx.itesm.aplicacion_comedor.viewmodel.DatosHoyVM
import mx.itesm.aplicacion_comedor.R

class DatosHoyFrag : Fragment() {

    companion object {
        fun newInstance() = DatosHoyFrag()
    }

    private lateinit var viewModel: DatosHoyVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_datos_hoy, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DatosHoyVM::class.java)
        // TODO: Use the ViewModel
    }

}
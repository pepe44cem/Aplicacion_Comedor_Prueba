package mx.itesm.aplicacion_comedor.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mx.itesm.aplicacion_comedor.viewmodel.EstadisticaVM
import mx.itesm.aplicacion_comedor.R

class EstadisticaFrag : Fragment() {

    companion object {
        fun newInstance() = EstadisticaFrag()
    }

    private lateinit var viewModel: EstadisticaVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_estadistica, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EstadisticaVM::class.java)
        // TODO: Use the ViewModel
    }

}
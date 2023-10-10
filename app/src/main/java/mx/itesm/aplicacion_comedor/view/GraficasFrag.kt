package mx.itesm.aplicacion_comedor.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mx.itesm.aplicacion_comedor.viewmodel.GraficasVM
import mx.itesm.aplicacion_comedor.R

class GraficasFrag : Fragment() {

    companion object {
        fun newInstance() = GraficasFrag()
    }

    private lateinit var viewModel: GraficasVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_graficas, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(GraficasVM::class.java)
        // TODO: Use the ViewModel
    }

}
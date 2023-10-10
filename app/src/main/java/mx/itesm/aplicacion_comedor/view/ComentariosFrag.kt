package mx.itesm.aplicacion_comedor.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mx.itesm.aplicacion_comedor.viewmodel.ComentariosVM
import mx.itesm.aplicacion_comedor.R

class ComentariosFrag : Fragment() {

    companion object {
        fun newInstance() = ComentariosFrag()
    }

    private lateinit var viewModel: ComentariosVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_comentarios, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ComentariosVM::class.java)
        // TODO: Use the ViewModel
    }

}
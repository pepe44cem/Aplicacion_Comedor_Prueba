package mx.itesm.aplicacion_comedor.view

import android.app.DatePickerDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import mx.itesm.aplicacion_comedor.viewmodel.ComentariosVM
import mx.itesm.aplicacion_comedor.R
import mx.itesm.aplicacion_comedor.databinding.FragmentComentariosBinding
import mx.itesm.aplicacion_comedor.databinding.FragmentGraficasBinding
import mx.itesm.aplicacion_comedor.model.others.Comentario
import mx.itesm.aplicacion_comedor.viewmodel.AdaptadorComentario
import java.util.Calendar

class ComentariosFrag : Fragment() {

    companion object {
        fun newInstance() = ComentariosFrag()
    }

    private val viewModel: ComentariosVM by viewModels()
    private lateinit var binding: FragmentComentariosBinding

    var adaptadorComentario: AdaptadorComentario? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentComentariosBinding.inflate(layoutInflater)
        return binding.root
        //return inflater.inflate(R.layout.fragment_comentarios, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registrarEventos()
        registrarObservadores()
        asignarFechasHoy()

        llenarComentarios()

    }

    private fun llenarComentarios() {
        val arrComentarios = arrayOf(
            Comentario("2023-10-18","Muy buen servicio"),
            Comentario("2023-10-18","Me trataron mal ):"),
            Comentario("2023-10-18","Muy buena comida"),
            Comentario("2023-10-18","La encargada Teresa me trató de mala gana"),
            Comentario("2023-10-18","La cocinera Ana muy buen trato")
            )
        val layout = LinearLayoutManager(requireContext())
        layout.orientation = LinearLayoutManager.VERTICAL
        binding.comentariosRV.layoutManager = layout
        adaptadorComentario = AdaptadorComentario(requireContext(), arrComentarios)
        binding.comentariosRV.adapter = adaptadorComentario
    }

    private fun asignarFechasHoy() {
        val calendar = Calendar.getInstance()
        val yearHoy = calendar.get(Calendar.YEAR)
        val mesHoy = calendar.get(Calendar.MONTH)
        val diaHoy = calendar.get(Calendar.DAY_OF_MONTH)

        viewModel.cambiarFechaDesde(yearHoy,mesHoy,diaHoy)
        viewModel.cambiarFechaHasta(yearHoy,mesHoy,diaHoy)

    }


    private fun registrarEventos() {
        binding.desdeBtn.setOnClickListener{
            val yearInicial = viewModel.yearDesde.value ?: 2021
            val mesInicial = viewModel.mesDesde.value ?: 7
            val diaInicial = viewModel.diaDesde.value ?: 26


            val datePickerDialog = DatePickerDialog(
                requireActivity(),
                { _, year, monthOfYear, dayOfMonth ->

                    viewModel.cambiarFechaDesde(year,monthOfYear,dayOfMonth)
                },
                yearInicial,
                mesInicial,
                diaInicial
            )

            datePickerDialog.show()
        }

        binding.hastaBtn.setOnClickListener{
            val yearInicial = viewModel.yearHasta.value ?: 2021
            val mesInicial = viewModel.mesHasta.value ?: 7
            val diaInicial = viewModel.diaHasta.value ?: 26


            val datePickerDialog = DatePickerDialog(
                requireActivity(),
                { _, year, monthOfYear, dayOfMonth ->


                    viewModel.cambiarFechaHasta(year,monthOfYear,dayOfMonth)
                },
                yearInicial,
                mesInicial,
                diaInicial
            )

            datePickerDialog.show()
        }
    }

    private fun registrarObservadores() {
        viewModel.fechaHastaString.observe(viewLifecycleOwner, Observer { nuevaFechaHasta ->
            binding.hastaBtn.text=nuevaFechaHasta
        })
        viewModel.fechaDesdeString.observe(viewLifecycleOwner, Observer { nuevaFechaDesde ->
            binding.desdeBtn.text=nuevaFechaDesde
        })
    }

}
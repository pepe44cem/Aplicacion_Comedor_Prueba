package mx.itesm.aplicacion_comedor.view

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.card.MaterialCardView
import mx.itesm.aplicacion_comedor.databinding.FragmentRegistroPersonaBinding
import mx.itesm.aplicacion_comedor.viewmodel.RegistroPersonaVM

class RegistroPersonaFrag : Fragment() {

    companion object {
        fun newInstance() = RegistroPersonaFrag()
    }

    private val viewModel : RegistroPersonaVM by viewModels()
    private lateinit var binding: FragmentRegistroPersonaBinding

    private val importantSelectedCourses = mutableListOf<String>()

    var selectedCurses = BooleanArray(0)
    var courseList = mutableListOf<Int>()
    var courseArray = listOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistroPersonaBinding.inflate(layoutInflater)
        val selectCard = binding.materialCardView
        val tvCourses = binding.tv
        registrarObservadores()
        registrarEventos()
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        viewModel.solicitarListaVulneravilidades()
    }

    private fun registrarObservadores() {
        viewModel.id.observe(viewLifecycleOwner, Observer {codigo ->
            val accion = RegistroPersonaFragDirections.actionRegistroPersonaFragToRegistroPersonaExitosoFrag(codigo, importantSelectedCourses.toTypedArray())
            findNavController().navigate(accion)
        })

        viewModel.lst.observe(viewLifecycleOwner, Observer {vulArray ->
            val selectCard = binding.materialCardView
            selectedCurses = BooleanArray(vulArray.size)
            courseArray = vulArray.toMutableList()

            selectCard.setOnClickListener {
                showCoursesDialog()
            }
        })
    }

    private fun showCoursesDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Select Courses")
        builder.setCancelable(false)
        val tvCourses = binding.tv

        val selectedCourses = BooleanArray(courseArray.size)

        builder.setMultiChoiceItems(courseArray.toTypedArray(), selectedCourses) { dialog, which, isChecked ->
            if (isChecked) {
                courseList.add(which)
            } else {
                courseList.remove(which)
            }
        }

        builder.setPositiveButton("OK") { dialog, which ->
            val stringBuilder = StringBuilder()
            for (i in 0 until courseList.size) {
                importantSelectedCourses.add(courseArray[courseList[i]])
                stringBuilder.append(courseArray[courseList[i]])
                if (i != courseList.size - 1) {
                    stringBuilder.append(", ")
                }
            }
            tvCourses.text = stringBuilder.toString()

        }

        builder.setNegativeButton("Cancel") { dialog, which ->
            for (i in selectedCourses.indices) {
                selectedCourses[i] = false
            }
            importantSelectedCourses.clear()
            courseList.clear()
            tvCourses.text = ""
        }

        builder.show()
    }


    private fun registrarEventos() {
        binding.button4.setOnClickListener {
            val nombre =  binding.etNombre.text.toString()
            val apellido = binding.etApellido.text.toString()
            val curp = binding.etCURP.text.toString()
            val sexo = binding.etSexo.text.toString()
            val fecha = binding.etFecha.text.toString()

            viewModel.descargarServicios(nombre, apellido, curp, sexo, fecha)
        }
    }
}
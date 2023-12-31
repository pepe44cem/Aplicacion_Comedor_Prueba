package mx.itesm.aplicacion_comedor.view

import android.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import mx.itesm.aplicacion_comedor.databinding.FragmentRegistroPersonaBinding
import mx.itesm.aplicacion_comedor.model.others.Validacion
import mx.itesm.aplicacion_comedor.viewmodel.RegistroPersonaVM

/**
 * Autor : Jose Antonio Moreno Tahuilan
 * Clase que representa a la VISTA en la arquitectura MVVM
 * Se encarga de controlar los elementos de la vista,
 * ademas de llamar a las funciones que se necesiten para la logica
 * cuando se registra una persona.
 *
 * El codigo implementado para el pop up para ocupar diferentes vulnerabilidades
 * esta en la siguiente liga: https://youtu.be/4GdbCl-47wE
 */

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

    private val valid = Validacion()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentRegistroPersonaBinding.inflate(layoutInflater)
        val selectCard = binding.materialCardView
        val tvCourses = binding.tv
        registrarObservadores()
        registrarEventos()
        val generoOptions = arrayOf("", "Femenino", "Masculino", "Prefiero no decir")
        val generoAdapter = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, generoOptions)
        binding.spinner2.adapter = generoAdapter
        binding.spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Cuando no se selecciona ningún elemento
            }
        }

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
            val sexo = binding.spinner2.selectedItem.toString()
            val fecha = binding.etFecha.text.toString()

            if (nombre.isBlank()) {
                Toast.makeText(requireContext(), "Por favor llene el usuario.", Toast.LENGTH_LONG).show()
            } else if (apellido.isBlank()) {
                Toast.makeText(requireContext(), "Por favor llene el apellido.", Toast.LENGTH_LONG).show()
            } else if (curp.isBlank()) {
                Toast.makeText(requireContext(), "Por favor llene el CURP.", Toast.LENGTH_LONG).show()
            } else if (sexo.isBlank()) {
                Toast.makeText(requireContext(), "Por favor seleccione el sexo.", Toast.LENGTH_LONG).show()
            } else if (fecha.isBlank()) {
                Toast.makeText(requireContext(), "Por favor llene la fecha.", Toast.LENGTH_LONG).show()
            } else if (valid.esCURPValida(curp)) {
                binding.etCURP.error = "Formato invalido."
                Toast.makeText(requireContext(), "El formato de la CURP no es valido, intente nuevamente", Toast.LENGTH_LONG).show()
            } else {
                viewModel.descargarServicios(nombre, apellido, curp, sexo, fecha)
            }
        }
    }
}
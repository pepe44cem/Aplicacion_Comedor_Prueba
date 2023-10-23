package mx.itesm.aplicacion_comedor.view

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.zxing.integration.android.IntentIntegrator
import mx.itesm.aplicacion_comedor.databinding.FragmentRegistroComidaBinding
import mx.itesm.aplicacion_comedor.viewmodel.RegistroComidaVM
import androidx.navigation.fragment.findNavController
import mx.itesm.aplicacion_comedor.model.others.Validacion
import mx.itesm.aplicacion_comedor.model.others.Single

/**
 * Autor : Jose Antonio Moreno Tahuilan
 * Clase que representa a la VISTA en la arquitectura MVVM
 * Se encarga de controlar los elementos de la vista,
 * ademas de recuperar los datos para posteriormente poder registrar a una nueva persona.
 */

class RegistroComidaFrag : Fragment() {

    private val viewModel: RegistroComidaVM by viewModels()
    private lateinit var binding: FragmentRegistroComidaBinding
    private val valid = Validacion()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentRegistroComidaBinding.inflate(layoutInflater)
        registrarObservadores()
        registrarEventos()
        Log.d("TAG", "ID COMEDORRRR: " + Single.idComedor)
        return binding.root
    }
    private fun registrarEventos() {
        binding.btnNuevaPersona.setOnClickListener {
            val acc = RegistroComidaFragDirections.actionRegistroComidaFragToRegistroPersonaFrag()
            findNavController().navigate(acc)
        }
        binding.btnCURP.setOnClickListener{
             lanzarPopUpCurp()
        }
        binding.btnCodigo.setOnClickListener {
            lanzarPopUpCodigo()
        }
        binding.ibEscanear.setOnClickListener{
            iniciarEscaneo()
        }
        binding.Regresar.setOnClickListener {
            val accion = RegistroComidaFragDirections.actionRegistroComidaFragToMenuFrag()
            findNavController().navigate(accion)
        }
    }
    private fun lanzarPopUpCodigo() {
        val miDialog = AlertDialog.Builder(requireActivity())
        val etCodigo= EditText(requireActivity())
        miDialog.setTitle("Pasame tu Codigo")
        miDialog.setView(etCodigo)
        etCodigo.setInputType(InputType.TYPE_CLASS_PHONE)

        miDialog.setPositiveButton("Listo", DialogInterface.OnClickListener { dialogInterface: DialogInterface, i: Int ->
            val texto = etCodigo.text.toString()
            if (texto.isBlank()) {
                Toast.makeText(requireContext(), "Porfavor llene los datos.", Toast.LENGTH_LONG).show()
            } else {
                viewModel.agregarConCodigo(texto.toInt(), Single.idComedor)
            }
        })
        miDialog.setNegativeButton("Cancelar", DialogInterface.OnClickListener { dialogInterface: DialogInterface, i: Int ->
            dialogInterface.cancel()
        })
        miDialog.show()
    }
    private fun lanzarPopUpCurp() {
        val miDialog = AlertDialog.Builder(requireActivity())
        val etCURP = EditText(requireActivity())

        miDialog.setTitle("Pasame tu CURP")
        miDialog.setView(etCURP)
        etCURP.setInputType(InputType.TYPE_CLASS_TEXT)

        miDialog.setPositiveButton("Listo", DialogInterface.OnClickListener { dialogInterface: DialogInterface, i: Int ->
            val texto = etCURP.text.toString()
            if (texto.isBlank()) {
                Toast.makeText(requireContext(), "Porfavor llene los datos.", Toast.LENGTH_LONG).show()
            } else if (!valid.esCURPValida(texto)) {
                Toast.makeText(requireContext(), "No es una CURP valida, por favor intente nuevamente.", Toast.LENGTH_LONG).show()
            } else {
                viewModel.agregarConCURP(texto, Single.idComedor)
            }
        })
        miDialog.setNegativeButton("Cancelar", DialogInterface.OnClickListener { dialogInterface: DialogInterface, i: Int ->
            dialogInterface.cancel()
        })
        miDialog.show()
    }

    private fun registrarObservadores() {
        viewModel.id.observe(viewLifecycleOwner, Observer {id ->
            if (id != null){
                val accion = RegistroComidaFragDirections.actionRegistroComidaFragToRegistroComidaExitosoFrag(id)
                findNavController().navigate(accion)
            }
        })
        viewModel.error.observe(viewLifecycleOwner, Observer { error ->
            Toast.makeText(requireContext(), error, Toast.LENGTH_LONG)
        })
    }

    private fun iniciarEscaneo() {
        val integrator = IntentIntegrator.forSupportFragment(this)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
        integrator.setPrompt("Coloque el Código QR en cuadro")
        integrator.setBeepEnabled(true)
        integrator.initiateScan()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(requireContext(), "Cancelado", Toast.LENGTH_LONG).show()
            } else {
                //Toast.makeText(requireContext(), "El valor escaneado es: " + result.contents, Toast.LENGTH_LONG).show()
                viewModel.agregarConCodigo(result.contents.toInt(), Single.idComedor)
                /*if (idComedor != null) {
                    viewModel.agregarConCodigo(result.contents.toInt(), idComedor)
                }*/
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}
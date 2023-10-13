package mx.itesm.aplicacion_comedor.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mx.itesm.aplicacion_comedor.R
import mx.itesm.aplicacion_comedor.viewmodel.RegistroPersonaExitosoVM
import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.telephony.SmsManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.Manifest


class RegistroPersonaExitosoFrag : Fragment() {

    companion object {
        fun newInstance() = RegistroPersonaExitosoFrag()
    }

    private lateinit var viewModel: RegistroPersonaExitosoVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_registro_persona_exitoso, container, false)

        val btnSMS = view.findViewById<Button>(R.id.btnSMS)
        val btnCorreo = view.findViewById<Button>(R.id.btnCorreo)

        btnSMS.setOnClickListener {
            mostrarDialogo("SMS")
        }

        btnCorreo.setOnClickListener {
            mostrarDialogo("Correo")
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RegistroPersonaExitosoVM::class.java)
        // TODO: Use the ViewModel
    }

    private val PERMISSIONS_REQUEST_SEND_SMS = 1

    private fun enviarSMS(numero: String) {
        val mensaje = " Prueba " //Codigo /n Guarda este código para registrar tu comida en cualquiera de nuestros comedores
        try {
            val smsManager = SmsManager.getDefault()
            smsManager.sendTextMessage(numero, null, mensaje, null, null)
            // SMS enviado exitosamente
            println("--$numero--")
            println("--El mensaje ha sido enviado--")
        } catch (e: Exception) {
            // Maneja cualquier error aquí
            println("--El mensaje no ha sido enviado por parte de la api: ${e.message}--")
        }
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSIONS_REQUEST_SEND_SMS -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permiso concedido
                    // Si tienes el número al que enviarlo en este punto, podrías llamar a enviarSMS()
                    // Por ejemplo: enviarSMS(numero)
                    Toast.makeText(requireContext(), "Permiso para enviar SMS concedido", Toast.LENGTH_SHORT).show()
                } else {
                    // Permiso denegado
                    Toast.makeText(requireContext(), "Permiso para enviar SMS denegado", Toast.LENGTH_SHORT).show()
                }
            }
            // Puedes agregar más códigos de solicitud de permiso si los necesitas en el futuro
        }
    }


    private fun mostrarDialogo(opcion: String) {
        val builder = AlertDialog.Builder(requireContext())
        val inflater = LayoutInflater.from(requireContext())
        val dialogView = inflater.inflate(R.layout.fragment_ingreso_datos, null)

        val editTextInput = dialogView.findViewById<EditText>(R.id.editTextInput)
        val btnAceptar = dialogView.findViewById<Button>(R.id.btnAceptar)
        val btnCancelar = dialogView.findViewById<Button>(R.id.btnCancelar)

        builder.setView(dialogView)
        builder.setTitle("Enviar $opcion")

        val dialog = builder.create()

        btnAceptar.setOnClickListener {
            if (opcion == "SMS") {
                if (ContextCompat.checkSelfPermission(
                        requireContext(),
                        Manifest.permission.SEND_SMS
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(
                        requireActivity(),
                        arrayOf(Manifest.permission.SEND_SMS),
                        PERMISSIONS_REQUEST_SEND_SMS
                    )
                } else {
                    enviarSMS(editTextInput.text.toString())
                }
            }
            else if (opcion == "Correo") {
                val email = editTextInput.text.toString()
                println("--$email--")
                val subject = "Código para comedor DIF"
                val message = "Con el siguiente código podrás registrar tu comida en la siguiente visita: Prueba"

                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
                    putExtra(Intent.EXTRA_SUBJECT, subject)
                    putExtra(Intent.EXTRA_TEXT, message)
                }

                try {
                    startActivity(Intent.createChooser(intent, "Enviar correo electrónico"))
                    println("---El correo ha sido enviado---")
                } catch (ex: ActivityNotFoundException) {
                    // Manejar el caso en el que no haya actividad de correo electrónico disponible
                    println("---No se encontró una actividad de correo electrónico---")
                    Toast.makeText(
                        requireContext(),
                        "No se encontró una actividad de correo electrónico. Por favor, configure una.",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            dialog.dismiss()
        }

        btnCancelar.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

}
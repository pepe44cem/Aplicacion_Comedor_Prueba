package mx.itesm.aplicacion_comedor.model.others

import android.content.Context
import android.graphics.Bitmap
import com.google.zxing.BarcodeFormat
import com.google.zxing.integration.android.IntentIntegrator
import com.journeyapps.barcodescanner.BarcodeEncoder

class QR {
    fun generarQR(codigo: Int): Bitmap{
        val barcodeEncoder = BarcodeEncoder()
        val bitMap = barcodeEncoder.encodeBitmap(codigo.toString(), BarcodeFormat.QR_CODE, 750, 750)
        return bitMap
    }

}


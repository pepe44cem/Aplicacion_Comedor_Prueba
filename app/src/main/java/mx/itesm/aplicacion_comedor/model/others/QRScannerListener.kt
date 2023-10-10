package mx.itesm.aplicacion_comedor.model.others

interface QRScannerListener {
    fun startScanner()
    fun handleScanResult(contents: String?)
}

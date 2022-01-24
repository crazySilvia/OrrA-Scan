package de.silvia.orra

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import de.silvia.orra.com.google.zxing.integration.android.IntentIntegrator

class MainActivity : Activity(), View.OnClickListener {
    private val formatTxt = findViewById<TextView>(R.id.scan_format)
    private val contentTxt = findViewById<TextView>(R.id.scan_content)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val scanBtn: Button = findViewById(R.id.scan_button)
        scanBtn.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    public override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent) {
        val scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent)
        if (scanningResult != null) {
            val scanContent = scanningResult.contents
            val scanFormat = scanningResult.formatName
            formatTxt.text = "FORMAT: $scanFormat"
            contentTxt.text = "CONTENT: $scanContent"
        } else {
            val toast = Toast.makeText(
                applicationContext,
                "No scan data received!", Toast.LENGTH_SHORT
            )
            toast.show()
        }
    }

    override fun onClick(view: View) {
        if (view.id == R.id.scan_button) {
            val scanIntegrator = IntentIntegrator(this)
            scanIntegrator.initiateScan()
        }
    }
}
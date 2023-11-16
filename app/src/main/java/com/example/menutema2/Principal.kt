package com.example.menutema2

import android.Manifest
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class Principal : AppCompatActivity() {
    private lateinit var btnCall: ImageButton
    private lateinit var btnAlarm: ImageButton
    private lateinit var btnURL: ImageButton
    private lateinit var btnEmail: ImageButton
    private val CODE_PERMISIONS_EMAIL = 124
    private val WEB_PERMISIONS_CODE = 123
    private fun PermissionPhone(): Boolean = ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal_version_dos)
        btnCall = findViewById(R.id.btn_call)
        btnAlarm = findViewById(R.id.btn_alarm)
        btnURL = findViewById(R.id.btn_url)
        btnEmail = findViewById(R.id.btn_email)

        btnCall.setOnClickListener { view ->
            val intent = Intent(this, ActivityCall::class.java)
            startActivity(intent)
        }
        btnAlarm.setOnClickListener {
            configurateAlarm()
        }
        btnURL.setOnClickListener {
            openURL()
        }
        btnEmail.setOnClickListener {
            sendEmail()
        }
    }
    fun configurateAlarm(){
        intent = Intent(AlarmClock.ACTION_SET_ALARM).apply{
            putExtra(AlarmClock.EXTRA_MESSAGE, "DESPIERTA QUE EMPIEZAN LAS CLASES")
            putExtra(AlarmClock.EXTRA_HOUR,6)
            putExtra(AlarmClock.EXTRA_MINUTES,30)
        }
        startActivity(intent)
    }
    fun openURL() {
        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.INTERNET), WEB_PERMISIONS_CODE)
        } else {
            val googleUri: Uri = Uri.parse("https://www.iesvirgendelcarmen.com/")
            val websiteIntent = Intent(Intent.ACTION_VIEW, googleUri)
            startActivity(websiteIntent)
        }
    }

    fun sendEmail() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.INTERNET) != android.content.pm.PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.INTERNET), CODE_PERMISIONS_EMAIL)
        } else {
            val recipientEmail = "23002401.consultaweb@g.educaand.es"
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:$recipientEmail")
            try {
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(this, "NO SE PUEDE ABRIR GMAIL", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
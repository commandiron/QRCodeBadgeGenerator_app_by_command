package com.demirli.a49qrcodebadgegenerator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Xml
import android.widget.Toast
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.MultiFormatWriter
import com.journeyapps.barcodescanner.BarcodeEncoder
import kotlinx.android.synthetic.main.activity_main.*
import net.glxn.qrgen.android.QRCode
import net.glxn.qrgen.core.scheme.Bookmark
import net.glxn.qrgen.core.scheme.ICal
import net.glxn.qrgen.core.scheme.MeCard
import net.glxn.qrgen.core.scheme.VCard

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        create_btn.setOnClickListener {

            if(name_et.text.toString() == ""){
                Toast.makeText(this, "Name area is empty", Toast.LENGTH_SHORT).show()
            }else if(emailAddress_et.text.toString() == ""){
                Toast.makeText(this, "Email area is empty", Toast.LENGTH_SHORT).show()
            }else{
                try {
                    val vcard = VCard(name_et.text.toString())
                        .setEmail(emailAddress_et.text.toString())
                        .setTitle(twitterAccountName_et.text.toString())
                        .setWebsite(githubAccountName_et.text.toString())

                    val myBitmap = QRCode.from(vcard).withSize(500,500).bitmap()
                    iv_barcode.setImageBitmap(myBitmap)

                }catch (e: Exception){
                    e.printStackTrace()
                }
            }
        }

        cancel_btn.setOnClickListener{
            name_et.setText("")
            emailAddress_et.setText("")
            githubAccountName_et.setText("")
            twitterAccountName_et.setText("")

            iv_barcode.setImageResource(R.drawable.ic_launcher_foreground)
        }
    }
}

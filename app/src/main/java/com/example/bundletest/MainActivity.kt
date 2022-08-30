package com.example.bundletest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout

class MainActivity : AppCompatActivity() {
    lateinit var btnBig: Button
    lateinit var btnSmall: Button
    lateinit var btnBigFr: Button
    lateinit var btnSmallFr: Button
    lateinit var container: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnBig = findViewById(R.id.button)
        btnSmall = findViewById(R.id.button2)
        btnBigFr = findViewById(R.id.button3)
        btnSmallFr = findViewById(R.id.button4)
        container = findViewById(R.id.container)


        btnBig.setOnClickListener {
            val intent = Intent(this, BlackActivity::class.java)
            val bundle = Bundle()

            for (i in 0..100_000) {   // int is 4 bytes.
                bundle.putString("$i", "asdasdd32313113")
            }

            intent.putExtras(bundle)
            startActivity(intent)            // todo !!! FAILED BINDER TRANSACTION !!!  (parcel size = 5596416)
        }

        btnBigFr.setOnClickListener {
            val intent = Intent(this, BlackActivity::class.java)
            val bundle = Bundle()

            for (i in 0..1_000_000) {   // int is 4 bytes.
                bundle.putString("$i", "asdasdd32313113")
            }

            intent.putExtras(bundle)
            val fragment = RedFragment()
            fragment.arguments = bundle
            supportFragmentManager.beginTransaction().replace(R.id.container, fragment, "tag").commit() // it`s work fine
        }
    }
}
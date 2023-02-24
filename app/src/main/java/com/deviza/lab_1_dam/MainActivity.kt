package com.deviza.lab_1_dam

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.*


class MainActivity : AppCompatActivity(), OnClickListener {

    val MENU_RESET_ID = 1
    val MENU_QUIT_ID = 2
    var etN1: EditText? = null
    var etNum2: EditText? = null
    lateinit var btnAdd: Button
    lateinit var btnSub: Button
    lateinit var btnMult: Button
    lateinit var btnDiv: Button
    lateinit var buttBack: Button
    lateinit var buttonGet: Button
    lateinit var textShow: TextView

    var tvResult: TextView? = null
    var oper = ""

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonGet = findViewById(R.id.button_get)
        textShow = findViewById(R.id.textdata)

        etN1 = findViewById(R.id.etNum1)
        etNum2 = findViewById(R.id.etNum2)
        btnAdd = findViewById(R.id.btnAdd)
        btnSub = findViewById(R.id.btnSub)
        btnMult = findViewById(R.id.btnMult)
        btnDiv = findViewById(R.id.btnDiv)
        tvResult = findViewById(R.id.tvResult)

        btnAdd.setOnClickListener(this@MainActivity)
        btnSub.setOnClickListener(this@MainActivity)
        btnMult.setOnClickListener(this@MainActivity)
        btnDiv.setOnClickListener(this@MainActivity)

        var myData = ""
        buttonGet.setOnClickListener {

            var aDataRow = ""
            var aBuffer = ""
            var ss: Array<String?>
            try {
                @SuppressLint("SdCardPath") val myFile = File("/storage/emulated/0/text1.txt")
                Log.d("asd", "asdasd1")
                val fIn = FileInputStream(myFile)
                Log.d("asd", "asdasd2")

                val myReader = BufferedReader(InputStreamReader(fIn))
                Log.d("asd", "asdasd3")

                while (myReader.readLine().also { aDataRow = it } != null) {
                    aBuffer += aDataRow.trimIndent()
                }
                textShow.append(
                    aBuffer.trimIndent()
                )
                myReader.close()
                Toast.makeText(
                    baseContext, "Done reading SD 'IA181.txt'$aBuffer", Toast.LENGTH_SHORT
                ).show()
            } catch (e: Exception) {
                Toast.makeText(
                    baseContext, e.message, Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onClick(v: View?) {
        var num1 = 0f
        var num2 = 0f
        var result = 0f

        if (TextUtils.isEmpty(etN1!!.text.toString()) || TextUtils.isEmpty(etNum2!!.text.toString())) {
            return
        }
        num1 = etN1!!.text.toString().toFloat()
        num2 = etNum2!!.text.toString().toFloat()
        when (v!!.id) {
            R.id.btnAdd -> {
                oper = "+"
                result = num1 + num2
            }
            R.id.btnSub -> {
                oper = "-"
                result = num1 - num2
            }
            R.id.btnMult -> {
                oper = "*"
                result = num1 * num2
            }
            R.id.btnDiv -> {
                oper = "/"
                result = num1 / num2
            }
            else -> {}
        }

        var results = "$num1 $oper $num2 = $result"
        tvResult!!.text = ""
        GlobalScope.launch {
            for (c: Char in results) {
                tvResult!!.text = "${tvResult!!.text}$c"
                delay(100)
            }
        }
    }


}

package com.example.aptekaapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class OrderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        val btnList = findViewById<Button>(R.id.btnList)
        val btnCart = findViewById<Button>(R.id.btnCart)
        val btnOrder = findViewById<Button>(R.id.btnOrder)
        val editFIO = findViewById<EditText>(R.id.editFIO)
        val editAddress = findViewById<EditText>(R.id.editAddress)
        val editPhone = findViewById<EditText>(R.id.editPhone)
        val buttonSubmit = findViewById<Button>(R.id.buttonSubmit)

        // Убираем обводку у текущей кнопки
        btnOrder.background = null

        btnList.setOnClickListener {
            val intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnCart.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
            finish()
        }

        buttonSubmit.setOnClickListener {
            val fio = editFIO.text.toString()
            val address = editAddress.text.toString()
            val phone = editPhone.text.toString()

            if (fio.isNotEmpty() && address.isNotEmpty() && phone.isNotEmpty()) {
                Toast.makeText(this, "Заказ принят! Оператор свяжется с вами", Toast.LENGTH_LONG).show()
                val intent = Intent(this, ListActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            } else {
                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
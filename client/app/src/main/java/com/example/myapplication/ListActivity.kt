package com.example.aptekaapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class ListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private var adapter: MedicineAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val btnList = findViewById<Button>(R.id.btnList)
        val btnCart = findViewById<Button>(R.id.btnCart)
        val btnOrder = findViewById<Button>(R.id.btnOrder)

        btnList.background = null

        btnCart.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
            finish()
        }

        btnOrder.setOnClickListener {
            startActivity(Intent(this, OrderActivity::class.java))
            finish()
        }

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Пустой адаптер на время загрузки
        adapter = MedicineAdapter(emptyList()) { }
        recyclerView.adapter = adapter

        loadMedicinesFromServer()
    }

    private fun loadMedicinesFromServer() {
        Thread {
            try {
                val url = URL("http://10.0.2.2:5094/api/лекарства")
                val connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "GET"
                connection.connect()

                if (connection.responseCode == 200) {
                    val inputStream = connection.inputStream
                    val reader = InputStreamReader(inputStream)
                    val response = reader.readText()

                    val jsonArray = JSONArray(response)
                    val medicines = mutableListOf<Medicine>()

                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        val medicine = Medicine(
                            id = jsonObject.getInt("id"),
                            name = jsonObject.getString("название"),
                            price = jsonObject.getDouble("цена"),
                            inStock = jsonObject.getBoolean("в_наличии")
                        )
                        medicines.add(medicine)
                    }

                    runOnUiThread {
                        // Здесь создаём адаптер с данными и функцией добавления в корзину
                        val newAdapter = MedicineAdapter(medicines) { medicine ->
                            CartManager.addToCart(medicine)
                            Toast.makeText(this@ListActivity, "${medicine.name} добавлен в корзину", Toast.LENGTH_SHORT).show()
                        }
                        recyclerView.adapter = newAdapter
                        adapter = newAdapter
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                runOnUiThread {
                    Toast.makeText(this@ListActivity, "Ошибка: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }.start()
    }
}
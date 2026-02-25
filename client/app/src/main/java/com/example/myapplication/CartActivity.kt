package com.example.aptekaapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Toast

class CartActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MedicineAdapter
    private lateinit var tvEmpty: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val btnList = findViewById<Button>(R.id.btnList)
        val btnCart = findViewById<Button>(R.id.btnCart)
        val btnOrder = findViewById<Button>(R.id.btnOrder)
        val buttonOrder = findViewById<Button>(R.id.buttonOrder)

        recyclerView = findViewById(R.id.recyclerViewCart)
        tvEmpty = findViewById(R.id.tvEmpty)

        // Настройка кнопок навигации
        btnCart.background = null

        btnList.setOnClickListener {
            startActivity(Intent(this, ListActivity::class.java))
            finish()
        }

        btnOrder.setOnClickListener {
            startActivity(Intent(this, OrderActivity::class.java))
            finish()
        }

        buttonOrder.setOnClickListener {
            startActivity(Intent(this, OrderActivity::class.java))
            finish()
        }

        // Настройка списка
        recyclerView.layoutManager = LinearLayoutManager(this)
        val cartItems = CartManager.getCartItems()

        if (cartItems.isEmpty()) {
            tvEmpty.visibility = TextView.VISIBLE
            recyclerView.visibility = RecyclerView.GONE
        } else {
            tvEmpty.visibility = TextView.GONE
            recyclerView.visibility = RecyclerView.VISIBLE

            adapter = MedicineAdapter(cartItems) { medicine ->
                // В корзине кнопка "В корзину" не нужна, но можно сделать удаление
                Toast.makeText(this, "${medicine.name} в корзине", Toast.LENGTH_SHORT).show()
            }
            recyclerView.adapter = adapter
        }
    }
}
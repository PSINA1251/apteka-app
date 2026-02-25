package com.example.aptekaapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MedicineAdapter(
    private val medicines: List<Medicine>,
    private val onAddToCart: (Medicine) -> Unit
) : RecyclerView.Adapter<MedicineAdapter.MedicineViewHolder>() {

    class MedicineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textName: TextView = itemView.findViewById(R.id.textName)
        val textPrice: TextView = itemView.findViewById(R.id.textPrice)
        val buttonAdd: Button = itemView.findViewById(R.id.buttonAdd)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicineViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_medicine, parent, false)
        return MedicineViewHolder(view)
    }

    override fun onBindViewHolder(holder: MedicineViewHolder, position: Int) {
        val medicine = medicines[position]
        holder.textName.text = medicine.name
        holder.textPrice.text = "${medicine.price} ₽"

        holder.buttonAdd.setOnClickListener {
            onAddToCart(medicine)
        }
    }

    override fun getItemCount() = medicines.size
}
package com.example.retrofitlistview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofitlistview.databinding.ItemUserBinding
import com.example.retrofitlistview.model.Data

// Type alias untuk callback ketika item diklik
typealias OnClickRetro = (Data) -> Unit

class AdapterRetrofit(
    private val listretrofit: ArrayList<Data>,   // Daftar data user yang akan ditampilkan
    private val onClickRetro: OnClickRetro        // Callback yang dijalankan saat item diklik
) : RecyclerView.Adapter<AdapterRetrofit.ItemRetrofitViewHolder>() {

    // ViewHolder yang mengikat data ke UI
    inner class ItemRetrofitViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Data) {
            with(binding) {
                // Mengikat data ke elemen UI
                nameTextView.text = "Name: ${data.firstName} ${data.lastName}"
                emailTextView.text = "Email: ${data.email}"

                // Memuat gambar avatar dengan Glide
                Glide.with(itemView.context)
                    .load(data.avatar)
                    .into(avatarImageView)

                // Menangani klik item dan meneruskan data ke callback
                itemView.setOnClickListener {
                    onClickRetro(data)
                }
            }
        }
    }

    // Membuat ViewHolder dan mengikatnya ke layout item_user.xml
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemRetrofitViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemRetrofitViewHolder(binding)
    }

    // Mengikat data ke ViewHolder berdasarkan posisi
    override fun onBindViewHolder(holder: ItemRetrofitViewHolder, position: Int) {
        holder.bind(listretrofit[position])
    }

    // Mengembalikan jumlah item yang ada di daftar
    override fun getItemCount(): Int {
        return listretrofit.size
    }
}

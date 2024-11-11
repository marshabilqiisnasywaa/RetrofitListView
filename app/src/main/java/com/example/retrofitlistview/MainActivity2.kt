package com.example.retrofitlistview

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.retrofitlistview.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mengambil data yang diteruskan dari MainActivity
        val namaDepan = intent.getStringExtra("namaDepan")
        val namaBelakang = intent.getStringExtra("namaBelakang")
        val email = intent.getStringExtra("email")
        val gambar = intent.getStringExtra("gambar")

        // Memastikan data tidak null dan menampilkannya pada UI
        with(binding) {
            nameTextView.text = "Name: ${namaDepan.orEmpty()} ${namaBelakang.orEmpty()}"
            emailTextView.text = "Email: ${email.orEmpty()}"

            // Cek apakah URL gambar valid sebelum memuatnya
            if (!gambar.isNullOrEmpty()) {
                Glide.with(this@MainActivity2)
                    .load(gambar) // Memuat gambar jika URL valid
                    .into(avatarImageView)
            } else {
                // Menangani jika URL gambar tidak valid atau kosong
                Toast.makeText(this@MainActivity2, "Gambar tidak tersedia", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

package com.example.retrofitlistview

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitlistview.databinding.ActivityMainBinding
import com.example.retrofitlistview.model.Data
import com.example.retrofitlistview.model.Users
import com.example.retrofitlistview.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val client = ApiClient.getInstance()
        val response = client.getAllUsers()
        val userList = ArrayList<Data>()

        // Mengatur pemanggilan API
        response.enqueue(object : Callback<Users> {
            override fun onResponse(call: Call<Users>, response: Response<Users>) {
                if (response.isSuccessful && response.body() != null) {
                    response.body()?.data?.let { dataList ->
                        userList.addAll(dataList)
                    }

                    // Inisialisasi AdapterRetrofit (pastikan class ini sudah dibuat)
                    val adapterRetrofit = AdapterRetrofit(userList) { user ->
                        val intent = Intent(this@MainActivity, MainActivity2::class.java).apply {
                            putExtra("namaDepan", user.firstName)
                            putExtra("namaBelakang", user.lastName)
                            putExtra("email", user.email)
                            putExtra("gambar", user.avatar)
                        }
                        startActivity(intent)
                    }

                    // Set up RecyclerView
                    binding.rvPesan.apply {
                        adapter = adapterRetrofit
                        layoutManager = LinearLayoutManager(this@MainActivity)
                    }
                } else {
                    Toast.makeText(this@MainActivity, "Gagal mengambil data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Users>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Koneksi error: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }
}

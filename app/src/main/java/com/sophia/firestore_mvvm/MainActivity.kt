package com.sophia.firestore_mvvm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sophia.firestore_mvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: AppAdapter
    private var infor: MutableList<Infor> = mutableListOf()

    private lateinit var viewmodel: AppViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewmodel = ViewModelProvider(this)[AppViewModel::class.java]

        nextBtn()
        initRecyclerView()
        setObserver()
    }

    private fun setObserver() {
        viewmodel.getInfor(infor).observe(this, {
            adapter.submitList(it)
        })
    }

    private fun initRecyclerView() {
        adapter = AppAdapter(infor)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
    }

    private fun nextBtn() {
        binding.add.setOnClickListener {
            val intent = Intent(applicationContext, EditActivity::class.java)
            startActivity(intent)
        }
    }
}
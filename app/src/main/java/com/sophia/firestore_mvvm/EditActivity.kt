package com.sophia.firestore_mvvm

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.sophia.firestore_mvvm.databinding.ActivityEditBinding

class EditActivity: AppCompatActivity() {

    private lateinit var binding: ActivityEditBinding
    private lateinit var viewmodel: AppViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewmodel = ViewModelProvider(this)[AppViewModel::class.java]
        init()
    }

    private fun init() {
        binding.btnSave.setOnClickListener {
            setEdit()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    private fun setEdit() {
        viewmodel.addInFor(
            binding.nameEdit.text.toString(),
            binding.birthEdit.text.toString()
        )
    }
}
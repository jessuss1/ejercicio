package com.example.ejercicio

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.SeekBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ejercicio.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val color = when (position) {
                    0 -> ContextCompat.getColor(this@MainActivity, R.color.red)
                    1 -> ContextCompat.getColor(this@MainActivity, R.color.blue)
                    2 -> ContextCompat.getColor(this@MainActivity, R.color.green)
                    else -> ContextCompat.getColor(this@MainActivity, R.color.black)
                }

                binding.button?.setBackgroundColor(color)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        binding.seekBar?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val alphaValue = progress / 100f
                binding.button?.alpha = alphaValue
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }
}

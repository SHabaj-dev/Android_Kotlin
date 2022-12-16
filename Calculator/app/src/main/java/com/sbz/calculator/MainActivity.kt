package com.sbz.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.sbz.calculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {


    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        binding.one.setOnClickListener {
            binding.display.append("1")
        }

        binding.two.setOnClickListener {
            binding.display.append("2")
        }
        binding.three.setOnClickListener {
            binding.display.append("3")
        }
        binding.four.setOnClickListener {
            binding.display.append("4")
        }
        binding.five.setOnClickListener {
            binding.display.append("5")
        }
        binding.six.setOnClickListener {
            binding.display.append("6")
        }
        binding.seven.setOnClickListener {
            binding.display.append("7")
        }
        binding.eight.setOnClickListener {
            binding.display.append("8")
        }
        binding.nine.setOnClickListener {
            binding.display.append("9")
        }
        binding.zero.setOnClickListener {
            binding.display.append("0")
        }

        binding.clear.setOnClickListener {
            binding.display.text = ""
        }

        binding.backDelete.setOnClickListener {
            try {
                var num = Integer.parseInt(binding.display.text.toString())
                if (num != 0) {
                    num /= 10
                    binding.display.text = num.toString()
                } else {
                    binding.display.text = ""
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }

        binding.plus.setOnClickListener {
            binding.display.append(" + ")
        }
        binding.sub.setOnClickListener {
            binding.display.append(" - ")
        }
        binding.multi.setOnClickListener {
            binding.display.append(" * ")
        }
        binding.div.setOnClickListener {
            binding.display.append(" / ")
        }
        binding.dot.setOnClickListener {
            binding.display.append(".")
        }



        binding.equals.setOnClickListener {
            try {
                val expression = ExpressionBuilder(binding.display.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()

                if (result == longResult.toDouble()) {
                    binding.display.text = longResult.toString()
                } else {
                    binding.display.text = result.toString()
                }
            }catch (e: Exception){
                binding.display.text = "0"
            }
        }

    }
}
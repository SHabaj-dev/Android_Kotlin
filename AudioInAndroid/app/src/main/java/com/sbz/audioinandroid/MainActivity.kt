package com.sbz.audioinandroid

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.SeekBar
import androidx.databinding.DataBindingUtil
import com.sbz.audioinandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var mediaPlayer: MediaPlayer? = null
    private lateinit var runnable: Runnable
    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
           binding = DataBindingUtil.setContentView(
               this@MainActivity,
               R.layout.activity_main
           )
        handler = Handler(Looper.getMainLooper())

        binding.btnPlay.setOnClickListener{

            if(mediaPlayer == null){
                mediaPlayer = MediaPlayer.create(this@MainActivity, R.raw.song)
                initializeSeekBar()
            }

            mediaPlayer?.start()
        }

        binding.btnPause.setOnClickListener{
            mediaPlayer?.pause()
        }

        binding.btnStop.setOnClickListener {

            mediaPlayer?.stop()
            mediaPlayer?.reset()
            mediaPlayer?.release()
            mediaPlayer = null
            handler.removeCallbacks(runnable)
            binding.seekBar.progress = 0
//            binding.tvCurrentPos.text = "0 secs"
//            binding.tvMaxDuartion.text = (mediaPlayer!!.duration / 1000).toString()
        }

    }

    private fun initializeSeekBar(){
        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if(fromUser) mediaPlayer?.seekTo(progress)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }

        })

        binding.seekBar.max = mediaPlayer!!.duration

        runnable = Runnable {
            val currentPosition = mediaPlayer!!.currentPosition / 1000
            val totalDuration = mediaPlayer!!.duration / 1000
            val dueTime = totalDuration - currentPosition
            binding.tvCurrentPos.text = currentPosition.toString() + "secs"
            binding.tvMaxDuartion.text = dueTime.toString() + "secs"
            binding.seekBar.progress = mediaPlayer!!.currentPosition
            handler.postDelayed(runnable, 1000)
        }
        handler.postDelayed(runnable, 1000)
    }
}
package com.berkaysazak.catchelon

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isInvisible
import kotlinx.android.synthetic.main.activity_start_game.*
import kotlin.random.Random

class StartGame : AppCompatActivity() {
    var score = 0
    var imageArray = ArrayList<ImageView>()
    var runnable = Runnable {  }
    var handler = Handler()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_game)
        var themeSong = MediaPlayer.create(this,R.raw.themesong)
        themeSong.start()
        themeSong.isLooping()

        imageArray.add(imageView1)
        imageArray.add(imageView2)
        imageArray.add(imageView3)
        imageArray.add(imageView4)
        imageArray.add(imageView5)
        imageArray.add(imageView6)
        imageArray.add(imageView7)
        imageArray.add(imageView8)
        imageArray.add(imageView9)

        hideImages()

        object : CountDownTimer(25500,1000) {
            override fun onTick(p0: Long) {
                timeText.text = "Time:" + p0 / 1000
            }

            override fun onFinish() {
                for (image in imageArray){
                    image.visibility = View.INVISIBLE
                }
                Toast.makeText(this@StartGame,"Your time is over.",Toast.LENGTH_LONG).show()
                timeText.text = "Time: 0"
                var alert = AlertDialog.Builder(this@StartGame)
                alert.setTitle("Game Over")
                alert.setMessage("Restart the Game?")
                alert.setPositiveButton("YES") {dialog, which ->
                    val intent = intent
                    finish()
                    startActivity(intent)
                    themeSong.stop()

                }
                alert.setNegativeButton("NO") {dialog, which ->
                    Toast.makeText(this@StartGame,"Loading....",Toast.LENGTH_LONG).show()
                    var intent = Intent(applicationContext,MainActivity::class.java)
                    startActivity(intent)
                }
                alert.show()
            }
        }.start()

    }

    fun increaseScore(view: View)

    {
        score = score + 1
        scoreText.text = "Score: $score"

    }

    fun hideImages(){
        runnable = object : Runnable {
            override fun run() {
                for (image in imageArray){
                    image.visibility = View.INVISIBLE
                }
                var random = Random
                var randomIndex = random.nextInt(9)
                imageArray[randomIndex].visibility = View.VISIBLE
                handler.postDelayed(runnable,500)
            }
        }
        handler.post(runnable)
    }
}
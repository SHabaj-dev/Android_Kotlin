package com.sbz.facecomparision

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import org.opencv.face.FaceRecognizer
import org.opencv.imgcodecs.Imgcodecs

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME)

    }


    fun compareFaces(face1: Mat, face2: Mat): Double {
        val recognizer = FaceRecognizer.createLBPHFaceRecognizer()
        recognizer.train(listOf(face1, face2), listOf(0, 1))
        val prediction = recognizer.predict(face1)
        return prediction.confidence
    }
}
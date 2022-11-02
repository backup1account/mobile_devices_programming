package com.example.list1

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SideActivity : AppCompatActivity() {
    private val textArea: TextView by lazy { findViewById(R.id.information) }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.side_activity)

        val cheatAnswer = intent.getSerializableExtra("showAnswerCheat")

        val score = intent.getSerializableExtra("points")
        val correctAnswers = intent.getSerializableExtra("correctAnswers")
        val showedAnswers = intent.getSerializableExtra("showedAnswers")

        if (cheatAnswer != null)
            textArea.text = cheatAnswer.toString()
        else if (score != null && correctAnswers != null && showedAnswers != null) {
            textArea.text = "Zdobyte punkty: $score \nPoprawne odpowiedzi: $correctAnswers \n" +
                    "Liczba oszustw: $showedAnswers"
        }

    }
}
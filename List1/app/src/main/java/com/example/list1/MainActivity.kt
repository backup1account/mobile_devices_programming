package com.example.list1

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView


class MainActivity : AppCompatActivity() {
    private val showQuestion: TextView by lazy { findViewById(R.id.show_question) }

    private val trueButton: Button by lazy { findViewById(R.id.true_answer_b) }
    private val falseButton: Button by lazy { findViewById(R.id.false_answer_b) }
    private val cheatButton: Button by lazy { findViewById(R.id.cheat_answer_b) }

    private val searchBarText: EditText by lazy { findViewById(R.id.search_text) }
    private val searchButton: Button by lazy { findViewById(R.id.search_button) }

    private val numOfQuestions = 10
    private var count = 0

    private var score = 0
    private var correctAnswers = 0
    private var showedAnswers = 0

    private val questions : Map<Int, List<String>> = mapOf(
        0 to listOf("Czy siła jest wielkością skalarną?", "NIE"),
        1 to listOf("Czy atom to najmniejsza cząstka elementarna?", "NIE"),
        2 to listOf("Czy zero absolutne to najniższa z możliwych temperatur?", "TAK"),
        3 to listOf("Czy we wzorze E=mc^2, c oznacza stałą upływu czasu?", "NIE"),
        4 to listOf("Czy Księżyc ma mniejszą masę niż Ziemia?", "TAK"),
        5 to listOf("Czy pracę oznacza się jednostką W?", "TAK"),
        6 to listOf("Czy siły akcji i reakcji przyczepione są do różnych ciał?", "TAK"),
        7 to listOf("Czy P=W*t to poprawny wzór na moc?", "NIE"),
        8 to listOf("Czy Jowisz ma większą masę od Saturna?", "TAK"),
        9 to listOf("Czy sia wyporu skierowana jest przeciwnie do ciężaru?", "TAK")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            count = savedInstanceState.getInt("count_state")
            score = savedInstanceState.getInt("points_state")
            correctAnswers = savedInstanceState.getInt("correct_answers_state")
            showedAnswers = savedInstanceState.getInt("showed_answers_state")
        }

        searchButton.setOnClickListener {
            val url = searchBarText.text.toString()
            intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.google.com/search?q=$url")
            startActivity(intent)
        }

        trueButton.setOnClickListener { v ->
            checkAnswer("TAK")
            nextQuestion(v)
        }
        falseButton.setOnClickListener { v ->
            checkAnswer("NIE")
            nextQuestion(v)
        }

        cheatButton.setOnClickListener {
            showedAnswers++
            score -= 15

            val intent = Intent(this, SideActivity::class.java)
            intent.putExtra("showAnswerCheat", questions[count]!![1])
            startActivity(intent)
        }
        showQuestion.text = questions[count]!![0]
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt("count_state", count)
        outState.putInt("correct_answers_state", correctAnswers)
        outState.putInt("showed_answers_state", showedAnswers)
        outState.putInt("points_state", score)
    }

    private fun nextQuestion(view: View?) {
        count++

        if (count < numOfQuestions)
            showQuestion.text = questions[count]!![0]
        else {
            val intent = Intent(this, SideActivity::class.java)
            intent.putExtra("points", score)
            intent.putExtra("correctAnswers", correctAnswers)
            intent.putExtra("showedAnswers", showedAnswers)
            startActivity(intent)
        }
    }

    private fun checkAnswer(selected: String) {
        val correctResult = questions[count]!![1]

        if (correctResult == selected) {
            correctAnswers++
            score += 10
        }
    }

}
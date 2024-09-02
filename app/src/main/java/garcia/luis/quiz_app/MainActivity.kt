package garcia.luis.quiz_app

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.Toast
import garcia.luis.quiz_app.databinding.ActivityMainBinding
import android.util.Log
import androidx.activity.viewModels

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val quizViewModel: QuizViewmodel by viewModels ()


    private lateinit var trueButton: Button
    private lateinit var falseButton: Button

    //banco de preguntas



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        

        
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(TAG, "Got a QuizViewModel: $quizViewModel")



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)

        binding.trueButton.setOnClickListener { view: View ->

            checkAnswer(true)

        }
        binding.falseButton.setOnClickListener { view: View ->
            checkAnswer(false)



        }

        binding.nextButton.setOnClickListener { view: View ->
            quizViewModel.moveToNext()
            updateQuestion()


        }

        binding.prevButton.setOnClickListener { view: View ->
            quizViewModel.moveToPrev()
            updateQuestion()
        }

        updateQuestion()
        Log.d(TAG,"Pase por el metodo onCreate")
        Log.d(TAG,"Tengo un QuizviewModel: $quizViewModel")


    }


    private fun updateQuestion() {
        val questionTextResId = quizViewModel.currentQuestionText
        binding.questionTextView.setText(questionTextResId)

    }

    private fun checkAnswer(userAnswer:Boolean){
        val correctAnswer = quizViewModel.currentQuestionAnswer


        val messageResId = if (userAnswer == correctAnswer){
            R.string.correct_toast
        }

        else{
            R.string.incorrect_toast
        }

        Toast.makeText(this,messageResId,Toast.LENGTH_LONG).show()
    }


}





package ro.iss.trainingexercises

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_quiz.*
import ro.iss.trainingexercises.FirstFragment.CommunicateWithActivity
import kotlin.collections.ArrayList

class QuizActivity() : AppCompatActivity(), CommunicateWithActivity {

    private lateinit var quizAdapter: QuizAdapter
    private lateinit var observableGetQuestions: Observable<ArrayList<Question>>
    private lateinit var apiCallDisposableGetQuestions: Disposable
    private lateinit var loadingDialog: ProgressDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        createAlertDialog()
        configurationToolbar()
        getQuestionList()

    }

    override fun setSomethung() {

    }

    private fun createAlertDialog() {
        loadingDialog = ProgressDialog(this)
        loadingDialog.setTitle("Loading Questions")
    }

    private fun configurationToolbar() {
        quiz_toolbar.title = "Submit Answers"
        setSupportActionBar(quiz_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun getQuestionList() {
//        val questionList = ArrayList<Question>()
//        for(i in 0 until 10) {
//            var answers = arrayListOf("Answer 1", "Answer 2", "Answer 3", "Answer 4")
//            questionList.add(Question(i, "Question $i", answers))
//        }
//        return questionList
        showLoadingDialog()
        observableGetQuestions = RetrofitCall.getQuestions()
        apiCallDisposableGetQuestions = observableGetQuestions.subscribe(
            {
                response ->
                setQuestionList(response)
            },
            { setErrorMessage() },
            {
                dismissLoadingDialog()
                apiCallDisposableGetQuestions.dispose()
            }
        )
    }

    private fun setQuestionList(questions: ArrayList<Question>  ) {
        view_list.visibility = View.VISIBLE
        quiz_error_message.visibility = View.GONE

        quizAdapter = QuizAdapter(questions, this)
        view_list.adapter = quizAdapter
        view_list.layoutManager = LinearLayoutManager(this)
    }

    private fun setErrorMessage() {
        view_list.visibility = View.GONE
        quiz_error_message.visibility = View.VISIBLE
    }

    private fun showLoadingDialog() {
        loadingDialog.show()
    }

    private fun dismissLoadingDialog() {
        loadingDialog.dismiss()
    }


}

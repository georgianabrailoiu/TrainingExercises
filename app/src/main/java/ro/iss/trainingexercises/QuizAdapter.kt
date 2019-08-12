package ro.iss.trainingexercises

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.okdroid.checkablechipview.CheckableChipView
import kotlinx.android.synthetic.main.question_item.view.*

class QuizAdapter(private val questionList: ArrayList<Question>, private val context: Context) :
    RecyclerView.Adapter<QuizAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.question_item, parent, false))
    }

    override fun getItemCount(): Int {
        return questionList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.questionName.text = questionList[position].questionText;
        holder.reponse1.text = questionList[position].answers?.get(0) ?: ""
        holder.reponse2.text = questionList[position].answers?.get(1) ?: ""
        holder.reponse3.text = questionList[position].answers?.get(2) ?: ""
        holder.reponse4.text = questionList[position].answers?.get(3) ?: ""
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val questionName: TextView = view.question_name
        val reponse1: CheckableChipView = view.response1
        val reponse2: CheckableChipView = view.response2
        val reponse3: CheckableChipView = view.response3
        val reponse4: CheckableChipView = view.response4

    }
}
package ro.iss.trainingexercises

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

object RetrofitCall {

    fun getQuestions(): Observable<ArrayList<Question>> {
        return RetrofitClient.getQuizClient()
            .getQuestions()
            .delay(2, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
package com.kotlin.rxjava.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.SearchView
import android.widget.TextView
import com.kotlin.rxjava.R
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import java.util.function.Predicate

class SerachActivity : AppCompatActivity() {

    lateinit var searchView: SearchView
    lateinit var searchTextResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_serach)
        searchView = findViewById(R.id.searchTextView)
        searchTextResult = findViewById(R.id.textViewResult)

        fromSearchView()
            .debounce(300, TimeUnit.MILLISECONDS)
            .filter(object : Predicate<String>, io.reactivex.functions.Predicate<String> {
                override fun test(t: String): Boolean {
                    if (t.isEmpty())
                        return false
                    else
                        return true
                }


            })
            .distinctUntilChanged()
            .switchMap { x -> datafromNetwork(x) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Consumer<String> {
                override fun accept(textR: String?) {
                    searchTextResult.text = textR
                }
            })
    }

    fun datafromNetwork(query: String): Observable<String> {
        return Observable.just("Text Reult $query")
    }


    fun fromSearchView(): Observable<String> {

        var subject: PublishSubject<String> = PublishSubject.create()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                subject.onComplete()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                subject.onNext(newText!!)
                return true
            }

        })

        return subject
    }
}

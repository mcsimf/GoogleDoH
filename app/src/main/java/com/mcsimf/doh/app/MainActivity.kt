package com.mcsimf.doh.app

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.mcsimf.doh.R
import com.mcsimf.doh.core.Res
import com.mcsimf.doh.core.Res.State.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.dohLiveData.observe(this) {
            when (it.state) {
                LOADING -> progress_bar.visibility = View.VISIBLE
                COMPLETE -> {
                    progress_bar.visibility = View.INVISIBLE
                    doh_result.text = it.data.toString()
                }
                ERROR -> {
                    progress_bar.visibility = View.INVISIBLE
                    showError(it.error)
                }
            }
        }

        btn_search.setOnClickListener {
            viewModel.lookupDoH(dns_text.text.toString())
        }

    }


    private fun showError(error: Res.Error?) {
        if (error == null) return
        // TODO: Show error
    }

}
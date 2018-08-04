package com.zawhtetnaing.mmkunyi_app_assignment_zhn.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.R
import kotlinx.android.synthetic.main.activity_request_information.*

class RequestInformationActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request_information)

        btnRequestInformationSubmit.setOnClickListener {
            val intent = Intent(applicationContext, JobListActivity::class.java)
            startActivity(intent)
        }
    }
}

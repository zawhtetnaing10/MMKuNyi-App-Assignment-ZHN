package com.zawhtetnaing.mmkunyi_app_assignment_zhn.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.R
import kotlinx.android.synthetic.main.activity_register.*


class RegisterActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        setSupportActionBar(registerToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btnRegister.setOnClickListener {
            val intent = Intent(applicationContext, RequestInformationActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
        }
        return true
    }
}

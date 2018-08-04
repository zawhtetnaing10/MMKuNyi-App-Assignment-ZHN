package com.zawhtetnaing.mmkunyi_app_assignment_zhn.activities

import android.content.Intent
import android.graphics.Paint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        tvForgotPassword.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        tvNoAccount.paintFlags = Paint.UNDERLINE_TEXT_FLAG

        btnSignInWithEmail.setOnClickListener {
            val intent = Intent(applicationContext, RequestInformationActivity::class.java)
            startActivity(intent)
        }

        tvNoAccount.setOnClickListener {
            val intent = Intent(applicationContext, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}

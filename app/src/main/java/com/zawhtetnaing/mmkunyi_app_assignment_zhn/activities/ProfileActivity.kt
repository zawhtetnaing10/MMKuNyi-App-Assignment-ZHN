package com.zawhtetnaing.mmkunyi_app_assignment_zhn.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.R
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.view_holder_applicant.view.*

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        setSupportActionBar(profileToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        Glide.with(ivProfilePic.context)
                .load(R.drawable.zg_portrait)
                .apply(RequestOptions.circleCropTransform())
                .into(ivProfilePic)
    }
}

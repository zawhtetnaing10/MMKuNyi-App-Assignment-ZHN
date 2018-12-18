package com.zawhtetnaing.mmkunyi_app_assignment_zhn.activities

import android.os.Bundle
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.R
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.delegates.LogInDelegate
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.delegates.RegisterDelegate
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.fragments.LoginFragment
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.fragments.RegisterFragment

class AccountControlActivity : BaseActivity(), LogInDelegate, RegisterDelegate {

    companion object {
        const val ACTION_TYPE = "action_type"
        const val ACTION_TYPE_LOGIN = 1111
        const val ACTION_TYPE_REGISTER = 2222

        const val NAVIGATE_TO_REGISTER = "navigate-to-register"
        const val NAVIGATE_TO_LOGIN = "navigate-to-login"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_control)

        val actionTypeExtra = intent.getIntExtra(ACTION_TYPE, 0)

        when (actionTypeExtra) {
            ACTION_TYPE_LOGIN -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.flContainer, LoginFragment())
                        .commit()
            }

            ACTION_TYPE_REGISTER -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.flContainer, RegisterFragment())
                        .commit()
            }
        }
    }

    override fun onTapFacebook() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onTapDontHaveAccountYet() {
        supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit)
                .replace(R.id.flContainer, RegisterFragment())
                .addToBackStack(NAVIGATE_TO_REGISTER)
                .commit()
    }

    override fun onTapGoogle() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onTapSignInWithEmail() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onTapForgotPassword() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onTapAlreadyLoggedIn() {
        supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit)
                .replace(R.id.flContainer, LoginFragment())
                .addToBackStack(NAVIGATE_TO_LOGIN)
                .commit()
    }

    override fun onTapRegister() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}

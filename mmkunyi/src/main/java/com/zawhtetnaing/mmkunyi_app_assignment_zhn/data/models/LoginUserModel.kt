package com.zawhtetnaing.mmkunyi_app_assignment_zhn.data.models

import com.zawhtetnaing.mmkunyi_app_assignment_zhn.data.vos.LoginUserVO

class LoginUserModel {
    companion object {
        private var objInstance: LoginUserModel? = null

        fun getInstance(): LoginUserModel {
            if (objInstance == null) {
                objInstance = LoginUserModel()
            }

            val i = objInstance
            return i!!
        }
    }

    private var logInUser: LoginUserVO? = null

    fun isUserLoggedIn(): Boolean {
        return logInUser != null
    }
}
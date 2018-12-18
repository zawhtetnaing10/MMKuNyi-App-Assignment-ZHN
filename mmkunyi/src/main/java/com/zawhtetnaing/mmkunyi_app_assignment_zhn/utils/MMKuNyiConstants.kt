package com.zawhtetnaing.mmkunyi_app_assignment_zhn.utils

class MMKuNyiConstants {
    companion object {
        const val API_BASE = "http://padcmyanmar.com/padc-3/final-projects/one-time-jobs/"
        const val GET_JOBS = "getJobs.php"
        const val PARAM_ACCESS_TOKEN = "access_token"
        const val PARAM_PAGE = "page"
        const val GOOGLE_MAP_ZOOM = 15.0f

        const val EMPTY_RESPONSE_ERROR = "Empty Response From Network"

        const val CANNOT_FIND_POSITION = "The location cannot be found"
        const val CANNOT_FETCH_JOB_DETAILS = "The Job Details are not available at the moment"

        const val TAPPED_CHAT = "Tapped Chat"
        const val TAPPED_ABOUT_US = "Tapped About us"

        fun numberOfPeopleApplied(numberOfPeople: Int) = "$numberOfPeople people applied"
        fun offerAmountAndDuration(offerAmount: Int?, duration: String?) = "${offerAmount}MMK ($duration)"
        fun totalWorkingDays(days:Int?) = "$days days"
        fun vacancy(availablePostCount:Int?) = "$availablePostCount posts available"
    }
}

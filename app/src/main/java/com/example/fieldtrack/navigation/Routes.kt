package com.example.fieldtrack.navigation

object Routes {
    const val HISTORY = "log_entry_list"
    const val ZONES = "zones"
    const val PRODUCTS = "products"
    const val LOG_ENTRY_FORM = "log_entry_form"
    const val LOG_ENTRY_DETAIL = "log_entry_details/{logEntryId}"

    fun logEntryDetail(logEntryId: Long): String {
        return "log_entry_details/$logEntryId"
    }
}
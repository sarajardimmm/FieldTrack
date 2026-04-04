package com.example.fieldtrack.navigation

object Routes {
    const val HISTORY = "log_entry_list"
    const val ZONES = "zones"
    const val PRODUCTS = "products"
    const val LOG_ENTRY_FORM = "log_entry_form/{logEntryId}"
    const val ZONE_FORM = "zone_form"
    const val LOG_ENTRY_DETAIL = "log_entry_details/{logEntryId}"
    const val ZONE_DETAIL = "zone_details/{zoneId}"

    fun logEntryDetail(logEntryId: Long): String {
        return "log_entry_details/$logEntryId"
    }
    fun logEntryForm(logEntryId: Long = -1L): String {
        return "log_entry_form/$logEntryId"
    }
    fun zoneDetail(zoneId: Long): String {
        return "zone_details/$zoneId"
    }
}
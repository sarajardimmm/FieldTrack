package com.example.fieldtrack.feature.zone.form

sealed interface ZoneEffect {
    data object NavigateBack : ZoneEffect
}
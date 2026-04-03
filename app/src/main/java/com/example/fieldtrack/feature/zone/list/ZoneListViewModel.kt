package com.example.fieldtrack.feature.zone.list

import androidx.lifecycle.ViewModel
import com.example.fieldtrack.data.repository.ZoneRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ZoneListViewModel @Inject constructor(
    zoneRepository: ZoneRepository
) : ViewModel() {

    val zones = zoneRepository.getZones()

    //Shows all user-created zones, for example:
    //
    //Actions:

    //add zone
    //edit zone
    //delete zone
    //open zone details

}
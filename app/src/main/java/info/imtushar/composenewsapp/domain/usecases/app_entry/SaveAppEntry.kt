package info.imtushar.composenewsapp.domain.usecases.app_entry

import info.imtushar.composenewsapp.domain.manager.LocalUserManager

class SaveAppEntry(
    private val localUserManager: LocalUserManager
) {

    suspend operator fun invoke(){
        localUserManager.saveAppEntry()
    }
}
package info.imtushar.composenewsapp.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import info.imtushar.composenewsapp.domain.usecases.app_entry.AppEntryUseCases
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor( private val appEntryUseCases: AppEntryUseCases) : ViewModel() {

    fun onEvent(event: OnboardingEvent){
        when(event){
            is OnboardingEvent.SaveAppEntry ->{
                saveAppEntry()
            }
        }
    }

    private fun saveAppEntry() {
        viewModelScope.launch {
            appEntryUseCases.saveAppEntry()
        }
    }

}
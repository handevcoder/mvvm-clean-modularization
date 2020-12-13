package hi.iwansyy.mvvm.presentation.ui.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job

abstract class BaseViewModel: ViewModel() {
    protected val jobs by lazy { mutableListOf<Job>() }

    fun onDestroy(){
        jobs.forEach{ if (!it.isCompleted) it.cancel() }

    }
}
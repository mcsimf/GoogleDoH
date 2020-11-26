package com.mcsimf.doh.app

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.mcsimf.doh.core.Core
import com.mcsimf.doh.core.Res
import com.mcsimf.doh.core.Res.State.COMPLETE
import com.mcsimf.doh.core.googledoh.entity.DoHResponse

/**
 * @author Maksym Fedyay on 11/26/20 (mcsimf@gmail.com).
 */
class MainViewModel : ViewModel() {


    private val _dohLiveData by lazy { MediatorLiveData<Res<DoHResponse>>() }


    val dohLiveData: LiveData<Res<DoHResponse>>
        get() {
            return _dohLiveData
        }


    fun lookupDoH(dns: String) {
        val response = Core.lookupDoH(dns)
        _dohLiveData.addSource(response) {
            if (it.state == COMPLETE) _dohLiveData.removeSource(response)
            _dohLiveData.value = it
        }
        return
    }

}
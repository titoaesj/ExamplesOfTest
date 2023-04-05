package com.br.titoaesj.examplesoftest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.titoaesj.examplesoftest.common.Resource
import com.br.titoaesj.examplesoftest.data.model.JediResponse
import com.br.titoaesj.examplesoftest.data.repository.JediRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val jediRepository: JediRepository
) : ViewModel() {

    private val _res = MutableLiveData<Resource<JediResponse>>()

    val res: LiveData<Resource<JediResponse>>
        get() = _res

    init {
        getAllCharacter()
    }

    private fun getAllCharacter() = viewModelScope.launch {
        _res.postValue(Resource.loading(null))
        jediRepository.getAllCharacter().let {
            if (it.isSuccessful) {
                _res.postValue(Resource.success(it.body()))
            } else {
                _res.postValue(Resource.error(it.errorBody().toString(), null))
            }
        }
    }

}
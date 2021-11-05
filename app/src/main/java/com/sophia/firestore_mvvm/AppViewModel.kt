package com.sophia.firestore_mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class AppViewModel: ViewModel() {

    private val repository = AppRepository()



    fun addInFor(name: String, birth: String) {
        repository.addInFor(name, birth)
    }

    fun getInfor(infor: MutableList<Infor>): LiveData<List<Infor>> {
        repository.getInfor(infor)
        return repository.getInfors()
    }
}
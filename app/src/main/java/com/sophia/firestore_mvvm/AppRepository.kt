package com.sophia.firestore_mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

class AppRepository {

    private val fireStore: FirebaseFirestore = FirebaseFirestore.getInstance()

    private val mInfor = MutableLiveData<List<Infor>>()

    fun getInfors(): LiveData<List<Infor>> = mInfor

    fun addInFor(name: String, birth: String) {
        val infor = Infor(name, birth)
        fireStore.collection("Infor").document().set(infor)
    }

    fun getInfor(inforList: MutableList<Infor>): LiveData<List<Infor>> {
        fireStore.collection("Infor")
            .addSnapshotListener { value, _ ->
                if (value != null) {
                    for (dc: DocumentChange in value.documentChanges) {
                        if (dc.type == DocumentChange.Type.ADDED) {
                            val infor = dc.document.toObject(Infor::class.java)
                            infor.name = dc.document.getString("name")
                            infor.birth = dc.document.getString("birth")

                            inforList.add(infor)
                            mInfor.value = inforList
                        }
                    }
                }
            }
        return mInfor
    }
}

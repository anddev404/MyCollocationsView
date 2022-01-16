package com.example.collocation_view_fragment.part_of_speech

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PartOfSpeechViewModel() : ViewModel() {

    private val _isCheckedVerb = MutableLiveData<Boolean>(false)
    private val _isCheckedNoun = MutableLiveData<Boolean>(false)
    private val _isCheckedAdj = MutableLiveData<Boolean>(false)
    private val _isCheckedAll = MutableLiveData<Boolean>(true)

    init {
        _isCheckedVerb.value = false
        _isCheckedNoun.value = false
        _isCheckedAdj.value = false
        _isCheckedAll.value = true
    }

    fun clickVerb() {
        _isCheckedVerb.value = true
        _isCheckedNoun.value = false
        _isCheckedAdj.value = false
        _isCheckedAll.value = false
    }

    fun clickNoun() {
        _isCheckedVerb.value = false
        _isCheckedNoun.value = true
        _isCheckedAdj.value = false
        _isCheckedAll.value = false
    }

    fun clickAdj() {
        _isCheckedVerb.value = false
        _isCheckedNoun.value = false
        _isCheckedAdj.value = true
        _isCheckedAll.value = false
    }

    fun clickAll() {
        _isCheckedVerb.value = false
        _isCheckedNoun.value = false
        _isCheckedAdj.value = false
        _isCheckedAll.value = true
    }

    fun isCheckedVerb(): LiveData<Boolean> {
        return _isCheckedVerb
    }

    fun isCheckedNoun(): LiveData<Boolean> {
        return _isCheckedNoun
    }

    fun isCheckedAdj(): LiveData<Boolean> {
        return _isCheckedAdj
    }

    fun isCheckedAll(): LiveData<Boolean> {
        return _isCheckedAll
    }


    fun isCheckedVerbNotNullOrDefault(): Boolean {
        return _isCheckedVerb.value ?: false
    }

    fun isCheckedNounNotNullOrDefault(): Boolean {
        return _isCheckedNoun.value ?: false
    }

    fun isCheckedAdjNotNullOrDefault(): Boolean {
        return _isCheckedAdj.value ?: false
    }

    fun isCheckedAllNotNullOrDefault(): Boolean {
        return _isCheckedAll.value ?: false
    }

    private val _relation = MutableLiveData<Int>(0)

    fun getRelation(): LiveData<Int> {
        return _relation
    }

    fun getRelationNotNullOrDefault(): Int {
        return _relation.value ?: 0
    }

    fun setRelation(relation: Int) {
        _relation.value = relation
    }

}
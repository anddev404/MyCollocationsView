package com.anddev404.words_list_view

class Word(var word: String) {

    companion object {
        fun getList(): ArrayList<Word> {

            return arrayListOf(Word("home"), Word("go"), Word("come"))
        }
    }
}
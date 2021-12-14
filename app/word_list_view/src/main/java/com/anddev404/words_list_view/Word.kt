package com.anddev404.words_list_view

class Word(var id: Int, var word: String, var translation: String, var prouncination: String) {

    companion object {
        fun getList(): ArrayList<Word> {

            return arrayListOf(
                Word(1, "home", "dom", "hołm"),
                Word(2, "go", "iść", "goł"),
                Word(3, "come", "przyjść", "kom")
            )
        }
    }
}
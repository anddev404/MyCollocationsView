package com.anddev404.words_list_view

class Word(var id: Int, var word: String, var translation: String, var prouncination: String) {

    companion object {
        fun getList(): ArrayList<Word> {
            var list = arrayListOf<Word>()

            for (i in 1..3000) {
                list.add(Word(1, "home", "dom", "hołm"))
                list.add(Word(2, "go", "iść", "goł"))
                list.add(Word(3, "come", "przyjść", "kom"))
            }

            return list

        }

//        fun getListSearch(): ArrayList<Word> {
//
//            return arrayListOf(
//                Word(-1, "słowo:", "dom", "hołm"),
//                Word(0, "marcin", "iść", "goł"),
//
//                Word(-2, "wyszukiwanie:", "przyjść", "kom"),
//                Word(1, "go", "iść", "goł"),
//                Word(-3, "tłuamczenie:", "przyjść", "kom"),
//                Word(2, "come", "przyjść", "kom")
//            )
//        }

        val ID_MINUS_ONE = "<b>szukasz:</b>"
        val ID_MINUS_TWO = "<b>eng:</b> ====="
        val ID_MINUS_THREE = "<b>pl:</b> ====="

    }

}
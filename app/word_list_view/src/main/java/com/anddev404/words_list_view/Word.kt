package com.anddev404.words_list_view

class Word(
    var id: Int,
    var word: String,
    var translation: String,
    var prouncination: String,
    var partOfSpeech: Int = 0,
    var forNr: Int = 0,
    var greenCollocationsCount: Int = 0,
    var greenSentencesCount: Int = 0,
) {
    var study_String = ""

    companion object {
        fun getList(): ArrayList<Word> {
            var list = arrayListOf<Word>()

            for (i in 1..3000) {
                list.add(Word(1, "home", "dom", "hołm", 1).apply {
                    greenCollocationsCount = 1
                    greenSentencesCount = 2
                })
                list.add(Word(2, "door", "drzwi", "doo", 1).apply {
                    greenCollocationsCount = 3
                    greenSentencesCount = 4
                })
                list.add(Word(3, "go", "iść", "goł", 3).apply {
                    greenCollocationsCount = 5
                    greenSentencesCount = 6
                })
                list.add(Word(4, "come", "przyjść", "kom", 3).apply {
                    greenCollocationsCount = 7
                    greenSentencesCount = 8
                })
                list.add(Word(5, "good", "dobry", "guud", 2))
                list.add(Word(6, "bad", "zły", "bad", 2))
                list.add(Word(5, "good", "dobry", "guud", 0))
                list.add(Word(6, "bad", "zły", "bad", 0))
                list.add(Word(5, "good", "dobry", "guud", 0))
                list.add(Word(6, "bad", "zły", "bad", 0))
                list.add(Word(6, "bad", "zły", "bad", 0))
                list.add(Word(5, "good", "dobry", "guud", 0))
                list.add(Word(6, "bad", "zły", "bad", 0))
                list.add(Word(5, "good", "dobry", "guud", 0))
                list.add(Word(6, "bad", "zły", "bad", 0))

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

        val ID_MINUS_FOUR = "<b> == :</b>"
        val ID_MINUS_FIVE = "<b> != :</b>"
//        val ID_MINUS_SIX = "<b>verbs:</b>"
//        val ID_MINUS_SEVEN = "<b>nouns:</b>"
//        val ID_MINUS_EIGHT = "<b>adj.:</b>"
//        val ID_MINUS_NINE = "<b>unkn.:</b>"
    }

}
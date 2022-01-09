package com.example.collocation_view_fragment

import android.util.Log
import java.lang.Exception

class GroupingCollocationTools {
    companion object {
        var list_to_get_into_A = arrayListOf<Collocation>(
            Collocation(1, "to get into a"),
            Collocation(1, "to get in b"),
            Collocation(1, "to get c"),
            Collocation(1, "to get"),
            Collocation(1, ""),
            Collocation(1, "to get asd d"),
            Collocation(1, "to get into e"),
            Collocation(1, "to get qwe f"),
            Collocation(1, "to get in g"),
            Collocation(1, "to get into h"),

            )
        var list_to_Get_A_into_B = arrayListOf<Collocation>(
            Collocation(1, "to get a into a"),
            Collocation(1, "to get b in a"),
            Collocation(1, "to get c c"),
            Collocation(1, "to get d asd d"),
            Collocation(1, "to get e into b"),
            Collocation(1, "to get f qwe e"),
            Collocation(1, "to get g in b"),
            Collocation(1, "to get h into c")
        )
        var list_to_A_into_family = arrayListOf<Collocation>(

            Collocation(1, "to a into family"),
            Collocation(1, "to b in family"),
            Collocation(1, "to c family"),
            Collocation(1, "to d asd family"),
            Collocation(1, "to e into family"),
            Collocation(1, "to f qwe family"),
            Collocation(1, "to g in family"),
            Collocation(1, "to h into family"),
        )
    }

    fun calculateAllFrequency(list: List<Collocation>): Int {
        var sum = 1
        for (i in list) {
            sum = sum + i.frequency
        }

        return sum
    }

    fun getCollocationHeader_Verb_ToVerbIntoA(
        collocationString: String,
        partOfSpeech: Int,
        relation: String
    ): Collocation {

        var stringTable = collocationString.split(" ")

        var s1 = stringTable.getOrNull(0) ?: ""
        var s2 = stringTable.getOrNull(1) ?: ""
        var s3 = stringTable.getOrNull(2) ?: ""
        var s4 = stringTable.getOrNull(3) ?: ""

        if (relation.equals("V:prep:N", true) && partOfSpeech == 3) {
            return Collocation().apply {
                id = -1
                collocation = "$s1 $s2 $s3 ..."
            }
        }
        if (relation.equals("V:obj+prep:N", true) && partOfSpeech == 3) {
            return Collocation().apply {
                id = -1
                collocation = "$s1 $s2 ... $s4 ..."
            }
        }
        if (relation.equals("V:prep:N", true) && partOfSpeech == 1) {
            return Collocation().apply {
                id = -1
                collocation = "$s1 ... $s3 $s4"
            }
        }
        if (relation.equals("V:obj+prep:N", true) && partOfSpeech == 1) {
            return Collocation().apply {
                id = -1
                collocation = "$s1 ... $s3 $s4 ..."
            }
        }

        return Collocation().apply {
            id = -1
            collocation = "........"
        }
    }

    fun getStatsCollocation(
        counterTheSameCollocation: Int,
        listSize: Int,
        localFrequencyCounter: Int,
        globalFrequencyCounter: Int
    ): String {
        try {
            return "${counterTheSameCollocation}/${listSize},  ${((counterTheSameCollocation.toFloat() / listSize) * 100).toInt()}%" + " || " + "$localFrequencyCounter/$globalFrequencyCounter,  ${((localFrequencyCounter.toFloat() / globalFrequencyCounter) * 100).toInt()}%"

        } catch (e: Exception) {
            return "${counterTheSameCollocation}/${listSize}" + " || " + "$localFrequencyCounter/$globalFrequencyCounter"

        }
    }

    fun groupListAndAddHeaders(
        list: List<Collocation>,
        partOfSpeech: Int,
        relation: String
    ): ArrayList<Collocation> {

//        val PART_OF_SPEECH_NOUN = 1
//        val PART_OF_SPEECH_VERB = 3

//        "V:prep:N"
//        "V:obj+prep:N"


        var newList = arrayListOf<Collocation>()
        var indeksList = arrayListOf<Int>()
        var globalFrequencyCounter = calculateAllFrequency(list)


        for (i in 0..list.size - 2) {

            var counterTheSameCollocation = 1//1 poniewaz juz od razu ta kolokacja bedzie dodana
            var localFrequencyCounter = 0
            var collocation = Collocation()

            if (!isIndex(i, indeksList)) {

                localFrequencyCounter = localFrequencyCounter + list[i].frequency
                indeksList.add(i)

                newList.add(
                    getCollocationHeader_Verb_ToVerbIntoA(
                        list[i].collocation,
                        partOfSpeech,
                        relation
                    )
                )
                ///////

                newList.add(list[i])

                for (j in i + 1..list.size - 1) {
                    if (!isIndex(j, indeksList)) {
                        Log.d("LIST_COLLOCATIONS", "= $i $j")
                        if (isTheSame(
                                list[i].collocation,
                                list[j].collocation, partOfSpeech, relation
                            )
                        ) {

                            counterTheSameCollocation++
                            localFrequencyCounter = localFrequencyCounter + list[j].frequency
                            indeksList.add(j)
                            if (counterTheSameCollocation < 8) {
                                newList.add(list[j])
                            }
                        }
                    }
                }
            }
            collocation.translatedCollocation = getStatsCollocation(
                counterTheSameCollocation, list.size, localFrequencyCounter, globalFrequencyCounter
            )

        }
        if (list.size > 0 && !isIndex(list.lastIndex, indeksList)) {
            var stringTable = list.last().collocation.split(" ")
            try {//to_get_into_A
                newList.add(
                    Collocation(
                        -1, "${stringTable[2]} ..."
                    )
                )
            } catch (e: Exception) {
                newList.add(Collocation(-1, list.last().collocation))

            }
            newList.add(list.last())
        }

        return newList
    }

    fun isTheSame(
        string1: String,
        string2: String,
        partOfSpeech: Int,
        relation: String
    ): Boolean {
        //to get into ...               verb 3 "V:prep:N"
        // to get ... into ...          verb 3 "V:obj+prep:N"

        // to ... into home             noun 1 "V:prep:N"
        // to ... home into ...         venoun 1 "V:obj+prep:N"

        var stringTable1 = string1.split(" ")
        var stringTable2 = string2.split(" ")

        var string1w1 = stringTable1.getOrNull(0) ?: ""
        var string1w2 = stringTable1.getOrNull(1) ?: ""
        var string1w3 = stringTable1.getOrNull(2) ?: ""
        var string1w4 = stringTable1.getOrNull(3) ?: ""

        var string2w1 = stringTable2.getOrNull(0) ?: ""
        var string2w2 = stringTable2.getOrNull(1) ?: ""
        var string2w3 = stringTable2.getOrNull(2) ?: ""
        var string2w4 = stringTable2.getOrNull(3) ?: ""

        if (partOfSpeech == 3 && relation.equals("V:prep:N", true)) {
            if (string1w1.equals(string2w1, true)
                && string1w2.equals(string2w2, true)
                && string1w3.equals(string2w3, true)
            ) {
                return true
            }
        }
        if (partOfSpeech == 3 && relation.equals("V:obj+prep:N", true)) {
            if (string1w1.equals(string2w1, true)
                && string1w2.equals(string2w2, true)
                && string1w4.equals(string2w4, true)
            ) {
                return true
            }
        }

        if (partOfSpeech == 1 && relation.equals("V:prep:N", true)) {
            if (string1w1.equals(string2w1, true)
                && string1w3.equals(string2w3, true)
                && string1w4.equals(string2w4, true)
            ) {
                return true
            }
        }
        if (partOfSpeech == 1 && relation.equals("V:obj+prep:N", true)) {
            if (string1w1.equals(string2w1, true)
                && string1w3.equals(string2w3, true)
                && string1w4.equals(string2w4, true)
            ) {
                return true
            }
        }


        return false
    }

    fun isIndex(index: Int, list: List<Int>): Boolean {
        for (i in list) {
            if (index == i) return true
        }

        return false
    }

    fun toLogCollocations(list: List<Collocation>) {
        var string = "TO STRING\n"
        for (i in list) {
            string = string + i.id + " " + i.collocation + "    " + i.translatedCollocation + "\n"
        }
        Log.d("LIST_COLLOCATIONS", "" + string);
    }
}
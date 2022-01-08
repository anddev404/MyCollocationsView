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

    fun groupList_TO_VERB_PREP_n(list: List<Collocation>): ArrayList<Collocation> {
//czasownik V:prep:N
        var newList = arrayListOf<Collocation>()
        var indeksList = arrayListOf<Int>()
        var globalFrequencyCounter = 1
        for (i in list) {
            globalFrequencyCounter = globalFrequencyCounter + i.frequency

        }

        for (i in 0..list.size - 2) {

            var counter = 0
            var localFrequencyCounter = 0

            var collocation = Collocation()
            var stringTable = list[i].collocation.split(" ")

            if (!isIndex(i, indeksList)) {
                try {//to_get_into_A

                    newList.add(collocation.apply {
                        id = -1
                        this.collocation = "TO VERB " + "${stringTable[2]} ..."
                    })

                } catch (e: Exception) {
                    newList.add(collocation.apply {
                        id = -1
                        this.collocation = list[i].collocation
                    })
                }

                counter++
                localFrequencyCounter = localFrequencyCounter + list[i].frequency

                indeksList.add(i)
                newList.add(list[i])

                for (j in i + 1..list.size - 1) {
                    if (!isIndex(j, indeksList)) {
                        Log.d("LIST_COLLOCATIONS", "= $i $j")
                        if (isTheSame_to_get_into_A(list[i].collocation, list[j].collocation)) {

                            counter++
                            localFrequencyCounter = localFrequencyCounter + list[j].frequency
                            if (localFrequencyCounter < 8) {
                                indeksList.add(j)
                                newList.add(list[j])
                                //  newList.add(list[j].apply { hideLayout = true })
                            }

                        }
                    }
                }

            }
            collocation.translatedCollocation =
                "${counter}/${list.size},  ${((counter.toFloat() / list.size) * 100).toInt()}%" + " || " + "$localFrequencyCounter/$globalFrequencyCounter,  ${((localFrequencyCounter.toFloat() / globalFrequencyCounter) * 100).toInt()}%"
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

    fun isTheSame_to_get_into_A(string1: String, string2: String): Boolean {
        var stringTable1 = string1.split(" ")
        var stringTable2 = string2.split(" ")

        try {

            if (stringTable1[0].equals(stringTable2[0], true)
                && stringTable1[1].equals(stringTable2[1], true)
                && stringTable1[2].equals(stringTable2[2], true)
            ) {
                return true
            }
        } catch (e: Exception) {
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
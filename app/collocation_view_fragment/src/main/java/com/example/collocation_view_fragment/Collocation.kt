package com.example.collocation_view_fragment

class Collocation(var collocation: String, var example_1: String, var example_2: String, var example_3: String) {

    companion object {

        fun getListOfCollocation(): List<Collocation> {
            var list = ArrayList<Collocation>()

            list.add(Collocation("to like dog 1", "example 1", "", ""))
            list.add(Collocation("to like dog 2", "example 2", "example 2", ""))
            list.add(Collocation("to like dog 3", "example 3", "example 2", "example 3"))

            return list
        }
    }
}
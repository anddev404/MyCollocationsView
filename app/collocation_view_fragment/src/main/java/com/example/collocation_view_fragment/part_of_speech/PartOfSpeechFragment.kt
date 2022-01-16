package com.example.collocation_view_fragment.part_of_speech

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import com.example.collocation_view_fragment.R
import kotlinx.android.synthetic.main.fragment_part_of_speech.*


/**
 * A simple [Fragment] subclass.
 * Use the [PartOfSpeechFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 *        fragmentPartOfSpeech = supportFragmentManager
.findFragmentById(R.id.partOfSpeechFragment) as PartOfSpeechFragment
fragmentPartOfSpeech.setOnPartOfSpeechFragmentListener(this)
 */
class PartOfSpeechFragment : Fragment() {

    var partOfSpeechViewModel = PartOfSpeechViewModel()
//    var partOfSpeech =
//        PART_OF_SPEECH_UNKNOWN//TODO co jesli zadna lista nie berdzie miała elemntów i bedzie prowbował wziac ten z indeksem 0 a tam nic nie bedzie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_part_of_speech, container, false)

        return view
    }

    fun getRelationListSize(): Int {
        if (partOfSpeechViewModel.isCheckedVerbNotNullOrDefault()) {
            return relationListVerb().size
        }
        if (partOfSpeechViewModel.isCheckedNounNotNullOrDefault()) {
            return relationListNoun().size
        }
        if (partOfSpeechViewModel.isCheckedAdjNotNullOrDefault()) {
            return relationListAdjective().size
        }
        return relationListAll().size
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        partOfSpeechViewModel.getRelation().observe(viewLifecycleOwner, Observer {

            showRelationOnTextView("${(it + 1)} / ${getRelationListSize()}  ")
            Log.d("RELATION", "observe - Relation change");
            mListener?.relation(getActualRelationString())
        })

        partOfSpeechViewModel.isCheckedVerb().observe(viewLifecycleOwner, Observer {
            if (it) {

                changeColor(textViewCzasownik)

                partOfSpeechViewModel.setRelation(0)


            } else {
                textViewCzasownik.setBackgroundColor(Color.TRANSPARENT)

            }
        })
        textViewCzasownik.setOnClickListener(object : OnClickListener {
            override fun onClick(v: View?) {

                partOfSpeechViewModel.clickVerb()

            }

        })
        partOfSpeechViewModel.isCheckedNoun().observe(viewLifecycleOwner, Observer {
            if (it) {

                changeColor(textViewRzeczownik)

                partOfSpeechViewModel.setRelation(0)


            } else {
                textViewRzeczownik.setBackgroundColor(Color.TRANSPARENT)

            }
        })
        textViewRzeczownik.setOnClickListener(object : OnClickListener {
            override fun onClick(v: View?) {

                partOfSpeechViewModel.clickNoun()

            }

        })
        partOfSpeechViewModel.isCheckedAdj().observe(viewLifecycleOwner, Observer {
            if (it) {

                changeColor(textViewPrzymiotnik)

                partOfSpeechViewModel.setRelation(0)


            } else {
                textViewPrzymiotnik.setBackgroundColor(Color.TRANSPARENT)

            }
        })
        textViewPrzymiotnik.setOnClickListener(object : OnClickListener {
            override fun onClick(v: View?) {

                partOfSpeechViewModel.clickAdj()

            }

        })
        partOfSpeechViewModel.isCheckedAll().observe(viewLifecycleOwner, Observer {
            if (it) {

                changeColor(textViewAll)

                partOfSpeechViewModel.setRelation(0)


            } else {
                textViewAll.setBackgroundColor(Color.TRANSPARENT)

            }
        })
        textViewAll.setOnClickListener(object : OnClickListener {
            override fun onClick(v: View?) {

                partOfSpeechViewModel.clickAll()

            }

        })
        textViewRelationLeft.setOnClickListener(object : OnClickListener {
            override fun onClick(v: View?) {

                var relation = partOfSpeechViewModel.getRelationNotNullOrDefault() - 1

                if (partOfSpeechViewModel.isCheckedVerbNotNullOrDefault()) {
                    if (relation < 0) {
                        relation = relationListVerb().size - 1
                    }
                    if (relation >= relationListVerb().size) {
                        relation = 0
                    }
                    // showRelationOnTextView("${(partOfSpeechViewModel.getRelationNotNullOrDefault() + 1)} / ${relationListVerb().size}  ")

                } else if (partOfSpeechViewModel.isCheckedAdjNotNullOrDefault()) {
                    if (relation < 0) {
                        relation = relationListAdjective().size - 1
                    }
                    if (relation >= relationListAdjective().size) {
                        relation = 0
                    }
                    //showRelationOnTextView("${(partOfSpeechViewModel.getRelationNotNullOrDefault() + 1)} / ${relationListAdjective().size}  ")

                } else if (partOfSpeechViewModel.isCheckedNounNotNullOrDefault()) {
                    if (relation < 0) {
                        relation = relationListNoun().size - 1
                    }
                    if (relation >= relationListNoun().size) {
                        relation = 0
                    }
                    //showRelationOnTextView("${(partOfSpeechViewModel.getRelationNotNullOrDefault() + 1)} / ${relationListNoun().size}  ")

                } else {
                    if (relation < 0) {
                        relation = relationListAll().size - 1
                    }
                    if (relation >= relationListAll().size) {
                        relation = 0
                    }
                    // showRelationOnTextView("${(partOfSpeechViewModel.getRelationNotNullOrDefault() + 1)} / ${relationListAll().size}  ")
                }
                partOfSpeechViewModel.setRelation(relation)
                //mListener?.relation(getActualRelationString())
                Log.d("RELATION", "LEFT");

            }

        })
        textViewRelationRight.setOnClickListener(object : OnClickListener {
            override fun onClick(v: View?) {

                var relation = partOfSpeechViewModel.getRelationNotNullOrDefault() + 1

                if (partOfSpeechViewModel.isCheckedVerbNotNullOrDefault()) {
                    if (relation < 0) {
                        relation = relationListVerb().size - 1
                    }
                    if (relation >= relationListVerb().size) {
                        relation = 0
                    }
                    // showRelationOnTextView("${(partOfSpeechViewModel.getRelationNotNullOrDefault() + 1)} / ${relationListVerb().size}  ")

                } else if (partOfSpeechViewModel.isCheckedAdjNotNullOrDefault()) {
                    if (relation < 0) {
                        relation = relationListAdjective().size - 1
                    }
                    if (relation >= relationListAdjective().size) {
                        relation = 0
                    }
                    // showRelationOnTextView("${(partOfSpeechViewModel.getRelationNotNullOrDefault() + 1)} / ${relationListAdjective().size}  ")

                } else if (partOfSpeechViewModel.isCheckedNounNotNullOrDefault()) {
                    if (relation < 0) {
                        relation = relationListNoun().size - 1
                    }
                    if (relation >= relationListNoun().size) {
                        relation = 0
                    }
                    // showRelationOnTextView("${(partOfSpeechViewModel.getRelationNotNullOrDefault() + 1)} / ${relationListNoun().size}  ")

                } else {
                    if (relation < 0) {
                        relation = relationListAll().size - 1
                    }
                    if (relation >= relationListAll().size) {
                        relation = 0
                    }
                    //  showRelationOnTextView("${(partOfSpeechViewModel.getRelationNotNullOrDefault() + 1)} / ${relationListAll().size}  ")
                }
                partOfSpeechViewModel.setRelation(relation)
                //mListener?.relation(getActualRelationString())
                Log.d("RELATION", "RIGHT");
            }

        })

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment PartOfSpeechFragment.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PartOfSpeechFragment().apply {
                arguments = Bundle().apply {
                }
            }

        val RELATION_1_V_obj_N = "V:obj:N"//
        val RELATION_2_V_prep_N = "V:prep:N"//
        val RELATION_3_V_subj_N = "V:subj:N"//
        val RELATION_4_V_sc_V = "V:sc:V"
        val RELATION_5_N_mod_A = "N:mod:A"
        val RELATION_6_N_prep_N = "N:prep:N"
        val RELATION_7_N_nn_N = "N:nn:N"
        val RELATION_8_V_mod_A = "V:mod:A"
        val RELATION_9_A_mod_A = "A:mod:A"
        val RELATION_10_V_obj_1_2_N = "V:obj1+2:N"
        val RELATION_11_V_obj_prep_N = "V:obj+prep:N"

        val PART_OF_SPEECH_UNKNOWN = 0
        val PART_OF_SPEECH_NOUN = 1
        val PART_OF_SPEECH_ADJECTIVE = 2
        val PART_OF_SPEECH_VERB = 3

        fun relationListVerb(): ArrayList<String> {
            var list = arrayListOf<String>()

            list.add(RELATION_3_V_subj_N)
            list.add(RELATION_1_V_obj_N)
            list.add(RELATION_2_V_prep_N)
            list.add(RELATION_11_V_obj_prep_N)
            list.add(RELATION_8_V_mod_A)
            list.add(RELATION_5_N_mod_A)

            return list
        }

        fun relationListAdjective(): ArrayList<String> {
            var list = arrayListOf<String>()
            list.add(RELATION_5_N_mod_A)
            list.add(RELATION_9_A_mod_A)
            return list
        }


        fun relationListNoun(): ArrayList<String> {
            var list = arrayListOf<String>()
            list.add(RELATION_3_V_subj_N)
            list.add(RELATION_1_V_obj_N)
            list.add(RELATION_2_V_prep_N)
            list.add(RELATION_11_V_obj_prep_N)
            list.add(RELATION_7_N_nn_N)
            list.add(RELATION_5_N_mod_A)
            return list
        }

        fun relationListAll(): ArrayList<String> {
            var list = arrayListOf<String>()
            list.add(RELATION_1_V_obj_N)
            list.add(RELATION_2_V_prep_N)
            list.add(RELATION_3_V_subj_N)
            list.add(RELATION_4_V_sc_V)
            list.add(RELATION_5_N_mod_A)
            list.add(RELATION_6_N_prep_N)
            list.add(RELATION_7_N_nn_N)
            list.add(RELATION_8_V_mod_A)
            list.add(RELATION_9_A_mod_A)
            list.add(RELATION_10_V_obj_1_2_N)
            list.add(RELATION_11_V_obj_prep_N)
            return list

        }

        fun getDescriptionByRelation(relation: String, partOfSpeech: Int): String {
            if (partOfSpeech == PART_OF_SPEECH_VERB) {

                if (relation.equals(RELATION_3_V_subj_N)) {
                    return "..... <b>czasownik</b>"
                }
                if (relation.equals(RELATION_1_V_obj_N)) {
                    return "<b>czasownik</b> ....."
                }
                if (relation.equals(RELATION_2_V_prep_N)) {
                    return "<b>czasownik</b> + to, in, on, for"
                }
                if (relation.equals(RELATION_8_V_mod_A)) {
                    return "kolokacje / przysłówki"
                }
                if (relation.equals(RELATION_5_N_mod_A)) {
                    return "nowe znaczenia/słowa"
                }
                if (relation.equals(RELATION_11_V_obj_prep_N)) {
                    return "złożone zdania"
                }

            }
            if (partOfSpeech == PART_OF_SPEECH_NOUN) {
                if (relation.equals(RELATION_3_V_subj_N)) {
                    return "<b>dog</b> bark"
                }
                if (relation.equals(RELATION_2_V_prep_N)) {
                    return "bark like <b>dog</b>"
                }
                if (relation.equals(RELATION_1_V_obj_N)) {
                    return "to have a <b>dog</b>"
                }
                if (relation.equals(RELATION_11_V_obj_prep_N)) {
                    return "to take <b>dog</b> for walk"
                }
                if (relation.equals(RELATION_7_N_nn_N)) {
                    return "<b>dog</b> food"
                }
                if (relation.equals(RELATION_5_N_mod_A)) {
                    return "black <b>dog</b>"
                }
            }
            if (partOfSpeech == PART_OF_SPEECH_ADJECTIVE) {

                if (relation.equals(RELATION_5_N_mod_A)) {
                    return "<b>fast</b> car"
                }
                if (relation.equals(RELATION_9_A_mod_A)) {
                    return "too <b>fast</b>"
                }
            }



            return ""
        }
    }

    fun getActualRelationString(): String {
        if (partOfSpeechViewModel.isCheckedVerbNotNullOrDefault()) {
            return relationListVerb().get(
                partOfSpeechViewModel.getRelationNotNullOrDefault()
            )
        }
        if (partOfSpeechViewModel.isCheckedNounNotNullOrDefault()) {
            return relationListNoun().get(
                partOfSpeechViewModel.getRelationNotNullOrDefault()
            )
        }
        if (partOfSpeechViewModel.isCheckedAdjNotNullOrDefault()) {
            return relationListAdjective().get(
                partOfSpeechViewModel.getRelationNotNullOrDefault()
            )
        }
        return relationListAll().get(partOfSpeechViewModel.getRelationNotNullOrDefault())//TODO takie cos moze nie zwrocic stringa jak lista bedzie pusta
    }

    //region < listener
    private var mListener: OnPartOfSpeechFragmentListener? = null


    fun setOnPartOfSpeechFragmentListener(onPartOfSpeechFragmentListener: OnPartOfSpeechFragmentListener) {
        mListener = onPartOfSpeechFragmentListener;

    }

    interface OnPartOfSpeechFragmentListener {
        fun relation(relation: String)
    }

    //endregion
    //region input
    fun resetViews(partOfSpeech: Int) {//to na poczatku dac zeby listener wywopłac zeby zwrocil jak mam filtrowac
//        deleteColorForAll()
//        relation = 0
        if (partOfSpeech == PART_OF_SPEECH_UNKNOWN) {
            partOfSpeechViewModel.clickAll()
        }
        if (partOfSpeech == PART_OF_SPEECH_NOUN) {
            partOfSpeechViewModel.clickNoun()
        }
        if (partOfSpeech == PART_OF_SPEECH_ADJECTIVE) {
            partOfSpeechViewModel.clickAdj()
        }
        if (partOfSpeech == PART_OF_SPEECH_VERB) {
            partOfSpeechViewModel.clickVerb()
        }
//        showRelationOnTextView()
//        mListener?.relation(getActualRelationString())//
        //TODO tutaj przy kazdej zmianie słowa równiez wywoła zmiane relacji i listener chociaż wczesniej nie wywoływało tego
    }

    private fun changeColor(textView: TextView) {

        textView.setBackgroundColor(requireView().resources.getColor(R.color.collocations_head_chosed))

    }

    fun showRelationOnTextView(string: String) {
        textViewRelationCentre.setText(string + getActualRelationString())
    }
//endregion
}
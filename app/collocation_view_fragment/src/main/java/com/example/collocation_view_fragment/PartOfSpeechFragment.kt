package com.example.collocation_view_fragment

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView


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

    var relation = 0
    var relationInfo = ""
    var partOfSpeech =
        PART_OF_SPEECH_UNKNOWN//TODO co jesli zadna lista nie berdzie miała elemntów i bedzie prowbował wziac ten z indeksem 0 a tam nic nie bedzie

    private lateinit var leftTextView: TextView
    private lateinit var centreTextView: TextView
    private lateinit var rightTextView: TextView

    private lateinit var verbTextView: TextView
    private lateinit var adjectiveTextView: TextView
    private lateinit var nounTextView: TextView
    private lateinit var allTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_part_of_speech, container, false)
        leftTextView = view.findViewById(R.id.textViewRelationLeft) as TextView
        centreTextView = view.findViewById(R.id.textViewRelation) as TextView
        rightTextView = view.findViewById(R.id.textViewRelationRight) as TextView

        verbTextView = view.findViewById(R.id.textViewCzasownik) as TextView
        adjectiveTextView = view.findViewById(R.id.textViewPrzymiotnik) as TextView
        nounTextView = view.findViewById(R.id.textViewRzeczownik) as TextView
        allTextView = view.findViewById(R.id.textViewAll) as TextView

        verbTextView.setOnClickListener(object : OnClickListener {
            override fun onClick(v: View?) {
                deleteColorForAll()
                changeColor(verbTextView)
                partOfSpeech = PART_OF_SPEECH_VERB
                relation = 0//TODO tu zmienaic dla czesci mowy
                relationInfo = "${(relation + 1)} / ${relationListVerb().size}  "
                showRelationOnTextView()
                mListener?.relation(getActualRelation())

            }

        })
        nounTextView.setOnClickListener(object : OnClickListener {
            override fun onClick(v: View?) {
                deleteColorForAll()
                changeColor(nounTextView)
                partOfSpeech = PART_OF_SPEECH_NOUN
                relation = 0//TODO tu zmienaic dla czesci mowy
                relationInfo = "${(relation + 1)} / ${relationListNoun().size}  "
                showRelationOnTextView()
                mListener?.relation(getActualRelation())
            }

        })
        adjectiveTextView.setOnClickListener(object : OnClickListener {
            override fun onClick(v: View?) {
                deleteColorForAll()
                changeColor(adjectiveTextView)
                partOfSpeech = PART_OF_SPEECH_ADJECTIVE
                relation = 0//TODO tu zmienaic dla czesci mowy
                relationInfo = "${(relation + 1)} / ${relationListAdjective().size}  "
                showRelationOnTextView()
                mListener?.relation(getActualRelation())
            }

        })
        allTextView.setOnClickListener(object : OnClickListener {
            override fun onClick(v: View?) {
                deleteColorForAll()
                changeColor(allTextView)
                partOfSpeech = PART_OF_SPEECH_UNKNOWN
                relation = 0//TODO tu zmienaic dla czesci mowy
                relationInfo = "${(relation + 1)} / ${relationListAll().size}  "
                showRelationOnTextView()
                mListener?.relation(getActualRelation())
            }

        })
        leftTextView.setOnClickListener(object : OnClickListener {
            override fun onClick(v: View?) {

                relation = relation - 1

                if (partOfSpeech == PART_OF_SPEECH_VERB) {
                    if (relation < 0) {
                        relation = relationListVerb().size - 1
                    }
                    if (relation >= relationListVerb().size) {
                        relation = 0
                    }
                    relationInfo = "${(relation + 1)} / ${relationListVerb().size}  "
                } else if (partOfSpeech == PART_OF_SPEECH_ADJECTIVE) {
                    if (relation < 0) {
                        relation = relationListAdjective().size - 1
                    }
                    if (relation >= relationListAdjective().size) {
                        relation = 0
                    }
                    relationInfo = "${(relation + 1)} / ${relationListAdjective().size}  "
                } else if (partOfSpeech == PART_OF_SPEECH_NOUN) {
                    if (relation < 0) {
                        relation = relationListNoun().size - 1
                    }
                    if (relation >= relationListNoun().size) {
                        relation = 0
                    }
                    relationInfo = "${(relation + 1)} / ${relationListNoun().size}  "
                } else {
                    if (relation < 0) {
                        relation = relationListAll().size - 1
                    }
                    if (relation >= relationListAll().size) {
                        relation = 0
                    }
                    relationInfo = "${(relation + 1)} / ${relationListAll().size}  "
                }
                showRelationOnTextView()
                mListener?.relation(getActualRelation())
            }

        })
        rightTextView.setOnClickListener(object : OnClickListener {
            override fun onClick(v: View?) {

                relation = relation + 1

                if (partOfSpeech == PART_OF_SPEECH_VERB) {
                    if (relation < 0) {
                        relation = relationListVerb().size - 1
                    }
                    if (relation >= relationListVerb().size) {
                        relation = 0
                    }
                    relationInfo = "${(relation + 1)} / ${relationListVerb().size}  "
                } else if (partOfSpeech == PART_OF_SPEECH_ADJECTIVE) {
                    if (relation < 0) {
                        relation = relationListAdjective().size - 1
                    }
                    if (relation >= relationListAdjective().size) {
                        relation = 0
                    }
                    relationInfo = "${(relation + 1)} / ${relationListAdjective().size}  "
                } else if (partOfSpeech == PART_OF_SPEECH_NOUN) {
                    if (relation < 0) {
                        relation = relationListNoun().size - 1
                    }
                    if (relation >= relationListNoun().size) {
                        relation = 0
                    }
                    relationInfo = "${(relation + 1)} / ${relationListNoun().size}  "
                } else {
                    if (relation < 0) {
                        relation = relationListAll().size - 1
                    }
                    if (relation >= relationListAll().size) {
                        relation = 0
                    }
                    relationInfo = "${(relation + 1)} / ${relationListAll().size}  "
                }
                showRelationOnTextView()
                mListener?.relation(getActualRelation())
            }

        })
        return view
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
            list.add(RELATION_2_V_prep_N)
            list.add(RELATION_8_V_mod_A)
            list.add(RELATION_1_V_obj_N)
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
                    return "<b>rain</b> come"
                }
                if (relation.equals(RELATION_2_V_prep_N)) {
                    return "to begin to <b>rain</b>"
                }
                if (relation.equals(RELATION_8_V_mod_A)) {
                    return "to <b>rain</b> hard (jak?)"
                }
                if (relation.equals(RELATION_1_V_obj_N)) {
                    return ""
                }
                if (relation.equals(RELATION_5_N_mod_A)) {
                    return ""
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

    fun getActualRelation(): String {
        if (partOfSpeech == PART_OF_SPEECH_VERB) {
            relationInfo = "${(relation + 1)} / ${relationListVerb().size}  "
            return relationListVerb().get(
                relation
            )
        }
        if (partOfSpeech == PART_OF_SPEECH_ADJECTIVE) {
            relationInfo = "${(relation + 1)}/${relationListAdjective().size}  "
            return relationListAdjective().get(
                relation
            )
        }
        if (partOfSpeech == PART_OF_SPEECH_NOUN) {
            relationInfo = "${(relation + 1)}/${relationListNoun().size}  "
            return relationListNoun().get(
                relation
            )
        }
        relationInfo = "${(relation + 1)}/${relationListAll().size}  "
        return relationListAll().get(relation)//TODO takie cos moze nie zwrocic stringa jak lista bedzie pusta
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
        deleteColorForAll()
        relation = 0
        if (partOfSpeech == PART_OF_SPEECH_UNKNOWN) {
            relationInfo = "${(relation + 1)} / ${relationListAll().size}  "
            changeColor(allTextView)
            this.partOfSpeech = PART_OF_SPEECH_UNKNOWN
        }
        if (partOfSpeech == PART_OF_SPEECH_NOUN) {
            relationInfo = "${(relation + 1)} / ${relationListNoun().size}  "
            changeColor(nounTextView)
            this.partOfSpeech = PART_OF_SPEECH_NOUN
        }
        if (partOfSpeech == PART_OF_SPEECH_ADJECTIVE) {
            relationInfo = "${(relation + 1)} / ${relationListAdjective().size}  "
            changeColor(adjectiveTextView)
            this.partOfSpeech = PART_OF_SPEECH_ADJECTIVE
        }
        if (partOfSpeech == PART_OF_SPEECH_VERB) {
            relationInfo = "${(relation + 1)} / ${relationListVerb().size}  "
            changeColor(verbTextView)
            this.partOfSpeech = PART_OF_SPEECH_VERB
        }
        showRelationOnTextView()
//        mListener?.relation(getActualRelation())
    }

    private fun deleteColorForAll() {
        verbTextView.setBackgroundColor(Color.TRANSPARENT)
        adjectiveTextView.setBackgroundColor(Color.TRANSPARENT)
        nounTextView.setBackgroundColor(Color.TRANSPARENT)
        allTextView.setBackgroundColor(Color.TRANSPARENT)

    }

    private fun changeColor(textView: TextView) {

        textView.setBackgroundColor(requireView().resources.getColor(R.color.collocations_head_chosed))

    }

    fun showRelationOnTextView() {
        centreTextView.setText(relationInfo + getActualRelation())
    }

//endregion
}
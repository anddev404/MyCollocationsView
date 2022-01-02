package com.anddev404.sentences_view

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class SentencesFragment : Fragment() {

    lateinit var example1TextView: TextView
    lateinit var example2TextView: TextView
    lateinit var example3TextView: TextView
    lateinit var example4TextView: TextView
    lateinit var example5TextView: TextView

    lateinit var translatedExample1TextView: TextView
    lateinit var translatedExample2TextView: TextView
    lateinit var translatedExample3TextView: TextView
    lateinit var translatedExample4TextView: TextView
    lateinit var translatedExample5TextView: TextView

    var isChecked1 = false
    var isChecked2 = false
    var isChecked3 = false
    var isChecked4 = false
    var isChecked5 = false

    fun setColors() {
        if (isChecked1) {
            example1TextView.setBackgroundColor(rootView.resources.getColor(R.color.collocations_head_chosed))
            translatedExample1TextView.setBackgroundColor(rootView.resources.getColor(R.color.collocations_head_chosed))
        } else {
            example1TextView.setBackgroundColor(Color.TRANSPARENT)
            translatedExample1TextView.setBackgroundColor(Color.TRANSPARENT)
        }
        if (isChecked2) {
            example2TextView.setBackgroundColor(rootView.resources.getColor(R.color.collocations_head_chosed))
            translatedExample2TextView.setBackgroundColor(rootView.resources.getColor(R.color.collocations_head_chosed))
        } else {
            example2TextView.setBackgroundColor(Color.TRANSPARENT)
            translatedExample2TextView.setBackgroundColor(Color.TRANSPARENT)
        }
        if (isChecked3) {
            example3TextView.setBackgroundColor(rootView.resources.getColor(R.color.collocations_head_chosed))
            translatedExample3TextView.setBackgroundColor(rootView.resources.getColor(R.color.collocations_head_chosed))
        } else {
            example3TextView.setBackgroundColor(Color.TRANSPARENT)
            translatedExample3TextView.setBackgroundColor(Color.TRANSPARENT)
        }
        if (isChecked4) {
            example4TextView.setBackgroundColor(rootView.resources.getColor(R.color.collocations_head_chosed))
            translatedExample4TextView.setBackgroundColor(rootView.resources.getColor(R.color.collocations_head_chosed))
        } else {
            example4TextView.setBackgroundColor(Color.TRANSPARENT)
            translatedExample4TextView.setBackgroundColor(Color.TRANSPARENT)
        }
        if (isChecked5) {
            example5TextView.setBackgroundColor(rootView.resources.getColor(R.color.collocations_head_chosed))
            translatedExample5TextView.setBackgroundColor(rootView.resources.getColor(R.color.collocations_head_chosed))
        } else {
            example5TextView.setBackgroundColor(Color.TRANSPARENT)
            translatedExample5TextView.setBackgroundColor(Color.TRANSPARENT)
        }
    }

    fun setSentences(
        s1: String = "",
        t1: String = "",
        isChecked1: Boolean = false,
        s2: String = "",
        t2: String = "",
        isChecked2: Boolean = false,
        s3: String = "",
        t3: String = "",
        isChecked3: Boolean = false,
        s4: String = "",
        t4: String = "",
        isChecked4: Boolean = false,
        s5: String = "",
        t5: String = "",
        isChecked5: Boolean = false
    ) {
        example1TextView.text = s1
        translatedExample1TextView.text = t1
        this.isChecked1 = isChecked1

        example2TextView.text = s2
        translatedExample2TextView.text = t2
        this.isChecked2 = isChecked2

        example3TextView.text = s3
        translatedExample3TextView.text = t3
        this.isChecked3 = isChecked3

        example4TextView.text = s4
        translatedExample4TextView.text = t4
        this.isChecked4 = isChecked4

        example5TextView.text = s5
        translatedExample5TextView.text = t5
        this.isChecked5 = isChecked5

        setColors()
    }

    lateinit var rootView: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        rootView = inflater.inflate(R.layout.fragment_sentences, container, false)

        example1TextView =
            rootView.findViewById<View>(R.id.textView_example_1) as TextView
        example2TextView =
            rootView.findViewById<View>(R.id.textView_example_2) as TextView
        example3TextView =
            rootView.findViewById<View>(R.id.textView_example_3) as TextView
        example4TextView =
            rootView.findViewById<View>(R.id.textView_example_4) as TextView
        example5TextView =
            rootView.findViewById<View>(R.id.textView_example_5) as TextView


        translatedExample1TextView =
            rootView.findViewById<View>(R.id.textView_translated_example_1) as TextView
        translatedExample2TextView =
            rootView.findViewById<View>(R.id.textView_translated_example_2) as TextView
        translatedExample3TextView =
            rootView.findViewById<View>(R.id.textView_translated_example_3) as TextView
        translatedExample4TextView =
            rootView.findViewById<View>(R.id.textView_translated_example_4) as TextView
        translatedExample5TextView =
            rootView.findViewById<View>(R.id.textView_translated_example_5) as TextView

        setColors()

        example1TextView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

                isChecked1 = !isChecked1
                setColors()
                mListener?.sentenceClick(
                    rootView,
                    1,
                    "" + example1TextView.text,
                    "" + translatedExample1TextView.text,
                    isChecked1
                );

            }
        })
        translatedExample1TextView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

                isChecked1 = !isChecked1
                setColors()
                mListener?.sentenceClick(
                    rootView,
                    1,
                    "" + example1TextView.text,
                    "" + translatedExample1TextView.text,
                    isChecked1
                );

            }
        })
        /////////////////////////////////
        example2TextView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

                isChecked2 = !isChecked2
                setColors()
                mListener?.sentenceClick(
                    rootView,
                    2,
                    "" + example2TextView.text,
                    "" + translatedExample2TextView.text,
                    isChecked2
                );

            }
        })
        translatedExample2TextView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

                isChecked2 = !isChecked2
                setColors()
                mListener?.sentenceClick(
                    rootView,
                    2,
                    "" + example2TextView.text,
                    "" + translatedExample3TextView.text,
                    isChecked2
                );

            }
        })
        /////////////////////////////////
        example3TextView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

                isChecked3 = !isChecked3
                setColors()
                mListener?.sentenceClick(
                    rootView,
                    3,
                    "" + example3TextView.text,
                    "" + translatedExample3TextView.text,
                    isChecked3
                );

            }
        })
        translatedExample3TextView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

                isChecked3 = !isChecked3
                setColors()
                mListener?.sentenceClick(
                    rootView,
                    3,
                    "" + example3TextView.text,
                    "" + translatedExample3TextView.text,
                    isChecked3
                );

            }
        })
        /////////////////////////////////
        example4TextView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

                isChecked4 = !isChecked4
                setColors()
                mListener?.sentenceClick(
                    rootView,
                    4,
                    "" + example4TextView.text,
                    "" + translatedExample4TextView.text,
                    isChecked4
                );

            }
        })
        translatedExample4TextView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

                isChecked4 = !isChecked4
                setColors()
                mListener?.sentenceClick(
                    rootView,
                    4,
                    "" + example4TextView.text,
                    "" + translatedExample4TextView.text,
                    isChecked4
                );

            }
        })
        /////////////////////////////////
        example5TextView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

                isChecked5 = !isChecked5
                setColors()
                mListener?.sentenceClick(
                    rootView,
                    5,
                    "" + example5TextView.text,
                    "" + translatedExample5TextView.text,
                    isChecked5
                );

            }
        })
        translatedExample5TextView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

                isChecked5 = !isChecked5
                setColors()
                mListener?.sentenceClick(
                    rootView,
                    5,
                    "" + example5TextView.text,
                    "" + translatedExample5TextView.text,
                    isChecked5
                );

            }
        })
        /////////////////////////////////
        return rootView
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) = SentencesFragment()

    }

    //region listener
//////////////////////////////
    private var mListener: OnSentenceClickListener? = null


    fun setOnSentenceClickListener(onSentenceClickListener: OnSentenceClickListener) {
        mListener = onSentenceClickListener;

    }

    interface OnSentenceClickListener {
        fun sentenceClick(
            fragment: View,
            nr: Int,
            sentence: String,
            translation: String,
            isChecked: Boolean
        )
    }

//////////////////////////////
}
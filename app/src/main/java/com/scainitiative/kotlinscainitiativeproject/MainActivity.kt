package com.scainitiative.kotlinscainitiativeproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var activeTv : TextView
    private lateinit var answer :Number
    var operator :String =""
    var operating: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val w = window;
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

    }

    fun numberButtonClicked(view: View){
        activeTv = if(operating){
            secondNumberTv
        }else firstNumberTv
        showAsExpression(view)
    }

    private fun showAsExpression(btn: View) {
        val button: Button = btn as Button
        val expression = if(activeTv==operatorTv){
            button.text.toString()
        } else activeTv.text.toString() + button.text.toString()
        activeTv.text = expression

    }

    fun operatorButtonClicked(view:View){
        activeTv=operatorTv
        if (operating){
            calculate()
            secondNumberTv.text=""
            firstNumberTv.text = answer_tv.text.toString()
        }else if (operator=="="){
            secondNumberTv.text=""
            firstNumberTv.text = answer_tv.text.toString()
        }
        showAsExpression(view)
        operating =true
    }

    fun equalButtonClicked(view:View){
        calculate()
        operating = false
        operator= "="
    }

    private fun calculate() {
        if (firstNumberTv.text.toString()=="")firstNumberTv.text="0"
        if (secondNumberTv.text.toString()=="")secondNumberTv.text="0"
           val firstNum: Double = firstNumberTv.text.toString().toDouble()
            val secondNum: Double = secondNumberTv.text.toString().toDouble()

            answer = when (operatorTv.text.toString()) {
                "+" -> firstNum.plus(secondNum)
                "-" -> firstNum.minus(secondNum)
                "*" -> firstNum.times(secondNum)
                "/" -> firstNum.div(secondNum)
                else -> firstNum.rem(secondNum)
            }

            if (answer.toString().endsWith(".0", true)) answer = answer.toInt()
            var answerString = answer.toString()
            if (answerString.length > 12) {
                answerString = answerString.subSequence(0, 12) as String
            }
            answer_tv.text = answerString

    }
}


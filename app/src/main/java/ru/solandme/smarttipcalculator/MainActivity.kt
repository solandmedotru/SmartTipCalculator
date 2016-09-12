package ru.solandme.smarttipcalculator

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*

import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private var billAmount = 0.0
    private var percent = 0.15

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialViews()
    }

    private fun initialViews() {
        tipTextView.text = currencyFormat.format(0)
        totalTextView.text = currencyFormat.format(0)

        amountEditText.addTextChangedListener(amountEditTextWatcher)
        percentSeekBar.setOnSeekBarChangeListener(seekBarListener)
    }

    private fun calculate() {
        percentTextView!!.text = percentFormat.format(percent)
        val tip = billAmount * percent
        val total = billAmount + tip
        tipTextView!!.text = currencyFormat.format(tip)
        totalTextView!!.text = currencyFormat.format(total)
    }

    private val seekBarListener = object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
            percent = progress / 100.0
            calculate()
        }

        override fun onStartTrackingTouch(seekBar: SeekBar) {
        }

        override fun onStopTrackingTouch(seekBar: SeekBar) {
        }
    }

    private val amountEditTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            try {
                billAmount = java.lang.Double.parseDouble(s.toString()) / 100.0
                amountTextView.text = currencyFormat.format(billAmount)
            } catch (e: NumberFormatException) {
                amountTextView.text = ""
                billAmount = 0.0
            }

            calculate()
        }

        override fun afterTextChanged(s: Editable) {
        }
    }

    companion object {

        private val currencyFormat = NumberFormat.getCurrencyInstance()
        private val percentFormat = NumberFormat.getPercentInstance()
    }
}

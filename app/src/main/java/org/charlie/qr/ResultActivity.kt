package org.charlie.qr

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_result.*


/**
 * Created by Charlie Wu on 15/11/17.
 */

class ResultActivity : Activity() {

    companion object {

        const val INTENT_EXTRA_TEXT = "TEXT"
        fun start(context: Context, text: String) {
            val intent = Intent(context, ResultActivity::class.java)
            intent.putExtra(INTENT_EXTRA_TEXT, text)
            context.startActivity(intent)
        }
    }

    private val resultText by lazy { intent.getStringExtra(INTENT_EXTRA_TEXT) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        textLabel.text = resultText

        copyButton.setOnClickListener {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("qr", resultText)
            clipboard.primaryClip = clip
            Toast.makeText(this, getString(R.string.copy_confirmation_label), Toast.LENGTH_SHORT).show()
        }

        doneButton.setOnClickListener {
            finish()
        }

    }
}
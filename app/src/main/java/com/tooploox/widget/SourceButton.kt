package com.tooploox.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.tooploox.R
import com.tooploox.utils.gone
import com.tooploox.utils.inflate
import com.tooploox.utils.visible
import kotlinx.android.synthetic.main.source_button_widget.view.*

class SourceButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    var label: String = ""
        set(value) {
            field = value
            labelTextView.text = value
        }

    var onSelectedCallback: (isSelected: Boolean) -> Unit = {}

    init {
        inflate(R.layout.source_button_widget)
        applyAttributes(attrs, context)
        initOnClickListener()
    }

    private fun initOnClickListener() {
        setOnClickListener {
            isSelected = !isSelected
            onSelectedCallback(isSelected)

        }
    }

    private fun applyAttributes(attrs: AttributeSet?, context: Context) {
        attrs?.let {
            val attributes = context.theme.obtainStyledAttributes(attrs, R.styleable.SourceButton, 0, 0)
            setLabelText(attributes.getString(R.styleable.SourceButton_label))
            setType(attributes.getInt(R.styleable.SourceButton_source, 0))
            attributes.recycle()
        }
    }

    private fun setType(type: Int) {
        when (type) {
            0 -> iconImageView.setImageResource(R.drawable.ic_itunes)
            1 -> iconImageView.setImageResource(R.drawable.ic_local)
        }
    }

    private fun setLabelText(text: String?) {
        labelTextView.text = text
    }

    override fun setSelected(selected: Boolean) {
        super.setSelected(selected)
        if (selected) {
            maskView.visible()
            checkBoxImageView.visible()
        } else {
            maskView.gone()
            checkBoxImageView.gone()
        }
    }
}
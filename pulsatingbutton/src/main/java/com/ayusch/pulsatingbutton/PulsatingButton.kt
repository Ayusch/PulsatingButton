package com.ayusch.pulsatingbutton

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.pulsating_button.view.*


/**
 * Created by Ayus'c'h Jain
 * on 2019-10-26
 *
 */

class PulsatingButton : LinearLayout {

    private var buttonText: CharSequence? = ""
    private var textColor: Int = ContextCompat.getColor(context, android.R.color.black)
    private var buttonColor: Int = ContextCompat.getColor(context, R.color.colorAccent)
    private var verticalOffset: Int = 40
    private var horizontalOffset: Int = 40
    private var repeatCount: Int = Int.MAX_VALUE
    private var animationDuration: Int = 1000
    val set = AnimatorSet()

    constructor(context: Context?) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        parseAttributes(context, attrs)
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        parseAttributes(context, attrs)
        init(context)
    }

    private fun init(context: Context?) {
        LayoutInflater.from(context).inflate(R.layout.pulsating_button, this, true)
        setColors()
        setText(buttonText)
    }

    private fun parseAttributes(context: Context, attributes: AttributeSet) {
        val attrs = context.theme.obtainStyledAttributes(attributes, R.styleable.PulsatingButton, 0, 0)
        try {
            this.animationDuration = attrs.getInt(R.styleable.PulsatingButton_pulseDuration, 100)
            this.verticalOffset = attrs.getInt(R.styleable.PulsatingButton_verticalOffset, 4)
            this.horizontalOffset = attrs.getInt(R.styleable.PulsatingButton_horizontalOffset, 4)
            this.repeatCount = attrs.getInt(R.styleable.PulsatingButton_pulseCount, Int.MAX_VALUE)
            this.buttonColor = attrs.getColor(R.styleable.PulsatingButton_buttonColor, ContextCompat.getColor(context, R.color.colorAccent))
            this.textColor = attrs.getColor(R.styleable.PulsatingButton_textColor, ContextCompat.getColor(context, R.color.colorAccent))
            this.buttonText = attrs.getText(R.styleable.PulsatingButton_text)
        } finally {
            attrs.recycle()
        }
    }

    fun startAnimation() {
        val animator = ValueAnimator.ofInt(0, verticalOffset)
        animator.repeatMode = ValueAnimator.REVERSE
        animator.interpolator = AccelerateDecelerateInterpolator()
        animator.duration = animationDuration.toLong()

        val animator2 = ValueAnimator.ofInt(0, horizontalOffset)
        animator2.repeatMode = ValueAnimator.REVERSE
        animator2.interpolator = AccelerateDecelerateInterpolator()
        animator2.duration = animationDuration.toLong()

        val originalheight = button.layoutParams.height
        val originalWidth = button.layoutParams.width

        animator.addUpdateListener { valueAnimator ->
            val params = button.layoutParams
            val animatedValue = valueAnimator.animatedValue as Int
            params.height = (originalheight + animatedValue)
            button.layoutParams = params
        }

        animator2.addUpdateListener { valueAnimator ->
            val params = button.layoutParams
            val animatedValue = valueAnimator.animatedValue as Int
            params.width = (originalWidth + animatedValue)
            button.layoutParams = params
        }

        if (repeatCount == Int.MAX_VALUE) {
            animator.repeatCount = Animation.INFINITE
            animator2.repeatCount = Animation.INFINITE
        } else {
            animator.repeatCount = repeatCount
            animator2.repeatCount = repeatCount
        }

        set.playTogether(animator, animator2)
        set.start()
    }

    fun setHorizontalOffset(horizontalOffset: Int) {
        this.horizontalOffset = horizontalOffset
    }

    fun setVerticalOffset(verticalOffset: Int) {
        this.verticalOffset = verticalOffset
    }

    fun setRepeatCount(count: Int) {
        this.repeatCount = count
    }

    fun setAnimationDuration(duration: Int) {
        this.animationDuration = duration
    }

    private fun setColors() {
        button.setBackgroundColor(buttonColor)
        button.setTextColor(textColor)
    }

    fun setButtonDrawable(drawable: Drawable) {
        button.background = drawable
    }

    fun setText(text: CharSequence?) {
        if (!TextUtils.isEmpty(text)) {
            button.text = text
        }
    }

    fun stopAnimation() {
        set.removeAllListeners()
        set.end()
        set.cancel()
    }

}

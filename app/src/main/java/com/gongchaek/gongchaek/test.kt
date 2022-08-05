package com.gongchaek.gongchaek

import android.content.Context
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView
import android.util.AttributeSet
import android.util.Log
import android.widget.ScrollView
import kotlin.math.abs

class test @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    ScrollView(context, attrs, defStyleAttr) {
    private var lastX = 0.0f
    private var lastY = 0.0f
    private var scrolling = false
    override fun onInterceptTouchEvent(e: MotionEvent): Boolean {
        var allowScroll = true
        when (e.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                lastX = e.x
                lastY = e.y
                Log.d("a", scrolling.toString())
                // If we were scrolling, stop now by faking a touch release

                val newEvent = MotionEvent.obtain(e)
                newEvent.action = MotionEvent.ACTION_UP
                Log.d("a", "ACTION_UP")
                return super.onInterceptTouchEvent(newEvent)
                if (scrolling) {
                    val newEvent = MotionEvent.obtain(e)
                    newEvent.action = MotionEvent.ACTION_UP
                    Log.d("a", "ACTION_UP")
                    return super.onInterceptTouchEvent(newEvent)
                }
            }
            MotionEvent.ACTION_MOVE -> {
                // We're moving, so check if we're trying
                // to scroll vertically or horizontally so we don't intercept the wrong event.
                val currentX = e.x
                val currentY = e.y
                val dx = abs(currentX - lastX)
                val dy = abs(currentY - lastY)

                allowScroll = dy > dx
            }
        }
        return if (!allowScroll) {
            false
        } else super.onInterceptTouchEvent(e)
    }

    override fun onScrollChanged (a: Int, b: Int, c: Int, d: Int) {

    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        when(ev.action) {
            MotionEvent.ACTION_SCROLL, MotionEvent.ACTION_MOVE -> {
                scrolling = true
            }
            MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP -> {
                scrolling = false
            }
        }
        return super.onTouchEvent(ev)
    }
}

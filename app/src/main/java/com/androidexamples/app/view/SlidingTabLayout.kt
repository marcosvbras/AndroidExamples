package com.androidexamples.app.view

/**
 * Created by marcos on 08/01/2017.
 */

import android.content.Context
import android.graphics.Typeface
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.util.SparseArray
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.HorizontalScrollView
import android.widget.LinearLayout
import android.widget.TextView

import com.androidexamples.app.utils.SlidingTabStrip

class SlidingTabLayout @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : HorizontalScrollView(context, attrs, defStyle) {

    // Metrics
    private val TITLE_OFFSET_DIPS = 24
    private val TAB_VIEW_PADDING_DIPS = 16
    private val TAB_VIEW_TEXT_SIZE_SP = 12

    // Views
    private var viewPager: ViewPager? = null
    private val tabStrip: SlidingTabStrip

    private val titleOffset: Int
    private var tabViewLayoutId: Int = 0
    private var tabViewTextViewId: Int = 0
    var isDistributeEvenly: Boolean = false
    private val contentDescriptions = SparseArray<String>()
    private var viewPagerPageChangeListener: ViewPager.OnPageChangeListener? = null
    var titleColor: Int = 0

    init {
        // Disabilitando a Scroll Bar do HorizontalScrollView
        isHorizontalScrollBarEnabled = false
        // Definindo que o TabStrip preenche esta View
        isFillViewport = true
        titleOffset = (TITLE_OFFSET_DIPS * resources.displayMetrics.density).toInt()
        tabStrip = SlidingTabStrip(context)
        addView(tabStrip, FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT)
    }

    /**
     * Sets the colors to be used for indicating the selected tab. These colors are treated as a
     * circular array. Providing one color will mean that all tabs are indicated with the same color.
     */
    fun setSelectedIndicatorColors(vararg colors: Int) {
        tabStrip.setSelectedIndicatorColors(*colors)
    }

    fun setOnPageChangeListener(listener: ViewPager.OnPageChangeListener) {
        viewPagerPageChangeListener = listener
    }

    /**
     * Set the custom layout to be inflated for the tab views.
     *
     * @param layoutResId Layout id to be inflated
     * @param textViewId id of the [TextView] in the inflated view
     */
    fun setCustomTabView(layoutResId: Int, textViewId: Int) {
        tabViewLayoutId = layoutResId
        tabViewTextViewId = textViewId
    }

    /**
     * Sets the associated view pager. Note that the assumption here is that the pager content
     * (number of tabs and tab titles) does not change after this call has been made.
     */
    fun setViewPager(viewPager: ViewPager?) {
        tabStrip.removeAllViews()
        this.viewPager = viewPager

        if (viewPager != null) {
            viewPager.addOnPageChangeListener(InternalViewPagerListener())
            populateTabStrip()
        }
    }

    /**
     * Create a default view to be used for tabs. This is called if a custom tab view is not set via
     * [.setCustomTabView].
     */
    protected fun createDefaultTabView(context: Context): TextView {
        val textView = TextView(context)
        textView.setTextColor(titleColor)
        textView.gravity = Gravity.CENTER
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, TAB_VIEW_TEXT_SIZE_SP.toFloat())
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        val outValue = TypedValue()
        getContext().theme.resolveAttribute(android.R.attr.selectableItemBackground, outValue, true)
        textView.setBackgroundResource(outValue.resourceId)
        textView.setAllCaps(true)

        val padding = (TAB_VIEW_PADDING_DIPS * resources.displayMetrics.density).toInt()
        textView.setPadding(padding, padding, padding, padding)

        return textView
    }

    private fun populateTabStrip() {
        val adapter = viewPager!!.adapter
        val tabClickListener = TabClickListener()

        for (i in 0 until adapter!!.count) {
            var tabView: View? = null
            var tabTitleView: TextView? = null

            if (tabViewLayoutId != 0) {
                // If there is a custom tab view layout id set, try and inflate it
                tabView = LayoutInflater.from(context).inflate(tabViewLayoutId, tabStrip, false)
                tabTitleView = tabView!!.findViewById<View>(tabViewTextViewId) as TextView
            }

            if (tabView == null)
                tabView = createDefaultTabView(context)

            if (tabTitleView == null && TextView::class.java.isInstance(tabView))
                tabTitleView = tabView as TextView?

            if (isDistributeEvenly) {
                val lp = tabView.layoutParams as LinearLayout.LayoutParams
                lp.width = 0
                lp.weight = 1f
            }

            tabTitleView!!.text = adapter.getPageTitle(i)
            tabView.setOnClickListener(tabClickListener)
            val desc = contentDescriptions.get(i, null)

            if (desc != null)
                tabView.contentDescription = desc

            tabStrip.addView(tabView)

            if (i == viewPager!!.currentItem)
                tabView.isSelected = true
        }
    }

    fun setContentDescription(i: Int, desc: String) {
        contentDescriptions.put(i, desc)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        if (viewPager != null)
            scrollToTab(viewPager!!.currentItem, 0)
    }

    private fun scrollToTab(tabIndex: Int, positionOffset: Int) {
        val tabStripChildCount = tabStrip.childCount

        if (tabStripChildCount == 0 || tabIndex < 0 || tabIndex >= tabStripChildCount)
            return

        val selectedChild = tabStrip.getChildAt(tabIndex)

        if (selectedChild != null) {
            var targetScrollX = selectedChild.left + positionOffset

            if (tabIndex > 0 || positionOffset > 0) {
                // If we're not at the first child and are mid-scroll, make sure we obey the offset
                targetScrollX -= titleOffset
            }

            scrollTo(targetScrollX, 0)
        }
    }

    private inner class InternalViewPagerListener : ViewPager.OnPageChangeListener {
        private var scrollState: Int = 0

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            val tabStripChildCount = tabStrip.childCount

            if (tabStripChildCount == 0 || position < 0 || position >= tabStripChildCount)
                return

            tabStrip.onViewPagerPageChanged(position, positionOffset)

            val selectedTitle = tabStrip.getChildAt(position)
            val extraOffset = if (selectedTitle != null) (positionOffset * selectedTitle.width).toInt() else 0
            scrollToTab(position, extraOffset)

            if (viewPagerPageChangeListener != null)
                viewPagerPageChangeListener!!.onPageScrolled(position, positionOffset, positionOffsetPixels)
        }

        override fun onPageScrollStateChanged(state: Int) {
            scrollState = state

            if (viewPagerPageChangeListener != null)
                viewPagerPageChangeListener!!.onPageScrollStateChanged(state)
        }

        override fun onPageSelected(position: Int) {
            if (scrollState == ViewPager.SCROLL_STATE_IDLE) {
                tabStrip.onViewPagerPageChanged(position, 0f)
                scrollToTab(position, 0)
            }
            for (i in 0 until tabStrip.childCount) {
                tabStrip.getChildAt(i).isSelected = position == i
            }
            if (viewPagerPageChangeListener != null) {
                viewPagerPageChangeListener!!.onPageSelected(position)
            }
        }
    }

    private inner class TabClickListener : View.OnClickListener {
        override fun onClick(v: View) {
            for (i in 0 until tabStrip.childCount) {
                if (v === tabStrip.getChildAt(i)) {
                    viewPager!!.currentItem = i
                    return
                }
            }
        }
    }

}
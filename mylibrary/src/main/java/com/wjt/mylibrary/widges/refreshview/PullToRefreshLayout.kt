package com.wjt.mylibrary.widges.refreshview

import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.*
import android.widget.FrameLayout
import androidx.core.view.ViewCompat
import androidx.core.view.ViewCompat.setTranslationY
import com.wjt.mylibrary.R
import com.wjt.mylibrary.base.BaseRefreshListener
import com.wjt.mylibrary.utils.DisplayUtil
import com.wjt.mylibrary.widges.refreshview.State.REFRESH_STATE
import com.wjt.mylibrary.widges.refreshview.ViewStatus.VIEW_STATUS

/**

 */
class PullToRefreshLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private var mHeaderView: HeadView? = null
    private var mFooterView: FooterView? = null
    private var mChildView: View? = null
    private var mTouchY = 0f
    private var mCurrentY = 0f
    private var canLoadMore = true
    private var canRefresh = true
    private var isRefresh = false
    private var isLoadMore = false

    //滑动的最小距离
    private var mTouchSlope = 0
    private var refreshListener: BaseRefreshListener? = null
    private var loadingView: View? = null
    private var errorView: View? = null
    private var emptyView: View? = null
    private var loading: Int = R.layout.layout_loading
    private var empty: Int = R.layout.layout_empty
    private var error: Int = R.layout.layout_error
    fun setRefreshListener(refreshListener: BaseRefreshListener?) {
        this.refreshListener = refreshListener
    }

    private fun cal() {
        head_height = DisplayUtil.dp2Px(context, HEAD_HEIGHT)
        foot_height = DisplayUtil.dp2Px(context, FOOT_HEIGHT)
        head_height_2 = DisplayUtil.dp2Px(context, HEAD_HEIGHT * 2)
        foot_height_2 = DisplayUtil.dp2Px(context, FOOT_HEIGHT * 2)
        mTouchSlope = ViewConfiguration.get(context).scaledTouchSlop
    }

    private fun init() {
        cal()
        val count = childCount
        if (count != 1) {
            IllegalArgumentException("child only can be one")
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        mChildView = getChildAt(0)
        addHeadView()
        addFooterView()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
    }

    private fun addHeadView() {
        if (mHeaderView == null) {
            mHeaderView = HeadRefreshView(context)
        } else {
            removeView(mHeaderView!!.view)
        }
        val layoutParams = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0)
        mHeaderView!!.view!!.layoutParams = layoutParams
        if (mHeaderView!!.view!!.parent != null) (mHeaderView!!.view!!.parent as ViewGroup).removeAllViews()
        addView(mHeaderView!!.view, 0)
    }

    private fun addFooterView() {
        if (mFooterView == null) {
            mFooterView = LoadMoreView(context)
        } else {
            removeView(mFooterView!!.view)
        }
        val layoutParams = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0)
        layoutParams.gravity = Gravity.BOTTOM
        mFooterView!!.view!!.layoutParams = layoutParams
        if (mFooterView!!.view!!.parent != null) (mFooterView!!.view!!.parent as ViewGroup).removeAllViews()
        addView(mFooterView!!.view)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        if (!canLoadMore && !canRefresh) return super.onInterceptTouchEvent(ev)
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                mTouchY = ev.y
                mCurrentY = mTouchY
            }
            MotionEvent.ACTION_MOVE -> {
                val currentY = ev.y
                val dy = currentY - mCurrentY
                if (canRefresh) {
                    val canChildScrollUp = canChildScrollUp()
                    if (dy > mTouchSlope && !canChildScrollUp) {
                        mHeaderView!!.begin()
                        return true
                    }
                }
                if (canLoadMore) {
                    val canChildScrollDown = canChildScrollDown()
                    if (dy < -mTouchSlope && !canChildScrollDown) {
                        mFooterView!!.begin()
                        return true
                    }
                }
            }
        }
        return super.onInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (isRefresh || isLoadMore) return true
        when (event.action) {
            MotionEvent.ACTION_MOVE -> {
                mCurrentY = event.y
                var dura = (mCurrentY - mTouchY) / 3.0f
                if (dura > 0 && canRefresh) {
                    dura = Math.min(head_height_2.toFloat(), dura)
                    dura = Math.max(0f, dura)
                    mHeaderView!!.view!!.layoutParams.height = dura.toInt()
                    mChildView?.setTranslationY(dura);
                    requestLayout()
                    mHeaderView!!.progress(dura, head_height.toFloat())
                } else {
                    if (canLoadMore) {
                        dura = Math.min(foot_height_2.toFloat(), Math.abs(dura))
                        dura = Math.max(0f, Math.abs(dura))
                        mFooterView!!.view!!.layoutParams.height = dura.toInt()
                        mChildView?.setTranslationY(-dura);
                        requestLayout()
                        mFooterView!!.progress(dura, foot_height.toFloat())
                    }
                }
                return true
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                val currentY = event.y
                val dy1 = (currentY - mTouchY).toInt() / 3
                if (dy1 > 0 && canRefresh) {
                    if (dy1 >= head_height) {
                        createAnimatorTranslationY(
                            State.REFRESH,
                            if (dy1 > head_height_2) head_height_2 else dy1, head_height,
                            object : CallBack {
                                override fun onSuccess() {
                                    isRefresh = true
                                    refreshListener?.refresh()
                                    mHeaderView!!.loading()
                                }
                            })
                    } else if (dy1 > 0 && dy1 < head_height) {
                        setFinish(dy1, State.REFRESH)
                        mHeaderView!!.normal()
                    }
                } else {
                    if (canLoadMore) {
                        if (Math.abs(dy1) >= foot_height) {
                            createAnimatorTranslationY(
                                State.LOADMORE,
                                if (Math.abs(dy1) > foot_height_2) foot_height_2 else Math.abs(dy1),
                                foot_height,
                                object : CallBack {
                                    override fun onSuccess() {
                                        isLoadMore = true
                                        refreshListener?.loadMore()
                                        mFooterView!!.loading()
                                    }
                                })
                        } else {
                            setFinish(Math.abs(dy1), State.LOADMORE)
                            mFooterView!!.normal()
                        }
                    }
                }
            }
        }
        return super.onTouchEvent(event)
    }

    private fun canChildScrollDown(): Boolean {
        return mChildView?.canScrollVertically(1) ?: false
    }

    private fun canChildScrollUp(): Boolean {
        return if (mChildView == null) {
            false
        } else  mChildView?.canScrollVertically(-1) ?: false
    }

    /**
     * 创建动画
     */
    fun createAnimatorTranslationY(
        @REFRESH_STATE state: Int, start: Int,
        purpose: Int, callBack: CallBack?
    ) {
        val anim: ValueAnimator
        anim = ValueAnimator.ofInt(start, purpose)
        anim.duration = ANIM_TIME
        anim.addUpdateListener { valueAnimator ->
            val value = valueAnimator.animatedValue as Int
            if (state == State.REFRESH) {
                mHeaderView!!.view!!.layoutParams.height = value
                mChildView?.setTranslationY( value.toFloat())
                if (purpose == 0) { //代表结束加载
                    mHeaderView!!.finishing(value.toFloat(), head_height_2.toFloat())
                } else {
                    mHeaderView!!.progress(value.toFloat(), head_height.toFloat())
                }
            } else {
                mFooterView!!.view!!.layoutParams.height = value
                mChildView?.setTranslationY( -value.toFloat())
                if (purpose == 0) { //代表结束加载
                    mFooterView!!.finishing(value.toFloat(), head_height_2.toFloat())
                } else {
                    mFooterView!!.progress(value.toFloat(), foot_height.toFloat())
                }
            }
            if (value == purpose) {
                callBack?.onSuccess()
            }
            requestLayout()
        }
        anim.start()
    }

    /**
     * 结束下拉刷新
     */
    private fun setFinish(height: Int, @REFRESH_STATE state: Int) {
        createAnimatorTranslationY(state, height, 0, object : CallBack {
            override fun onSuccess() {
                if (state == State.REFRESH) {
                    isRefresh = false
                    mHeaderView!!.normal()
                } else {
                    isLoadMore = false
                    mFooterView!!.normal()
                }
            }
        })
    }

    private fun setFinish(@REFRESH_STATE state: Int) {
        if (state == State.REFRESH) {
            if (mHeaderView != null && mHeaderView!!.view!!.layoutParams.height > 0 && isRefresh) {
                setFinish(head_height, state)
            }
        } else {
            if (mFooterView != null && mFooterView!!.view!!.layoutParams.height > 0 && isLoadMore) {
                setFinish(foot_height, state)
            }
        }
    }

    interface CallBack {
        fun onSuccess()
    }

    private fun showLoadingView() {
        if (loadingView == null) {
            loadingView = LayoutInflater.from(context).inflate(loading, null)
            val layoutParams = LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            addView(loadingView, layoutParams)
        } else {
            loadingView!!.visibility = VISIBLE
        }
    }

    private fun showEmptyView() {
        if (emptyView == null) {
            emptyView = LayoutInflater.from(context).inflate(empty, null)
            val layoutParams = LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            addView(emptyView, layoutParams)
        } else {
            emptyView!!.visibility = VISIBLE
        }
    }

    private fun showErrorView() {
        if (errorView == null) {
            errorView = LayoutInflater.from(context).inflate(error, null)
            val layoutParams = LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            addView(errorView, layoutParams)
        } else {
            errorView!!.visibility = VISIBLE
        }
    }

    private fun hideView(view: View?) {
        if (view != null) view.visibility = GONE
    }

    private fun switchView(status: Int) {
        when (status) {
            ViewStatus.CONTENT_STATUS -> {
                hideView(loadingView)
                hideView(emptyView)
                hideView(errorView)
                mChildView!!.visibility = VISIBLE
            }
            ViewStatus.LOADING_STATUS -> {
                hideView(mChildView)
                hideView(emptyView)
                hideView(errorView)
                showLoadingView()
            }
            ViewStatus.EMPTY_STATUS -> {
                hideView(mChildView)
                hideView(loadingView)
                hideView(errorView)
                showEmptyView()
            }
            ViewStatus.ERROR_STATUS -> {
                hideView(mChildView)
                hideView(loadingView)
                hideView(emptyView)
                showErrorView()
            }
            else -> {
                hideView(loadingView)
                hideView(emptyView)
                hideView(errorView)
                mChildView!!.visibility = VISIBLE
            }
        }
    }

    /**
     * 设置展示view (error,empty,loading)
     */
    fun showView(@VIEW_STATUS status: Int) {
        switchView(status)
    }

    /**
     * 获取view (error,empty,loading)
     */
    fun getView(@VIEW_STATUS status: Int): View? {
        when (status) {
            ViewStatus.EMPTY_STATUS -> return emptyView
            ViewStatus.LOADING_STATUS -> return loadingView
            ViewStatus.ERROR_STATUS -> return errorView
            ViewStatus.CONTENT_STATUS -> return mChildView
        }
        return null
    }

    fun autoRefresh() {
        createAnimatorTranslationY(
            State.REFRESH,
            0, head_height,
            object : CallBack {
                override fun onSuccess() {
                    isRefresh = true
                    refreshListener?.refresh()
                    mHeaderView!!.loading()
                }
            })
    }

    /**
     * 结束刷新
     */
    fun finishRefresh() {
        setFinish(State.REFRESH)
    }

    /**
     * 结束加载更多
     */
    fun finishLoadMore() {
        setFinish(State.LOADMORE)
    }

    /**
     * 设置是否启用加载更多
     */
    fun setCanLoadMore(canLoadMore: Boolean) {
        this.canLoadMore = canLoadMore
    }

    /**
     * 设置是否启用下拉刷新
     */
    fun setCanRefresh(canRefresh: Boolean) {
        this.canRefresh = canRefresh
    }

    /**
     * 设置是下拉刷新头部
     *
     * @param mHeaderView 需实现 HeadView 接口
     */
    fun setHeaderView(mHeaderView: HeadView?) {
        this.mHeaderView = mHeaderView
        addHeadView()
    }

    /**
     * 设置是下拉刷新尾部
     *
     * @param mFooterView 需实现 FooterView 接口
     */
    fun setFooterView(mFooterView: FooterView?) {
        this.mFooterView = mFooterView
        addFooterView()
    }

    /**
     * 设置刷新控件的高度
     *
     * @param dp 单位为dp
     */
    fun setHeadHeight(dp: Int) {
        head_height = DisplayUtil.dp2Px(context, dp)
    }

    /**
     * 设置加载更多控件的高度
     *
     * @param dp 单位为dp
     */
    fun setFootHeight(dp: Int) {
        foot_height = DisplayUtil.dp2Px(context, dp)
    }

    /**
     * 同时设置加载更多控件和刷新控件的高度
     *
     * @param dp 单位为dp
     */
    fun setAllHeight(dp: Int) {
        head_height = DisplayUtil.dp2Px(context, dp)
        foot_height = DisplayUtil.dp2Px(context, dp)
    }

    /**
     * 同时设置加载更多控件和刷新控件的高度
     *
     * @param refresh  刷新控件的高度 单位为dp
     * @param loadMore 加载控件的高度 单位为dp
     */
    fun setAllHeight(refresh: Int, loadMore: Int) {
        head_height = DisplayUtil.dp2Px(context, refresh)
        foot_height = DisplayUtil.dp2Px(context, loadMore)
    }

    /**
     * 设置刷新控件的下拉的最大高度 且必须大于本身控件的高度  最佳为2倍
     *
     * @param dp 单位为dp
     */
    fun setMaxHeadHeight(dp: Int) {
        if (head_height >= DisplayUtil.dp2Px(context, dp)) {
            return
        }
        head_height_2 = DisplayUtil.dp2Px(context, dp)
    }

    /**
     * 设置加载更多控件的上拉的最大高度 且必须大于本身控件的高度  最佳为2倍
     *
     * @param dp 单位为dp
     */
    fun setMaxFootHeight(dp: Int) {
        if (foot_height >= DisplayUtil.dp2Px(context, dp)) {
            return
        }
        foot_height_2 = DisplayUtil.dp2Px(context, dp)
    }

    /**
     * 同时设置加载更多控件和刷新控件的最大高度 且必须大于本身控件的高度  最佳为2倍
     *
     * @param dp 单位为dp
     */
    fun setAllMaxHeight(dp: Int) {
        if (head_height >= DisplayUtil.dp2Px(context, dp)) {
            return
        }
        if (foot_height >= DisplayUtil.dp2Px(context, dp)) {
            return
        }
        head_height_2 = DisplayUtil.dp2Px(context, dp)
        foot_height_2 = DisplayUtil.dp2Px(context, dp)
    }

    /**
     * 同时设置加载更多控件和刷新控件的最大高度 且必须大于本身控件的高度  最佳为2倍
     *
     * @param refresh  刷新控件下拉的最大高度 单位为dp
     * @param loadMore 加载控件上拉的最大高度 单位为dp
     */
    fun setAllMaxHeight(refresh: Int, loadMore: Int) {
        if (head_height >= DisplayUtil.dp2Px(context, refresh)) {
            return
        }
        if (foot_height >= DisplayUtil.dp2Px(context, loadMore)) {
            return
        }
        head_height_2 = DisplayUtil.dp2Px(context, refresh)
        foot_height_2 = DisplayUtil.dp2Px(context, loadMore)
    }

    companion object {
        private const val ANIM_TIME: Long = 300
        private const val HEAD_HEIGHT = 60
        private const val FOOT_HEIGHT = 60
        private var head_height = 0
        private var head_height_2 = 0
        private var foot_height = 0
        private var foot_height_2 = 0
    }

    init {
        val a =
            context.obtainStyledAttributes(attrs, R.styleable.PullToRefreshLayout, defStyleAttr, 0)
        error = a.getResourceId(R.styleable.PullToRefreshLayout_view_error, error)
        loading = a.getResourceId(R.styleable.PullToRefreshLayout_view_loading, loading)
        empty = a.getResourceId(R.styleable.PullToRefreshLayout_view_empty, empty)
        init()
    }
}
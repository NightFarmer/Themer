package com.nightfarmer.themer

import android.app.Activity
import android.app.Application
import android.app.Application.ActivityLifecycleCallbacks
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.TypedValue

/**
 * 主题切换工具
 * Created by zhangfan on 16-9-8.
 */
object Themer : ActivityLifecycleCallbacks {

    var CachedDataKey = "ThemerCachedDataKey"
        private set

    var activityList = arrayListOf<Activity>()
    var style = 0
        private set

    /**
     * 初始化
     * @param application app的application
     * @param style 初始的主题样式
     */
    fun init(application: Application, style: Int = 0) {
        application.registerActivityLifecycleCallbacks(this)
        this.style = style
    }

    /**
     * 切换主题
     * @param activity 调用此方法时处于活动的activity
     * @param styleRes 主题资源id
     * @param cacheData 需要缓存的临时数据
     */
    fun setTheme(activity: Activity, styleRes: Int, cacheData: Bundle?) {
        style = styleRes
        activityList
                .filterIndexed {
                    i, activity ->
                    i < activityList.size - 1
                }
                .forEach {
                    it.recreate()
                }
        val intent = Intent(activity, activity.javaClass)
        intent.putExtra(CachedDataKey, cacheData)
        activity.startActivity(intent)
        activity.overridePendingTransition(0, 0)
        activity.finish()
    }

    /**
     * 切换主题, 带有渐变效果
     * @param activity 调用此方法时处于活动的activity
     * @param styleRes 主题资源id
     * @param cacheData 需要缓存的临时数据
     */
    fun setThemeSoft(activity: Activity, styleRes: Int, cacheData: Bundle?) {
        style = styleRes
        activityList
                .filterIndexed {
                    i, activity ->
                    i < activityList.size - 1
                }
                .forEach {
                    it.recreate()
                }
        val intent = Intent(activity, activity.javaClass)
        intent.putExtra(CachedDataKey, cacheData)
        activity.startActivity(intent)
        activity.overridePendingTransition(R.animator.alpha_in, R.animator.alpha_out)
        activity.finish()
    }

    /**
     * 获取当前主题下的资源属性值,如color
     * @param context context
     * @param attrRes 资源在主题中的名称 如R.attr.colorPrimary
     */
    fun getColor(context: Context, attrRes: Int): Int {
        val typedValue = TypedValue()
        context.theme.resolveAttribute(attrRes, typedValue, true)
        return typedValue.data
    }

    /**
     * 获取当前主题下的资源的Id,如colorResId
     * @param context context
     * @param attrRes 资源在主题中的名称 如R.attr.colorPrimary
     */
    fun getColorRes(context: Context, attrRes: Int): Int {
        val typedValue = TypedValue()
        context.theme.resolveAttribute(attrRes, typedValue, true)
        return typedValue.resourceId
    }

    override fun onActivityCreated(activity: Activity?, bundle: Bundle?) {
        activity?.let {
            activityList.add(activity)
            activity.setTheme(style)
        }
    }

    override fun onActivityDestroyed(activity: Activity?) {
        activity?.let {
            activityList.remove(activity)
        }
    }


    override fun onActivityPaused(activity: Activity?) {
    }

    override fun onActivityStarted(activity: Activity?) {
    }

    override fun onActivitySaveInstanceState(activity: Activity?, bundle: Bundle?) {
    }

    override fun onActivityStopped(activity: Activity?) {
    }

    override fun onActivityResumed(activity: Activity?) {
    }
}
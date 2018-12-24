package com.tomeen.kazeik.tracesource

import android.app.Application
import com.uuzuche.lib_zxing.activity.ZXingLibrary


/**
 * @author kazeik chen
 *         QQ:77132995 email:kazeik@163.com
 *         2018 12 24 16:02
 * 类说明:
 */
class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        ZXingLibrary.initDisplayOpinion(this)
    }
}
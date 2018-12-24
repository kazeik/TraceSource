package com.tomeen.kazeik.tracesource

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.tbruyelle.rxpermissions2.RxPermissions
import com.uuzuche.lib_zxing.activity.CaptureActivity
import com.uuzuche.lib_zxing.activity.CodeUtils
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkPermission()

        tvScan.setOnClickListener {
            val intent = Intent(this, CaptureActivity::class.java)
            startActivityForResult(intent, 2001)
        }
    }
    @SuppressLint("CheckResult")
    private fun checkPermission() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            RxPermissions(this).request(
                Manifest.permission.READ_EXTERNAL_STORAGE
                , Manifest.permission.WRITE_EXTERNAL_STORAGE
                , Manifest.permission.CAMERA)
                .subscribe { permission ->
                    if (!permission) {
                        toast("请同意权限")
                        finish()
                    }
                }
        }
    }
    @SuppressLint("SetTextI18n")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data == null) return
        when (requestCode) {
            2001 -> {
                val bundle = data.extras ?: return
                val result = bundle.getString(CodeUtils.RESULT_STRING)
                toast(result)
            }
        }
    }
}

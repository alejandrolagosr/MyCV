package com.lagos.data.job

import com.lagos.domain.common.PostExecutionThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

class UIThread : PostExecutionThread {
    override fun getSchedule(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

}
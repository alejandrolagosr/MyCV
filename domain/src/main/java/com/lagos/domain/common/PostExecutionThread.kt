package com.lagos.domain.common

import io.reactivex.Scheduler

interface PostExecutionThread {
    fun getSchedule(): Scheduler
}
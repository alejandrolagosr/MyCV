package com.lagos.domain.common

class ImmediateThreadExecutor : ThreadExecutor {
    override fun execute(runnable: Runnable?) {
        runnable?.run()
    }
}
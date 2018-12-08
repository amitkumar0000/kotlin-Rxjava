package com.kotlin.rxjava.module

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ContextModule(val context: Context) {
    @Provides
    fun ProvidesContext():Context{
        return context
    }
}
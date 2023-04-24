package com.appsample.app.di

import android.content.Context
import com.appsample.common.di.BaseComponentHolder
import com.appsample.common.di.Component

interface AppComponent : Component {

    var context: Context
}

internal class AppComponentImpl : AppComponent {

    override lateinit var context: Context
}

object AppComponentHolder : BaseComponentHolder<AppComponent>() {

    override fun build(): AppComponent = AppComponentImpl()
}

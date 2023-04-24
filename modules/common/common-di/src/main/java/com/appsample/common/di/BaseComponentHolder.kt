package com.appsample.common.di

interface Component

abstract class BaseComponentHolder<Component> {

    private var component: Component? = null

    protected abstract fun build(): Component

    fun get(): Component {
        return component ?: build().also(::set)
    }

    fun set(component: Component) {
        this.component = component
    }

    fun clear() {
        component = null
    }
}

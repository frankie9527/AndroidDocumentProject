package android.kotlin.practice.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
internal annotation class ActivityScope

@Module
@InstallIn(ActivityComponent::class)
class BaseActivityModule {
    @ActivityScope
    @Provides
    fun provide(): String {
        return hashCode().toString()
    }
}
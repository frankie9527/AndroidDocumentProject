package android.kotlin.practice.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
internal annotation class FragmentScope

@Module
@InstallIn(FragmentComponent::class)
class BaseFragmentModule {
    @FragmentScope
    @Provides
    fun provide(): String {
        return hashCode().toString()
    }
}
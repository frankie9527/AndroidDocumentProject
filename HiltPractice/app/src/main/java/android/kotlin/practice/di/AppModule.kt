package android.kotlin.practice.di

import android.app.Application
import android.kotlin.practice.data.dao.UserDatabase
import android.kotlin.practice.data.dao.UserRepository
import android.kotlin.practice.data.dao.UserRepositoryImpl
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import javax.inject.Qualifier
import javax.inject.Singleton


@Qualifier
@Retention(AnnotationRetention.RUNTIME)
internal annotation class AppScope

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun provideUserDatabase(app: Application): UserDatabase {
        return Room.databaseBuilder(
            app,
            UserDatabase::class.java, "user"
        ).build();
    }

    @Provides
    @Singleton
    fun provideUserRepository(db: UserDatabase): UserRepository {
        return UserRepositoryImpl(db.userDao());
    }

    @AppScope
    @Provides
    fun provide(): String {
        return hashCode().toString()
    }
}
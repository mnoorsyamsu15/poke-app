package net.mnsam.pokeapp.repository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import net.mnsam.pokeapp.repository.list.ListRepository
import net.mnsam.pokeapp.repository.list.ListRepositoryImpl

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    fun provideListRepository(impl: ListRepositoryImpl): ListRepository = impl
}

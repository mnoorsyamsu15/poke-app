package net.mnsam.pokeapp.remote.monster

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import net.mnsam.pokeapp.remote.monster.service.MonsterApi
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(ViewModelComponent::class)
object ApiServiceModule {

    @Provides
    fun provideMonsterApi(retrofit: Retrofit): MonsterApi = retrofit.create()
}

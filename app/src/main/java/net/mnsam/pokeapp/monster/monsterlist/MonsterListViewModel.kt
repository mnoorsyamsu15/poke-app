package net.mnsam.pokeapp.monster.monsterlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import net.mnsam.pokeapp.repository.list.ListRepository
import net.mnsam.pokeapp.repository.list.MonsterDomain
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

@HiltViewModel
class MonsterListViewModel @Inject constructor(
    dispatcher: CoroutineContext,
    listRepository: ListRepository
) : ViewModel() {

    val monsterList: LiveData<State> get() = _monsterList
    private val _monsterList = MutableLiveData<State>()

    init {
        _monsterList.value = State.Loading
        viewModelScope.launch(dispatcher) {
            listRepository.getMonster(0, 1)
                .catch { e ->
                    _monsterList.postValue(State.Error(e.message ?: ""))
                }
                .collectLatest { result -> _monsterList.postValue(State.Loaded(result)) }
        }
    }

    sealed class State {
        class Loaded(val list: List<MonsterDomain>) : State()
        object Loading : State()
        class Error(val message: String) : State()
    }
}

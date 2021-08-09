package net.mnsam.pokeapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestCoroutineDispatcher
import net.mnsam.pokeapp.monster.monsterlist.MonsterListViewModel
import net.mnsam.pokeapp.repository.list.ListRepository
import net.mnsam.pokeapp.repository.list.MonsterDomain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals
import kotlin.test.assertIs

@RunWith(JUnit4::class)
class MonsterListViewModelTest {

    @get:Rule
    val instantTaskExecutor: TestRule = InstantTaskExecutorRule()

    @MockK(relaxed = true)
    private lateinit var listRepository: ListRepository

    @MockK(relaxed = true)
    private lateinit var monsterObserver: Observer<MonsterListViewModel.State>
    private lateinit var viewModel: MonsterListViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `get list from repository, when no error, viewmodel should dispatch loaded state`() {
        val captureState = slot<MonsterListViewModel.State>()
        // given fake monster list as a result
        val fakeMonsterList = listOf(
            MonsterDomain(
                name = "fake",
                spriteDefaultUrl = "fake uri",
                spriteShinyUrl = "fake uri",
                element = listOf("poison")
            )
        )
        // when get monster invoked
        every {
            listRepository.getMonster(any(), any())
        } returns flow<List<MonsterDomain>> {
            emit(fakeMonsterList)
        }
        viewModel = MonsterListViewModel(TestCoroutineDispatcher(), listRepository)
        viewModel.monsterList.observeForever(monsterObserver)
        // verify on changed is invoked exactly 1 time
        verify(exactly = 1) { monsterObserver.onChanged(capture(captureState)) }
        // verify captured state's type is MonsterListViewModel.State.Loaded
        assertIs<MonsterListViewModel.State.Loaded>(captureState.captured)
        // verify captured state fakeMonsterList instance from above
        val loadedState = captureState.captured as MonsterListViewModel.State.Loaded
        assertEquals(fakeMonsterList, loadedState.list)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `get list from repository, when throw error, viewmodel should dispatch error state`() {
        val captureState = slot<MonsterListViewModel.State>()
        // given fake error
        val fakeException = Exception("fake error")
        every {
            listRepository.getMonster(any(), any())
        } returns flow<List<MonsterDomain>> {
            throw fakeException
        }

        viewModel = MonsterListViewModel(TestCoroutineDispatcher(), listRepository)
        viewModel.monsterList.observeForever(monsterObserver)
        verify(exactly = 1) { monsterObserver.onChanged(capture(captureState)) }
        assertIs<MonsterListViewModel.State.Error>(captureState.captured)
        val errorState = captureState.captured as MonsterListViewModel.State.Error
        assertEquals(fakeException.message, errorState.message)
    }

    @After
    fun clear() {
        viewModel.monsterList.removeObserver(monsterObserver)
    }
}

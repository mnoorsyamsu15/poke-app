package net.mnsam.pokeapp.monster.monsterlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import net.mnsam.pokeapp.databinding.MonsterListFragmentBinding

@AndroidEntryPoint
class ListFragment : Fragment() {

    private var _binding: MonsterListFragmentBinding? = null
    private val binding: MonsterListFragmentBinding get() = _binding!!
    private val viewModel by viewModels<MonsterListViewModel>()
    private var monsterListAdapter: MonsterListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        monsterListAdapter = MonsterListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MonsterListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvMonster.layoutManager = LinearLayoutManager(requireContext())
        binding.rvMonster.adapter = monsterListAdapter
        binding.rvMonster.addItemDecoration(
            DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
        )
    }

    override fun onResume() {
        super.onResume()
        viewModel.monsterList.observe(viewLifecycleOwner) {
            if (it is MonsterListViewModel.State.Loaded) {
                binding.rvMonster.visibility = View.VISIBLE
                binding.cvProgress.visibility = View.GONE
                monsterListAdapter?.submitList(it.list)
            }
            if (it is MonsterListViewModel.State.Error) {
                Snackbar.make(binding.root, it.message, Snackbar.LENGTH_SHORT).show()
            }
            if (it is MonsterListViewModel.State.Loading) {
                binding.cvProgress.visibility = View.VISIBLE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.rvMonster.adapter = null
        _binding = null
    }

    companion object {
        const val TAG = "ListFragment"
    }
}

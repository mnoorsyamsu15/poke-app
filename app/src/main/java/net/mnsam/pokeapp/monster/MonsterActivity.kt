package net.mnsam.pokeapp.monster

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import net.mnsam.pokeapp.R
import net.mnsam.pokeapp.databinding.MonsterActivityBinding
import net.mnsam.pokeapp.monster.monsterlist.ListFragment

@AndroidEntryPoint
class MonsterActivity : AppCompatActivity() {

    private lateinit var binding: MonsterActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MonsterActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        changeFragment(ListFragment(), ListFragment.TAG)
    }

    fun changeFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
            .add(R.id.frame_layout, fragment, tag)
            .addToBackStack(ListFragment.TAG)
            .commitAllowingStateLoss()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount <= 1) {
            finish()
            return
        }
        super.onBackPressed()
    }
}

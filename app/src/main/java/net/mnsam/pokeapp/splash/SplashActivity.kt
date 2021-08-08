package net.mnsam.pokeapp.splash

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import net.mnsam.pokeapp.R
import net.mnsam.pokeapp.databinding.SplashActivityBinding
import net.mnsam.pokeapp.monster.MonsterActivity

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: SplashActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SplashActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Glide.with(this)
            .load(R.drawable.splash_banner)
            .into(binding.ivBanner)
        lifecycleScope.launchWhenResumed {
            delay(1_000L)
            withContext(Dispatchers.Main) {
                val intent = Intent(this@SplashActivity, MonsterActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
            }
        }
    }
}

package com.example.aplikacjagdynia

import CiekawostkiFragment
import android.R
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import com.example.aplikacjagdynia.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(binding.fragmentContainer.id, StartFragment())
                .commit()
        }
    }

    fun zdjecia(view: View){
        replaceFragment(ZdjeciaFragment())
    }

    fun ciekawostki(view: View){
        replaceFragment(CiekawostkiFragment())
    }

    fun komentarze(view: View){
        replaceFragment(KomentarzeFragment())
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            replace(binding.fragmentContainer.id, fragment)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }

}
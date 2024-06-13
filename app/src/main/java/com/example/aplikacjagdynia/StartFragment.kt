package com.example.aplikacjagdynia

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aplikacjagdynia.databinding.FragmentStartBinding

class StartFragment : Fragment() {
    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        val view = binding.root

        val sharedPref = activity?.getSharedPreferences("com.example.aplikacjagdynia.PREFERENCE_FILE_KEY", Context.MODE_PRIVATE)
        val commentCount = sharedPref?.getInt("comment_count", 0)

        binding.commentCountText.setText("Liczba komentarzy: $commentCount")

        return view
    }
}
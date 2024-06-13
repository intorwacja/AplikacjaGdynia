package com.example.aplikacjagdynia

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.aplikacjagdynia.databinding.FragmentKomentarzeBinding


class KomentarzeFragment : Fragment() {

    private var _binding: FragmentKomentarzeBinding? = null
    private val binding get() = _binding!!
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var adapter: ArrayAdapter<String>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentKomentarzeBinding.inflate(inflater, container, false)
        val view = binding.root

        dbHelper = DatabaseHelper(requireContext())

        val comments = dbHelper.getComments()

        adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, comments)
        binding.commentsList.adapter = adapter

        binding.addCommentButton.setOnClickListener {
            val comment = binding.inputComment.text.toString()
            if (comment.isNotEmpty()) {
                dbHelper.addComment(comment)
                adapter.clear()
                adapter.addAll(dbHelper.getComments())
                adapter.notifyDataSetChanged()
                binding.inputComment.text.clear()
            }
        }

        return view
    }

    private fun updateComments() {
        adapter.clear()
        adapter.addAll(dbHelper.getComments())
        adapter.notifyDataSetChanged()
        saveCommentCount()
    }

    private fun saveCommentCount() {
        val sharedPref = activity?.getSharedPreferences(
            "com.example.aplikacjagdynia.PREFERENCE_FILE_KEY",
            Context.MODE_PRIVATE
        )
        with(sharedPref?.edit()) {
            this?.putInt("comment_count", adapter.count)
            this?.apply()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
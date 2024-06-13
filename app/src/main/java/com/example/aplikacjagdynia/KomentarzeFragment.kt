package com.example.aplikacjagdynia

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
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

        adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, comments.map { it.text })
        binding.commentsList.adapter = adapter

        binding.addCommentButton.setOnClickListener {
            val comment = binding.inputComment.text.toString()
            if (comment.isNotEmpty()) {
                dbHelper.addComment(comment)
                updateComments()
                binding.inputComment.text.clear()
            }
        }

        binding.commentsList.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val comment = dbHelper.getComments()[position]
            val intent = CommentDetailActivity.newIntent(requireContext(), comment)
            startActivity(intent)
        }

        return view
    }

    private fun updateComments() {
        adapter.clear()
        adapter.addAll(dbHelper.getComments().map { it.text })
        adapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.example.aplikacjagdynia

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aplikacjagdynia.databinding.ActivityCommentDetailBinding

class CommentDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCommentDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommentDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val commentText = intent.getStringExtra(EXTRA_COMMENT_TEXT) ?: ""
        val commentDate = intent.getStringExtra(EXTRA_COMMENT_DATE) ?: ""

        binding.commentTextView.text = commentText
        binding.commentDateTextView.text = "Dodano: $commentDate"
    }

    companion object {
        private const val EXTRA_COMMENT_TEXT = "com.example.aplikacjagdynia.comment_text"
        private const val EXTRA_COMMENT_DATE = "com.example.aplikacjagdynia.comment_date"

        fun newIntent(context: Context, comment: Comment): Intent {
            return Intent(context, CommentDetailActivity::class.java).apply {
                putExtra(EXTRA_COMMENT_TEXT, comment.text)
                putExtra(EXTRA_COMMENT_DATE, comment.dateAdded)
            }
        }
    }
}
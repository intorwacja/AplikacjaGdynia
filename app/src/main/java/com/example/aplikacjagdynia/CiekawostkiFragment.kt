// CiekawostkiFragment.kt
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.BulletSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.aplikacjagdynia.R
import com.example.aplikacjagdynia.databinding.FragmentCiekawostkiBinding

class CiekawostkiFragment : Fragment() {
    private var _binding: FragmentCiekawostkiBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCiekawostkiBinding.inflate(inflater, container, false)
        val view = binding.root

        val ciekawostki = resources.getStringArray(R.array.ciekawostki_list)

        val spannableString = SpannableString(ciekawostki.joinToString("\n"))
        var start = 0
        for (ciekawostka in ciekawostki) {
            spannableString.setSpan(BulletSpan(15), start, start + ciekawostka.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            start += ciekawostka.length + 1
        }

        binding.bulletList.text = spannableString

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

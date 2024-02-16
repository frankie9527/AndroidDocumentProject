package android.kotlin.practice.ui

import android.kotlin.practice.R
import android.kotlin.practice.data.TestRepository
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
@AndroidEntryPoint
class BaseFragment : Fragment() {
    @Inject
    lateinit var testRepository: TestRepository;
    val viewModel: BaseFragmentViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_base, container, false)
        setupViews(view)
        return view
    }

    private fun setupViews(view: View) {
        val tv = view.findViewById<View>(R.id.text) as TextView;

        val same=testRepository==viewModel.testRepository;
        tv.setText("The repository is the same ="+same)
    }
}
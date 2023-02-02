import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.gamessearchapp.BaseFragment
import com.example.gamessearchapp.databinding.FragmentWelcomeeBinding

class WelcomeFragment : BaseFragment<FragmentWelcomeeBinding>(FragmentWelcomeeBinding::inflate) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onClickListeners()
    }

    fun onClickListeners() {
        binding.button.setOnClickListener {
            findNavController().navigate(WelcomeFragmentDirections.actionWelcomeFragmentToLoginnFragment())
        }
        binding.register.setOnClickListener {
            findNavController().navigate(WelcomeFragmentDirections.actionWelcomeFragmentToRegistrationnFragment())
        }
    }

}
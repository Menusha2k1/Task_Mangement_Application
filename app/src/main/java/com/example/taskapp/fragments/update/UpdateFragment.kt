import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.taskapp.R
import com.example.taskapp.models.User
import com.example.taskapp.viewmodel.UserViewModel

class UpdateFragment : Fragment() {

    lateinit var updateFirstName: EditText
    lateinit var updateLastName: EditText
    lateinit var updateAge: EditText
    lateinit var updateButton: Button
    private lateinit var mUserViewModel: UserViewModel
    private var user: User? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        updateFirstName = view.findViewById(R.id.updateFirstName_et)
        updateLastName = view.findViewById(R.id.updateLastName_et)
        updateAge = view.findViewById(R.id.updateAge_et)
        updateButton = view.findViewById(R.id.update_btn)

        // Retrieve the User object from the arguments
        user = arguments?.getSerializable("user") as User?

        // Populate the EditText fields with the current values of the User
        updateFirstName.setText(user?.firstname)
        updateLastName.setText(user?.lastName)
        updateAge.setText(user?.age.toString())

        updateButton.setOnClickListener {
            updateUser()
        }

        return view
    }

    private fun updateUser() {
        val firstName = updateFirstName.text.toString()
        val lastName = updateLastName.text.toString()
        val age = updateAge.text.toString().toInt()

        if (user != null) {
            // Update the User object with the new values
            user?.firstname = firstName
            user?.lastName = lastName
            user?.age = age

            // Update the user in the database
            mUserViewModel.updateUser(user!!)
        }
    }
}

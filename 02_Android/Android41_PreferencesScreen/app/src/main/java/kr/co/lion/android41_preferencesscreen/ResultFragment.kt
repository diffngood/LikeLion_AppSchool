package kr.co.lion.android41_preferencesscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.preference.PreferenceManager
import kr.co.lion.android41_preferencesscreen.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    lateinit var fragmentResultBinding: FragmentResultBinding
    lateinit var mainActivity: MainActivity
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        fragmentResultBinding = FragmentResultBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        fragmentResultBinding.apply {

            // PreferencesScreen 을 관리하는 Preference 객체를 가져온다.
            val pref = PreferenceManager.getDefaultSharedPreferences(mainActivity)
            // 저장된 데이터를 가져온다.
            // EditTextPreference
            val data1 = pref.getString("data1", null)
            // CheckBoxPreference
            val data2 = pref.getBoolean("data2", false)
            // SwitchPreference
            val data3 = pref.getBoolean("data3", false)

            textViewResult.apply {
                text = "data1 : ${data1}\n"
                append("data2 : ${data2}\n")
                append("data3 : ${data3}\n")
            }
        }

        return fragmentResultBinding.root
    }
}
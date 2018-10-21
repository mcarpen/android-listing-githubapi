package studio.beway.iimandroidproject

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_info.*

class InfoFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name = arguments!!.getString("name")
        val avatarUrl = arguments!!.getString("avatarUrl")
        val starsCount = arguments!!.getString("starsCount")
        val watchersCount = arguments!!.getString("forksCount")
        val issuesCount = arguments!!.getString("issuesCount")
        val language = arguments!!.getString("language")

        title.text = name
        starsText.text = starsCount
        forksText.text = watchersCount
        issuesText.text = issuesCount
        languageText.text = language

        Glide.with(this).load(avatarUrl).into(image)
    }

}
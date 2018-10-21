package studio.beway.iimandroidproject

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_commits.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CommitsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_commits, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val lm = LinearLayoutManager(view.context)
        commitsRecycler.layoutManager = lm
        commitsRecycler.addItemDecoration(DividerItemDecoration(view.context, lm.orientation))

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(API::class.java)

        val name = arguments!!.getString("name")
        val call = api.getCommits(name)

        call.enqueue(object : Callback<List<FullCommit>> {
            override fun onFailure(call: Call<List<FullCommit>>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<List<FullCommit>>, response: Response<List<FullCommit>>) {
                val commits = response.body()!!
                commitsRecycler.adapter = CommitsAdapter(commits)
            }
        })
    }
}
package studio.beway.iimandroidproject

import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_repo.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RepoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(API::class.java)

        val name = intent.getStringExtra("name")
        val call = api.getRepo(name)

        call.enqueue(object : Callback<FullRepo> {
            override fun onFailure(call: Call<FullRepo>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<FullRepo>, response: Response<FullRepo>) {
                val repo = response.body()!!

                val fragment = InfoFragment()
                val args = Bundle()
                args.putString("name", repo.name)
                args.putString("avatarUrl", repo.owner.avatar_url)
                args.putString("starsCount", repo.stargazers_count)
                args.putString("forksCount", repo.forks_count)
                args.putString("issuesCount", repo.open_issues_count)
                args.putString("language", repo.language)
                fragment.arguments = args
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame, fragment)
                    .commit()

                navigation.setOnNavigationItemSelectedListener { item ->
                    when (item.itemId) {
                        R.id.navigation_info -> {
                            val fragment = InfoFragment()
                            val args = Bundle()
                            args.putString("name", repo.name)
                            args.putString("avatarUrl", repo.owner.avatar_url)
                            args.putString("starsCount", repo.stargazers_count)
                            args.putString("forksCount", repo.forks_count)
                            args.putString("issuesCount", repo.open_issues_count)
                            args.putString("language", repo.language)
                            fragment.arguments = args
                            supportFragmentManager
                                .beginTransaction()
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                .replace(R.id.frame, fragment)
                                .commit()
                        }
                        R.id.navigation_commits -> {
                            val fragment = CommitsFragment()
                            val args = Bundle()
                            args.putString("name", repo.name)
                            fragment.arguments = args
                            supportFragmentManager
                                .beginTransaction()
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                .replace(R.id.frame, fragment)
                                .commit()
                        }
                    }
                    true
                }
            }
        })
    }
}

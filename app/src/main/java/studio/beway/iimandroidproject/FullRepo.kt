package studio.beway.iimandroidproject

data class FullRepo(
    val name: String,
    val owner: Owner,
    val stargazers_count: String,
    val forks_count: String,
    val open_issues_count: String,
    val language: String
)

data class Owner(
    val avatar_url: String
)
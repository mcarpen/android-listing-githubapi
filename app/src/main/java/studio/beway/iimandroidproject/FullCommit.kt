package studio.beway.iimandroidproject

data class FullCommit(
    val commit: Commit
)

data class Commit(
    val author: Author,
    val message: String
)

data class Author(
    val name: String
)
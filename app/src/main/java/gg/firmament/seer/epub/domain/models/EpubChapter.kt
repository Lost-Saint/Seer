package gg.firmament.seer.epub.domain.models

/**
 * Represents a chapter in an epub book.
 *
 * @param absPath The absolute path of the chapter.
 * @param title The title of the chapter.
 * @param body The body of the chapter.
 */
data class EpubChapter(
    val absPath: String,
    val title: String,
    val body: String
)
//{
//    init {
//        require(title.isNotBlank()) { "Title cannot be blank" }
//        require(body.isNotBlank()) { "Body cannot be blank" }
//    }
//}
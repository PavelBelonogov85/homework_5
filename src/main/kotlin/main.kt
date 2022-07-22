/* <<<<<<<<<<<<<<<<<<<<<<<<<<< ПЕРЕЧИСЛЕНИЯ: >>>>>>>>>>>>>>>>>>>>>>>>>>>>> */

enum class PostTypes {
    POST, COPY, REPLY, POSTPONE, SUGGEST
}

enum class DonutEditModes {
    ALL /*всю информацию о Donut*/,
    DURATION /*время, в течение которого запись будет доступна только платным подписчикам Donut*/
}

/* <<<<<<<<<<<<<<<<<<<<<<<<<<< Data-классы: >>>>>>>>>>>>>>>>>>>>>>>>>>>>> */

data class Copyright(
    val id: Int,
    val link: String,
    val name: String,
    val Type: String
)

data class Comment( /* это класс для ЕДИНИЧНОГО комментария (не очень понял смысл оригинальной архитектуры - у меня комментарий это текст с подписью, они объединяются в массив внутри поста) */
    val authorId: Int,
    val text: String
)

data class Donut(
    val isВonut: Boolean, /* запись доступна только платным подписчикам */
    val paidDuration: Int, /* время, в течение которого запись будет доступна только платным подписчикам */
    val placeholder: String, /* заглушка для пользователей, которые не оформили подписку. Отображается вместо содержимого записи. */
    val canPublishFreeCopy: Boolean, /* можно ли открыть запись для всех пользователей, а не только подписчиков Donut */
    val editMode: DonutEditModes /* информация о том, какие значения Donut можно изменить в записи */
)

data class Post(
    val id: Int=0, /* Идентификатор записи. */
    val ownerId: Int=0, /* Идентификатор владельца стены */
    val fromId: Int=0, /* Идентификатор автора записи (от чьего имени опубликована запись) */
    val createdBy: Int=0, /* Идентификатор администратора, который опубликовал запись */
    val date: Long=0, /* Время публикации записи в формате unixtime */
    val text: String="", /* Текст записи */
    val replyOwnerId: Int=0, /* Идентификатор владельца записи, в ответ на которую была оставлена текущая */
    val replyPostId: Int=0, /* Идентификатор записи, в ответ на которую была оставлена текущая */
    val friendsOnly: Boolean=false, /* опцией «Только для друзей» */
    var comments: Array<Comment> = emptyArray<Comment>(), /* массив с комментариями - УПРОЩЕННО */
    val copyright: Copyright, /* объект-источник материала - УПРОЩЕННО */
    val likes: Int = 0, /* количество лайков - УПРОЩЕННО */
    val reposts: Int = 0, /* количество репостов - УПРОЩЕННО */
    val views: Int = 0, /* кол-во просмотров - УПРОЩЕННО */
    val postType: PostTypes, /* Тип записи */
    val signerId: Int=0, /* Идентификатор автора, если запись была опубликована от имени сообщества и подписана пользователем; */
    val canPin: Boolean=false, /* Информация о том, может ли текущий пользователь закрепить запись */
    val canDelete: Boolean=false, /* Информация о том, может ли текущий пользователь удалить запись */
    val canEdit: Boolean=false, /* Информация о том, может ли текущий пользователь редактировать запись */
    val isPinned: Boolean=false, /* Информация о том, что запись закреплена. */
    val markedAsAds: Boolean=false, /* Информация о том, содержит ли запись отметку «реклама» */
    val isFavorite: Boolean=false, /* true, если объект добавлен в закладки у текущего пользователя. */
    val donut: Donut, /* Информация о записи Donut - УПРОЩЕННО */
    val postponedId: Int=0 /* Идентификатор отложенной записи. Это поле возвращается тогда, когда запись стояла на таймере */
) {

}

/* <<<<<<<<<<<<<<<<<<<<<<<<<<< СЕРВИСЫ: >>>>>>>>>>>>>>>>>>>>>>>>>>>>> */

object WallService {
    private var posts = emptyArray<Post>()
    private var currentId: Int = 0

    fun add(post: Post): Post { /* добавление нового поста с присвоением нового Id */
        currentId += 1
        posts += post.copy(id = currentId)
        return posts.last()
    }

    fun update(post: Post): Boolean { /* Изменение существующего поста, в случае успеха возвращает true */
        var result: Boolean = false;

        for ((index, postInArray) in posts.withIndex()) {
            if (postInArray.id == post.id) {
                posts[index] = post.copy(ownerId = postInArray.ownerId, date = postInArray.date)
                result = true
            }
        }
        return result
    }

    fun likeById(id: Int) {
        for ((index, post) in posts.withIndex()) {
            if (post.id == id) {
                posts[index] = post.copy(likes = post.likes + 1)
            }
        }
    }
}

/* <<<<<<<<<<<<<<<<<<<<<<<<<<< ФУНКЦИОНАЛ: >>>>>>>>>>>>>>>>>>>>>>>>>>>>> */

fun main() {

}
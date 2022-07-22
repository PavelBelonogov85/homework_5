/* <<<<<<<<<<<<<<<<<<<<<<<<<<< ������������: >>>>>>>>>>>>>>>>>>>>>>>>>>>>> */

enum class PostTypes {
    POST, COPY, REPLY, POSTPONE, SUGGEST
}

enum class DonutEditModes {
    ALL /*��� ���������� � Donut*/,
    DURATION /*�����, � ������� �������� ������ ����� �������� ������ ������� ����������� Donut*/
}

/* <<<<<<<<<<<<<<<<<<<<<<<<<<< Data-������: >>>>>>>>>>>>>>>>>>>>>>>>>>>>> */

data class Copyright(
    val id: Int,
    val link: String,
    val name: String,
    val Type: String
)

data class Comment( /* ��� ����� ��� ���������� ����������� (�� ����� ����� ����� ������������ ����������� - � ���� ����������� ��� ����� � ��������, ��� ������������ � ������ ������ �����) */
    val authorId: Int,
    val text: String
)

data class Donut(
    val is�onut: Boolean, /* ������ �������� ������ ������� ����������� */
    val paidDuration: Int, /* �����, � ������� �������� ������ ����� �������� ������ ������� ����������� */
    val placeholder: String, /* �������� ��� �������������, ������� �� �������� ��������. ������������ ������ ����������� ������. */
    val canPublishFreeCopy: Boolean, /* ����� �� ������� ������ ��� ���� �������������, � �� ������ ����������� Donut */
    val editMode: DonutEditModes /* ���������� � ���, ����� �������� Donut ����� �������� � ������ */
)

data class Post(
    val id: Int=0, /* ������������� ������. */
    val ownerId: Int=0, /* ������������� ��������� ����� */
    val fromId: Int=0, /* ������������� ������ ������ (�� ����� ����� ������������ ������) */
    val createdBy: Int=0, /* ������������� ��������������, ������� ����������� ������ */
    val date: Long=0, /* ����� ���������� ������ � ������� unixtime */
    val text: String="", /* ����� ������ */
    val replyOwnerId: Int=0, /* ������������� ��������� ������, � ����� �� ������� ���� ��������� ������� */
    val replyPostId: Int=0, /* ������������� ������, � ����� �� ������� ���� ��������� ������� */
    val friendsOnly: Boolean=false, /* ������ ������� ��� ������ */
    var comments: Array<Comment> = emptyArray<Comment>(), /* ������ � ������������� - ��������� */
    val copyright: Copyright, /* ������-�������� ��������� - ��������� */
    val likes: Int = 0, /* ���������� ������ - ��������� */
    val reposts: Int = 0, /* ���������� �������� - ��������� */
    val views: Int = 0, /* ���-�� ���������� - ��������� */
    val postType: PostTypes, /* ��� ������ */
    val signerId: Int=0, /* ������������� ������, ���� ������ ���� ������������ �� ����� ���������� � ��������� �������������; */
    val canPin: Boolean=false, /* ���������� � ���, ����� �� ������� ������������ ��������� ������ */
    val canDelete: Boolean=false, /* ���������� � ���, ����� �� ������� ������������ ������� ������ */
    val canEdit: Boolean=false, /* ���������� � ���, ����� �� ������� ������������ ������������� ������ */
    val isPinned: Boolean=false, /* ���������� � ���, ��� ������ ����������. */
    val markedAsAds: Boolean=false, /* ���������� � ���, �������� �� ������ ������� �������� */
    val isFavorite: Boolean=false, /* true, ���� ������ �������� � �������� � �������� ������������. */
    val donut: Donut, /* ���������� � ������ Donut - ��������� */
    val postponedId: Int=0 /* ������������� ���������� ������. ��� ���� ������������ �����, ����� ������ ������ �� ������� */
) {

}

/* <<<<<<<<<<<<<<<<<<<<<<<<<<< �������: >>>>>>>>>>>>>>>>>>>>>>>>>>>>> */

object WallService {
    private var posts = emptyArray<Post>()
    private var currentId: Int = 0

    fun add(post: Post): Post { /* ���������� ������ ����� � ����������� ������ Id */
        currentId += 1
        posts += post.copy(id = currentId)
        return posts.last()
    }

    fun update(post: Post): Boolean { /* ��������� ������������� �����, � ������ ������ ���������� true */
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

/* <<<<<<<<<<<<<<<<<<<<<<<<<<< ����������: >>>>>>>>>>>>>>>>>>>>>>>>>>>>> */

fun main() {

}
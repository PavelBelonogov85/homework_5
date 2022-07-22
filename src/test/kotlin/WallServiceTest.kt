import org.junit.Assert.*
import org.junit.Test
import java.time.LocalDateTime
import java.util.*


class WallServiceTest {

    @Test
    fun add() {
        val calendar = Calendar.getInstance()
        val timestamp = calendar.timeInMillis

        var newPost: Post = Post(
            ownerId = 112,
            date = timestamp,
            text = "�����-�� ����",
            copyright = Copyright(0, "", "", ""),
            postType = PostTypes.POST,
            donut = Donut(false, 0, "", false, DonutEditModes.ALL)
        )

        newPost = WallService.add(newPost)

        assertEquals(1, newPost.id)
    }

    @Test
    fun update_RealId() {

        /* ���������� ������ ��� ������ ��������� Post � id=1 */

        val calendar = Calendar.getInstance()
        val timestamp = calendar.timeInMillis

        var newPost: Post = Post(
            id = 1,
            ownerId = 112,
            date = timestamp,
            text = "����� �����, ����� �����!",
            copyright = Copyright(0, "", "", ""),
            postType = PostTypes.POST,
            donut = Donut(false, 0, "", false, DonutEditModes.ALL)
        )

        assertEquals(true, WallService.update(newPost))
    }

    @Test
    fun update_NotRealId() {

        /* ���������� ������ ��� ������ ��������� Post � id=1 */
        
        val calendar = Calendar.getInstance()
        val timestamp = calendar.timeInMillis

        var newPost: Post = Post(
            id = 999,
            ownerId = 112,
            date = timestamp,
            text = "����� �����, ����� �����!",
            copyright = Copyright(0, "", "", ""),
            postType = PostTypes.POST,
            donut = Donut(false, 0, "", false, DonutEditModes.ALL)
        )

        assertEquals(false, WallService.update(newPost))
    }
}
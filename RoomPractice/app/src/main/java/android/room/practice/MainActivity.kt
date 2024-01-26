package android.room.practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.room.practice.db.AppDatabase
import android.room.practice.db.User
import android.room.practice.db.UserDao
import android.view.View
import androidx.room.Room

class MainActivity : AppCompatActivity() {
    var dao :UserDao?=null;

    val user1 = User(1, "frankie", "13668232308");
    val user2 = User(2, "Tom", "17748784194");
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dao = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "user"
        ).build().userDao();
    }

    fun insert(view: View) {
        Thread {
            dao?.insertAll(user1, user2);
        }.start()
    }

    fun delete(view: View) {
        Thread {
            dao?.delete(user1);
        }.start()
    }

    fun getAll(view: View) {
        Thread {
            val list = dao?.getAll();
            list?.forEach { i ->
                println("user info $i")
            }
        }.start()
    }
}
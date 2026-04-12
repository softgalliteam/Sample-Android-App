package com.learning.exp.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Update
import com.learning.exp.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.random.Random

class RoomDbFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.chat_fragment, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val resultTV = view.findViewById<android.widget.TextView>(R.id.callFragmentTextView)
        val addUserTv = view.findViewById<android.widget.TextView>(R.id.addUserBtn)
        val getUsersTv = view.findViewById<android.widget.TextView>(R.id.getUsersBtn)
        val deleteTv = view.findViewById<android.widget.TextView>(R.id.deleteUserBtn)
        val updateBtn = view.findViewById<android.widget.TextView>(R.id.updateUserBtn)
        val empNameEt = view.findViewById<android.widget.EditText>(R.id.empNameEt)
        val empEmailEt = view.findViewById<android.widget.EditText>(R.id.empEmailEt)
        // Room Database related code can be added here in the future

        // @Entity : It is used to annotate a data class that represents a table in the database.
        // @Dao : Stands for Data Access Object. It is used to annotate an interface or abstract class that defines the methods for accessing the database.
        // @Database : It is used to annotate an abstract class that extends RoomDatabase and serves


        // Get the database instance
        val db = AppDatabase.getDatabase(requireActivity())
        val userDao = db.userDao()




        addUserTv.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                // Insert users into the database
                val newEmp = User(
                    Random.nextInt(1, 1000),//Generated random ID for the user
                    empNameEt.text.toString(), //Getting the user name from the EditText
                    empEmailEt.text.toString() //Getting the user email from the EditText
                )
                userDao.addUser(newEmp)

            }

            Toast.makeText(requireActivity(), "Users added to the database", Toast.LENGTH_SHORT)
                .show()
        }

        lateinit var usersList: List<User>

        getUsersTv.setOnClickListener {
            //Retrieving users from the database
            lifecycleScope.launch(Dispatchers.IO) {
                usersList = userDao.getAllUsers()
            }
            Toast.makeText(
                requireActivity(),
                "Users retrieved from the database",
                Toast.LENGTH_SHORT
            ).show()
            Handler().postDelayed({
                resultTV.text = "Users in the database:\n"
                usersList.forEach {
                    resultTV.append("ID: ${it.id}, Name: ${it.name}, Email: ${it.userEmail}\n")
                }
            }, 1000)
        }

        deleteTv.setOnClickListener {
            //Deleting user from the database
            lifecycleScope.launch(Dispatchers.IO) {
               userDao.deleteUser(usersList[0]) // Deleting the first user in the list for demonstration purposes
            }
            Toast.makeText(
                requireActivity(),
                "User deleted from the database",
                Toast.LENGTH_SHORT
            ).show()
        }
        updateBtn.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                userDao.updateUser(usersList[0].copy(name = "Manish Rathore", userEmail = "manish@gmail.com"))
            }
        }
    }


    @Entity(tableName = "users")
    data class User(
        @PrimaryKey val id: Int,
        @ColumnInfo(name = "userName") val name: String,
        val userEmail: String
    )


    @Dao
    interface UserDao {
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun addUser(user: User)

        @Query("SELECT * FROM users")
        fun getAllUsers(): List<User>


        @Update
        suspend fun updateUser(user: User)

        @Delete
        suspend fun deleteUser(user: User)
    }


    @Database(entities = [User::class], version = 1)
    abstract class AppDatabase : RoomDatabase() {
        abstract fun userDao(): UserDao

        companion object {
            @Volatile
            private var INSTANCE: AppDatabase? = null

            fun getDatabase(context: Context): AppDatabase {
                return INSTANCE ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "user_database"
                    ).build()
                    INSTANCE = instance
                    instance
                }
            }
        }
    }
}



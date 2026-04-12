package com.learning.exp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.learning.exp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(mBinding.root)

        mBinding.loginBtn.setOnClickListener {
            startActivity("Login")
        }
        mBinding.signUpBtn.setOnClickListener {
            startActivity("SignUp")
        }
        mBinding.listViewBtn.setOnClickListener {
            startActivity("List View")
        }
        mBinding.recyclerViewBtn.setOnClickListener {
            startActivity("Recycler View")
        }
        mBinding.whatsappBtn.setOnClickListener {
            startActivity("What's App")
        }
        mBinding.apiCallBtn.setOnClickListener {
            startActivity("API Call")
        }

    }

    fun startActivity(name: String) {
        when (name) {
            "Login" -> startActivity(Intent(this, LoginActivity::class.java))
            //"SignUp" -> startActivity(SignUpActivity.newIntent(this))
            "List View" -> startActivity(Intent(this, ListActivity::class.java))
            "Recycler View" -> startActivity(
                Intent(this, RecyclerViewActivity::class.java)
                    .putExtra("EXTRA_DATA", "Hello from MainActivity")
                    .putExtra("EXTRA_NUMBER", 123)
            )

            "What's App" -> startActivity(Intent(this, WhatsAppActivity::class.java))
            "API Call" -> startActivity(Intent(this, ApiCallActivity::class.java))
        }
    }
}


package com.sangmoki.fb_anonymous_login

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    // Firebase 인증 객체 생성
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Firebase 인증 객체 초기화
        auth = FirebaseAuth.getInstance()

        val loginBtn = findViewById<Button>(R.id.anonymousLoginBtn)
        loginBtn.setOnClickListener {

            // 익명 사용자 로그인 실행
            auth.signInAnonymously()
                .addOnCompleteListener(this) { task ->

                    // 로그인이 성공했을 때
                    if (task.isSuccessful) {
                        val user = auth.currentUser

                        // 로그인이 성공한 경우 사용자의 uid를 로그에 출력
                        Log.d("MainActivity", user!!.uid)

                    // 실패한 경우
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(
                            baseContext,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                }

        }
    }

}
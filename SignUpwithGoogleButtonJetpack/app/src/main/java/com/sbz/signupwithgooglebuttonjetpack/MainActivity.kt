package com.sbz.signupwithgooglebuttonjetpack

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.sbz.signupwithgooglebuttonjetpack.ui.theme.SignUpWithGoogleButtonJetpackTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SignUpWithGoogleButtonJetpackTheme {
                // A surface container using the 'background' color from the theme
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    GoogleButton(
                        text = "Sign Up With Google",
                        loadingText = "Account Creating...",
                        onClicked = {
                            Toast.
                            makeText(this@MainActivity, "Logging in.", Toast.LENGTH_SHORT)
                                .show()
                        }
                    )

                    GoogleButton(
                        text = "Login With Mail",
                        loadingText = "Please Weight",
                        icon = painterResource(id = R.drawable.ic_launcher_foreground),
                        onClicked = {
                            Toast.
                            makeText(this@MainActivity, "Logging in.", Toast.LENGTH_SHORT)
                                .show()
                        }
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SignUpWithGoogleButtonJetpackTheme {

    }
}
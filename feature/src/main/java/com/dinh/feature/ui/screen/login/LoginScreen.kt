package com.dinh.feature.ui.screen.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dinh.feature.R
import com.dinh.feature.navigation.AppDestination
import com.dinh.feature.ui.component.TitleScreen

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onNavigate: (AppDestination) -> Unit
) {
    LoginContent(onClick = {
        onNavigate.invoke(AppDestination.MainScreen)
    })
}

@Composable
fun LoginContent(onClick:() -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(Color(0xFF255A65))
            .fillMaxSize()
    ) {
        TitleScreen("Harvest Login")
        AppIcon()
        LoginForm(onClick)
    }
}

@Composable
fun AppIcon() {
    Box(
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.ic_app),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(200.dp)
        )
    }
}

@Composable
fun LoginForm(onClick:() -> Unit) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier.wrapContentSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.et_bg),
                contentDescription = null,
                modifier = Modifier.matchParentSize(),
                contentScale = ContentScale.FillBounds
            )

            BasicTextField(
                value = username,
                onValueChange = { username = it },
                textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
                singleLine = true,
                modifier = Modifier
                    .width(200.dp)
                    .height(35.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .wrapContentSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.et_bg),
                contentDescription = null,
                modifier = Modifier.matchParentSize(),
                contentScale = ContentScale.FillBounds
            )

            BasicTextField(
                value = password,
                onValueChange = { password = it },
                textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
                singleLine = true,
                modifier = Modifier
                    .width(200.dp)
                    .height(35.dp)
            )
        }

        ButtonList(onClick = {
            onClick.invoke()
        })
    }
}

@Composable
fun ButtonList(onClick:() -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        ImageButton(R.drawable.bt_login) {
            onClick.invoke()
        }
        Spacer(modifier = Modifier.height(16.dp))
        ImageButton(R.drawable.bt_login_google) {
            onClick.invoke()
        }
        Spacer(modifier = Modifier.height(16.dp))
        ImageButton(R.drawable.bt_login_apple) {
            onClick.invoke()
        }
    }
}

@Composable
fun ImageButton(
    backgroundRes: Int,
    onClick: () -> Unit
) {
    Image(
        painter = painterResource(id = backgroundRes),
        contentDescription = null,
        modifier = Modifier
            .clickable { onClick.invoke() }
            .size(200.dp, 35.dp),
        contentScale = ContentScale.Fit
    )
}

@Preview
@Composable
fun PreviewLoginScreen() {
    LoginContent({})
}
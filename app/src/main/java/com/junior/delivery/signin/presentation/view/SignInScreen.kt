package com.junior.delivery.signin.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.junior.delivery.R
import com.junior.delivery.core.routes.LocalNavController
import com.junior.delivery.core.routes.Routes
import com.junior.delivery.signin.presentation.view.composables.BasicTextField
import com.junior.delivery.signin.presentation.view.composables.CircularButton
import com.junior.delivery.signin.presentation.view.composables.PasswordTextField
import com.junior.delivery.signin.presentation.view.composables.RoundedButton
import com.junior.delivery.signin.presentation.viewmodel.SignInViewModel
import com.junior.delivery.ui.theme.SoftPurple
import com.junior.delivery.ui.theme.UltraPurple

@Composable
fun SignInScreen(navController: NavHostController = LocalNavController.current,
                 signInViewModel: SignInViewModel = hiltViewModel()) {
    val isLoading: Boolean by signInViewModel.isLoading.observeAsState(initial = false)

    if (isLoading) {
        Loading()
    } else {
        View(navController, signInViewModel)
    }
}

@Composable
fun Loading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
fun View(navController: NavHostController, signInViewModel: SignInViewModel) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(SoftPurple),
    ) {
        val (header, body, footer) = createRefs()
        val topGuide = createGuidelineFromTop(0.38f)

        Header(modifier = Modifier.constrainAs(header) {
            top.linkTo(parent.top)
        })
        Body(modifier = Modifier.constrainAs(body) {
            top.linkTo(topGuide)
        }, signInViewModel = signInViewModel,navController)
        Footer(modifier = Modifier.constrainAs(footer) {
            bottom.linkTo(parent.bottom)
        }, navController)
    }
}

@Composable
fun Header(modifier: Modifier) {
    Box(modifier.fillMaxWidth()) {
        ImageLogo(
            Modifier
                .align(Alignment.TopCenter)
                .padding(top = 50.dp))
    }

}

@Composable
fun ImageLogo(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.logo),
        contentScale = ContentScale.Crop,
        contentDescription = stringResource(id = R.string.app_name),
        modifier = modifier
            .size(170.dp)
            .clip(CircleShape)
    )
}

@Composable
fun Body(modifier: Modifier, signInViewModel: SignInViewModel, navController: NavHostController) {
    val emailOrPhone: String by signInViewModel.emailOrPhone.observeAsState(initial = "")
    val password: String by signInViewModel.password.observeAsState(initial = "")
    val isSignInButtonEnable: Boolean
            by signInViewModel.isSignInButtonEnable.observeAsState(initial = false)

    Column(modifier = modifier
        .fillMaxWidth()
        .padding(horizontal = 30.dp)) {
        PhoneOrEmailInputText(emailOrPhone) {
            signInViewModel.onSignInChanged(emailOrPhone = it, password = password)
        }
        Spacer(modifier = Modifier.size(16.dp))
        PasswordInputText(password) {
            signInViewModel.onSignInChanged(emailOrPhone = emailOrPhone, password = it)
        }
        Spacer(modifier = Modifier.size(16.dp))
        //ForgotPassword(Modifier.align(Alignment.End))
        Spacer(modifier = Modifier.size(16.dp))
        SignInButton(isSignInButtonEnable, signInViewModel,navController)
        Spacer(modifier = Modifier.size(32.dp))
        OauthButtons(signInViewModel = signInViewModel)
    }
}

@Composable
fun PhoneOrEmailInputText(value: String, onTextChanged: (String) -> Unit) {
    BasicTextField(
        value = value,
        placeholder = stringResource(id = R.string.user),
        label = stringResource(id = R.string.email_or_phone),
        onTextChanged = onTextChanged
    )
}

@Composable
fun PasswordInputText(password: String, onTextChanged: (String) -> Unit) {
    PasswordTextField(
        password = password,
        onTextChanged = onTextChanged
    )
}

@Composable
fun SignInButton(
    loginEnable: Boolean,
    signInViewModel: SignInViewModel,
    navController: NavHostController
) {
    RoundedButton(
        text = stringResource(id = R.string.login),
        //onClick = { signInViewModel.onSignInButtonCLicked() },
        onClick = {
            navController.navigate(Routes.HomeScreen.route)
        },
        //enabled = loginEnable
        enabled = true
    )
}

@Composable
fun OauthButtons(signInViewModel: SignInViewModel) {
    Row(horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxSize()) {
        CircularButton(
            icon = painterResource(id = R.drawable.ic_facebook),
            text = "Facebook",
            color = Color.Blue
        ) {}
        CircularButton(
            icon = painterResource(id = R.drawable.ic_google),
            text = "Google",
            color = Color.Red
        ) {}
        CircularButton(
            icon = painterResource(id = R.drawable.ic_github),
            text = "Github",
            color = Color.Black
        ) {}
    }
}

@Composable
fun Footer(modifier: Modifier, navController: NavHostController) {
    Column(modifier = modifier
        .fillMaxWidth()
        .padding(bottom = 20.dp)) {
        SignUpButton(navController)
    }
}

@Composable
fun SignUpButton(navController: NavHostController) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Text(
            text = stringResource(id = R.string.sign_up_question),
            fontSize = 16.sp,
            color = UltraPurple
        )
        Text(
            text = stringResource(id = R.string.sign_up),
            Modifier
                .padding(horizontal = 8.dp)
                .clickable { navController.navigate(Routes.SignUpScreen.route) },
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = UltraPurple,
        )
    }
}
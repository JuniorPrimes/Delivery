package com.junior.delivery.signup.presentation.view

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.junior.delivery.R
import com.junior.delivery.core.routes.LocalNavController
import com.junior.delivery.core.routes.Routes
import com.junior.delivery.signup.presentation.view.composables.BasicTextField
import com.junior.delivery.signup.presentation.view.composables.PasswordTextField
import com.junior.delivery.signup.presentation.view.composables.RoundedButton
import com.junior.delivery.ui.theme.SoftPurple
import com.junior.delivery.ui.theme.TitleTextStyle
import com.junior.delivery.ui.theme.UltraPurple

@Composable
fun SignUpScreen(navController: NavHostController = LocalNavController.current) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(SoftPurple)
    ) {
        val (header, body, footer) = createRefs()

        Header(modifier = Modifier.constrainAs(header) {
            top.linkTo(parent.top)
            bottom.linkTo(body.top)
        })
        Body(modifier = Modifier.constrainAs(body) {
            top.linkTo(header.bottom)
            bottom.linkTo(footer.top)
        }, navController)
        Footer(modifier = Modifier.constrainAs(footer) {
            top.linkTo(body.bottom)
            bottom.linkTo(parent.bottom)
        }, navController)

        createVerticalChain(header, body, footer, chainStyle = ChainStyle.SpreadInside)
    }
}

@Composable
fun Header(modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ImageLogo()
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = stringResource(id = R.string.motivational_phrase), style = TitleTextStyle)
    }
}

@Composable
fun ImageLogo() {
    Image(
        painter = painterResource(id = R.drawable.logo),
        contentScale = ContentScale.Crop,
        contentDescription = stringResource(id = R.string.app_name),
        modifier = Modifier
            .size(150.dp)
            .clip(CircleShape)
    )
}

@Composable
fun Body(modifier: Modifier, navController: NavHostController) {
    val emailOrPhone: String = ""
    val user: String = ""
    val fullName: String = ""
    val password: String = ""
    val isLoginEnable: Boolean = true

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        PhoneOrEmailTextField(emailOrPhone) { }
        UserTextField(user) { }
        FullNameTextField(fullName) { }
        KeyWordTextField(password) { }
        Spacer(modifier = Modifier.height(4.dp))
        SignUpButton(isLoginEnable,navController)
    }
}

@Composable
fun PhoneOrEmailTextField(value: String, onTextChanged: (String) -> Unit) {
    BasicTextField(
        value = value,
        placeholder = stringResource(id = R.string.user),
        label = stringResource(id = R.string.email_or_phone),
        onTextChanged = onTextChanged,
        imageVector = Icons.Filled.Email
    )
}

@Composable
fun UserTextField(value: String, onTextChanged: (String) -> Unit) {
    BasicTextField(
        value = value,
        placeholder = stringResource(id = R.string.username),
        label = stringResource(id = R.string.username),
        onTextChanged = onTextChanged,
        imageVector = Icons.Filled.AccountCircle
    )
}

@Composable
fun FullNameTextField(value: String, onTextChanged: (String) -> Unit) {
    BasicTextField(
        value = value,
        placeholder = stringResource(id = R.string.full_name),
        label = stringResource(id = R.string.name_and_surname),
        onTextChanged = onTextChanged,
        imageVector = Icons.Filled.Face
    )
}

@Composable
fun KeyWordTextField(password: String, onTextChanged: (String) -> Unit) {
    PasswordTextField(
        password = password,
        imageVector = Icons.Filled.Lock,
        onTextChanged = onTextChanged
    )
}

@Composable
fun SignUpButton(
    loginEnable: Boolean, /*signInViewModel: SignInViewModel*/
    navController: NavHostController
) {
    val context= LocalContext.current

    RoundedButton(
        text = stringResource(id = R.string.register),
        onClick = {
            Toast.makeText(context, "Te has registrado", Toast.LENGTH_SHORT).show()
            navController.navigate(Routes.SignInScreen.route) },
        enabled = loginEnable
    )
}

@Composable
fun Footer(modifier: Modifier, navController: NavHostController) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SignInButton(navController)
    }
}

@Composable
fun SignInButton(navController: NavHostController) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Text(
            text = stringResource(id = R.string.sign_in_question),
            fontSize = 16.sp,
            color = UltraPurple
        )
        Text(
            text = stringResource(id = R.string.sign_in),
            Modifier
                .padding(horizontal = 8.dp)
                .clickable { navController.navigate(Routes.SignInScreen.route) },
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = UltraPurple,
        )
    }
}
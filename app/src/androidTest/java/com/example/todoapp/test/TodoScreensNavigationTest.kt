package com.example.todoapp.test

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.testing.TestNavHostController
import org.junit.Rule

class TodoScreensNavigationTest {
}

@get:Rule
val composeTestRule = createAndroidComposeRule<ComponentActivity>()

private lateinit var navController: TestNavHostController


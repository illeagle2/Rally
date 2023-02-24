package com.example.compose.rally

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.example.compose.rally.ui.components.RallyTopAppBar
import com.example.compose.rally.ui.overview.OverviewBody
import com.example.compose.rally.ui.theme.RallyTheme
import org.junit.Rule
import org.junit.Test

class TopAppBarTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun rallyTopAppBarTest(){

        val allScreens = RallyScreen.values().toList()

        composeTestRule.setContent {
            RallyTheme {
                RallyTopAppBar(
                    allScreens = allScreens,
                    onTabSelected = { },
                    currentScreen = RallyScreen.Accounts
                )
            }
        }

        composeTestRule
            .onNodeWithContentDescription(RallyScreen.Accounts.name)
            .assertIsSelected()

        composeTestRule.onRoot().printToLog("currentLabelExists")
        composeTestRule
            .onNodeWithContentDescription(RallyScreen.Accounts.name)
            .assertExists()

        composeTestRule.onRoot(useUnmergedTree = true).printToLog("currentLabelExists")

        composeTestRule
            .onNode(
                hasText(RallyScreen.Accounts.name.uppercase()) and
                        hasParent(
                            hasContentDescription(RallyScreen.Accounts.name)
                        ),
                useUnmergedTree = true
            ).assertExists()
    }

    @Test
    fun overViewScreenTest() {

        composeTestRule.setContent {
            RallyTheme {
                OverviewBody()
            }
        }

        composeTestRule
            .onNodeWithText("Alerts")
            .assertIsDisplayed()
    }
}
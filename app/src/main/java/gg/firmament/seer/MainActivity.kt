package gg.firmament.seer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import gg.firmament.seer.ui.theme.SeerTheme

data class BottomNavigationItem(
	val title: String,
	val selectedIcon: ImageVector,
	val unselectedIcon: ImageVector,
	val hasNews: Boolean,
)

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			SeerTheme {
				val items = listOf(
					BottomNavigationItem(
						title = "Library",
						selectedIcon = Icons.Filled.Home,
						unselectedIcon = Icons.Outlined.Home,
						hasNews = false,
					),
					BottomNavigationItem(
						title = "Feed",
						selectedIcon = Icons.Filled.Email,
						unselectedIcon = Icons.Outlined.Email,
						hasNews = false,
					),
					BottomNavigationItem(
						title = "Settings",
						selectedIcon = Icons.Filled.Settings,
						unselectedIcon = Icons.Outlined.Settings,
						hasNews = true,
					),
				)
				var selectedItemIndex by rememberSaveable {
					mutableIntStateOf(0)
				}

				Scaffold(
					modifier = Modifier.fillMaxSize(),
					bottomBar = {
						NavigationBar {
							items.forEachIndexed { index, item ->
								NavigationRailItem(
									selected = selectedItemIndex == index,
									onClick = {
										selectedItemIndex = index
										// navController.navigate(item.title)
									},
									label = {
										Text(text = item.title)
									},
									alwaysShowLabel = false,
									icon = {
										BadgedBox(
											badge = {
												if (item.hasNews) {
													Badge()
												}
											},
										) {
											Icon(
												imageVector = if (index == selectedItemIndex) {
													item.selectedIcon
												} else item.unselectedIcon,
												contentDescription = item.title,
											)
										}
									},
									modifier = Modifier.weight(1F),
								)
							}
						}
					},
				) {
					innerPadding ->
					Greeting(name = "Saint", modifier = Modifier.padding(innerPadding))
				}
			}
		}
	}
}

@Composable
fun Greeting(
	name: String,
	modifier: Modifier = Modifier,
) {
	Text(text = "Hello $name!", modifier = modifier)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
	SeerTheme { Greeting("Saint") }
}

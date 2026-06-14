package com.example.lista6zad2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument

// 1. STRUKTURY DANYCH (klasy )
data class Task(val id: Int, val description: String, val maxPoints: Int)

data class AssignmentList(
    val id: String,
    val subject: String,
    val listNumber: Int,
    val grade: Double,
    val tasks: List<Task>
)

// 2. PRZYKlADOWE DANE (przepisane z zadanie)
val sampleAssignmentLists = listOf(
    AssignmentList("PUM1_L1", "Matematyka", 1, 4.5, listOf(
        Task(1, "Implementacja FizzBuzz", 3),
        Task(2, "Sprawdzenie palindromu", 3),
        Task(3, "Trójkąt Pascala", 4)
    )),
    AssignmentList("PUM1_L2", "Matematyka", 2, 5.0, listOf(
        Task(1, "Funkcje rozszerzające", 4),
        Task(2, "Funkcje wyższego rzędu", 6)
    )),
    AssignmentList("SO_L1", "PUM", 1, 3.5, listOf(
        Task(1, "Implementacja semafora", 5),
        Task(2, "Problem producenta-konsumenta", 5)
    )),
    AssignmentList("SO_L2", "Fizyka", 1, 4.0, listOf(
        Task(1, "Algorytmy szeregowania CPU", 6),
        Task(2, "Zarządzanie pamięcią", 4)
    ))
)

// 3. EKRANY NAWIGACJI (ważne!!!)
sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    object AssignmentLists : Screen("lists", "Listy zadań", Icons.Default.List)
    object GradesSummary : Screen("grades", "Oceny", Icons.Default.Star)
}

// 4. GŁÓWNA KLASA AKTYWNOŚCI (jeszcze ważniejsze!!!!!)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainApp()
                }
            }
        }
    }
}

// 5. GŁÓWNY KOMPONENT NAWIGACJI (ważniejsze od papieża)
@Composable
fun MainApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        bottomBar = {
            // Pokazuj dolny pasek tylko na głównych ekranach
            if (currentDestination?.route != "detail/{listId}") {
                NavigationBar {
                    val items = listOf(Screen.AssignmentLists, Screen.GradesSummary)
                    items.forEach { screen ->
                        NavigationBarItem(
                            icon = { Icon(screen.icon, contentDescription = screen.label) },
                            label = { Text(screen.label) },
                            selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.AssignmentLists.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            // E1 - Ekran List Zadań
            composable(Screen.AssignmentLists.route) {
                AssignmentListsScreen { listId ->
                    navController.navigate("detail/$listId")
                }
            }
            // E2 - Ekran Ocen
            composable(Screen.GradesSummary.route) {
                GradesSummaryScreen()
            }
            // E3 - Ekran Szczegółów Listy
            composable(
                route = "detail/{listId}",
                arguments = listOf(navArgument("listId") { type = NavType.StringType })
            ) { backStackEntry ->
                val listId = backStackEntry.arguments?.getString("listId")
                ListDetailScreen(listId)
            }
        }
    }
}

// 6. EKRAN 1: LISTY ZADAŃ (E1)
@Composable
fun AssignmentListsScreen(onListClick: (String) -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Moje Listy Zadań", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(sampleAssignmentLists) { list ->
                Card(
                    modifier = Modifier.fillMaxWidth().clickable { onListClick(list.id) },
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp).fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(list.subject, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                            Text("Liczba zadań: ${list.tasks.size}", fontSize = 14.sp)
                        }
                        Column(horizontalAlignment = Alignment.End) {
                            Text("Lista ${list.listNumber}", fontSize = 14.sp)
                            Text("Ocena: ${list.grade}", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}

// 7. EKRAN 2: OCeNY (E2)
@Composable
fun GradesSummaryScreen() {
    // Automatyczne grupowanie po przedmiotach i liczenie średniej
    val subjectSummaries = sampleAssignmentLists.groupBy { it.subject }
        .mapValues { entry ->
            val avg = entry.value.map { it.grade }.average()
            val count = entry.value.size
            Pair(avg, count)
        }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Moje Oceny", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            subjectSummaries.forEach { (subject, data) ->
                item {
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Row(
                            modifier = Modifier.padding(16.dp).fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(subject, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                                Text("Liczba list: ${data.second}", fontSize = 14.sp)
                            }
                            Text("Średnia: ${String.format("%.2f", data.first)}", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        }
                    }
                }
            }
        }
    }
}

// --- 8. EKRAN 3: SZCZEGÓŁY LISTY (E3) ---
@Composable
fun ListDetailScreen(listId: String?) {
    val assignmentList = sampleAssignmentLists.find { it.id == listId }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        if (assignmentList != null) {
            Text(assignmentList.subject, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text("Lista ${assignmentList.listNumber}", fontSize = 18.sp)
            Spacer(modifier = Modifier.height(24.dp))

            LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                items(assignmentList.tasks) { task ->
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Zadanie ${task.id}", fontWeight = FontWeight.Bold)
                            Text("pkt: ${task.maxPoints}")
                        }
                        Text(task.description, modifier = Modifier.padding(top = 4.dp))
                        HorizontalDivider(modifier = Modifier.padding(top = 8.dp))
                    }
                }
            }
        } else {
            Text("Nie znaleziono listy.")
        }
    }
}
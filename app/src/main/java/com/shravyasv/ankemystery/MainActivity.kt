package com.shravyasv.ankemystery
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.material3.OutlinedTextField
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.shravyasv.ankemystery.ui.theme.AnkeMysteryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnkeMysteryTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
@Composable
fun Greeting(modifier: Modifier = Modifier) {

    var difficulty by remember { mutableStateOf("") }
    var guess by remember { mutableStateOf("") }
    var secretNumber by remember { mutableStateOf(0) }
    var result by remember { mutableStateOf("") }
    var attempts by remember { mutableStateOf(0) }
    var history by remember { mutableStateOf(listOf<Int>()) }
    var hint by remember {
        mutableStateOf(
            if (secretNumber % 2 == 0)
                "💡 Hint: Even Number"
            else
                "💡 Hint: Odd Number"
        )
    }
      Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "🎮 AnkeMystery",
            fontSize = 30.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text("Choose Stage")

        Spacer(modifier = Modifier.height(20.dp))

          Button(onClick = {
              difficulty = "Easy (1-50)"
              secretNumber = (1..50).random()
              hint = if (secretNumber % 2 == 0)
                  "💡 Hint: Even Number"
              else
                  "💡 Hint: Odd Number"

              guess = ""
              result = ""
              attempts = 0
              history = emptyList()
          }) {
              Text("Easy")
          }

        Spacer(modifier = Modifier.height(10.dp))

          Button(onClick = {
              difficulty = "Medium (1-100)"
              secretNumber = (1..100).random()
              hint = if (secretNumber % 2 == 0)
                  "💡 Hint: Even Number"
              else
                  "💡 Hint: Odd Number"

              guess = ""
              result = ""
              attempts = 0
              history = emptyList()
          }) {
              Text("Medium")
          }

        Spacer(modifier = Modifier.height(10.dp))

          Button(onClick = {
              difficulty = "Hard (1-500)"
              secretNumber = (1..500).random()
              hint = if (secretNumber % 2 == 0)
                  "💡 Hint: Even Number"
              else
                  "💡 Hint: Odd Number"

              guess = ""
              result = ""
              attempts = 0
              history = emptyList()
          }) {
              Text("Hard")
          }

        Spacer(modifier = Modifier.height(20.dp))

        Text("Selected: $difficulty")
        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = guess,
            onValueChange = { guess = it },
            label = { Text("Guess and Enter a Number") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
          Spacer(modifier = Modifier.height(20.dp))

          Button(
              onClick = {
                  if (secretNumber == 0) {
                      result = "⚠ Please select a stage first!"
                      return@Button
                  }

                  val number = guess.toIntOrNull()

                  if (number == null) {
                      result = "❌ Enter numbers only"
                  } else {
                      attempts++
                      history = history + number

                      if (number == secretNumber) {
                          result = "🎉 Congratulations! You Got It🏆"
                      } else {
                          val diff = kotlin.math.abs(secretNumber - number)

                          result =
                              if (number < secretNumber)
                                  "⬇️ You are Low!"
                              else
                                  "⬆️ You are High!"

                          result += "\n"

                          result += when {
                              diff <= 3 -> "🔥 Very Close!"
                              diff <= 10 -> "😊 Close!"
                              diff <= 20 -> "🙂 Near But Not Close!"
                              else -> "❄️ Far Away!"
                          }
                      }
                  }
              }
          ) {
              Text("Guess")
          }
          Spacer(modifier = Modifier.height(20.dp))

          Text(hint)

          Spacer(modifier = Modifier.height(10.dp))

          Text(result)

          Spacer(modifier = Modifier.height(10.dp))

          Text("Attempts: $attempts")

          Text("History: $history")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AnkeMysteryTheme {
        Greeting(modifier = Modifier)
    }
}
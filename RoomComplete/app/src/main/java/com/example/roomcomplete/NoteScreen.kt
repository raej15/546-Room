package com.example.roomcomplete

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NoteScreen(viewModel: NoteViewModel) {
    val notes by viewModel.notes.observeAsState(emptyList())
    var input by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Spacer(Modifier.height(32.dp))

        OutlinedTextField(
            value = input,
            onValueChange = { input = it },
            label = { Text("New Note") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        Button(onClick = {
            if (input.isNotBlank()) {
                viewModel.addNote(input)
                input = ""
            }
        }) {
            Text("Add")
        }

        Spacer(Modifier.height(16.dp))

        LazyColumn {
            items(notes) { note ->
                Text(
                    text = note.text,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { viewModel.deleteNote(note) }
                        .padding(8.dp)
                )
            }
        }
    }
}

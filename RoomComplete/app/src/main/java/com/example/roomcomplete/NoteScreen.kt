package com.example.roomcomplete

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import java.text.DateFormat.getDateInstance
import java.util.Date
import java.util.Locale

@Composable
fun NoteScreen(viewModel: NoteViewModel) {
    val notes by viewModel.notes.observeAsState(emptyList())
    var titleInput by remember { mutableStateOf("") }
    var noteInput by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Spacer(Modifier.height(16.dp))

        // Title input field
        OutlinedTextField(
            value = titleInput,
            onValueChange = { titleInput = it },
            label = { Text("Note Title") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        // Note content input field
        OutlinedTextField(
            value = noteInput,
            onValueChange = { noteInput = it },
            label = { Text("Note Content") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = false,
            maxLines = 3
        )

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = {
                if (titleInput.isNotBlank() && noteInput.isNotBlank()) {
                    viewModel.addNote(
                        title = titleInput,
                        text = noteInput
                    )
                    titleInput = ""
                    noteInput = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Note")
        }

        Spacer(Modifier.height(16.dp))

        LazyColumn {
            items(notes) { note ->
                NoteItem(
                    note = note,
                    onDelete = { viewModel.deleteNote(note) }
                )
            }
        }
    }
}

@Composable
fun NoteItem(note: Note, onDelete: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onDelete() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFF9C4) // Light yellow background
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Note Title
            Text(
                text = note.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(Modifier.height(8.dp))

            // Note Content
            Text(
                text = note.text,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.DarkGray
            )

            Spacer(Modifier.height(8.dp))

            // Note Date (formatted)

        }
    }
}
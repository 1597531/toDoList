package com.example.todolist.ui.edit

import android.app.Application
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todolist.util.TimeFormatters

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoEditScreen(
    taskId: Long,
    onNavigateBack: () -> Unit,
) {
    val application = LocalContext.current.applicationContext as Application
    val vm: TodoEditViewModel = viewModel(
        factory = TodoEditViewModelFactory(application, taskId),
    )
    val state by vm.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (state.isNew) "新建事项" else "编辑事项") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "返回")
                    }
                },
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = state.title,
                onValueChange = vm::setTitle,
                modifier = Modifier.fillMaxWidth(),
                label = { Text("标题") },
                singleLine = false,
                minLines = 1,
            )
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(
                value = state.detail,
                onValueChange = vm::setDetail,
                modifier = Modifier.fillMaxWidth(),
                label = { Text("备注（可选）") },
                minLines = 3,
            )
            if (!state.isNew && state.loadedEntity != null) {
                Spacer(modifier = Modifier.height(16.dp))
                val e = state.loadedEntity!!
                Text(
                    text = "创建 ${TimeFormatters.formatMillis(e.createdAtMillis)}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                Text(
                    text = "上次更新 ${TimeFormatters.formatMillis(e.updatedAtMillis)}（保存后会刷新）",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = { vm.save(onNavigateBack) },
                modifier = Modifier.fillMaxWidth(),
                enabled = state.title.trim().isNotEmpty(),
            ) {
                Text("保存")
            }
            if (!state.isNew) {
                Spacer(modifier = Modifier.height(12.dp))
                OutlinedButton(
                    onClick = { vm.delete(onNavigateBack) },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text("删除")
                }
            }
        }
    }
}

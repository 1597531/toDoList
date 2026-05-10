package com.example.todolist.ui.edit

import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todolist.ui.theme.mikuFilledButtonColors
import com.example.todolist.ui.theme.mikuOutlinedPrimaryBorderStroke
import com.example.todolist.ui.theme.mikuOutlinedPrimaryButtonColors
import com.example.todolist.ui.theme.mikuOutlinedTextFieldColors
import com.example.todolist.ui.theme.mikuTopAppBarColors
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

    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.secondary.copy(alpha = 0.12f),
                            MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.42f),
                            MaterialTheme.colorScheme.background,
                        ),
                    ),
                ),
        )
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = Color.Transparent,
            topBar = {
                TopAppBar(
                    title = { Text(if (state.isNew) "新建事项" else "编辑事项") },
                    navigationIcon = {
                        IconButton(onClick = onNavigateBack) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "返回")
                        }
                    },
                    colors = mikuTopAppBarColors(),
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
                    colors = mikuOutlinedTextFieldColors(),
                    shape = MaterialTheme.shapes.medium,
                )
                Spacer(modifier = Modifier.height(12.dp))
                OutlinedTextField(
                    value = state.detail,
                    onValueChange = vm::setDetail,
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("备注（可选）") },
                    minLines = 3,
                    colors = mikuOutlinedTextFieldColors(),
                    shape = MaterialTheme.shapes.medium,
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
                    colors = mikuFilledButtonColors(),
                    shape = MaterialTheme.shapes.medium,
                ) {
                    Text("保存")
                }
                if (!state.isNew) {
                    Spacer(modifier = Modifier.height(12.dp))
                    OutlinedButton(
                        onClick = { vm.delete(onNavigateBack) },
                        modifier = Modifier.fillMaxWidth(),
                        colors = mikuOutlinedPrimaryButtonColors(),
                        shape = MaterialTheme.shapes.medium,
                        border = mikuOutlinedPrimaryBorderStroke(),
                    ) {
                        Text("删除")
                    }
                }
            }
        }
    }
}

package com.example.todolist.ui.edit

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.TodoRepository
import com.example.todolist.data.local.TodoDatabase
import com.example.todolist.data.local.TodoEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class TodoEditUiState(
    val title: String = "",
    val detail: String = "",
    val loadedEntity: TodoEntity? = null,
    val isNew: Boolean = true,
)

class TodoEditViewModel(
    application: Application,
    private val taskId: Long,
) : AndroidViewModel(application) {

    private val repository = TodoRepository(TodoDatabase.get(application).todoDao())

    private val _uiState = MutableStateFlow(TodoEditUiState(isNew = taskId == 0L))
    val uiState: StateFlow<TodoEditUiState> = _uiState.asStateFlow()

    init {
        if (taskId != 0L) {
            viewModelScope.launch {
                val entity = repository.getById(taskId)
                if (entity != null) {
                    _uiState.value = TodoEditUiState(
                        title = entity.title,
                        detail = entity.detail.orEmpty(),
                        loadedEntity = entity,
                        isNew = false,
                    )
                }
            }
        }
    }

    fun setTitle(value: String) {
        _uiState.value = _uiState.value.copy(title = value)
    }

    fun setDetail(value: String) {
        _uiState.value = _uiState.value.copy(detail = value)
    }

    fun save(onDone: () -> Unit) {
        viewModelScope.launch {
            val state = _uiState.value
            val title = state.title.trim()
            if (title.isEmpty()) return@launch

            if (state.isNew) {
                repository.insert(title, state.detail.ifBlank { null })
            } else {
                val entity = state.loadedEntity ?: return@launch
                repository.update(entity, title, state.detail.ifBlank { null })
            }
            onDone()
        }
    }

    fun delete(onDone: () -> Unit) {
        viewModelScope.launch {
            val entity = _uiState.value.loadedEntity ?: return@launch
            repository.delete(entity)
            onDone()
        }
    }
}

class TodoEditViewModelFactory(
    private val application: Application,
    private val taskId: Long,
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TodoEditViewModel(application, taskId) as T
    }
}

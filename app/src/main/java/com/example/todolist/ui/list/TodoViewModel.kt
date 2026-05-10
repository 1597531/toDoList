package com.example.todolist.ui.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.TodoRepository
import com.example.todolist.data.local.TodoDatabase
import com.example.todolist.data.local.TodoEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
class TodoViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = TodoRepository(TodoDatabase.get(application).todoDao())

    private val searchQuery = MutableStateFlow("")

    val searchQueryState: StateFlow<String> = searchQuery.asStateFlow()

    private val searchTrigger = merge(
        searchQuery.take(1),
        searchQuery.drop(1).debounce(250L),
    )

    val todos: StateFlow<List<TodoEntity>> = searchTrigger
        .flatMapLatest { q -> repository.observeTodos(q) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun setSearchQuery(value: String) {
        searchQuery.value = value
    }

    fun toggleComplete(item: TodoEntity) {
        viewModelScope.launch {
            repository.toggleComplete(item)
        }
    }

    fun delete(item: TodoEntity) {
        viewModelScope.launch {
            repository.delete(item)
        }
    }
}

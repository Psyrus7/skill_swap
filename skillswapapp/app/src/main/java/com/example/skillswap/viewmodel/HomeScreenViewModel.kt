package com.example.skillswap.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.skillswap.model.SkillUser
import com.example.skillswap.repo.UserRepository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
class HomeScreenViewModel(
    private val repository: UserRepository = UserRepository()
) : ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText: StateFlow<String> = _searchText

    private val _selectedNavIndex = MutableStateFlow(0)
    val selectedNavIndex: StateFlow<Int> = _selectedNavIndex

    private val _users = MutableStateFlow<List<SkillUser>>(emptyList())
    val users: StateFlow<List<SkillUser>> = _users

    // Reactive filtered list
    val filteredUsers: StateFlow<List<SkillUser>> = combine(_users, _searchText) { users, query ->
        val trimmedQuery = query.trim().lowercase()
        if (trimmedQuery.isEmpty()) users
        else {
            users.filter { user ->
                user.name.lowercase().contains(trimmedQuery) ||
                        user.teaches.any { it.lowercase().contains(trimmedQuery) } ||
                        user.wantsToLearn.any { it.lowercase().contains(trimmedQuery) }
            }
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun fetchUsersFromDatabase() {
        repository.fetchUsers { userList, error ->
            if (error == null) {
                _users.value = userList
            } else {
                println("Error fetching users: $error")
            }
        }
    }

    fun onSearchTextChange(value: String) {
        _searchText.value = value
    }

    fun onNavItemSelected(index: Int) {
        _selectedNavIndex.value = index
    }
}


//class HomeScreenViewModel : ViewModel() {
//
//    private val database = FirebaseDatabase.getInstance().getReference("users")
//
//    private val _searchText = MutableStateFlow("")
//    val searchText: StateFlow<String> = _searchText
//
//    private val _selectedNavIndex = MutableStateFlow(0)
//    val selectedNavIndex: StateFlow<Int> = _selectedNavIndex
//
//    private val _users = MutableStateFlow<List<SkillUser>>(emptyList())
//    val users: StateFlow<List<SkillUser>> = _users
//
//    // Reactive filtered list that updates the UI automatically
//    val filteredUsers: StateFlow<List<SkillUser>> = combine(_users, _searchText) { users, query ->
//        val trimmedQuery = query.trim().lowercase()
//        if (trimmedQuery.isEmpty()) users
//        else {
//            users.filter { user ->
//                user.name.lowercase().contains(trimmedQuery) ||
//                        user.teaches.any { it.lowercase().contains(trimmedQuery) } ||
//                        user.wantsToLearn.any { it.lowercase().contains(trimmedQuery) }
//            }
//        }
//    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
//
//    fun fetchUsersFromDatabase() {
//        database.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                val userList = mutableListOf<SkillUser>()
//                for (userSnapshot in snapshot.children) {
//                    val user = userSnapshot.getValue(SkillUser::class.java)
//                    user?.let { userList.add(it) }
//                }
//                _users.value = userList
//            }
//
//            override fun onCancelled(error: DatabaseError) {}
//        })
//    }
//
//    fun onSearchTextChange(value: String) {
//        _searchText.value = value
//    }
//
//    fun onNavItemSelected(index: Int) {
//        _selectedNavIndex.value = index
//    }
//}
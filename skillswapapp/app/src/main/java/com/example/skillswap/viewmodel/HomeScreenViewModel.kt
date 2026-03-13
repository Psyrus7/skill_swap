////
////package com.example.skillswap.viewmodel
////
////import androidx.lifecycle.ViewModel
////import com.example.skillswap.model.SkillUser
////import kotlinx.coroutines.flow.MutableStateFlow
////import kotlinx.coroutines.flow.StateFlow
////
////class HomeScreenViewModel : ViewModel() {
////
////    // SEARCH TEXT STATE
////    private val _searchText = MutableStateFlow("")
////    val searchText: StateFlow<String> = _searchText
////
////
////    // NAVIGATION BAR STATE
////    private val _selectedNavIndex = MutableStateFlow(0)
////    val selectedNavIndex: StateFlow<Int> = _selectedNavIndex
////
////
////    // USER LIST
////    private val _users = MutableStateFlow(
////        listOf(
////            SkillUser(
////                id = "1",
////                name = "Alex Thompson",
////                profileImage = "",
////                teaches = listOf("Guitar", "Music Theory"),
////                wantsToLearn = listOf("Photography", "Video Editing"),
////                rating = 5f
////            ),
////            SkillUser(
////                id = "2",
////                name = "Sophia Lee",
////                profileImage = "",
////                teaches = listOf("Spanish"),
////                wantsToLearn = listOf("Cooking"),
////                rating = 4f
////            ),
////            SkillUser(
////                id = "3",
////                name = "Daniel Scott",
////                profileImage = "",
////                teaches = listOf("Yoga"),
////                wantsToLearn = listOf("Guitar"),
////                rating = 5f
////            ),
////            SkillUser(
////                id = "4",
////                name = "Emma Watson",
////                profileImage = "",
////                teaches = listOf("Photography"),
////                wantsToLearn = listOf("Yoga"),
////                rating = 4f
////            )
////        )
////    )
////
////    val users: StateFlow<List<SkillUser>> = _users
////
////
////    // SEARCH CHANGE
////    fun onSearchTextChange(value: String) {
////        _searchText.value = value
////    }
////
////
//////     NAVIGATION ITEM CLICK
////    fun onNavItemSelected(index: Int) {
////        _selectedNavIndex.value = index
////    }
////
////
////    // FILTER USERS
////    fun filteredUsers(): List<SkillUser> {
////
////        val query = _searchText.value.trim().lowercase()
////
////        if (query.isEmpty()) return _users.value
////
////        return _users.value.filter { user ->
////            user.name.lowercase().contains(query) ||
////                    user.teaches.any { it.lowercase().contains(query) } ||
////                    user.wantsToLearn.any { it.lowercase().contains(query) }
////        }
////    }
////}
////
//
//
//package com.example.skillswap.viewmodel
//
//import androidx.lifecycle.ViewModel
//import com.example.skillswap.model.SkillUser
//import com.google.firebase.database.DataSnapshot
//import com.google.firebase.database.DatabaseError
//import com.google.firebase.database.FirebaseDatabase
//import com.google.firebase.database.ValueEventListener
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//
//class HomeScreenViewModel : ViewModel() {
//
//    private val database = FirebaseDatabase.getInstance().getReference("users")
//
//    // SEARCH TEXT STATE
//    private val _searchText = MutableStateFlow("")
//    val searchText: StateFlow<String> = _searchText
//
//    // NAVIGATION BAR STATE
//    private val _selectedNavIndex = MutableStateFlow(0)
//    val selectedNavIndex: StateFlow<Int> = _selectedNavIndex
//
//    // USER LIST - Initialized empty
//    private val _users = MutableStateFlow<List<SkillUser>>(emptyList())
//    val users: StateFlow<List<SkillUser>> = _users
//
//    // Fetch users from Firebase
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
//            override fun onCancelled(error: DatabaseError) {
//                // Handle error
//            }
//        })
//    }
//
//    // SEARCH CHANGE
//    fun onSearchTextChange(value: String) {
//        _searchText.value = value
//    }
//
//    // NAVIGATION ITEM CLICK
//    fun onNavItemSelected(index: Int) {
//        _selectedNavIndex.value = index
//    }
//
//    // FILTER USERS
//    fun filteredUsers(): List<SkillUser> {
//        val query = _searchText.value.trim().lowercase()
//        if (query.isEmpty()) return _users.value
//
//        return _users.value.filter { user ->
//            user.name.lowercase().contains(query) ||
//                    user.teaches.any { it.lowercase().contains(query) } ||
//                    user.wantsToLearn.any { it.lowercase().contains(query) }
//        }
//    }
//}



package com.example.skillswap.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.skillswap.model.SkillUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class HomeScreenViewModel : ViewModel() {

    private val database = FirebaseDatabase.getInstance().getReference("users")

    private val _searchText = MutableStateFlow("")
    val searchText: StateFlow<String> = _searchText

    private val _selectedNavIndex = MutableStateFlow(0)
    val selectedNavIndex: StateFlow<Int> = _selectedNavIndex

    private val _users = MutableStateFlow<List<SkillUser>>(emptyList())
    val users: StateFlow<List<SkillUser>> = _users

    // Reactive filtered list that updates the UI automatically
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
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val userList = mutableListOf<SkillUser>()
                for (userSnapshot in snapshot.children) {
                    val user = userSnapshot.getValue(SkillUser::class.java)
                    user?.let { userList.add(it) }
                }
                _users.value = userList
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }

    fun onSearchTextChange(value: String) {
        _searchText.value = value
    }

    fun onNavItemSelected(index: Int) {
        _selectedNavIndex.value = index
    }
}
package com.example.skillswap.repo

import com.example.skillswap.model.SkillUser
import com.google.firebase.database.*

class UserRepository(
    private val database: DatabaseReference = FirebaseDatabase.getInstance().getReference("users")
) {

    fun fetchUsers(onResult: (List<SkillUser>, String?) -> Unit) {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val userList = mutableListOf<SkillUser>()
                for (userSnapshot in snapshot.children) {
                    val user = userSnapshot.getValue(SkillUser::class.java)
                    user?.let { userList.add(it) }
                }
                onResult(userList, null)
            }

            override fun onCancelled(error: DatabaseError) {
                onResult(emptyList(), error.message)
            }
        })
    }
}

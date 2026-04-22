package com.dmitry.test.messenger.data

import com.google.firebase.Timestamp

data class Chat(
    val participants: List<String> = emptyList(),
    val lastMessage: String,
    val lastMessageTime: Timestamp? = null,
    val lastMessageSenderId: String
)

data class Message(
    val text: String = "",
    val senderId: String = "",
    val timestamp: Timestamp? = null,
    val type: String = "text",
    val status: String = "sent"
)

data class UserChat(
    val chatId: String = "",
    val otherUserId: String = "",
    val otherUserName: String = "",
    val lastMessage: String = "",
    val lastMessageTime: Timestamp? = null
)

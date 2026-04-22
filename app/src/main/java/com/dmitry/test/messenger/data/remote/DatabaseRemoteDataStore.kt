package com.dmitry.test.messenger.data.remote

import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class DatabaseRemoteDataStore @Inject constructor(
    private val firestore: FirebaseFirestore
){
    suspend fun createChatIfNotExists(
        currentUserId: String,
        otherUserId: String,
        currentUserName: String,
        otherUserName: String
    ): String {

        val chatId = generateChatId(currentUserId, otherUserId)
        val chatRef = firestore.collection("chats").document(chatId)

        val snapshot = chatRef.get().await()

        if(!snapshot.exists()){

            val chat = hashMapOf(
                "participants" to listOf(currentUserId, otherUserId),
                "lastMessage" to "",
                "lastMessageTime" to FieldValue.serverTimestamp(),
                "lastMessageSenderId" to ""
            )

            chatRef.set(chat).await()

            createUserChat(currentUserId, chatId, otherUserId, otherUserName)
            createUserChat(otherUserId, chatId, currentUserId, currentUserName)
        }

        return chatId
    }

    fun generateChatId(user1: String, user2: String): String {
        return listOf(user1, user2).sorted().joinToString("_")
    }

    suspend fun createUserChat(
        userId: String,
        chatId: String,
        otherUserId: String,
        otherUserName: String
    ) {
        val data = hashMapOf(
            "chatId" to chatId,
            "otherUserId" to otherUserId,
            "otherUserName" to otherUserName,
            "lastMessage" to "",
            "lastMessageTime" to FieldValue.serverTimestamp()
        )

        firestore.collection("userChats")
            .document(userId)
            .collection("chats")
            .document(chatId)
            .set(data)
            .await()
    }

    suspend fun sendMessage(
        chatId: String,
        text: String,
        senderId: String,
        receiverId: String
    ) {
        val message = hashMapOf(
            "text" to text,
            "senderId" to senderId,
            "timestamp" to FieldValue.serverTimestamp()
        )

        val chatRef = firestore.collection("chats").document(chatId)

        chatRef.collection("messages").add(message).await()

        chatRef.update(
            mapOf(
                "lastMessage" to text,
                "lastMessageTime" to FieldValue.serverTimestamp(),
                "lastMessageSenderId" to senderId
            )
        )


    }

    suspend fun updateUserChat(
        userId: String,
        chatId: String,
        lastMessage: String
    ) {
        firestore.collection("userChats")
            .document(userId)
            .collection("chats")
            .document(chatId)
            .update(
                mapOf(
                    "lastMessage" to lastMessage,
                    "lastMessageTime" to FieldValue.serverTimestamp()
                )
            )
    }
}
# 🚀 TCP Socket Chat Server (Java)

A lightweight, real-time **multi-client chat server** built using **pure Java TCP sockets** (no HTTP, no database, no frameworks).  
This project demonstrates strong backend fundamentals such as **network programming, concurrency, protocol design, and thread safety**.

> ✅ Interview-ready backend assignment showcasing low-level system design skills.

---

## 📌 Features

- Pure **TCP socket-based** communication
- Supports **multiple concurrent clients**
- Username-based **LOGIN flow**
- **Real-time message broadcasting**
- Graceful **client disconnect handling**
- Clean, text-based custom protocol
- Bonus commands implemented:
  - `WHO` – list active users
  - `DM` – private messaging
  - `PING / PONG` – heartbeat support

---

## 🧠 Architecture Overview

```
ChatServer
   |
   |-- accepts socket connections
   |
ClientHandler (one thread per client)
   |
   |-- ConcurrentHashMap<String, ClientHandler>
```

### Design Decisions
- **Thread-per-client model** for clarity and simplicity
- **ConcurrentHashMap** for thread-safe user management
- No external dependencies — Java Standard Library only

---

## 🛠️ Tech Stack

- **Language:** Java 21
- **Networking:** `ServerSocket`, `Socket`
- **Concurrency:** Java Threads
- **Build Tool:** Maven
- **Libraries:** Java Standard Library

---

## 📂 Project Structure

```
src/main/java/com/example/demo/chat
│
├── ChatServer.java     # TCP socket server (port 4000)
├── ClientHandler.java  # Handles each connected client
├── ChatClient.java     # Simple client for testing
```

---

## ▶️ How to Run

### 1️⃣ Start the Server

Run `ChatServer.java` from your IDE or terminal.

Expected output:
```
Chat server starting on port 4000
```

⚠️ Keep the server running before starting clients.

---

### 2️⃣ Start Clients

Run `ChatClient.java` **multiple times**.  
Each run represents a **separate user**.

Example:
```
LOGIN Nischay
OK
MSG Hello everyone
```

Second client:
```
LOGIN Rahul
OK
MSG Hi Nischay
```

---

## 🧾 Supported Commands

### Login
```
LOGIN <username>
```

### Send Message
```
MSG <text>
```

### List Active Users
```
WHO
```

### Private Message
```
DM <username> <text>
```

### Heartbeat
```
PING
```
Server responds:
```
PONG
```

---

## 📺 Example Interaction

Client 1:
```
LOGIN Nischay
OK
MSG Hi everyone
```

Client 2:
```
LOGIN Rahul
OK
MSG Hello!
```

Client 1 sees:
```
MSG Rahul Hello!
```

Client 2 sees:
```
MSG Nischay Hi everyone
```

When a client disconnects:
```
INFO Nischay disconnected
```

---

## 🧪 Testing

- Tested with multiple concurrent clients
- Handles abrupt client termination gracefully
- No shared-state race conditions

---

## 🎥 Demo Video

A short screen recording (1–2 minutes) demonstrating:
- Server startup
- Multiple clients connecting
- Real-time messaging
- Disconnect handling

📎 *(Attach video link here)*

---

## 🏆 Why This Project

This project highlights:
- Strong understanding of **networking fundamentals**
- Practical **concurrency handling**
- Clean and readable **production-style Java code**
- Ability to design **custom communication protocols**

> Built intentionally without Spring or HTTP to showcase core backend engineering skills.

---

## 🔮 Possible Enhancements

- Idle client timeout
- Java NIO (non-blocking) version
- TLS-secured sockets
- Dockerized deployment

---

## 👤 Author

**Nischay**  
Backend Developer (Java)


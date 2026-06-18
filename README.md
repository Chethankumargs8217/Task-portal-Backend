 Task Portal - AI Powered Task Management System

 Project Overview

Task Portal is a full-stack AI-powered task management application that enables users to create, manage, update, and track tasks efficiently. The application uses JWT-based authentication for secure access and integrates Google Gemini AI to automatically generate professional task descriptions.

# Tech Stack Used

## Frontend

* React.js
* Vite
* Axios
* React Router DOM
* Tailwind CSS

## Backend

* Java 17
* Spring Boot
* Spring Security
* Spring Data JPA
* Hibernate
* Maven

## Database

* MySQL

## AI Integration

* Google Gemini API

---

# Architecture Overview

The application follows a Client-Server Architecture.

Frontend (React + Vite)

↓

REST API Communication

↓

Spring Boot Backend

↓

MySQL Database

↓

Google Gemini AI API

### Flow

1. User interacts with the React frontend.
2. Frontend sends requests to Spring Boot REST APIs.
3. Spring Boot processes requests and stores data in MySQL.
4. JWT authentication secures protected endpoints.
5. Gemini AI generates task descriptions when requested.
6. Responses are returned to the frontend and displayed to users.

---

# Setup Instructions

## Backend Setup

### 1. Clone Repository

```bash
git clone <repository-url>
```

### 2. Configure MySQL Database

Create a database:

```sql
CREATE DATABASE taskportal;
```

### 3. Update application.properties

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/taskportal
spring.datasource.username=root
spring.datasource.password=your password

jwt.secret=YOUR_SECRET_KEY
jwt.expiration=86400000

gemini.api.key=YOUR_GEMINI_API_KEY
```

### 4. Run Backend

```bash
mvn spring-boot:run
```

Backend runs on:

```text
http://localhost:8080
```

---

## Frontend Setup

### 1. Navigate to Frontend

```bash
cd taskportal-frontend
```

### 2. Install Dependencies

```bash
npm install
```

### 3. Run Frontend

```bash
npm run dev
```

Frontend runs on:

```text
http://localhost:5173
```

---

# API Endpoints

## Authentication APIs

### Register User

```http
POST /api/auth/register
```

### Login User

```http
POST /api/auth/login
```

---

## Task APIs

### Get All Tasks

```http
GET /api/tasks
```

### Create Task

```http
POST /api/tasks
```

### Update Task

```http
PUT /api/tasks/{id}
```

### Delete Task

```http
DELETE /api/tasks/{id}
```

---

## AI API

### Generate Task Description

```http
GET /api/ai/generate?title={taskTitle}
```

---

# AI Integration Explanation

The application integrates Google Gemini AI to automatically generate task descriptions.

### Working Process

1. User enters a task title.
2. Frontend sends the title to:

```http
GET /api/ai/generate
```

3. Spring Boot calls the Gemini API using the configured API key.
4. Gemini generates a professional task description.
5. The generated description is returned to the frontend.
6. The description is automatically populated into the task form.

### Example

Input:

```text
Prepare Client Presentation
```

Output:

```text
Prepare a professional presentation covering project objectives, milestones, timelines, deliverables, and recommendations for stakeholders.
```

---

# Screenshots

## Login Page
<img width="1919" height="996" alt="Screenshot 2026-06-17 144346" src="https://github.com/user-attachments/assets/3d3c7f50-fd4d-4bd7-9e5c-6fa70595ed7f" />



## Registration Page

<img width="1919" height="985" alt="Screenshot 2026-06-17 144354" src="https://github.com/user-attachments/assets/a3406d16-48fa-46dd-b28e-42c73816ea52" />


## Dashboard
<img width="1919" height="983" alt="Screenshot 2026-06-17 144431" src="https://github.com/user-attachments/assets/1bef139a-f5e0-451e-b032-2822a0711c36" />


## Create Task

<img width="1919" height="993" alt="Screenshot 2026-06-17 144440" src="https://github.com/user-attachments/assets/545d5f01-008f-405f-99a9-55293bf233fb" />


## AI Generated Description

<img width="1919" height="994" alt="Screenshot 2026-06-17 144519" src="https://github.com/user-attachments/assets/b04b34aa-1cf4-4406-aad6-a508114308a5" />


## After adding Task 
<img width="1917" height="994" alt="Screenshot 2026-06-17 144529" src="https://github.com/user-attachments/assets/da2987cd-aa78-408e-8e0e-fedee9a67401" />

# Developer
Chethan Kumar


# TaskPortal — AI-Powered Task Management Portal

A full-stack task management application built with Spring Boot, React, and Google Gemini AI.

![TaskPortal](https://img.shields.io/badge/Status-Live-brightgreen) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.3-green) ![React](https://img.shields.io/badge/React-18-blue) ![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Neon-blue)

---



| | URL |
|---|---|
|  Frontend | https://taskportal-frontend.vercel.app |
|  Backend API | https://task-portal-backend-b42u.onrender.com |
|  Swagger Docs | https://task-portal-backend-b42u.onrender.com/swagger-ui/index.html |

>  Backend is hosted on Render free tier — first request after inactivity may take 30-60 seconds to wake up.

---

## Screenshots


## Login Page
<img width="1919" height="996" alt="Screenshot 2026-06-17 144346" src="https://github.com/user-attachments/assets/94d435a0-17e1-457a-927f-cbbba80f68d6" />



## Registration Page

<img width="1919" height="985" alt="Screenshot 2026-06-17 144354" src="https://github.com/user-attachments/assets/b6736bd9-98e2-4f1b-8fb6-5ccf4432b6db" />


## Dashboard
<img width="1919" height="983" alt="Screenshot 2026-06-17 144431" src="https://github.com/user-attachments/assets/80422fb6-b202-43c4-91b3-26bbe2bf7350" />


## Create Task

<img width="1919" height="993" alt="Screenshot 2026-06-17 144440" src="https://github.com/user-attachments/assets/a4783762-39b6-4251-998d-9f6a9e1f57f5" />


## AI Generated Description

<img width="1919" height="994" alt="Screenshot 2026-06-17 144519" src="https://github.com/user-attachments/assets/023c084f-1169-4cc8-b976-9bb5f905b9fe" />



## After adding Task 
<img width="1917" height="994" alt="Screenshot 2026-06-17 144529" src="https://github.com/user-attachments/assets/8393e138-300e-4d40-b559-e0dd6c502000" />


---

##  Tech Stack

### Backend
| Technology | Version | Purpose |
|---|---|---|
| Spring Boot | 3.5.3 | REST API framework |
| Spring Security | 6.x | Authentication & authorization |
| JWT (jjwt) | 0.11.5 | Token-based auth |
| Spring Data JPA | 3.x | Database ORM |
| PostgreSQL | 18.x | Production database |
| MySQL | 8.x | Local development database |
| Lombok | Latest | Boilerplate reduction |
| Springdoc OpenAPI | 2.8.9 | Swagger documentation |
| Google Gemini API | 2.5 Flash | AI task description generation |

### Frontend
| Technology | Version | Purpose |
|---|---|---|
| React | 18.x | UI framework |
| Vite | 5.x | Build tool |
| Tailwind CSS | 3.x | Styling |
| React Router DOM | 6.x | Client-side routing |
| Axios | 1.x | HTTP client |
| Lucide React | 0.383 | Icons |
| date-fns | 3.x | Date formatting |

### Deployment
| Service | Purpose |
|---|---|
| Render | Backend hosting (Docker) |
| Vercel | Frontend hosting |
| Neon | PostgreSQL cloud database |
| GitHub | Source control |

---
## Architecture Overview

```
┌─────────────────────┐         ┌──────────────────────┐
│   React Frontend    │  HTTPS  │  Spring Boot Backend  │
│   (Vercel)          │────────▶│  (Render - Docker)    │
│                     │         │                        │
│  - Login/Register   │         │  - REST APIs           │
│  - Kanban Board     │         │  - JWT Auth            │
│  - AI Generate      │         │  - Business Logic      │
└─────────────────────┘         └──────────┬─────────────┘
                                            │
                          ┌─────────────────┴──────────────┐
                          │                                 │
               ┌──────────▼──────────┐      ┌──────────────▼──────┐
               │  PostgreSQL (Neon)   │      │  Google Gemini API   │
               │  - users table       │      │  - AI descriptions   │
               │  - tasks table       │      │  - Priority hints    │
               └─────────────────────┘      └─────────────────────┘
```

---

##  Project Structure

### Backend
```
taskportal-skeleton/
├── src/main/java/com/chethan/taskportal/
│   ├── config/          # CORS, Security, Swagger config
│   ├── controller/      # AuthController, TaskController, AIController
│   ├── Dto/             # Request/Response DTOs
│   ├── entity/          # User, Task entities
│   ├── globalException/ # Exception handlers
│   ├── Mapper/          # TaskMapper
│   ├── repository/      # JPA repositories
│   ├── Security/        # JWT filter, UserDetailsService
│   ├── service/         # Service interfaces & implementations
│   └── Util/            # JwtUtil
├── src/main/resources/
│   └── application.properties
├── Dockerfile
└── pom.xml
```

### Frontend
```
taskportal-frontend/
├── src/
│   ├── components/      # Navbar, TaskCard, TaskModal, StatsBar, Toast, DeleteConfirm
│   ├── context/         # AuthContext (JWT state management)
│   ├── pages/           # LoginPage, RegisterPage, DashboardPage
│   ├── services/        # api.js (Axios client)
│   ├── App.jsx          # Routes
│   ├── main.jsx         # Entry point
│   └── index.css        # Tailwind + custom styles
├── vercel.json          # Vercel routing config
├── vite.config.js
└── package.json
```

---

##  API Endpoints

### Authentication
| Method | Endpoint | Description | Auth Required |
|---|---|---|---|
| POST | `/api/auth/register` | Register new user | No |
| POST | `/api/auth/login` | Login and get JWT token | No |

### Tasks
| Method | Endpoint | Description | Auth Required |
|---|---|---|---|
| GET | `/api/tasks` | Get all tasks for logged-in user | Yes |
| POST | `/api/tasks` | Create new task | Yes |
| PUT | `/api/tasks/{id}` | Update task | Yes |
| DELETE | `/api/tasks/{id}` | Delete task | Yes |

### AI
| Method | Endpoint | Description | Auth Required |
|---|---|---|---|
| GET | `/api/ai/generate?title=` | Generate task description using Gemini AI | Yes |

---

##  AI Integration

**Feature:** AI Task Description Generator (Option A)

**How it works:**
1. User enters a task title in the New Task modal
2. Clicks the  **AI Generate** button
3. Frontend sends `GET /api/ai/generate?title=<title>` to backend
4. Backend calls **Google Gemini 2.5 Flash API** with a prompt
5. Gemini returns a detailed description with priority suggestion
6. Response is sanitized (markdown stripped) and populated in the form
7. User can edit the generated content before saving

**Prompt structure:**
```
Generate a task description for: "<title>"
Include: detailed steps, priority level (HIGH/MEDIUM/LOW), estimated time
```

**Graceful fallback:** If Gemini API fails, a default description is returned and the user is notified with a warning message.

---

## Database Schema

### users table
| Column | Type | Constraints |
|---|---|---|
| id | BIGINT | PRIMARY KEY, AUTO INCREMENT |
| name | VARCHAR(255) | NOT NULL |
| email | VARCHAR(255) | NOT NULL, UNIQUE |
| password | VARCHAR(255) | NOT NULL (BCrypt hashed) |

### tasks table
| Column | Type | Constraints |
|---|---|---|
| id | BIGINT | PRIMARY KEY, AUTO INCREMENT |
| title | VARCHAR(255) | NOT NULL |
| description | VARCHAR(2000) | NOT NULL |
| priority | ENUM | HIGH / MEDIUM / LOW |
| status | ENUM | TODO / IN_PROGRESS / DONE |
| due_date | DATE | NOT NULL |
| created_at | TIMESTAMP | Auto-set on create |
| user_id | BIGINT | FOREIGN KEY → users.id |

---

##  Local Setup Instructions

### Prerequisites
- Java 17+
- Node.js 18+
- MySQL 8+
- IntelliJ IDEA
- VS Code

### Backend Setup

1. Clone the repository:
```bash
git clone https://github.com/Chethankumargs8217/Task-portal-Backend.git
cd Task-portal-Backend/taskportal-skeleton
```

2. Create MySQL database:
```sql
CREATE DATABASE taskportal;
```

3. Set environment variables in IntelliJ (Run → Edit Configurations → Environment Variables):
```
DB_URL=jdbc:mysql://localhost:3306/taskportal
DB_USERNAME=root
DB_PASSWORD=yourpassword
JWT_SECRET=MyVerySecretJwtKeyForTaskPortalApplication123456
GEMINI_API_KEY=your-gemini-api-key
```

4. Run the application from IntelliJ — Spring Boot will create all tables automatically.

5. Access Swagger UI: `http://localhost:8080/swagger-ui/index.html`

### Frontend Setup

1. Clone the repository:
```bash
git clone https://github.com/Chethankumargs8217/Taskportal-Frontend.git
cd Taskportal-Frontend
```

2. Install dependencies:
```bash
npm install
```

3. Run the development server:
```bash
npm run dev
```

4. Open `http://localhost:5173`

---

##  Security Features

- **Password Hashing** — BCrypt encryption
- **JWT Authentication** — Stateless token-based auth (24hr expiry)
- **Protected APIs** — All task endpoints require valid JWT
- **User Isolation** — Users can only see and modify their own tasks
- **Ownership Checks** — Update/delete validates task belongs to user
- **Environment Variables** — No secrets hardcoded in source code
- **CORS Configuration** — Only allowed origins can access the API

---

##  Features

- User registration and login
-  JWT-based authentication
   Create, edit, delete tasks
-  Kanban board (To Do / In Progress / Done)
-  Task priority levels (High / Medium / Low)
-  Due date and created timestamp tracking
-  Search tasks by title
-  Filter by status and priority
-  AI-powered task description generation (Google Gemini)
-  Responsive UI (mobile + desktop)
-  Swagger/OpenAPI documentation
-  Graceful error handling

---

## Deployment

| Component | Platform | Config |
|---|---|---|
| Backend | Render (Docker) | Auto-deploys on GitHub push |
| Frontend | Vercel | Auto-deploys on GitHub push |
| Database | Neon (PostgreSQL) | Managed cloud DB |

### Environment Variables on Render
```
DB_URL=jdbc:postgresql://your-neon-host/neondb?sslmode=require
DB_USERNAME=neondb_owner
DB_PASSWORD=your-password
JWT_SECRET=your-jwt-secret
GEMINI_API_KEY=your-gemini-key
```

### Environment Variables on Vercel
```
VITE_API_URL=https://task-portal-backend-b42u.onrender.com/api
```

---

##  Author

**Chethan Kumar**  
GitHub: [@Chethankumargs8217](https://github.com/Chethankumargs8217)

---

##  Assumptions

1. Each user can only see and manage their own tasks
2. Task status defaults to TODO on creation
3. Due date is required when creating a task
4. AI generation is optional — users can write descriptions manually
5. JWT tokens expire after 24 hours requiring re-login
6. Free tier hosting means backend may have cold start delays (~50s)

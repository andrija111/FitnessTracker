# FitnessTracker

Personalized fitness tracking API with AI-generated workout plans.
Built with Spring Boot, MySQL and Groq AI.

## Prerequisites

- Java 17+
- IntelliJ IDEA (required - NetBeans is not supported due to Lombok)
- Docker Desktop (https://www.docker.com/products/docker-desktop)

## Setup

### 1. Clone the repository
git clone https://github.com/andrija111/FitnessTracker.git
cd FitnessTracker

### 2. Create application.properties
Create file: src/main/resources/application.properties

spring.datasource.url=jdbc:mysql://localhost:3306/fitnesstracker
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
server.port=8080
groq.api.key=YOUR_GROQ_API_KEY

### 3. Start the database
Open Docker Desktop, then run in terminal:
docker compose up -d

### 4. Run the application
Open project in IntelliJ IDEA and run FitnessTrackerApiApplication

### 5. Access Swagger UI
http://localhost:8080/swagger-ui.html

## Testing via Swagger UI

### Step 1 - Register a new user
POST /api/auth/register
{
  "email": "test@gmail.com",
  "password": "password123",
  "firstName": "Marko",
  "lastName": "Markovic"
}

### Step 2 - Login and get JWT token
POST /api/auth/login
{
  "email": "test@gmail.com",
  "password": "password123"
}
Copy the token from the response.

### Step 3 - Authorize in Swagger
Click the Authorize button (top right), paste the token and click Authorize.

### Step 4 - Create user profile
POST /api/profile
{
  "age": 22,
  "height": 180,
  "weight": 80,
  "goal": "MUSCLE_GAIN",
  "experienceLevel": "INTERMEDIATE",
  "equipment": "GYM",
  "injuries": "knee"
}

### Step 5 - Generate AI workout plan
POST /api/workout/generate
No parameters needed - AI reads your profile automatically.

### Step 6 - View all generated plans
GET /api/workout

## Tech Stack
- Java 17
- Spring Boot 3.4.5
- Spring Security + JWT
- MySQL 8.0
- Docker
- Groq AI API (llama-3.3-70b-versatile)
- Swagger/OpenAPI
# assignment_springboot
## Load & Booking Management System

## Overview
This project is a **Spring Boot** application designed for managing **loads** and **bookings** in a logistics system. It leverages **PostgreSQL** as the database and operates in a **Dockerized environment**. The system allows **shippers** to create and manage loads, while **transporters** can book available loads.

---

## Setup Instructions

### Prerequisites
Ensure you have the following installed:
- **Java 17** or later
- **Docker & Docker Compose**
- **Postman** or any API testing tool

### Steps to Run

1. **Clone the repository**
   ```sh
   git clone https://github.com/emperorpriyansh02/assignment_springboot
   cd assignment_springboot
   ```

2. **Start PostgreSQL with Docker**
   ```sh
   docker-compose up -d
   ```

3. **Configure `application.properties` (Optional)**
   Update `src/main/resources/application.properties` if needed:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
   spring.datasource.username=priyansh
   spring.datasource.password=Mark@seven7
   ```

4. **Build and Run the application**
   ```sh
   mvn clean install
   mvn spring-boot:run
   ```

5. **Verify API is running**
   Open [http://localhost:9090](http://localhost:9090) in a browser or test endpoints using Postman.

---

## API Endpoints

### Load Management

#### Create a Load
   ```http
   POST /load
   ```
   **Request Body:**
   ```json
   {
       "shipperId": "SHIPPER123",
       "productType": "Steel",
       "truckType": "Container",
       "noOfTrucks": 5,
       "weight": 1000,
       "comment": "Urgent shipment",
       "datePosted": "2024-04-01T10:00:00Z"
   }
   ```

#### Get Loads (Filtered by Shipper ID & Truck Type)
   ```http
   GET /load?shipperId=SHIPPER123&truckType=Container
   ```

#### Get Load by ID
   ```http
   GET /load/{loadId}
   ```

#### Update Load
   ```http
   PUT /load/{loadId}
   ```

#### Delete Load
   ```http
   DELETE /load/{loadId}
   ```

---

### Booking Management

#### Create a Booking
   ```http
   POST /booking
   ```
   **Request Body:**
   ```json
   {
       "loadId": "UUID",
       "shipperId": "SHIPPER123",
       "transporterId": "TRANSPORTER456",
       "proposedRate": 5000.00,
       "comment": "Price negotiable",
       "status": "PENDING"
   }
   ```

#### Get Bookings (Filtered by Shipper ID & Transporter ID)
   ```http
   GET /booking?shipperId=SHIPPER123&transporterId=TRANSPORTER456
   ```

#### Get Booking by ID
   ```http
   GET /booking/{bookingId}
   ```

#### Update Booking
   ```http
   PUT /booking/{bookingId}
   ```

#### Delete Booking (Automatically Cancels Load)
   ```http
   DELETE /booking/{bookingId}
   ```

---

## Assumptions
1. **A Load can only be booked once** – If a load is booked, its status changes to `BOOKED`.
2. **If a Booking is deleted, the associated Load is CANCELLED.**
3. **Bookings start with a `PENDING` status.**
4. **Shippers create loads, and Transporters book them.**
5. **Filtering is done using query parameters in the GET requests.**

---

## Future Enhancements
- Implement **user authentication** (JWT)
- Add **email notifications** for booking status updates
- Introduce an **admin dashboard** for monitoring loads & bookings

---

## Contribution
Feel free to fork this repository and raise pull requests with improvements.


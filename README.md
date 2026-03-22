# 🧪 Technical Test – Spring Boot REST API

> ⚠️ This technical test is adapted from **TodoCode – Spring Boot Technical Challenge**.

---

## 📑 Table of Contents

- [Objective](#-objective)
- [Case Description](#-case-description)
- [Main Entities](#-main-entities)
- [Relationships](#-relationships)
- [Technical Requirements](#-technical-requirements)
- [Functional Requirements (User Stories)](#-functional-requirements-user-stories)
    - [Products](#-products)
    - [Branches](#-branches-)
    - [Sales](#-sales)
- [Extra – Statistics](#-extra--statistics)

---

## 🎯 Objective

The purpose of this technical test is to evaluate your knowledge of **Java and Spring Boot**, including the development of a complete **RESTful API**.

The solution should demonstrate:

- Implementation of CRUD operations using **JPA**
- Proper handling of **entity relationships**
- Error and exception handling
- Use of **DTOs (Data Transfer Objects)**
- REST best practices
- Functional programming concepts (**lambdas and streams**) where applicable

---

## 📘 Case Description

A well-known supermarket chain wants to digitize its **sales management system**.

To achieve this, they require an API capable of:

- Registering products with their respective prices
- Managing the different store branches where products are sold
- Recording sales made at a specific branch, including products and quantities

Additionally, the company wants to:

- Query sales by branch
- Calculate total revenue
- Identify best-selling products
- Perform other analytical operations

---

## 🧩 Main Entities

- **Branch**  
  Represents a physical supermarket location.

- **Product**  
  Represents an item available for sale (e.g., rice, bottled water, etc.).

- **Sale**  
  Represents a transaction containing one or more products.

---

## 🔗 Relationships

- A **branch** can have many **sales**
- A **sale** contains multiple **products**
- A **product** can belong to multiple **sales**

---

## ⚙️ Technical Requirements

- Use **Spring Boot** with **JPA** for database management
- Use a **relational database** (e.g., H2 or MySQL)
- Expose **RESTful endpoints** for CRUD operations:
    - `GET`, `POST`, `PUT`, `DELETE`
- Implement **DTOs** to separate domain models from external representations
- Proper error handling using:
    - `ResponseEntity`
    - Correct HTTP status codes
    - Clear and meaningful messages
- Use **lambdas or streams** in at least one backend operation
- Maintain a clean project structure:
    - `controller`
    - `service`
    - `repository`

---

## 📖 Functional Requirements (User Stories)

### 📦 Products

#### 1. Get all products
- **Method:** `GET`
- **Path:** `/api/products`
- **Description:** Retrieve a list of all registered products

#### 2. Create a new product
- **Method:** `POST`
- **Path:** `/api/products`
- **Description:** Create a new product with name, price, and category

#### 3. Update an existing product
- **Method:** `PUT`
- **Path:** `/api/products/{id}`
- **Description:** Update the details of a specific product

#### 4. Delete a product
- **Method:** `DELETE`
- **Path:** `/api/products/{id}`
- **Description:** Remove a product from the system

---

### 🏪 Branches 

#### 1. Get all branches
- **Method:** `GET`
- **Path:** `/api/branches`
- **Description:** Retrieve all branches in the system

#### 2. Create a new branch
- **Method:** `POST`
- **Path:** `/api/branches`
- **Description:** Create a new branch with name, address, etc.

#### 3. Update an existing branch
- **Method:** `PUT`
- **Path:** `/api/branches/{id}`
- **Description:** Update branch information

#### 4. Delete a branch
- **Method:** `DELETE`
- **Path:** `/api/branches/{id}`
- **Description:** Remove a branch from the system

---

### 💰 Sales

#### 1. Register a new sale
- **Method:** `POST`
- **Path:** `/api/sales`
- **Payload:**
```json
{
  "branchId": 1,
  "detail": [
    { "productId": 10, "quantity": 2 },
    { "productId": 5, "quantity": 1 }
  ]
}
```

### 📊 Extra – Statistics
#### 1. Get the best-selling product
- **Method:** `GET`
- **Path:** `/api/statistics/bestseller-product`
- **Description:** Calculate the most sold product using Java Streams

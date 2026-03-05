# Hibernate HQL Skill 3

A Hibernate project demonstrating HQL (Hibernate Query Language) operations on a retail inventory system.

## Project Structure

```
src/main/java/com/inventory/
├── entity/          - Product entity class
├── util/            - HibernateUtil session factory helper
├── loader/          - ProductDataLoader for sample data
└── demo/            - HQLDemo with various HQL query examples
```

## HQL Operations Demonstrated

1. **SELECT ALL** — Fetch all products
2. **WHERE clause** — Filter by price
3. **ORDER BY** — Sort results
4. **Aggregate functions** — COUNT, AVG, MAX, MIN, SUM
5. **Partial SELECT** — Select specific columns
6. **LIKE** — Pattern matching search
7. **Pagination** — Limit and offset results
8. **UPDATE** — Bulk update with HQL
9. **DELETE** — Bulk delete with HQL

## Prerequisites

- Java 17+
- Maven
- MySQL with database `inventory_db`

## How to Run

```bash
mvn clean compile exec:java -Dexec.mainClass="com.inventory.demo.HQLDemo"
```

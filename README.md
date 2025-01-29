
# Getting Started with Finance Manager Application

## Prerequisites:
- **JDK 17** or later installed
- **Maven** installed on your system
- A running **H2 Database** for testing (configured in `application.properties`)

## Setting up the Project:
1. **Clone the Repository** (if it's hosted on GitHub):
   ```bash
   git clone https://github.com/your-repository-link.git
   cd your-repository-folder
   ```

2. **Build the Project** using Maven:
   ```bash
   mvn clean install
   ```

3. **Run the Application**:
   You can run the application using the Maven Spring Boot plugin:
   ```bash
   mvn spring-boot:run
   ```
   The application will start on port `8081` as configured in the `application.properties` file.

## API Endpoints:

### User Operations:
- **POST /api/users/register**: Register a new user.
- **GET /api/users/{id}**: Get user details by `id`.

### Category Operations:
- **POST /api/categories**: Add a new category.
- **GET /api/categories/user/{userId}**: Get all categories for a user.
- **PUT /api/categories/{categoryId}**: Update an existing category by its `id`.
- **DELETE /api/categories/{id}**: Delete a category by `id`.

### Transaction Operations:
- **POST /api/transactions**: Add a new transaction.
- **GET /api/transactions/monthly/{userId}**: Get all transactions for a user.
- **DELETE /api/transactions/{id}**: Delete a transaction by `id`.

### Savings Goal Operations:
- **POST /api/savings-goals**: Create a new savings goal.
- **GET /api/savings-goals/user/{userId}**: Get all savings goals for a user.
- **PUT /api/savings-goals**: Update an existing savings goal.
- **DELETE /api/savings-goals/{id}**: Delete a savings goal by `id`.

### Report Operations:
- **GET /api/reports/monthly/{userId}**: Generate a monthly report for a user based on `startDate` and `endDate` query parameters.

## Accessing H2 Database Console:
- The H2 console is enabled and can be accessed at:  
  `http://localhost:8081/h2-console`
- Use the following database URL in the H2 console:
  ```
  jdbc:h2:file:/Users/{USER_NAME}/testing;AUTO_SERVER=TRUE;DB_CLOSE_DELAY=-1
  ```
- Username: `sa`, Password: `password`

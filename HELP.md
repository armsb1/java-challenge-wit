# Java Challenge: Calculator, Rest, and Notification Modules

## Getting Started

### Prerequisites

Before you begin, make sure you have the following tools installed:

- **Java 17** or later: The project requires Java 17 to run. You can install it from [Oracle's official website](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) or through a package manager like `brew install openjdk@17`.
- **Docker Desktop**: To run Kafka and Zookeeper in containers, you will need Docker installed. [Install Docker Desktop](https://www.docker.com/products/docker-desktop).
- **Maven 3.9.9**: This project uses Apache Maven for dependency management and builds. You can download it from [Apache Maven](https://maven.apache.org/download.cgi), though any version of Maven 3.x will likely work.


### Setting Up the Project

1. **Clone the repository**  
   First, clone the project from GitHub:

   ```bash
   git clone https://github.com/yourusername/your-repository.git


## Running Kafka and Zookeeper with Docker Compose in IntelliJ IDEA

### 1. Open the Project in IntelliJ IDEA

1. Open **IntelliJ IDEA**.
2. From the welcome screen, select **New** → **Project from Existing Sources**.
3. Choose the directory where you unzipped the project and open it.
4. Run the following Maven command to clean and install dependencies:

   ```bash
   mvn clean install

### 2. Start Kafka and Zookeeper with Docker Compose

Navigate to the root directory of your project in the terminal and start the Kafka and Zookeeper containers using Docker Compose.

#### Steps:
1. Open a terminal in the root directory of the project.
2. Run the following command to start Kafka and Zookeeper in the background:

   ```bash
   docker-compose up -d

4. **Run the Spring Boot Modules**  
   After Kafka and Zookeeper are up and running, configure and run all Spring Boot modules:

   - In IntelliJ, find the **Run Configuration** for `Run All SpringBoot Modules`.
   - Select this configuration and run it. This will start all three modules (Calculator, Rest, and Notification) simultaneously.

## Workflow of the Application

Once the Spring Boot modules are running, the flow of the application depends on user input in the **Calculator** module.

### **Calculator Module**:
1. The **Calculator Module** asks the user to input a mathematical equation (e.g., `5+5`).
2. After the user inputs the equation:
   - The **Calculator Module** identifies the operator (+, -, *, /).
   - It calls the appropriate REST API in the **Rest Module** (e.g., `/sum`, `/subtraction`).
   - The **Rest Module** performs the calculation and returns the result.
   - The **Calculator Module** sends the result to the **Notification Module** via Kafka.
3. The next equation can be entered.

### **Rest Module**:
- Exposes REST endpoints to perform arithmetic operations (e.g., `/sum`, `/subtraction`).
- Receives operands and operator, performs the calculation, and returns the result to the **Calculator Module**.

### **Notification Module**:
- Listens to Kafka messages for calculation results.
- Logs the operation and result when a message is received (e.g., "Calculator registered another operation: Sum with result = 10").

---

## Steps for Running the Application

1. **Enter a Calculation**: In the **Calculator Module** console, input an equation (e.g., `5+5`, or `3.3*20`).
2. **Trigger the Flow**:
   - The **Calculator Module** calls the REST API in the **Rest Module**.
   - The **Rest Module** calculates and returns the result.
   - The **Calculator Module** sends the result to Kafka.
3. **View the Logs**:
   - **Calculator Module** logs the input, result, and Kafka message.
   - **Rest Module** logs the calculation request.
   - **Notification Module** logs the received operation (e.g., Sum:10).
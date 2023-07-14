# Notification Service

The Notification Service is a microservice designed to handle email notifications within a larger architecture. It integrates with a Eureka server for service discovery and registration. This README file provides an overview of the service, its functionalities, and instructions for setting up and running the service.

## Table of Contents

- [Features](#features)
- [Architecture](#architecture)
- [Prerequisites](#prerequisites)
- [Setup](#setup)
  - [Configuration](#configuration)
  - [Dependencies](#dependencies)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Features

The Notification Service offers the following features:

1. Sending simple email messages
2. Sending HTML template-based email messages
3. Integration with a Eureka server for service discovery and registration
4. Scalable microservice architecture

## Architecture

The Notification Service is built using a microservice architecture pattern. It operates as an independent service that can be deployed and scaled separately from other components in the system. The service communicates with other microservices through APIs.

The service integrates with a Eureka server, which provides service discovery and registration capabilities. This allows other microservices to discover and consume the Notification Service without having to know its exact location.

## Prerequisites

Before setting up and running the Notification Service, ensure that you have the following prerequisites:

1. Java Development Kit (JDK) 8 or higher installed
2. Maven build tool installed
3. Eureka server instance running

## Setup

To set up the Notification Service, follow the steps below:

### Configuration

1. Clone the repository to your local machine:

   ```shell
   git clone https://github.com/Team-0230/notification-service-springboot.git
   ```

2. Navigate to the project directory:

   ```shell
   cd notification-service-springboot
   ```

3. Open the `application.properties` file and configure the following properties:

   ```properties
   # Eureka server configuration
   eureka.client.serviceUrl.defaultZone=http://eureka-server-url/eureka/
   
   # Email configuration
   notification.email.host=smtp.example.com
   notification.email.port=587
   notification.email.username=your-email@example.com
   notification.email.password=your-email-password
   ```

   Replace the placeholder values with your specific configuration.

### Dependencies

The Notification Service uses Maven for dependency management. Run the following command to download the required dependencies:

```shell
mvn clean install
```

## Usage

To run the Notification Service, use the following command:

```shell
mvn spring-boot:run
```

The service will start and attempt to register itself with the Eureka server. Once registered, it will be available for other microservices to discover and consume.

To send a simple email message, make a POST request to the `/api/notifications/send` endpoint with the following JSON payload:

```json
{
  "email": "recipient@example.com",
  "subject": "Notification Subject",
  "text": "Notification Body"
}
```

To send an HTML template-based email, include the `template` field in the JSON payload with the path to your HTML template file:

```json
{
  "email": "recipient@example.com",
  "subject": "Notification Subject",
  "text": "<html><h1>Notification body</h1></html>",
}
```

Replace the placeholder values with the actual recipient email, subject, template path, and data to be used in the template.

## Contributing

Contributions to the Notification Service are welcome! If you encounter any issues or have suggestions for improvement, please submit an issue or pull request to the GitHub repository.

## License

The Notification Service is open-source software released under the [MIT License](https://opensource.org/licenses/MIT). You are free to use, modify, and distribute this software according to the terms of the license.

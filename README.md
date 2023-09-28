# PaymentGatewayApplication
Architecture:
Presentation Layer: This layer is responsible for handling HTTP requests and responses. It includes the RESTful API controllers, which define the API endpoints and interact with incoming requests from clients.

Business Logic Layer: The business logic layer contains services that process the incoming requests, perform business operations, and communicate with the data layer. It also includes Data Transfer Objects (DTOs) for mapping data between the API and data entities.

Data Layer: The data layer is responsible for interacting with the database. It includes the entity classes that represent database tables and repositories that define JPA (Java Persistence API) methods for database operations.

Workflow:
Here is the workflow of the Payment Gateway Spring Boot application:

Merchant Registration:

The client sends a POST request to the /merchant/register endpoint with the merchant's registration details in the request body (MerchantDTO).

The MerchantController receives the request and delegates it to the MerchantService.

The MerchantService processes the registration request, maps the incoming data to a Merchant entity, and saves it to the database using the MerchantRepository.

The response is sent back to the client, indicating whether the merchant registration was successful.

Payment Request:

The client sends a POST request to the /payment-gateway/makePayment endpoint with payment details in the request body (PaymentDTO).

The PaymentGatewayController handles the payment request and forwards it to the PaymentGatewayService.

Inside the PaymentGatewayService, the payment request is validated, and a unique payment code is generated.

A Payment entity is created from the incoming data, including the generated payment code, and is saved to the database using the PaymentRepository.

Depending on the outcome of the payment (simulated success or failure), the payment status is set, and the response includes payment details and status.

Check Payment Status:

The client sends a GET request to the /payment-gateway/status/{paymentCode} endpoint, where {paymentCode} is the unique identifier for the payment.

The PaymentGatewayController receives the request and extracts the payment code from the path.

The PaymentGatewayService uses the PaymentRepository to look up the payment details based on the provided payment code.

The payment status is retrieved from the database, and a response is sent back to the client, indicating whether the payment was successful or not.

API EndPoints
Merchant Registration Endpoint:

Endpoint: /merchant/register

HTTP Method: POST

Purpose: This endpoint allows merchants to register with the Payment Gateway. When a POST request is sent to this endpoint, the merchant's registration details are provided in the request body (typically as JSON data). The server processes the request, validates the data, and registers the merchant, saving their information in the database.

Example Request Body (MerchantDTO):

json
Copy code
{
  "merchantId": "your-merchant-id",
  "name": "Merchant Name",
  "email": "merchant@example.com",
  "businessType": "Online Retail",
  "address": "123 Main St, City",
  "phone": "123-456-7890"
}
Response: The response to this request typically includes a message indicating whether the merchant registration was successful or if there was an error.

Payment Request Endpoint:

Endpoint: /payment-gateway/makePayment

HTTP Method: POST

Purpose: This endpoint allows clients (merchants) to initiate payment requests to the Payment Gateway. A POST request to this endpoint includes payment details in the request body (PaymentDTO). The server processes the payment request, generates a unique payment code, simulates the payment's success or failure, and stores payment details in the database.

Example Request Body (PaymentDTO):

{
  "merchantId": "your-merchant-id",
  "amount": 50.00,
  "currency": "USD",
  "orderId": "order123"
}
Response: The response includes payment details such as the payment code and the simulated payment status (success or failure).

Check Payment Status Endpoint:

Endpoint: /payment-gateway/status/{paymentCode}

HTTP Method: GET

Purpose: This endpoint allows clients to check the status of a payment using a unique payment code. The payment code is provided as a path variable ({paymentCode}), and the server looks up the payment status in the database based on this code.

Example URL: /payment-gateway/status/unique-payment-code

Response: The response indicates whether the payment associated with the provided payment code was successful or not.

Scripts-
CREATE DATABASE:



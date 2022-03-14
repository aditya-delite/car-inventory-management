# Build the project

mvn clean install

# Buyer endpoints

1. Get the list of available cars from inventory

(GET) http://localhost:8080/inventory/buyer/cars

2. Buy a car.

(POST) http://localhost:8080/inventory/buyer/{buyerId}

Here {buyerId} is the id of the buyer. As to purchase a car buyer needs to be registered in the inventory system.

#### List of valid buyer ids are:
300 - 308 (all inclusive).

### Request payload:
{
    "carMake": "Ford",
    "carModel": "Figo",
    "fuelType": "Petrol",
    "color": "White"
}

# Admin endoints

1. Add car to inventory.

(POST) http://localhost:8080/inventory/users/{userId}/car

{userId} is the id of the admin.

#### List of valid admin ids are:
100-108 (all inclusive)

### Request payload:
{
"carMake": "Ford",
"carModel": "Figo",
"fuelType": "Petrol",
"color": "White"
}

2. Remove a car from inventory

(DELETE) http://localhost:8080/inventory/users/{userId}/car

{userId} is the id of the admin.

#### List of valid admin ids are:
100-108 (all inclusive)

### Request payload:
{
"carId": "be6369b3-f07d-44db-beea-6a28a3a5fa79",
"carMake": "Ford",
"carModel": "Figo",
"fuelType": "Petrol",
"color": "White"
}
# ecommerce
e-commerce project

This is an e-commerce project

Technical Assignment - Backend Web API Development
Functional Requirements:
• Design a database model for a simple e-commerce web site with products and shopping cart.
• Develop RESTful Web API that can perform these functionalities:
a) Get list of products from the database. The API response must have at least these details:
Product Name, Description and Unit Price.
b) Add product into shopping cart. Increase the order quantity if same product is added.
c) Remove the added product from the shopping cart. Decrease the quantity if same product is
removed.
d) Get the list of products in the shopping cart. The API response must have at least these details:
Product Name, Quantity, Unit Price, Total Amount.

To complete a-d

step 1:add product with POST method at http://localhost:8080/api/products
- using json format
{
  "name" : "product name",
  "description" : "product description",
  "unitPrice" : "99.99"
}
step 2: check if product is correctly added with GET method at http://localhost:8080/api/products
{
  {
    "id" : 1,
    "name" : "product name",
    "description" : "product description",
    "unitPrice" : 99.99
  }
}
step 3: add product into cart with PUT method at http://localhost:8080/api/shopping_carts/add/product=1

step 4: add again the product into cart with PUT method at http://localhost:8080/api/shopping_carts/add/product=1

step 5: check if the product added into cart with GET method at http://localhost:8080/api/shopping_carts

{
  {
    "id" : 1,
    "productName" : "product name",
    "productQuantity" : 2,
    "unitPrice" : 99.99,
    "totalAmount" : 199.98
  }
}

step 6: remove the product from cart with PUT method at http://localhost:8080/api/shopping_carts/remove/product=1

step 7: check if the product been remove from the cart with GET method at http://localhost:8080/api/shopping_carts
{
  {
    "id" : 1,
    "productName" : "product name",
    "productQuantity" : 1,
    "unitPrice" : 99.99,
    "totalAmount" : 99.99
  }
}

step 8: remove again the product from cart with PUT method at http://localhost:8080/api/shopping_carts/remove/product=1

step 9:check if the product been remove from the cart with GET method at http://localhost:8080/api/shopping_carts






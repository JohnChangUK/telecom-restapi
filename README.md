# Telecom REST API

- Backend server operations on customer to phone numbers relationship
- API's designed with REST architecture

## Implemented Endpoints

```$xslt
Method: GET     /phoneNumbers                                   "Gets all existing phone numbers"
Method: GET     /customer/{id}                                  "Gets all the phone numbers of a single customer"
Method: PUT     /customer/{id}?phoneNumber=0111                 "Activates the customer's provided phone number"
Method: POST    /customer/{id}?phonenumber=0777                 "Adds the given phone number to the customer's account.
                                                                 If customer does not have an account, the API creates a                                                                      customer account with the provided ID"
```                                                
                                                
## Usage of API
### Get all phone numbers

- When retrieving all existing phone numbers, make a GET request to the endpoint `localhost:8080/phoneNumbers`
- Payload is a List of Phone Numbers
```
[
    {
        "phoneNumber": "0111",
        "activated": false
    },
    {
        "phoneNumber": "0222",
        "activated": false
    },
    {
        "phoneNumber": "0555",
        "activated": false
    },
    {
        "phoneNumber": "0444",
        "activated": false
    },
    {
        "phoneNumber": "0333",
        "activated": false
    }
]
```

### Get all phone numbers belonging to a customer

- Perform a GET request to the endpoint `localhost:8080/customer{id}`
- Below are the phone numbers beloning to customer with ID '1'
```
[
    {
        "phoneNumber": "0111",
        "activated": false
    },
    {
        "phoneNumber": "0222",
        "activated": false
    }
]
```

### Activate a phone number belonging to customer

- Perform a PUT request to the endpoint `localhost:8080/customer/1?phoneNumber=0111`
- Below are the phone number '0111' has been activated to `true`
```
{
    "phoneNumber": "0111",
    "activated": true
}
```

### Create a customer account

- Perform a POST request to the endpoint `localhost:8080/customer/12?phoneNumber=079133`
- New customer ID and phone number generated and saved to the database
```
{
    "12": [
        {
            "phoneNumber": "079133",
            "activated": false
        }
    ]
}
```




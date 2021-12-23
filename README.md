# phoneBookApp
PhoneBookApplication.java is the main class
Run as spring boot app, once the server started follow the below step.

Using Postman:

1. Add a user to the system 

<Http Method : URL>

POST : localhost:9080/phoneBook/createUser


Json(Request body):

 {  
        "userName": "Arun",
        "password": "1234",
        "emailAddress": "abc@gamil.com",
        "preferredPhoneNumber":"123456789",
        "phoneDetails": [
            {
                
                "phoneName": "Apple",
                "phoneNumber": "123456789",
                "phoneModel": "IOS"
            },
             {
                
                "phoneName": "SAMSUNG",
                "phoneNumber": "987654321",
                "phoneModel": "ANDROID"
            },
             {
                
                "phoneName": "HTC",
                "phoneNumber": "1112223334",
                "phoneModel": "ANDROID"
            }
        ]
}

Below will be response we get with user details along userId
{
    "userId": "b5d2f186-4086-4fcf-b9a7-ef48ccd72773",
    "userName": "Arun",
    "password": "1234",
    "emailAddress": "abc@gamil.com",
    "preferredPhoneNumber": "123456789",
    "phoneDetails": [
        {
            "phoneId": "dcf8696d-5edf-4423-8d20-fdf984342409",
            "phoneName": "SAMSUNG",
            "phoneNumber": "987654321",
            "phoneModel": "ANDROID"
        },
        {
            "phoneId": "2c40de73-e414-4a8e-91bf-52cb787a9776",
            "phoneName": "Apple",
            "phoneNumber": "123456789",
            "phoneModel": "IOS"
        },
        {
            "phoneId": "45caa477-86ab-4fa4-a264-516174bc58be",
            "phoneName": "HTC",
            "phoneNumber": "1112223334",
            "phoneModel": "ANDROID"
        }
    ]
}

---------------------------------------------
2. Get all the user details

GET : localhost:9080/phoneBook/allUsers
----------------------------------------------
3. Get the user's phone list alone

GET : localhost:9080/phoneBook/phoneList/{userId}
      (get userid from above step 1) localhost:9080/phoneBook/phoneList/b5d2f186-4086-4fcf-b9a7-ef48ccd72773
----------------------------------------------
4. Delete a user from system

DELETE : localhost:9080/phoneBook/deleteUser/{userId}
(get userid from above step 1) localhost:9080/phoneBook/deleteUser/b5d2f186-4086-4fcf-b9a7-ef48ccd72773
----------------------------------------------
5. Update user's preferred phone

PUT : localhost:9080/phoneBook/updatePhone/{userId}/{phoneNumber}
(get userid from above step 1) localhost:9080/phoneBook/updatePhone/b5d2f186-4086-4fcf-b9a7-ef48ccd72773/987654321
----------------------------------------------
6. Delete the user's phone details

DELETE : localhost:9080/phoneBook/deleteUserPhone/{userId}/{phoneNumber}
(get userid from above step 1) localhost:9080/phoneBook/deleteUserPhone/b5d2f186-4086-4fcf-b9a7-ef48ccd72773/1112223334
----------------------------------------------
7. Add a phone details to existing user

PUT : localhost:9080/phoneBook/addPhone/{userId}
(get userid from above step 1) localhost:9080/phoneBook/addPhone/b5d2f186-4086-4fcf-b9a7-ef48ccd72773

Json(Request body):

{
 "phoneDetails": [
        {
            "phoneName": "GOOGLE PIXEL",
            "phoneNumber": "9998887776",
            "phoneModel": "ANDROID"
        }
]

}
----------------------------------------

Note: Service layer test is covered in PhoneBookServiceImplTest.java file

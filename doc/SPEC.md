# API-SPEC E-Commerce

# REGISTER VALIDATE EMAIL
- Endpoint:
> **POST** /api/ecommerce/v0.1/auth/validate_email/{email}

- Headers:
>Content-Type: application/json

- Response Body **(200)**:
```json
{
  "api_version": "v0.1",
  "status_code": 200,
  "message": "Success send otp to email, check your email",
  "data": {
    "email": "random@gmail.com"
  },
  "timestamp": "12301230123",
  "path": "/api/ecommerce/v0.1/auth/validate_email"
}
```

- Response Body **(400)**:
```json
{
  "api_version": "v0.1",
  "status_code": 400,
  "message": "Failed send otp to email",
  "data": {
    "error": "Email has been previously registered, please log in"
  },
  "timestamp": "12301230123",
  "path": "/api/ecommerce/v0.1/auth/validate_email"
}
```
---

# REGISTER
- Endpoint:
> POST /api/ecommerce/v0.1/auth/register

- Headers:
> Accept: application/json
>
> Content-Type: application/json

- Request Body:
```json
{
  "email": "random@gmail.com",
  "otp": "123123",
  "name": "Muhammad Sandi",
  "password": "secret"
}
```

- Response Body **(200)**:
```json
{
  "api_version": "v0.1",
  "status_code": 200,
  "message": "Success register your email",
  "data": {
    "email": "random@gmail.com",
    "name": "Muhammad Sandi",
    "created_at": "04.01.2024"
  },
  "timestamp": "12301230123",
  "path": "/api/ecommerce/v0.1/auth/register"
}
```
- Response Body **(400)**:
```json
{
  "api_version": "v0.1",
  "status_code": 400,
  "message": "Failed register your email",
  "data": {
    "error": "otp email is wrong, check your email"
  },
  "timestamp": "12301230123",
  "path": "/api/ecommerce/v0.1/auth/register_email"
}
```

---

# OTP LOGIN

- Enpoint:
> GET /api/ecommerce/v0.1/auth/otp_login

- Headers:
> Accept: application/json
>
> Content-Type: application/json

- Request Body:
```json
{
  "email": "random@gmail.com",
  "password": "secret"
}
```

- Response Body **(200)**:
```json
{
  "api_version": "v0.1",
  "status_code": 200,
  "message": "Success get otp for your account",
  "data": {
    "email": "random@gmail.com",
    "name": "Muhammad Sandi",
    "update_at": "05.01.2024"
  },
  "timestamp": "12301230123",
  "path": "/api/ecommerce/v0.1/auth/otp_login"
}
```

- Response Body **(404)**:
```json
{
  "api_version": "v0.1",
  "status_code": 404,
  "message": "Failed get otp for your account",
  "data": {
    "error": "your email or password is wrong"
  },
  "timestamp": "12301230123",
  "path": "/api/ecommerce/v0.1/auth/otp_login"
}
```

---

# LOGIN

- Endpoint:
> *GET* /api/ecommerce/v0.1/auth/login

- Headers:
> Accept: application/json
>
> Content-Type: application/json

- Request Body:
```json
  {
  "email": "random@gmail.com",
  "password": "secret",
  "otp": 123123
}
```

- Response Body (*200*):
```json
{
  "api_version": "v0.1",
  "status_code": 200,
  "message": "Success to Login",
  "data": {
    "email": "random@gmail.com",
    "handphone": 123123123,
    "name": "Muhammad Sandi",
    "token": "USINGUUIDRANDOM",
    "token_expired_at": 13332313313231,
    "update_at": "05.01.2024"
  },
  "timestamp": "12301230123",
  "path": "/api/ecommerce/v0.1/auth/login"
}
```

- Response Body **(400)**:
```json
{
  "api_version": "v0.1",
  "status_code": 400,
  "message": "Failed to Login",
  "data": {
    "error": "OTP login is wrong check again"
  },
  "timestamp": "12301230123",
  "path": "/api/ecommerce/v0.1/auth/login"
}
```

---

# CHANGE NAME

- Endpoint:
> **PUT** /api/ecommerce/v0.1/user/name

- Headers:
> Accept: application/json
>
> Content-Type: application/json
>
> User-Token: randonm-random-ranodom

- Request Body:
```json
{
  "name": "something"
}
```

- Response Body **(200)**:
```json
{
  "api_version": "v0.1",
  "status_code": 200,
  "message": "Success change your Name",
  "data": {
    "name": "new name",
    "email": "random@gmail.com",
    "update_at": "202020"
  },
  "timestamp": "12301230123",
  "path": "/api/ecommerce/v0.1/user/name"
}
```

- Response Body **(404)**:
```json
{
  "api_version": "v0.1",
  "status_code": 404,
  "message": "Failed change your Name",
  "data": {
    "error": "Your token is wrong"
  },
  "timestamp": "12301230123",
  "path": "/api/ecommerce/v0.1/user/name"
}
```

# GENERATE OTP EMAIL

- Endpoint:
> **GET** /api/ecommerce/v0.1/user/email

- Headers:
> Content-Type: application/json
>
> User-Token: randonm-random-ranodom


- Response Body **(200)**:
```json
{
  "api_version": "v0.1",
  "status_code": 200,
  "message": "Success send otp on your Email, check your email",
  "data": {
    "email": "random@gmail.com"
  },
  "timestamp": "12301230123",
  "path": "/api/ecommerce/v0.1/user/email"
}
```

- Response Body **(404)**:
```json
{
  "api_version": "v0.1",
  "status_code": 404,
  "message": "Failed send opt on your Email",
  "data": {
    "error": "Token is wrong"
  },
  "timestamp": "12301230123",
  "path": "/api/ecommerce/v0.1/user/email"
}
```

---

# VALIDATE OTP

- Endpoint:
> **POST** /api/ecommerce/v0.1/user/email

- Headers:
> Content-Type: application/json
>
> User-Token: randonm-random-ranodom

- Response Body **(200)**:
```json
{
  "api_version": "v0.1",
  "status_code": 200,
  "message": "Success validate otp",
  "data": {
    "email": "rendom@gmail.com"
  },
  "timestamp": "12301230123",
  "path": "/api/ecommerce/v0.1/user/email"
}
```

- Response Body **(404)**:
```json
{
  "api_version": "v0.1",
  "status_code": 404,
  "message": "Failed validate otp",
  "data": {
    "error": "your otp is wrong"
  },
  "timestamp": "12301230123",
  "path": "/api/ecommerce/v0.1/user/email"
}
```

---

# CHANGE EMAIL

- Endpoint:
> **PUT** /api/ecommerce/v0.1/user/email

- Headers:
> Accept: application/json
>
> Content-Type: application/json
>
> User-Token: randonm-random-ranodom

- Request Body:
```json
{
  "nem_email": "newEmail@gmail.com",
  "otp": 123123
}
```

- Response Body **(200)**:
```json
{
  "api_version": "v0.1",
  "status_code": 200,
  "message": "Success change your email",
  "data": {
    "email": "yourNew@gmail.com"
  },
  "timestamp": "12301230123",
  "path": "/api/ecommerce/v0.1/user/email"
}
```

- Response Body **(404)**:
```json
{
  "api_version": "v0.1",
  "status_code": 404,
  "message": "Failed change your email",
  "data": {
    "error": "email was used"
  },
  "timestamp": "12301230123",
  "path": "/api/ecommerce/v0.1/user/email"
}
```

---

# ADD BIRTH

- Endpoint:
> **POST** /api/ecommerce/v0.1/user/birth

- Headers:
> Accept: application/json
>
> Content-Type: application/json
>
> User-Token: randonm-random-ranodom

- Request Body:
```json
{
  "birth": 123123
}
```

- Response Body **(200)**:
```json
{
  "api_version": "v0.1",
  "status_code": 200,
  "message": "Success add your Birth",
  "data": {
    "birth": 12123123
  },
  "timestamp": "12301230123",
  "path": "/api/ecommerce/v0.1/user/birth"
}
```

- Response Body **(404)**:
```json
{
  "api_version": "v0.1",
  "status_code": 404,
  "message": "Failed add your Birth",
  "data": {
    "error": "Token is wrong"
  },
  "timestamp": "12301230123",
  "path": "/api/ecommerce/v0.1/user/birth"
}
```

---

# ADD GENDER

- Endpoint:
> **POST** /api/ecommerce/v0.1/user/gender

- Headers:
> Accept: application/json
>
> Content-Type: application/json
>
> User-Token: randonm-random-ranodom

- Request Body:
```json
{
  "gender": "female/male"
}
```

- Response Body **(200)**:
```json
{
  "api_version": "v0.1",
  "status_code": 200,
  "message": "Success add your gender",
  "data": {
    "gender": "female/male"
  },
  "timestamp": "12301230123",
  "path": "/api/ecommerce/v0.1/user/gender"
}
```

- Response Body **(404)**:
```json
{
  "api_version": "v0.1",
  "status_code": 404,
  "message": "Failed add your male",
  "data": {
    "error": "Token is wrong"
  },
  "timestamp": "12301230123",
  "path": "/api/ecommerce/v0.1/user/gender"
}
```

---

# UPDATE PROFILE PICTURE

- Endpoint:
> **POST** /api/ecommerce/v0.1/user/picture

- Headers:
> Accept: application/json
>
> Content-Type: application/json
>
> User-Token: randonm-random-ranodom

- Request Body:
```json
{
  "picture": "url or something"
}
```

- Response Body **(200)**:
```json
{
  "api_version": "v0.1",
  "status_code": 200,
  "message": "Success add your picture",
  "data": {
    "picture": "url or something"
  },
  "timestamp": "12301230123",
  "path": "/api/ecommerce/v0.1/user/picture"
}
```

- Response Body **(404)**:
```json
{
  "api_version": "v0.1",
  "status_code": 404,
  "message": "Failed add your picture",
  "data": {
    "error": "Token is wrong"
  },
  "timestamp": "12301230123",
  "path": "/api/ecommerce/v0.1/user/picture"
}
```

--- USER

# ADD ADDRESS

- Endpoint:
> **POST** /api/ecommerce/v0.1/user/address

- Headers:
> Accept: application/json
>
> Content-Type: application/json
>
> User-Token: randonm-random-ranodom

- Request Body:
```json
{
  "name": "Muhammad Sandi",
  "handphone": "08123123123",
  "label": "home/apartmen",
  "city": "Sangatta Utara",
  "profince": "Kalimantan Timur",
  "postal_code": "75611",
  "complete_address": "jalan, gang, nomer, perumahan",
  "coordinate": "123123123123123",
  "note": "dari saiapa ini yakk",
  "main_address": true
}
```

- Response Body **(200)**:
```json
{
  "api_version": "v0.1",
  "status_code": 200,
  "message": "Success add new Address",
  "data": {
    "id": "ranombvalavlav",
    "name": "Muhammad Sandi",
    "handphone": "08123123123",
    "label": "home/apartmen",
    "city": "Sangatta Utara",
    "profince": "Kalimantan Timur",
    "postal_code": "75611",
    "complete_address": "jalan, gang, nomer, perumahan",
    "coordinate": "123123123123123",
    "note": "dari saiapa ini yakk",
    "main_address": true
},
  "timestamp": "12301230123",
  "path": "/api/ecommerce/v0.1/user/address"
}
```

- Response Body **(404)**:
```json
{
  "api_version": "v0.1",
  "status_code": 404,
  "message": "Failed add new Address",
  "data": {
    "error": "token is wrong"
  },
  "timestamp": "12301230123",
  "path": "/api/ecommerce/v0.1/user/address"
}
```

---

# EDIT ADDRESS

- Endpoint:
> **PUT** /api/ecommerce/v0.1/user/address/{id_address}

- Headers:
> Accept: application/json
>
> Content-Type: application/json
>
> User-Token: randonm-random-ranodom

- Request Body:
```json
{
  "name": "Muhammad Sandi",
  "handphone": "08123123123",
  "label": "home/apartmen",
  "city": "Sangatta Utara",
  "profince": "Kalimantan Timur",
  "postal_code": "75611",
  "complete_address": "jalan, gang, nomer, perumahan",
  "coordinate": "123123123123123",
  "note": "dari saiapa ini yakk"
}
```

- Response Body **(200)**:
```json
{
  "api_version": "v0.1",
  "status_code": 200,
  "message": "Success edit a Address",
  "data": {
    "id": "ranombvalavlav",
    "name": "Muhammad Sandi",
    "handphone": "08123123123",
    "label": "home/apartmen",
    "city": "Sangatta Utara",
    "profince": "Kalimantan Timur",
    "postal_code": "75611",
    "complete_address": "jalan, gang, nomer, perumahan",
    "coordinate": "123123123123123",
    "note": "dari saiapa ini yakk",
    "main_address": true
},
  "timestamp": "12301230123",
  "path": "/api/ecommerce/v0.1/user/address/{id_address}"
}
```

- Response Body **(404)**:
```json
{
  "api_version": "v0.1",
  "status_code": 404,
  "message": "Failed edit a Address",
  "data": {
    "error": "ID is not found"
  },
  "timestamp": "12301230123",
  "path": "/api/ecommerce/v0.1/user/address/{id_address}"
}
```

---

# SELECT MAIN ADDRESS

- Endpoint:
> **POST** /api/ecommerce/v0.1/user/main_address/{id_address}

- Headers:
> Content-Type: application/json
>
> User-Token: randonm-random-ranodom

- Response Body **(200)**:
```json
{
  "api_version": "v0.1",
  "status_code": 200,
  "message": "Success select main address",
  "data": {
    "id": "ranombvalavlav",
    "name": "Muhammad Sandi",
    "handphone": "08123123123",
    "label": "home/apartmen",
    "city": "Sangatta Utara",
    "profince": "Kalimantan Timur",
    "postal_code": "75611",
    "complete_address": "jalan, gang, nomer, perumahan",
    "coordinate": "123123123123123",
    "note": "dari saiapa ini yakk",
    "main_address": true
},
  "timestamp": "12301230123",
  "path": "/api/ecommerce/v0.1/user/main_address/{id_address}"
}
```

- Response Body **(404)**:
```json
{
  "api_version": "v0.1",
  "status_code": 404,
  "message": "Failed select main address",
  "data": {
    "error": "ID is not found"
  },
  "timestamp": "12301230123",
  "path": "/api/ecommerce/v0.1/user/main_address/{id_address}"
}
```

---

# REMOVE ADDRESS

- Endpoint:
> **DELETE** /api/ecommerce/v0.1/user/address/{id_address}

- Headers:
> Content-Type: application/json
>
> User-Token: randonm-random-ranodom

- Response Body **(200)**:
```json
{
  "api_version": "v0.1",
  "status_code": 200,
  "message": "Success remove a address",
  "data": {
    "id": "ranombvalavlav",
    "name": "Muhammad Sandi",
    "handphone": "08123123123",
    "label": "home/apartmen",
    "city": "Sangatta Utara",
    "profince": "Kalimantan Timur",
    "postal_code": "75611",
    "complete_address": "jalan, gang, nomer, perumahan",
    "coordinate": "123123123123123",
    "note": "dari saiapa ini yakk"
},
  "timestamp": "12301230123",
  "path": "/api/ecommerce/v0.1/user/address/{id_address}"
}
```

- Response Body **(404)**:
```json
{
  "api_version": "v0.1",
  "status_code": 404,
  "message": "Failed remove a address",
  "data": {
    "error": "id is not found"
  },
  "timestamp": "12301230123",
  "path": "/api/ecommerce/v0.1/user/address/{id}"
}
```

---

# REGISTER SELLER

- Endpoint:
> **POST** /api/ecommerce/v0.1/user/seller

- Headers:
> Accept: application/json
>
> Content-Type: application/json
>
> User-Token: randonm-random-ranodom

- Request Body:
```json
{
  "name": "andfaoTOKO",
  "domain": "apaja",
  "province": "kutai timur",
  "city": "sangatta",
  "postal_code": "23123"
}
```

- Response Body **(200)**:
```json
{
  "api_version": "v0.1",
  "status_code": 200,
  "message": "Success register for seller",
  "data": {
    "id": "blablabla",
    "name": "andaTOKO",
    "domain": "apaja",
    "province": "kutai timur",
    "city": "sangatta",
    "postal_code": "2312312",
    "created_at": "2024013231"
},
  "timestamp": "12301230123",
  "path": "/api/ecommerce/v0.1/user/seller"
}
```

- Response Body **(404)**:
```json
{
  "api_version": "v0.1",
  "status_code": 404,
  "message": "Failed register for seller",
  "data": {
    "error": "Your user was a seller"
  },
  "timestamp": "12301230123",
  "path": "/api/ecommerce/v0.1/user/seller"
}
```

---

# EDIT DESCRIPTION

- Endpoint:
> **PUT** /api/ecommerce/v0.1/user/seller

- Headers:
> Accept: application/json
>
> Content-Type: application/json
>
> User-Token: randonm-random-ranodom

- Request Body:

```json
{
  "description": "something about toko"
}
```

- Response Body **(200)**:

```json
{
  "api_version": "v0.1",
  "status_code": 200,
  "message": "Success edit description",
  "data": {
    "id": "reandom_random",
    "name": "somethingTOKO",
    "domain": "tokogerung",
    "created_at": "12-12-12"
  },
  "timestamp": "12301230123",
  "path": "/api/ecommerce/v0.1/user/seller"
}
```

- Response Body **(404)**:
```json
{
  "api_version": "v0.1",
  "status_code": 404,
  "message": "Failed edit description",
  "data": {
    "error": "Token is wrong"
  },
  "timestamp": "12301230123",
  "path": "/api/ecommerce/v0.1/user/seller"
}
```

---

# EDIT ADDRESS SELLER

- Endpoint:
> **PUT** /api/ecommerce/v0.1/user/seller/address

- Headers:
> Accept: application/json
>
> Content-Type: application/json
>
> User-Token: randonm-random-ranodom

- Request Body:

```json
{
  "city": "kota samarinda",
  "province": "kalimantan timur",
  "complete_address": "somtehing about name street or what",
  "postal_code": "12312",
  "coordinate:": "coordinate from google"
}
```

- Response Body **(200)**:
```json
{
  "api_version": "v0.1",
  "status_code": 200,
  "message": "Success edit address seller",
  "data": {
    "id": "random-random",
    "city": "kota samarinda",
    "province": "kalimantan timur",
    "complete_address": "somtehing about name street or what",
    "postal_code": "12312",
    "coordinate:": "coordinate from google"
},
  "timestamp": "12301230123",
  "path": "/api/ecommerce/v0.1/user/seller/address"
}
```

- Response Body **(404)**:
```json
{
  "api_version": "v0.1",
  "status_code": 404,
  "message": "Failed edit address seller",
  "data": {
    "error": "Token is wrong"
  },
  "timestamp": "12301230123",
  "path": "/api/ecommerce/v0.1/user/seller/address"
}
```

---

# UPDATE PROFILE PICTURE SELLER

- Endpoint:
> **PUT** /api/ecommerce/v0.1/user/seller/picture

- Headers:
> Accept: application/json
>
> Content-Type: application/json
>
> User-Token: randonm-random-ranodom

- Request Body:
```json
{
  "profile_picture": "something url or what"
}
```

- Response Body **(200)**:
```json
{
  "api_version": "v0.1",
  "status_code": 200,
  "message": "Success update profile picture",
  "data": {
    "id": "reandom-random",
    "name": "blabalbalTOKO",
    "profile_picture": "urlSAVED"
},
  "timestamp": "12301230123",
  "path": "/api/ecommerce/v0.1/user/seller/picture"
}
```

- Response Body **(404)**:
```json
{
  "api_version": "v0.1",
  "status_code": 404,
  "message": "Failed update profile picture",
  "data": {
    "error": "Token is wrong"
  },
  "timestamp": "12301230123",
  "path": "/api/ecommerce/v0.1/user/seller/picture"
}
```

---

# ADD PRODUCT

- Endpoint:
> **POST** /api/ecommerce/v0.1/user/seller/product

- Headers:
> Accept: application/json
>
> Content-Type: application/json
>
> User-Token: randonm-random-ranodom

- Request Body:

```json
{
  "name": "jual obat batok",
  "category": "obat",
  "description": "sometghin",
  "etalase_id": ["random","rando"],
  "variant_1": {
    "name": "warna",
    "subvariant": [
      {
        "name": "merah",
        "image": "sometho",
        "price": "123",
        "stock": "123",
        "weight": "123"
      }
    ]
  },
  "variant_2": {},
  "condition": "new",
  "minimum_buy": "12",
  "stock": ""
}
```

- Response Body **(200)**:
```json
{
  "api_version": "v0.1",
  "status_code": 200,
  "message": "Success created product",
  "data": {
    "id": "random-random",
    "name": "jual obat batok",
    "category": "obat",
    "description": "sometghin",
    "etalase_id": ["random","rando"],
    "variant_1": {
      "name": "warna",
      "subvariant": [
        {
          "name": "merah",
          "image": "sometho",
          "price": "123",
          "stock": "123",
          "weight": "123"
        }
      ]
    },
    "variant_2": {},
    "condition": "new",
    "minimum_buy": "12",
    "stock": "",
    "status_active": "true",
    "created_at": "12312-12312"
},
  "timestamp": "12301230123",
  "path": "/api/ecommerce/v0.1/user/seller/product"
}
```

- Response Body **(404)**:
```json
{
  "api_version": "v0.1",
  "status_code": 404,
  "message": "Failed create product",
  "data": {
    "error": "Field blabla cannot be empty"
  },
  "timestamp": "12301230123",
  "path": "/api/ecommerce/v0.1/user/seller/product"
}
```

---

# EDIT PRODUCT

- Endpoint:
> **PUT** /api/ecommerce/v0.1/user/seller/product/{product_id}

- Headers:
> Accept: application/json
>
> Content-Type: application/json
>
> User-Token: randonm-random-ranodom

- Request Body:
```json
{
  "name": "jual obat batok",
  "category": "obat",
  "description": "sometghin",
  "etalase_id": ["random","rando"],
  "variant_1": {
    "name": "warna",
    "subvariant": [
      {
        "name": "merah",
        "image": "sometho",
        "price": "123",
        "stock": "123",
        "weight": "123"
      }
    ]
  },
  "variant_2": {},
  "condition": "new",
  "minimum_buy": "12",
  "stock": ""
}
```

- Response Body **(200)**:
```json
{
  "api_version": "v0.1",
  "status_code": 200,
  "message": "Success edit product",
  "data": {
    "id": "random-random",
    "name": "jual obat batok",
    "category": "obat",
    "description": "sometghin",
    "etalase_id": ["random","rando"],
    "variant_1": {
      "name": "warna",
      "subvariant": [
        {
          "name": "merah",
          "image": "sometho",
          "price": "123",
          "stock": "123",
          "weight": "123"
        }
      ]
    },
    "variant_2": {},
    "condition": "new",
    "minimum_buy": "12",
    "stock": "",
    "status_active": "true"
},
  "timestamp": "12301230123",
  "path": "/api/ecommerce/v0.1/"
}
```

- Response Body **(404)**:
```json
{
  "api_version": "v0.1",
  "status_code": 404,
  "message": "Failed edit product",
  "data": {
    "error": "product id is not found"
  },
  "timestamp": "12301230123",
  "path": "/api/ecommerce/v0.1/user/seller/product"
}
```

---

# ACTIVATED PRODUCT

- Endpoint:
> **PUT** /api/ecommerce/v0.1/user/seller/product_active/{product_id}

- Headers:
> Content-Type: application/json
>
> User-Token: randonm-random-ranodom

- Response Body **(200)**:
```json
{
  "api_version": "v0.1",
  "status_code": 200,
  "message": "Success change status active",
  "data": {
    "id": "random-random",
    "name": "semthing",
    "status_active": "false"
},
  "timestamp": "12301230123",
  "path": "/api/ecommerce/v0.1/user/seller/product_active"
}
```

- Response Body **(404)**:
```json
{
  "api_version": "v0.1",
  "status_code": 404,
  "message": "Failed change status active",
  "data": {
    "error": "product id is not found"
  },
  "timestamp": "12301230123",
  "path": "/api/ecommerce/v0.1/user/seller/product_active/"
}
```

---



# REMOVE PRODUCT

- Endpoint:
> **DELETE** /api/ecommerce/v0.1/user/seller/product/{product_id}

- Headers:
> Content-Type: application/json
>
> User-Token: randonm-random-ranodom

- Response Body **(200)**:

```json
{
  "api_version": "v0.1",
  "status_code": 200,
  "message": "Success remove product",
  "data": {
    "id": "random-random",
    "name": "jual obat batok",
    "category": "obat",
    "description": "sometghin",
    "etalase_id": [
      "random",
      "rando"
    ],
    "variant_1": {
      "name": "warna",
      "subvariant": [
        {
          "name": "merah",
          "image": "sometho",
          "price": "123",
          "stock": "123",
          "weight": "123"
        }
      ]
    },
    "variant_2": {},
    "condition": "new",
    "minimum_buy": "12",
    "stock": "",
    "status_active": "true",
    "created_at": "12-12-12"
  },
  "timestamp": "12301230123",
  "path": "/api/ecommerce/v0.1/user/seller/product"
}
```

- Response Body **(404)**:
```json
{
  "api_version": "v0.1",
  "status_code": 404,
  "message": "Failed remove product",
  "data": {
    "error": "product id is not found"
  },
  "timestamp": "12301230123",
  "path": "/api/ecommerce/v0.1/user/seller/product"
}
```

---

# LIST YOUR PRODUCT

- Endpoint:
> **GET** /api/ecommerce/v0.1/user/seller/product

- Headers:
> Content-Type: application/json
>
> User-Token: randonm-random-ranodom

- Response Body **(200)**:

```json
{
  "api_version": "v0.1",
  "status_code": 200,
  "message": "Success show list product",
  "data": {
    "product_list": [
      {
        "id": "random-random",
        "name": "jual obat batok",
        "category": "obat",
        "description": "sometghin",
        "etalase_id": [
          "random",
          "rando"
        ],
        "variant_1": {
          "name": "warna",
          "subvariant": [
            {
              "name": "merah",
              "image": "sometho",
              "price": "123",
              "stock": "123",
              "weight": "123"
            }
          ]
        },
        "variant_2": {},
        "condition": "new",
        "minimum_buy": "12",
        "stock": "",
        "status_active": "true",
        "created_at": "12-12-12"
      },
      {
        
      }
    ]
  },
  "timestamp": "12301230123",
  "path": "/api/ecommerce/v0.1/user/seller/product"
}
```

- Response Body **(404)**:
```json
{
  "api_version": "v0.1",
  "status_code": 404,
  "message": "Failed show list product",
  "data": {
    "error": "token is wrong"
  },
  "timestamp": "12301230123",
  "path": "/api/ecommerce/v0.1/user/seller/product"
}
```

---


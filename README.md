# _Hair Salon_

#### _Hair Salon, 03-31-2017_

#### By _**Megan and Megan Warnock**_

## Description
_Example text for the description of the project_


## Specifications

| Behavior                   | Input Example     | Output Example    |
| -------------------------- | -----------------:| -----------------:|
| The application will return an instance of the Stylist class | New Stylist | Clair |
| The application will recognize more than one stylist | StylistOne, StylistTwo | Clair, Bob|
| The application will return an instance of the Client class | New Client | Phill  |
| The application will recognize more than one client | ClientOne, ClientTwo | Phill, Abe|
| The application will return a list of all stylist | StylistOne, StylistTwo, StylistThree | Clair, Bob, Laquisha|
| The application will return a list of all clients | ClientOne, ClientTwo, ClientThree | Phill, Abe, Megan|
| The application will be able to delete instances stylists | StylistTwo | null |
| The application will be able to update stylists name | Bob | Bill |
| The application will be able to delete instances of clients | Phill | null |
| The application will be able to update clients information | Abe, 5th st, 333-333-3333 | Abraham, 6th st, 222-333-3333 |



## Setup/Installation Requirements

_Clone the repository_

In PSQL: CREATE DATABASE hair_salon
* switch to database: \c hair_salon
* CREATE TABLE stylists (id serial PRIMARY KEY, name varchar);
* CREAT TABLE clients (id serial PRIMARY KEY, name varchar, address varchar, phone varchar, stylist_id int);

To run application:
* _Run the command 'gradle run'_
* _Open browser and go to localhost:4567_


### License

Copyright (c) 2017 **_Megan Warnock_**

This software is licensed under the MIT license.

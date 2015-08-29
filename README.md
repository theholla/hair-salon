# Hair Salon

##### A RESTful API for hair salon administrators to assign clients to stylists, August 28, 2015.

#### By **Diana Holland**

## Description

You may add hair stylists and clients to this application, and assign multiple clients to each stylist. This RESTful application uses a database to store the information.

## Setup

* Please have all Java developer tools ready, including the JDK
* You'll need Gradle or some other way to run and compile Java
* This app uses Spark and Velocity for coordinating its fron end
* You may use the database included with this file. Otherwise, create your own using the following information:

**In PSQL:**

* CREATE DATABASE hair_salon;
* CREATE TABLE stylists (id SERIAL PRIMARY KEY, name VARCHAR, phone VARCHAR(12));
* CREATE TABLE clients (id SERIAL PRIMARY KEY, client_name VARCHAR, client_phone VARCHAR(12), stylist_id INT);

## Technologies Used

This app is written in Java with a little help from Bootstrap.

### Legal

Copyright (c) 2015 **Diana Holland**

This software is licensed under the MIT license.

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.

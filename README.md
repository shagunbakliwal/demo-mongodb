Setup MongoDB

mongod --port <port> --dbpath <path>
mongo --port <port>

use admin
db.createUser(
  {
    user: "user",
    pwd: "password",
    roles: [ { role: "userAdminAnyDatabase", db: "admin" } ]
  }
)

#Re-start the MongoDB instance with access control
mongod --auth --port <port> --dbpath <path>

#Connect and authenticate as the user administrator
mongo --port <port> -u <user> -p <password> --authenticationDatabase "admin"

#Create additional users as needed for your deployment (in the test authentication database)
use test
db.createUser(
  {
    user: "tester1",
    pwd: "password",
    roles: [ { role: "readWrite", db: "products" },
             { role: "read", db: "customers" } ]
  }
)

#Connect and authenticate as tester1.
mongo --port 27017 -u "myTester" -p "xyz123" --authenticationDatabase "test"

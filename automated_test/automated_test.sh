#/bin/bash

#Get auth token
AUTH_TOKEN=`curl -X POST "http://localhost:8080/api/auth" -H "accept: */*" -H "Content-Type: application/json" -d "@auth.json"`
echo $AUTH_TOKEN





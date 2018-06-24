import requests
import functions as f

def get_auth_token():
    print "Getting authentication token"
    r = requests.post("http://localhost:8080/api/auth", json={"user": "user", "pass": "pass"},
                      headers={"Content-Type": "application/json"})
    f.validate_request(r)

    print "Token obtained is", r.json()
    return r.json()

def get_create_survey_json(emails):
    json = {
        "comment": "Encuesta de prueba automatica",
        "completedSurveys": 0,
        "emails": str(emails),
        "period": "1er cuatrimestre 2010",
        "subjects": [
            {
                "classes": [
                    {
                        "name": "C1",
                        "schedules": [
                            "Jueves 20hs"
                        ],
                        "size": 20,
                        "teachers": "pepe"
                    },
                    {
                        "name": "C2",
                        "schedules": [
                            "Jueves 10hs", "Viernes 10hs"
                        ]
                    }
                ],
                "name": "Mate 1",
                "optionChosen": "",
                "options": [
                    "Aun no voy a cursar",
                    "Ya aprobe",
                    "Cursaria en C1",
                    "Cursaria en C2"
                ]
            },
            {
                "classes": [
                    {
                        "name": "C1",
                        "schedules": [
                            "Lunes 09hs"
                        ],
                        "size": 10,
                        "teachers": "pepita"
                    }
                ],
                "name": "Orga",
                "optionChosen": "",
                "options": [
                    "Aun no voy a cursar",
                    "Ya aprobe",
                    "Cursaria en C1"
                ]
            }
        ]
    }
    return json

def create_survey(emails):
    token = get_auth_token()
    print "Creating survey..."
    json = get_create_survey_json(emails)
    r = requests.post("http://localhost:8080/api/survey", headers={"auth-token":token, "Content-Type":"application/json"}, json=json)
    f.validate_request(r)
    survey_id = r.json()
    print "Survey created with id:",survey_id
    return survey_id



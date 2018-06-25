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
                "name": "Matematica 1",
                "optionChosen": "",
                "options": [
                    "Aun no voy a cursar",
                    "Ya aprobe",
                    "Cursaria en C1",
                    "Cursaria en C2",
                    "Cursaria en C3"
                ],
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
                    },
                    {
                        "name": "C3",
                        "schedules": [
                            "Jueves 10hs", "Viernes 10hs"
                        ]
                    }
                ]
            },
            {
                "name": "Organizacion de computadoras",
                "optionChosen": "",
                "options": [
                    "Aun no voy a cursar",
                    "Ya aprobe",
                    "Cursaria en C1",
                    "Cursaria en C2",
                    "Cursaria en C3"
                ],
                "classes": [
                    {
                        "name": "C1",
                        "schedules": [
                            "Lunes 09hs"
                        ],
                        "size": 10,
                        "teachers": "pepita"
                    },
                    {
                        "name": "C2",
                        "schedules": [
                            "Lunes 09hs"
                        ],
                        "size": 10,
                        "teachers": "pepita"
                    },
                    {
                        "name": "C3",
                        "schedules": [
                            "Lunes 09hs"
                        ],
                        "size": 10,
                        "teachers": "pepita"
                    }
                ]
            },
            {
                "name": "Matematica 2",
                "optionChosen": "",
                "options": [
                    "Aun no voy a cursar",
                    "Ya aprobe",
                    "Cursaria en C1",
                    "Cursaria en C2"
                ],
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
                ]
            },
            {
                "name": "Introduccion a la programacion",
                "optionChosen": "",
                "options": [
                    "Aun no voy a cursar",
                    "Ya aprobe",
                    "Cursaria en C1",
                    "Cursaria en C2",
                    "Cursaria en C3"
                ],
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
                    },
                    {
                        "name": "C3",
                        "schedules": [
                            "Jueves 10hs", "Viernes 10hs"
                        ]
                    }
                ]
            },
            {
                "name": "Programacion con objetos 1",
                "optionChosen": "",
                "options": [
                    "Aun no voy a cursar",
                    "Ya aprobe",
                    "Cursaria en C1",
                    "Cursaria en C2",
                    "Cursaria en C3"
                ],
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
                    },
                    {
                        "name": "C3",
                        "schedules": [
                            "Jueves 10hs", "Viernes 10hs"
                        ]
                    }
                ]
            },
            {
                "name": "Programacion con objetos 2",
                "optionChosen": "",
                "options": [
                    "Aun no voy a cursar",
                    "Ya aprobe",
                    "Cursaria en C1",
                    "Cursaria en C2"
                ],
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
                ]
            },
            {
                "name": "Programacion con objetos 3",
                "optionChosen": "",
                "options": [
                    "Aun no voy a cursar",
                    "Ya aprobe",
                    "Cursaria en C1"
                ],
                "classes": [
                    {
                        "name": "C1",
                        "schedules": [
                            "Jueves 20hs"
                        ],
                        "size": 20,
                        "teachers": "pepe"
                    }
                ]
            },
            {
                "name": "Bases de datos",
                "optionChosen": "",
                "options": [
                    "Aun no voy a cursar",
                    "Ya aprobe",
                    "Cursaria en C1",
                    "Cursaria en C2"
                ],
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
                ]
            },
            {
                "name": "Sistemas operativos",
                "optionChosen": "",
                "options": [
                    "Aun no voy a cursar",
                    "Ya aprobe",
                    "Cursaria en C1",
                    "Cursaria en C2"
                ],
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
                ]
            },
            {
                "name": "Seguridad informatica",
                "optionChosen": "",
                "options": [
                    "Aun no voy a cursar",
                    "Ya aprobe",
                    "Cursaria en C1"
                ],
                "classes": [
                    {
                        "name": "C1",
                        "schedules": [
                            "Jueves 20hs"
                        ],
                        "size": 20,
                        "teachers": "pepe"
                    }
                ]
            },
            {
                "name": "Ingles 1",
                "optionChosen": "",
                "options": [
                    "Aun no voy a cursar",
                    "Ya aprobe",
                    "Cursaria en C1",
                    "Cursaria en C2"
                ],
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
                ]
            },
            {
                "name": "Ingles 2",
                "optionChosen": "",
                "options": [
                    "Aun no voy a cursar",
                    "Ya aprobe",
                    "Cursaria en C1"
                ],
                "classes": [
                    {
                        "name": "C1",
                        "schedules": [
                            "Jueves 20hs"
                        ],
                        "size": 20,
                        "teachers": "pepe"
                    }
                ]
            },
            {
                "name": "Ingeneria de software",
                "optionChosen": "",
                "options": [
                    "Aun no voy a cursar",
                    "Ya aprobe",
                    "Cursaria en C1"
                ],
                "classes": [
                    {
                        "name": "C1",
                        "schedules": [
                            "Jueves 20hs"
                        ],
                        "size": 20,
                        "teachers": "pepe"
                    }
                ]
            },
            {
                "name": "Programacion funcional",
                "optionChosen": "",
                "options": [
                    "Aun no voy a cursar",
                    "Ya aprobe",
                    "Cursaria en C1",
                    "Cursaria en C2"
                ],
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
                ]
            },
            {
                "name": "Desarrollo de aplicaciones",
                "optionChosen": "",
                "options": [
                    "Aun no voy a cursar",
                    "Ya aprobe",
                    "Cursaria en C1",
                    "Cursaria en C2"
                ],
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
                ]
            },
            {
                "name": "Interfaces de usuario",
                "optionChosen": "",
                "options": [
                    "Aun no voy a cursar",
                    "Ya aprobe",
                    "Cursaria en C1",
                    "Cursaria en C2"
                ],
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
                ]
            },
            {
                "name": "Estrategias de persistencia",
                "optionChosen": "",
                "options": [
                    "Aun no voy a cursar",
                    "Ya aprobe",
                    "Cursaria en C1"
                ],
                "classes": [
                    {
                        "name": "C1",
                        "schedules": [
                            "Jueves 20hs"
                        ],
                        "size": 20,
                        "teachers": "pepe"
                    }
                ]
            },
            {
                "name": "Programacion concurrente",
                "optionChosen": "",
                "options": [
                    "Aun no voy a cursar",
                    "Ya aprobe",
                    "Cursaria en C1",
                    "Cursaria en C2"
                ],
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
                ]
            },
            {
                "name": "Redes",
                "optionChosen": "",
                "options": [
                    "Aun no voy a cursar",
                    "Ya aprobe",
                    "Cursaria en C1",
                    "Cursaria en C2"
                ],
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
                ]
            },
            {
                "name": "Taller de trabajo intelectual",
                "optionChosen": "",
                "options": [
                    "Aun no voy a cursar",
                    "Ya aprobe",
                    "Cursaria en C1",
                    "Cursaria en C2",
                    "Cursaria en C3"
                ],
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
                    },
                    {
                        "name": "C3",
                        "schedules": [
                            "Jueves 10hs", "Viernes 10hs"
                        ]
                    }
                ]
            },
            {
                "name": "Laboratorio sistemas operativos",
                "optionChosen": "",
                "options": [
                    "Aun no voy a cursar",
                    "Ya aprobe",
                    "Cursaria en C1"
                ],
                "classes": [
                    {
                        "name": "C1",
                        "schedules": [
                            "Jueves 20hs"
                        ],
                        "size": 20,
                        "teachers": "pepe"
                    }
                ]
            },
            {
                "name": "Proyectos software libre",
                "optionChosen": "",
                "options": [
                    "Aun no voy a cursar",
                    "Ya aprobe",
                    "Cursaria en C1"
                ],
                "classes": [
                    {
                        "name": "C1",
                        "schedules": [
                            "Jueves 20hs"
                        ],
                        "size": 20,
                        "teachers": "pepe"
                    }
                ]
            },
            {
                "name": "TIP",
                "optionChosen": "",
                "options": [
                    "Aun no voy a cursar",
                    "Ya aprobe",
                    "Cursaria en C1"
                ],
                "classes": [
                    {
                        "name": "C1",
                        "schedules": [
                            "Jueves 20hs"
                        ],
                        "size": 20,
                        "teachers": "pepe"
                    }
                ]
            },
            {
                "name": "Algoritmos",
                "optionChosen": "",
                "options": [
                    "Aun no voy a cursar",
                    "Ya aprobe",
                    "Cursaria en C1"
                ],
                "classes": [
                    {
                        "name": "C1",
                        "schedules": [
                            "Jueves 20hs"
                        ],
                        "size": 20,
                        "teachers": "pepe"
                    }
                ]
            },
            {
                "name": "Arquitectura de software 1",
                "optionChosen": "",
                "options": [
                    "Aun no voy a cursar",
                    "Ya aprobe",
                    "Cursaria en C1"
                ],
                "classes": [
                    {
                        "name": "C1",
                        "schedules": [
                            "Jueves 20hs"
                        ],
                        "size": 20,
                        "teachers": "pepe"
                    }
                ]
            }
        ]
    }
    return json


def create_survey(emails):
    token = get_auth_token()
    print "Creating survey..."
    json = get_create_survey_json(emails)
    r = requests.post("http://localhost:8080/api/survey", headers={"auth-token": str(token), "Content-Type": "application/json"}, json=json)
    f.validate_request(r)
    survey_id = r.json()
    print "Survey created with id:", survey_id
    return survey_id



function getAuthToken {
  logDebug "Authenticating user to get token"
  AUTH_TOKEN=`curl -X POST "http://localhost:8080/api/auth" -H "Content-Type: application/json" -d "@auth.json"`
  logDebug "token obtained is: $AUTH_TOKEN "
}

function createSurvey {
  getAuthToken

  logDebug "Creating survey"

  json=$(cat <<EOF
{
	  "comment": "Encuesta de prueba automatica",
	  "completedSurveys": 0,
	  "emails": $1,
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
		    "Jueves 10hs","Viernes 10hs"
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
	EOF
	)

  curl -X POST "http://localhost:8080/api/survey" -H "auth-token: $AUTH_TOKEN" -H "Content-Type: application/json" -d $json
}


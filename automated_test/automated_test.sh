#/bin/bash

DEBUG="true"

. ./create_survey.sh --source-only

function logDebug {
  if [ $DEBUG == "true" ]; then
    echo "$1"
  fi
}

function log {
  echo "${1}"
}

function getStatistics {
  logDebug "Getting survey statistics"
  STATISTICS=`curl -X GET "http://localhost:8080/api/statistics" -H "auth-token: $AUTH_TOKEN" -H "accept: application/json"`
  log "Survey statistics: $STATISTICS"
}


function answerSurvey {
  logDebug "Getting survey hashes"
  
  SURVEY=`curl -X GET "http://localhost:8080/api/student-survey/all/1" -H "accept: application/json"`

  set -f
  array=(${SURVEY//,/ })
  for i in "${!array[@]}"
  do
    if [[ ${array[i]} = *"surveyHash"* ]]; then
      HASH=`cut -d ":" -f 2 <<< ${array[i]}`
      logDebug "Completing survey $HASH"
      curl -X PUT "http://localhost:8080/api/answer/$HASH" -H "Content-Type: application/json" -d "@answer_survey.json"     
    fi
  done
}

emails="lucas,pepe,jose"

createSurvey $emails

#answerSurvey
#getStatistics

logDebug "Finished"
exit 0


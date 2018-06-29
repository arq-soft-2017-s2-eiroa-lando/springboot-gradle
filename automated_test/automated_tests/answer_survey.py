import requests
import functions as f


def answer_survey(answer):
    json = answer["answers"]
    try:
        print "Answering survey"
        r = requests.put("http://localhost:8080/api/answer/"+str(answer["hash"]), timeout=3, headers={"Content-Type":"application/json"}, json=json)
        f.validate_request(r)
    except:
        print "===============TIMEOUT================"


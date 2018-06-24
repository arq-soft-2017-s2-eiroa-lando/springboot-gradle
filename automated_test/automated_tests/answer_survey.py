import requests
import functions as f


def answer_survey(answer):
    print "Answering survey for hash:", answer["hash"]
    json = answer["answers"]
    r = requests.put("http://localhost:8080/api/answer/"+str(answer["hash"]), headers={"Content-Type":"application/json"}, json=json)
    f.validate_request(r)
    print "Survey answered for hash", answer["hash"]


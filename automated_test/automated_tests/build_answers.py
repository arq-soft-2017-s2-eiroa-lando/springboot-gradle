import requests
import functions as f
import random


def build_answers(survey_id, students):
    print "Building answers for survey with id",survey_id
    r = requests.get("http://localhost:8080/api/student-survey/all/"+str(survey_id))
    f.validate_request(r)
    surveys = r.json()
    answers = []

    while len(answers) < students:
        for survey in surveys:
            answer = {"hash": survey["surveyHash"]}

            answer["answers"] = []

            for subject in survey["subjects"]:
                options = {}
                options["id"] = subject["id"]
                options["option"] = random.choice(subject["options"])
                answer["answers"].append(options)
            answers.append(answer)

    return answers

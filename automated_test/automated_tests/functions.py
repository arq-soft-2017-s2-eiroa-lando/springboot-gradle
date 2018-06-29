import string
import random


def generateEmails(students):
    def id_generator(size=6, chars=string.ascii_uppercase):
        return ''.join(random.choice(chars) for _ in range(size))

    emails = []
    for _ in range(0, students):
        emails.append(id_generator())
    return ",".join(emails)


def validate_request(r):
    if r.status_code != 200:
        print "Invalid status code"
        print r.status_code, r.reason
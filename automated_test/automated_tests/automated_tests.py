#!/usr/bin/env python

import sys
import functions as f
import create_survey as cs
import answer_survey as answ
import build_answers as ba
from multiprocessing import Process
from time import sleep

if len(sys.argv) < 3:
    sys.exit("Must provide number of students and tps number.")

students = int(sys.argv[1])
tps = int(sys.argv[2])

print "Executing automated tests with", students,"students, and tps:",tps

emails = f.generateEmails(students)
survey_id = cs.create_survey(emails)
answers = ba.build_answers(survey_id)

processes = []

counter = 0
for answer in answers:
    counter = counter + 1
    p = Process(target=answ.answer_survey, args=(answer,))
    p.start()
    processes.append(p)
    if counter == tps:
       counter = 0
       sleep(1)


print "Finished executing automated tests"

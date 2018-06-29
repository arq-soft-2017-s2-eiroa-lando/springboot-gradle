#!/usr/bin/env python

from __future__ import division
import functions as f
import create_survey as cs
import answer_survey as answ
import build_answers as ba
from multiprocessing import Process
import time
import random


if __name__ == "__main__":
    test_duration_in_seconds = 360
    max_tps = 30

    print "Executing automated tests for", test_duration_in_seconds, "seconds, and tps:", max_tps

    students = 25
    answers = 20000
    emails = f.generateEmails(students)
    survey_id = cs.create_survey(emails)
    answers = ba.build_answers(survey_id, answers)
    print len(answers)
    processes = []

    start = time.time()
    time.clock()
    elapsed = 0
    delay_between_requests = 1  # 1 TPS
    current_tps = 1
    seconds_to_increase = 3
    seconds_to_decrease = 2
    next_decrease = 270
    next_increase = 2
    max_tps_reached = False
    while elapsed < test_duration_in_seconds:
        elapsed = time.time() - start

        if elapsed > next_decrease and current_tps > 1:
            next_decrease += seconds_to_decrease
            current_tps -= 1
            delay_between_requests = 1 / current_tps

        if not max_tps_reached:
            if elapsed > 0 and elapsed > next_increase:
                current_tps += 1
                next_increase += seconds_to_increase
                delay_between_requests = 1 / current_tps
                max_tps_reached = current_tps == max_tps

        p = Process(target=answ.answer_survey, args=(answers.pop(),))
        p.start()
        processes.append(p)
        time.sleep(delay_between_requests)

    print "Finished executing automated tests"




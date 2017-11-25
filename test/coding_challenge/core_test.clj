(ns coding-challenge.core-test
  (:require [midje.sweet :refer :all]
            [coding-challenge.core :refer :all]))

(facts
  "Some tests"
  (fact
    "test 1"
    (solution (int-array [1 3 6 4 1 2])) => 5)
  (fact
    "test 2"
    (solution (int-array [1 2 3])) => 4)
  (fact
    "test 3"
    (solution (int-array [-1 -3])) => 1))

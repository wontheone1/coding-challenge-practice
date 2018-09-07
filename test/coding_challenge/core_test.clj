(ns coding-challenge.core-test
  (:require [midje.sweet :refer :all]
            [coding-challenge.core :refer :all]))

(facts
  "Some tests"
  (fact
    (solution 1 2 3 4 5 6) => "12:34:56")

  (fact
    (solution 8 2 4 1 2 6) => "12:26:48")

  (fact
    (solution 2 3 3 2 3 4) => "22:33:34")

  (fact
    (solution 0 0 0 0 0 0 ) => "00:00:00")

  (fact
    (solution 8 8 1 3 5 8) => "18:38:58")

  (fact
    (solution 8 8 8 9 9 9) => "NOT POSSIBLE")

  (fact
    (solution 5 9 5 9 2 3) => "23:59:59")

  (fact
    (solution 5 9 5 9 5 9) => "NOT POSSIBLE")

  (fact
    (solution 0 0 2 4 0 0) => "00:00:24"))

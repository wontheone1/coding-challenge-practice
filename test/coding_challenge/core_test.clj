(ns coding-challenge.core-test
  (:require [midje.sweet :refer :all])
  (:import (jv.coding_challenge Solution)))

(facts
  "Solve with Java solutions"
  (fact
    "ex1"
    (Solution/solution 6) => 0)

  (fact
    "ex2"
    (Solution/solution 328) => 2)

  (fact
    "ex3"
    (Solution/solution 5) => 1)

  (fact
    "ex4"
    (Solution/solution 16) => 0)

  (fact
    "ex5"
    (Solution/solution 1024) => 0)

  (fact
    "ex6"
    (Solution/solution 1162) => 3)

  (fact
    "ex7"
    (Solution/solution 51712) => 2)

  (fact
    "ex8"
    (Solution/solution 20) => 1)

  (fact
    "ex9"
    (Solution/solution 66561) => 9)

  (fact
    "ex10"
    (Solution/solution 6291457) => 20)

  (fact
    "ex11"
    (Solution/solution 805306373) => 25)

  (fact
    "ex12"
    (Solution/solution 1610612737) => 28))

(ns coding-challenge.core-test
  (:require [midje.sweet :refer :all]
            [coding-challenge.core :refer :all]))

(facts
  "Some tests"
  (fact
    (sherlockAndAnagrams "abba") => 4)

  (fact
    (sherlockAndAnagrams "abcd") => 0)

  (fact
    (sherlockAndAnagrams "ifailuhkqq") => 3)

  (fact
    (sherlockAndAnagrams "kkkk") => 10)

  (fact
    (sherlockAndAnagrams "cdcd") => 5))

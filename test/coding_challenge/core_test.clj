(ns coding-challenge.core-test
  (:require [clojure.test.check :as tc]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [midje.sweet :refer :all]
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
    (solution (int-array [-1 -3])) => 1)
  (fact
    "test 4"
    (solution (int-array [-1 -3])) => 1)
  (fact
    "test 5"
    (solution (int-array
                [4 -96 -4 -2 0 -1 0
                 7 1
                 5
                 0 -7 -6 21 0 -95 -4 0 0 -1 -13 -12 1
                 73 -1 -90 0 14 -27 -1 -2
                 -23 -232 0 18 0 -2 -1 13 -40 -7 3
                 -1 4 4
                 -2 2
                 -14 -76 -24 -5 31 -10 -9 -11
                 -1 -13 -4 0 5 2 -4 -245 -2 55
                 47 -2 26 7
                 5 -1 119 -5 -1
                 4 -1 -1 6
                 -3 0 1 68 20 -1 -60 1 -1 103 -13
                 20 58 -4 -150
                 1 0])) => 8))

(def element-gen
  (gen/large-integer* {:min -1000000, :max 1000000}))

(def find-solution
  (prop/for-all [input-ints (gen/fmap #(int-array %)
                                      (gen/not-empty (gen/vector element-gen 100000)))]
                (do
                  #_(println :answer (solution ^ints input-ints))
                  #_(println (take 200 (filter pos? (sort ^ints input-ints))))
                  (> (solution ^ints input-ints) 0))))

(time
  (fact
    (tc/quick-check 10 find-solution)
    => #(:result %)))

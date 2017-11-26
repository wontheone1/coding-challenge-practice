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
    (let [input (int-array [1 3 6 4 1 2])]
      (= 5
         (solution-by-int-array-sequential-impl input)
         (solution-by-int-array-parallel-impl input)
         (solution-by-persistent-vector input))) => true)
  (fact
    "test 2"
    (let [input (int-array [1 2 3])]
      (= 4
         (solution-by-int-array-sequential-impl input)
         (solution-by-int-array-parallel-impl input)
         (solution-by-persistent-vector input))) => true)
  (fact
    "test 3"
    (let [input (int-array [-1 -3])]
      (= 1
         (solution-by-int-array-sequential-impl input)
         (solution-by-int-array-parallel-impl input)
         (solution-by-persistent-vector input))) => true)
  (fact
    "test 4"
    (let [input (int-array [1 1 1 1 1 1 1 1 1 1 2 2 2 2 2 2 2 2 2 2 3 3 3 3 3 3 3 4 4
                            6 7 7 8 8 9 9 10 11 11 12 13 13 14 15 15 15 16 18 24 26
                            27 28 30 31 34 36 36 36 45 46 47 49 53 55 61 67 68 80 89 93 108 126])]
      (= 5
         (solution-by-int-array-sequential-impl input)
         (solution-by-int-array-parallel-impl input)
         (solution-by-persistent-vector input))) => true)
  (fact
    "test 5"
    (let [input (int-array [4 -96 -4 -2 0 -1 0
                            7 1
                            5
                            0 -7 -6 21 0 -95 -4 0 0 -1 -13 -12 1
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
                            1 0])]
      (= 8
         (solution-by-int-array-sequential-impl input)
         (solution-by-int-array-parallel-impl input)
         (solution-by-persistent-vector input))) => true))

(def element-gen
  (gen/large-integer* {:min -1000000, :max 1000000}))

(defn find-by-solution [solution-fn]
  (prop/for-all [input-ints (gen/fmap #(int-array %)
                                      (gen/not-empty (gen/vector element-gen 100000)))]
                (do
                  #_(println :answer (solution-by-int-array-sequential-impl ^ints input-ints))
                  #_(println (take 200 (filter pos? (sort ^ints input-ints))))
                  (> (solution-fn ^ints input-ints) 0))))

(time
  (fact
    (println :solution-by-int-array-sequential-impl)
    (tc/quick-check 1 (find-by-solution solution-by-int-array-sequential-impl))
    => #(:result %)))

(time
  (fact
    (println :solution-by-persistent-vector)
    (tc/quick-check 1 (find-by-solution solution-by-persistent-vector))
    => #(:result %)))

(time
  (fact
    (println :solution-by-int-array-parallel-impl)
    (tc/quick-check 1 (find-by-solution solution-by-int-array-parallel-impl))
    => #(:result %)))

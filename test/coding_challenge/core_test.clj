(ns coding-challenge.core-test
  (:require [clojure.test.check :as tc]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [coding-challenge.core :as core]
            [midje.sweet :refer :all]))

(facts
  "Some tests"
  (fact
    "test 1"
    (let [input (int-array [1 3 6 4 1 2])]
      (= 5
         (core/solution-by-int-array-map-sequential-impl input)
         (core/solution-by-int-array-map-sequential-impl-without-hint input)
         (core/solution-by-int-array-reduce-sequential-impl input)
         (core/solution-by-int-array-reduce-sequential-impl-without-hint input)
         (core/solution-by-int-array-parallel-impl input)
         (core/solution-by-int-array-parallel-impl-without-hint input)
         (core/solution-by-persistent-vector input)
         (core/solution-by-persistent-vector-without-hint input))) => true)
  (fact
    "test 2"
    (let [input (int-array [1 2 3])]
      (= 4
         (core/solution-by-int-array-map-sequential-impl input)
         (core/solution-by-int-array-map-sequential-impl-without-hint input)
         (core/solution-by-int-array-reduce-sequential-impl input)
         (core/solution-by-int-array-reduce-sequential-impl-without-hint input)
         (core/solution-by-int-array-parallel-impl input)
         (core/solution-by-int-array-parallel-impl-without-hint input)
         (core/solution-by-persistent-vector input)
         (core/solution-by-persistent-vector-without-hint input))) => true)
  (fact
    "test 3"
    (let [input (int-array [-1 -3])]
      (= 1
         (core/solution-by-int-array-map-sequential-impl input)
         (core/solution-by-int-array-map-sequential-impl-without-hint input)
         (core/solution-by-int-array-reduce-sequential-impl input)
         (core/solution-by-int-array-reduce-sequential-impl-without-hint input)
         (core/solution-by-int-array-parallel-impl input)
         (core/solution-by-int-array-parallel-impl-without-hint input)
         (core/solution-by-persistent-vector input)
         (core/solution-by-persistent-vector-without-hint input))) => true)
  (fact
    "test 4"
    (let [input (int-array [1 1 1 1 1 1 1 1 1 1 2 2 2 2 2 2 2 2 2 2 3 3 3 3 3 3 3 4 4
                            6 7 7 8 8 9 9 10 11 11 12 13 13 14 15 15 15 16 18 24 26
                            27 28 30 31 34 36 36 36 45 46 47 49 53 55 61 67 68 80 89 93 108 126])]
      (= 5
         (core/solution-by-int-array-map-sequential-impl input)
         (core/solution-by-int-array-map-sequential-impl-without-hint input)
         (core/solution-by-int-array-reduce-sequential-impl input)
         (core/solution-by-int-array-reduce-sequential-impl-without-hint input)
         (core/solution-by-int-array-parallel-impl input)
         (core/solution-by-int-array-parallel-impl-without-hint input)
         (core/solution-by-persistent-vector input)
         (core/solution-by-persistent-vector-without-hint input))) => true)
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
         (core/solution-by-int-array-map-sequential-impl input)
         (core/solution-by-int-array-map-sequential-impl-without-hint input)
         (core/solution-by-int-array-reduce-sequential-impl input)
         (core/solution-by-int-array-reduce-sequential-impl-without-hint input)
         (core/solution-by-int-array-parallel-impl input)
         (core/solution-by-int-array-parallel-impl-without-hint input)
         (core/solution-by-persistent-vector input)
         (core/solution-by-persistent-vector-without-hint input))) => true))

(def element-gen
  (gen/large-integer* {:min -1000000, :max 1000000}))

(defn find-by-solution [solution-fn number-of-ints]
  (prop/for-all [input-ints (gen/fmap #(int-array %)
                                      (gen/not-empty (gen/vector element-gen number-of-ints)))]
                (do
                  #_(println :answer (solution-fn ^ints input-ints))
                  #_(println (take 200 (filter pos? (sort ^ints input-ints))))
                  (> (solution-fn ^ints input-ints) 0))))

(defn measure-performance-of-all-function [number-of-ints]
  (println :time-measure-with number-of-ints "ints")
  (time
    (fact
      (println :solution-by-int-array-map-sequential-impl)
      (tc/quick-check 1 (find-by-solution core/solution-by-int-array-map-sequential-impl number-of-ints))
      => #(:result %)))

  (time
    (fact
      (println :solution-by-int-array-map-sequential-impl-without-hint)
      (tc/quick-check 1 (find-by-solution core/solution-by-int-array-map-sequential-impl-without-hint number-of-ints))
      => #(:result %)))

  (time
    (fact
      (println :solution-by-int-array-reduce-sequential-impl)
      (tc/quick-check 1 (find-by-solution core/solution-by-int-array-reduce-sequential-impl number-of-ints))
      => #(:result %)))

  (time
    (fact
      (println :solution-by-int-array-reduce-sequential-impl-without-hint)
      (tc/quick-check 1 (find-by-solution core/solution-by-int-array-reduce-sequential-impl-without-hint
                                          number-of-ints))
      => #(:result %)))

  (time
    (fact
      (println :solution-by-int-array-parallel-impl)
      (tc/quick-check 1 (find-by-solution core/solution-by-int-array-parallel-impl number-of-ints))
      => #(:result %)))

  (time
    (fact
      (println :solution-by-int-array-parallel-impl-without-hint)
      (tc/quick-check 1 (find-by-solution core/solution-by-int-array-parallel-impl-without-hint number-of-ints))
      => #(:result %)))

  (time
    (fact
      (println :solution-by-persistent-vector)
      (tc/quick-check 1 (find-by-solution core/solution-by-persistent-vector number-of-ints))
      => #(:result %)))

  (time
    (fact
      (println :solution-by-persistent-vector-without-hint)
      (tc/quick-check 1 (find-by-solution core/solution-by-persistent-vector-without-hint number-of-ints))
      => #(:result %))))

(measure-performance-of-all-function 50000)

(measure-performance-of-all-function 100000)

(measure-performance-of-all-function 300000)

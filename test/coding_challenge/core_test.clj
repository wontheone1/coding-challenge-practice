(ns coding-challenge.core-test
  (:require [clojure.test.check :as tc]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [coding-challenge.core :as sol]
            [midje.sweet :refer :all]))

(fact
  "Some test"
  (let [input (int-array [1 3 6 4 1 2])]
    (sol/solution input)) => 1)

(def int-gen
  (gen/large-integer* {:min -1000000, :max 1000000}))

(defn find-by-solution [solution-fn number-of-ints]
  (prop/for-all [input-ints (gen/fmap #(int-array %)
                                      (gen/not-empty (gen/vector int-gen number-of-ints)))]
                (do
                  #_(println :answer (solution ^ints input-ints))
                  #_(println (seq input-ints))
                  (> (solution-fn ^ints input-ints) 0))))

(defn measure-performance [number-of-ints]
  (println :time-measure-with number-of-ints "ints")
  (time
    (fact
      (println :solution)
      (tc/quick-check 1 (find-by-solution sol/solution number-of-ints))
      => #(:result %))))

(measure-performance 50000)
(measure-performance 100000)

(ns coding-challenge.core-test
  (:require [clojure.test.check :as tc]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [coding-challenge.core :as sol]
            [midje.sweet :refer :all]))

(fact
  (let [input (int-array [4 6 2 1 5])]
    (sol/solution input)) => 23)

(fact
  (let [input (int-array [13 2 5])]
    (sol/solution input)) => 7)

(def int-gen
  (gen/large-integer* {:min 1, :max 1000000000}))

(defn find-by-solution [solution-fn number-of-ints]
  (prop/for-all [input-ints (gen/fmap #(int-array %)
                                      (gen/not-empty (gen/vector-distinct int-gen
                                                                          {:num-elements number-of-ints})))]
                (do
                  #_(println :answer (solution ^ints input-ints))
                  #_(println (seq input-ints))
                  (> (solution-fn ^ints input-ints) 0))))

(defn measure-performance [number-of-ints]
  (println :time-measure-with number-of-ints "ints")
  (time
    (fact
      (tc/quick-check 1 (find-by-solution sol/solution number-of-ints))
      true
      => true)))

(measure-performance 2)
(measure-performance 4)
(measure-performance 8)
(measure-performance 16)
(measure-performance 32)
(measure-performance 36)

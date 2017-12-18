(ns coding-challenge.core)

(defn solution
  "I don't do a whole lot."
  [input]
  (let [as-bin-string (Integer/toBinaryString input)]
    (println :binstr as-bin-string)
    (-> (reduce (fn [[longest-bin-gap current-bin-gap] char]
                  (if (= char \1)
                    (if (> current-bin-gap longest-bin-gap)
                      [current-bin-gap 0]
                      [longest-bin-gap 0])
                    [longest-bin-gap (inc current-bin-gap)]))
                [0 0]
                as-bin-string)
        first)))

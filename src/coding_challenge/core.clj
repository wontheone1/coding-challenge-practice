(ns coding-challenge.core)

(defn minimumBribes [num-person q]
  (let [result (loop [i 0
                      count 0]
                 (if (< i num-person)
                   (if (< 3 (- (q i) i))
                     false
                     (let [num-qi-taken-over (loop [j (max 0 (- (q i) 2))
                                                    num-qi-taken-over 0]
                                               (if (< j i)
                                                 (recur (inc j)
                                                        (if (> (q j) (q i))
                                                          (inc num-qi-taken-over)
                                                          num-qi-taken-over))
                                                 num-qi-taken-over))]
                       (recur (inc i) (+ count num-qi-taken-over))))
                   count))]
    (if result
      (println result)
      (println "Too chaotic"))))

(defn solve []
  (let [num-person 5
        q (vec (map #(Integer/parseInt %) (clojure.string/split "2 1 5 3 4" #" ")))]
    (minimumBribes num-person q)))

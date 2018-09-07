(ns coding-challenge.core)

(defn- increase-bribe-count [{:keys [bribed-person-num->bribe-count] :as result} person-num]
  (let [bribed-persons (keys bribed-person-num->bribe-count)]
    (reduce (fn [result bribed-person]
              (if (> bribed-person person-num)
                (if (= 2 (get-in result [:bribed-person-num->bribe-count bribed-person]))
                  (assoc result :possible false)
                  (update-in result [:bribed-person-num->bribe-count bribed-person] inc))
                result))
            result
            bribed-persons)))

(defn minimumBribes [num-person q]
  (let [result (loop [index 1
                      result {:bribed-person-num->bribe-count {}
                              :possible                       true}]
                 (if (and (<= index num-person) (:possible result))
                   (let [person-num (q (dec index))]
                     (recur (inc index)
                            (increase-bribe-count
                              (assoc-in result [:bribed-person-num->bribe-count person-num] 0)
                              person-num)))
                   result))]
    (if (:possible result)
      (println (apply + (vals (:bribed-person-num->bribe-count result))))
      (println "Too chaotic"))))

(defn -main []
  (let [num-person 5
        q (vec (map #(Integer/parseInt %) (clojure.string/split "2 1 5 3 4" #" ")))]
    (minimumBribes num-person q)))

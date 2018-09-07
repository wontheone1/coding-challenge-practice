(ns coding-challenge.core)

(def not-possible-result "NOT POSSIBLE")

(defn solution [a b c d e f]
  (let [digit->val {}
        [smallest-num & nums-asc] (sort [a b c d e f])]
    (if (> smallest-num 2)
      not-possible-result
      (let [digit->val (assoc digit->val 1 smallest-num)
            nums-bigger-than-5 (filter (fn [n] (> n 5)) nums-asc)
            num-nums-bigger-than-5 (count nums-bigger-than-5)]
        (if (> num-nums-bigger-than-5 3)
          not-possible-result
          (loop [postions-to-fill (take num-nums-bigger-than-5 [6 4 2])
                 digit->val digit->val
                 nums-desc (sort > nums-asc)]
            (if (seq postions-to-fill)
              (recur
                (rest postions-to-fill)
                (assoc digit->val (first postions-to-fill) (first nums-desc))
                (rest nums-desc))
              (let [[second-digit :as nums-asc] (sort nums-desc)
                    empty-positions (remove (into #{} (keys digit->val)) [2 3 4 5 6])
                    digit->val (reduce (fn [digit->val [pos val]]
                                         (assoc digit->val pos val))
                                       digit->val
                                       (zipmap empty-positions nums-asc))]
                (if (and (= (digit->val 1) 2) (> second-digit 3))
                  not-possible-result
                  (apply format "%c%c:%c%c:%c%c"
                         (map identity (reduce (fn [time-string [_ val]]
                                                 (str time-string val))
                                               ""
                                               (sort-by key (into [] digit->val))))))))))))))

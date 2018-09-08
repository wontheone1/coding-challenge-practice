(ns coding-challenge.core)

(defn- sum [nums]
  (apply + nums))

(defn- char-sequence-counter [s subsring-len string-len]
  (loop [index 0
         frequencies-of-ch->frequency []]
    (if (< (+ index subsring-len -1) string-len)
      (recur (inc index)
             (conj frequencies-of-ch->frequency (frequencies (subs s index (+ index subsring-len)))))
      (frequencies frequencies-of-ch->frequency))))

(defn- count-matching-anagrams [frequencies-of-ch->frequency]
  (sum (map #(sum (range %)) (vals frequencies-of-ch->frequency))))

(defn sherlockAndAnagrams [s]
  (let [string-len (count s)]
    (sum (for [substring-len (range 1 string-len)]
           (count-matching-anagrams (char-sequence-counter s substring-len string-len))))))

(ns coding-challenge.core)

(set! *warn-on-reflection* true)

; (set! *unchecked-math* true) ;for the performance reason

(defn solution [^ints xs] ;type hints for performance
  (let [int-seq (int-array (take (inc (alength xs)) (rest (range))))]
    (areduce xs i _ nil
             (try (aset int-seq (dec (aget xs i)) 0)
                  (catch Throwable _)))
    (first (filter #(not (zero? %)) int-seq))))

(defn solution2 [^ints xs]                                  ;type hints for performance
  (let [int-seq (int-array (take (inc (alength xs)) (rest (range))))]
    (doall (pmap (fn [num]
                   (try (aset int-seq (dec num) 0)
                        (catch Throwable _)))
                 xs))
    (first (filter #(not (zero? %)) int-seq))))

(ns coding-challenge.core)

(set! *warn-on-reflection* true)

; (set! *unchecked-math* true) ;for the performance reason

(defn solution-by-int-array-sequential-impl [^ints xs]      ;type hints for performance
  (let [int-seq (int-array (take (+ 2 (alength xs)) (range)))]
    (areduce xs i _ nil
             (try (aset int-seq (aget xs i) 0)
                  (catch Throwable _)))
    (first (filter #(not (zero? %)) int-seq))))

(defn solution-by-int-array-sequential-impl-without-hint [xs]      ;type hints for performance
  (let [int-seq (int-array (take (+ 2 (alength xs)) (range)))]
    (areduce xs i _ nil
             (try (aset int-seq (aget xs i) 0)
                  (catch Throwable _)))
    (first (filter #(not (zero? %)) int-seq))))

(defn solution-by-persistent-vector [^ints xs]              ;type hints for performance
  (let [int-vec (vec (take (+ 2 (alength xs)) (range)))
        not-occurring-ints (reduce (fn [int-vec num]
                                     (try (assoc int-vec num 0)
                                          (catch Throwable _
                                            int-vec)))
                                   int-vec
                                   xs)]
    (first (filter #(not (zero? %)) not-occurring-ints))))

(defn solution-by-persistent-vector-without-hint [xs]              ;type hints for performance
  (let [int-vec (vec (take (+ 2 (alength xs)) (range)))
        not-occurring-ints (reduce (fn [int-vec num]
                                     (try (assoc int-vec num 0)
                                          (catch Throwable _
                                            int-vec)))
                                   int-vec
                                   xs)]
    (first (filter #(not (zero? %)) not-occurring-ints))))

(defn solution-by-int-array-parallel-impl [^ints xs]        ;type hints for performance
  (let [int-seq (int-array (take (+ 2 (alength xs)) (range)))]
    (doall (pmap (fn [num]
                   (try (aset int-seq num 0)
                        (catch Throwable _)))
                 xs))
    (first (filter #(not (zero? %)) int-seq))))

(defn solution-by-int-array-parallel-impl-without-hint [xs]        ;type hints for performance
  (let [int-seq (int-array (take (+ 2 (alength xs)) (range)))]
    (doall (pmap (fn [num]
                   (try (aset int-seq num 0)
                        (catch Throwable _)))
                 xs))
    (first (filter #(not (zero? %)) int-seq))))

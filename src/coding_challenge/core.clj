(ns coding-challenge.core)

(set! *warn-on-reflection* true)

(defn- count-possible-move-pattern [move-pattern-length hunted-nests hunting-nests opposite-nests cnt]
  (cond
    (= move-pattern-length (count hunted-nests))
    1

    (empty? hunting-nests)
    0

    :else
    (apply + (for [[_ hunted-nest-height :as hunted-nest] hunting-nests]
               (count-possible-move-pattern move-pattern-length
                                            (cons hunted-nest hunted-nests)
                                            (remove #(< (second %) hunted-nest-height) opposite-nests)
                                            (remove #{hunted-nest} hunting-nests)
                                            cnt)))))

(defn solution [^ints input-ints]
  (let [nests (map #(do [%1 %2]) (range) (vec input-ints))]
    (apply + (count nests)
           (for [move-pattern-length (range 2 (inc (count nests)))]
             (apply +
                    (for [[nest-index nest-height :as nest] nests
                          :let [left-nests (filter #(< (first %) nest-index) nests)
                                right-nests (filter #(> (first %) nest-index) nests)
                                left-nests-at-higher (filter #(> (second %) nest-height) left-nests)
                                right-nests-at-higher (filter #(> (second %) nest-height) right-nests)]]
                      (+ (count-possible-move-pattern move-pattern-length
                                                      '(nest)
                                                      left-nests-at-higher
                                                      right-nests-at-higher
                                                      0)

                         (count-possible-move-pattern move-pattern-length
                                                      '(nest)
                                                      right-nests-at-higher
                                                      left-nests-at-higher
                                                      0))))))))

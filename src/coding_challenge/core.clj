(ns coding-challenge.core)

(set! *warn-on-reflection* true)

(defn- count-possible-move-pattern [hunting-nests opposite-nests cnt]
  (cond
    (empty? hunting-nests)
    cnt

    :else
    (apply + (for [[_ hunted-nest-height] hunting-nests]
               (count-possible-move-pattern (remove #(< (second %) hunted-nest-height) opposite-nests)
                                            hunting-nests
                                            (inc cnt))))))

(defn solution [^ints input-ints]
  (let [nests (map #(do [%1 %2]) (range) (vec input-ints))]
    (apply + (alength input-ints)
           (for [[nest-index nest-height :as nest] nests
                 :let [left-nests (filter #(< (first %) nest-index) nests)
                       right-nests (filter #(> (first %) nest-index) nests)
                       left-nests-at-higher (filter #(> (second %) nest-height) left-nests)
                       right-nests-at-higher (filter #(> (second %) nest-height) right-nests)]]
             (+ (count-possible-move-pattern left-nests-at-higher
                                             right-nests-at-higher
                                             0)

                (count-possible-move-pattern right-nests-at-higher
                                             left-nests-at-higher
                                             0))))))

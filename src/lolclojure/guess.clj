(ns lolclojure.guess)

;; Using refs for these is overkill, but the original
;; used mutable global variables.
(def small (ref 1))
(def big (ref 100))

(defn guess-my-number
  "This is, effectively, a binary search between the two extremes."
  []
  (Math/round (Math/floor (double (/ (+ @small @big) 2)))))

(defn smaller
  "The guess was too high, so lower it."
  []
  (dosync
   (ref-set big (dec (guess-my-number)))))

(defn bigger
  "The guess was too low, so raise it."
  []
  (dosync
   (ref-set small (inc (guess-my-number)))))

(defn start-over
  "Reset everything and prepare to start over."
  []
  (dosync
   (ref-set small 1)
   (ref-set big 100)))


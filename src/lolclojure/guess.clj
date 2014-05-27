(ns lolclojure.guess)

(def *small* (ref 1))
(def *big* (ref 100))

(defn guess-my-number []
  (Math/round (Math/floor (double (/ (+ @*small* @*big*) 2)))))

(defn smaller []
  (dosync
   (ref-set *big* (dec (guess-my-number)))))

(defn bigger []
  (dosync
   (ref-set *small* (inc (guess-my-number)))))

(defn start-over []
  (dosync
   (ref-set *small* 1)
   (ref-set *big* 100)))


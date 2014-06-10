(ns lolclojure.ch3)

;; CL uses (eq...) for basic equality; Clojure uses (=...)
(= 'foo 'foo)

;; CL has (expt) for exponents; Clojure has... nothing...
(defn expt
  "Raise x to the nth power"
  [x n]
  (letfn [(rexpt [n acc]
                 (if (zero? n)
                   acc
                   (recur (dec n) (* acc x))))]
    (rexpt n 1)))

(expt 2 2)
(expt 2 3)
(expt 2 10)

;; using loop instead of a nested function
(defn expt
  "Raise x to the nth power"
  [x n]
  (loop [n n
         acc 1]
    (if (zero? n)
      acc
      (recur (dec n) (* acc x)))))

(expt 2 2)
(expt 2 3)
(expt 2 10)
(expt 53N 53)
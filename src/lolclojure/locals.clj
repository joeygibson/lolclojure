(ns lolclojure.locals)

;; CL uses a nested list for locals in a let, but Clojure uses a single vector
(let [a 5
      b 6]
  (+ a b))

;; CL uses flet for local functions; Clojure uses letfn

(letfn [(f [n]
           (+ n 10))]
  (f 5))

(letfn [(f [n]
           (+ n 10))
        (g [n]
           (- n 3))]
  (g (f 5)))

;; In CL, functions in flet can't see each other, and you have to use (labels...)
;; In Clojure, they can see each other, so there is no labels
(letfn [(a [n]
           (+ n 5))
        (b [n]
           (+ (a n) 6))]
  (b 10))

;; You *can* also use plain, old, let for local functions, but it does
;; not support recursion.
(let [a (fn [n]
          (+ n 5))
      b (fn [n]
          (+ (a n) 6))
      c 10]
  (b c))


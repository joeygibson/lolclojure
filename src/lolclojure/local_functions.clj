(ns lolclojure.local-functions)

;; CL uses flet for local functions; Clojure uses letfn

(letfn [(f [n]
           (+ n 10))]
  (println (f 5)))

(letfn [(f [n]
           (+ n 10))
        (g [n]
           (- n 3))]
  (println (g (f 5))))

;; In CL, functions in flet can't see each other, and you have to use (labels...)
;; In Clojure, they can see each other, so there is no labels
(letfn [(a [n]
           (+ n 5))
        (b [n]
           (+ (a n) 6))]
  (println (b 10)))


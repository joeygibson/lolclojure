(ns lolclojure.ch3)

;; CL uses (defun...) to define functions, Clojure uses (defn...)
(defn square
  "Returns the square of the passed-in number"
  [n]
  (* n n))

;; CL uses (eq...) for basic equality; Clojure uses (=...)
(= 'foo 'foo)

;; CL has (expt) for exponents; Clojure has... nothing built in...
;; You could use Java's Math.pow, but it only does doubles
(Math/pow 2 2)                                              ; 4.0
(Math/pow 2 3)                                              ; 8.0
(Math/pow 2.0 3)                                            ; 8.0
(Math/pow 2 10)                                             ; 1024.0
(Math/pow 53N 53)                                           ; 2.4356848165022712E91

;; Here are two expt functions, both of which are tail recursive
;; The first uses a nested function
(defn expt
  "Raise x to the nth power"
  [x n]
  (letfn [(rexpt [n acc]
                 (if (zero? n)
                   acc
                   (recur (dec n) (* acc x))))]
    (rexpt n 1)))

(expt 2 2)                                                  ; 4
(expt 2 3)                                                  ; 8
(expt 2.0 3)                                                ; 8.0
(expt 2 10)                                                 ; 1024
(expt 53N 53)                                               ; 24356848165022712132477606520104725518533453128685640844505130879576720609150223301256150373

;; This one recurs on a loop instead of a nested function
(defn expt
  "Raise x to the nth power"
  [x n]
  (loop [n n
         acc 1]
    (if (zero? n)
      acc
      (recur (dec n) (* acc x)))))

(expt 2 2)                                                  ; 4
(expt 2 3)                                                  ; 8
(expt 2.0 3)                                                ; 8.0
(expt 2 10)                                                 ; 1024
(expt 53N 53)                                               ; 24356848165022712132477606520104725518533453128685640844505130879576720609150223301256150373

;; CL uses (princ) (prin1) (print), etc. for output
;; Clojure has (print) (println), etc.
(print "He yelled \"Stop that thief!\" from the busy street.") ; no newline
(println "He yelled \"Stop that thief!\" from the busy street.") ; newline

;; In CL, the building block of a list is the cons cell. Clojure does
;; not have cons cells. In most/all cases, a vector will suffice.
(def pair [:a :b])                                          ; a vector of two items
(def tuple [:a :b :c])                                      ; a vector of three items

(vector 'chicken 'cat)                                      ; ~= (CHICKEN . CAT)

;; The other example on page 39 don't have direct equivalents

;; Clojure has (cons), but it adds an item to a list. It does not
;; create connected cons cells.
(cons 'beef (cons 'chicken '()))                            ; (beef chicken)
(cons 'pork (cons 'beef (cons 'chicken ())))                ; (pork beef chicken)

;; CL uses car, cdr, cadr, caddr, etc. Clojure has first, rest, second.
(first '(pork beef chicken))                                ; (car '(pork beef chicken)) => pork
(rest '(pork beef chicken))                                 ; (cdr '(pork beef chicken)) => (beef chicken)
(second '(pork beef chicken))                               ; (cadr '(pork beef chicken)) => beef

;; CL defines c*r functions, up to four levels deep. You *could* simulate these
;; in Clojure like this
(defn cdar
  [lst]
  (rest (first lst)))

(cdar '((peas carrots tomatoes) (pork beef chicken)))       ; (carrots tomatoes)

(defn cddr
  [lst]
  (rest (rest lst)))

(cddr '((peas carrots tomatoes) (pork beef chicken) duck))  ; (duck)

(defn caddr
  [lst]
  (first (rest (rest lst))))

(caddr '((peas carrots tomatoes) (pork beef chicken) duck)) ; duck

(defn cddar
  [lst]
  (rest (rest (first lst))))

(cddar '((peas carrots tomatoes) (pork beef chicken) duck)) ; (tomatoes)

(defn cadadr
  [lst]
  (first (rest (first (rest lst)))))

(cadadr '((peas carrots tomatoes) (pork beef chicken) duck)) ; beef

;; But destructuring is probably a better choice
(let [[fst] '((peas carrots tomatoes) (pork beef chicken))]
  (rest fst))                                               ; (carrots tomatoes) ; instead of cdar

(let [[_ & rst] '((peas carrots tomatoes) (pork beef chicken) duck)]
  (rest rst))                                               ; (duck) ; instead of cddr

(let [[_ & rst] '((peas carrots tomatoes) (pork beef chicken) duck)
      tail (rest rst)]
  (first tail))                                             ; duck ; instead of caddr

(let [[fst] '((peas carrots tomatoes) (pork beef chicken) duck)
      tail (rest fst)]
  (rest tail))                                              ; (tomatoes) ; instead of cddar

(let [[_ & rst] '((peas carrots tomatoes) (pork beef chicken) duck)
      fst (first rst)
      tail (rest fst)]
  (first tail))                                             ; beef ; instead of cadadr






















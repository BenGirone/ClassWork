;; Ben Girone. 2/28/18. CSC 410
;; Implements several functions on a small data structure and demonstrates lazy sequences.

(ns bmagprog2.core
  (:gen-class))

;; 1.
(def bmg-data {0 {:name "jimmy" :clojure false :sanity 9}
			   1 {:name "johny" :clojure true  :sanity 2}
			   2 {:name "jilly" :clojure true  :sanity 5}
			   3 {:name "janey" :clojure true  :sanity 8}
			   4 {:name "jelly" :clojure false :sanity 10}})

;; 2.
(defn student-related-details
	"Accepts the id-number of a student and returns the record details."
	[id]
	(get bmg-data id))

;; 3.
(defn unhinged?
	"Returns true if a student is not fully sane.returns true if a student is not fully sane."
	[student]
	(< (:sanity student) 6))

;; 4.
(defn unstable-students
	"Returns a list of potential unstable students."
	([] (unstable-students (apply vector (range (count bmg-data)))))
	([ids]
		(filter unhinged? (map student-related-details ids))))

;; 5.
(def very-stable? #((complement unhinged?) %))

;; 6.
(defn adjusted-sanity-students
	"Returns a list of all the students who enjoy clojure with their sanity-rating set to -2."
	([] (adjusted-sanity-students (apply vector (range (count bmg-data)))))
	([ids]
		(map #(assoc % :sanity -2) (filter #(:clojure %) (map student-related-details ids)))))

;; 7.
(defn squared-numbers
  ([] (squared-numbers 1))
  ([n] (cons (* n n) (lazy-seq (squared-numbers (inc n))))))

;; 8.
(defn fibonacci
	"Take a positive integer n and returns the nth Fibonacci number."
	[n]
	(if (= n 1) 
		0
	(if (= n 2) 
		1
		(+ (fibonacci (- n 1)) (fibonacci (- n 2))))))

;; 9.
(def lazy-fib
	((fn f [a b] 
		(lazy-seq (cons a (f b (+ a b)))))
	0 1))

(defn -main
  "Tests each part of the assignment"
  [& args]

  (println "1: " (str "\n" (clojure.string/join "\n" bmg-data) "\n"))
  (println "2: " (str (student-related-details 1)) "\n")
  (println "3: " (str (unhinged? (student-related-details 2))) "\n")
  (println "4: " (str (clojure.string/join " " (unstable-students))) "\n")
  (println "5: " (str (very-stable? (student-related-details 2))) "\n")
  (println "6: " (str (clojure.string/join " " (adjusted-sanity-students)) "\n"))
  (println "7: " (str (clojure.string/join " " (take 6 (squared-numbers))) "\n"))
  (println "8: " (str (fibonacci 6)) "\n")
  (println "9: " (str (clojure.string/join " " (take 6 lazy-fib)) "\n")))
  

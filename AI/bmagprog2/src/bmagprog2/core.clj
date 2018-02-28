(ns bmagprog2.core
  (:gen-class))

;; 2.
(defn student-related-details
	"Accepts the id-number of a student and returns the record details."
	[id data]
	(loop [searchData data]
		(if (empty? searchData)
			(println "ERROR: No data with that ID")
			(let [[part & remaining] searchData]
				(if (= (:id part) id)
					part
					(recur remaining))))))

;; 3.
(defn unhinged
	"Returns true if a student is not fully sane.returns true if a student is not fully sane."
	[student]
	(< (:sanity student) 6))

;; 4.
(defn unstable-students
	"Returns a list of potential unstable students."
	[data]
	(loop [searchData data unstable []]
		(if (empty? searchData)
			unstable
			(let [[part & remaining] searchData]
				(if (and (:clojure part) (unhinged part)) ;;student likes clojure and is unhinged
					(recur remaining (into unstable [part]))
					(recur remaining unstable))))))

;; 5.
(def very-stable #((complement unhinged) %))

;; 6.
(defn adjusted-sanity-students
	"Returns a list of all the students who enjoy clojure with their sanity-rating set to -2."
	[data]
	(loop [searchData data adjusted []]
		(if (empty? searchData)
			adjusted
			(let [[part & remaining] searchData]
				(if (:clojure part)
					(recur remaining (into adjusted [(assoc part :sanity -2)]))
					(recur remaining adjusted))))))

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

  ;; 1.
  (def bmg-data [{:id 0 :name "jimmy" :clojure false :sanity 9}
  				 {:id 1 :name "johny" :clojure true  :sanity 2}
  				 {:id 2 :name "jilly" :clojure true  :sanity 5}
  				 {:id 3 :name "janey" :clojure true  :sanity 8}
  				 {:id 4 :name "jelly" :clojure false :sanity 10}])

  (println "2: " (str (student-related-details 1 bmg-data)) "\n")
  (println "3: " (str (unhinged (student-related-details 2 bmg-data))) "\n")
  (println "4: " (str (unstable-students bmg-data)) "\n")
  (println "5: " (str (very-stable (student-related-details 2 bmg-data))) "\n")
  (println "6: " (str (adjusted-sanity-students bmg-data))  "\n")
  (println "7: " (clojure.string/join ", " (take 6 (squared-numbers))) "\n")
  (println "8: " (str (fibonacci 6)) "\n")
  (println "9: " (clojure.string/join ", " (take 6 lazy-fib)) "\n"))
  

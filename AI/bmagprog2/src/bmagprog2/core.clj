(ns bmagprog2.core
  (:gen-class))

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

(defn unhinged
	"Returns true if a student is not fully sane.returns true if a student is not fully sane."
	[student]
	(< (:sanity student) 6))

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

(def very-stable #((complement unhinged) %))

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

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (def bmg-data [{:id 0 :name "jimmy" :clojure false :sanity 9}
  				 {:id 1 :name "johny" :clojure true  :sanity 2}
  				 {:id 2 :name "jilly" :clojure true  :sanity 5}
  				 {:id 3 :name "janey" :clojure true  :sanity 8}
  				 {:id 4 :name "jelly" :clojure false :sanity 10}]))

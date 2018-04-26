;Ben Girone CSC 410 3/20/18
;Exercises 1 through 5 on page 124 of https://www.braveclojure.com/functional-programming/

(ns bmagprog3.core
  (:gen-class))

(defn just-nums
	[L]
	(reduce 
		#(if (number? %2)
        	(into %1 [%2])
        	%1)
        [] L))

(def character
  {:name "Smooches McCutes"
   :attributes {:intelligence 10
                :strength 4
                :dexterity 5}})

(defn attr
	"An alternative to (comp :intelligence :attributes)."
	[attribute]
	((character :attributes) attribute))

(defn my-comp
	"Implementation of the comp function."
	[& funcs]
	(reduce #(fn [& args] (%1 (apply %2 args))) funcs))

(defn my-assoc-in
	"Implementation of the assoc-in function."
	[m [k & ks] v]
	(if (empty? ks)
		(assoc m k v)
		(assoc  m k (my-assoc-in m ks v))))

(defn my-update-in
	"Implementation of the update-in function."
	[m [k & ks] f]
	(if (empty? ks)
		(update m k f)
		(assoc m k (my-update-in (m k) ks f))))

(defn -main
  "Tests each function above."
  [& args]

  ;1
  (println (str "1: " (attr :intelligence)))

  ;2
  (println (str "2: "
  ((my-comp 			;f(g(h(x))) = (3x - 2)^2
  	(fn [x] (* x x)) 	;f(x) = x^2
  	(fn [x] (- x 2)) 	;g(x) = x - 2
  	(fn [x] (* x 3))) 	;h(x) = 3x
  5)))					;x = 5

  ;3
  (println (str "3: " (my-assoc-in {} [:cookie :monster :vocals] "Finntroll")))

  ;4
  (println (str "4: " (update-in {:a {:b {:c 1}}} [:a :b :c] inc)))

  ;5
  (println (str "5: " (my-update-in {:a {:b {:c 1}}} [:a :b :c] inc)))

  "done")

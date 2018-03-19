(ns bmagprog3.core
  (:gen-class))

(defn just-nums
	[L]
	(reduce 
		#(if (number? %2)
        	(into %1 [%2])
        	%1)
        [] L))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

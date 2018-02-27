(ns tests.core
  (:gen-class))

(defn loopTest
"Takes an array of the form [ [1 2 3] [4 5 6] ] and outputs [1 2 3 4 5 6]"
	[x]
	(loop [xLoop x result []]
		(if (empty? xLoop)
			result
			(let [[inner & remaining] xLoop]
				(recur remaining (into result inner))))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (set (loopTest [ [1 2 3] [4 6] [9] [1 2 3 4 5] ])))
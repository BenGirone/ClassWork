;;Ben Girone	CSC 410		4/17/18
;;This program uses the principles of genetic algorithms to solve the eight queens problem

(ns bmagfinalb.core
  (:gen-class))

(defn random-board
	"Return a random board."
	[]
	(apply vector (repeatedly 8 #(+ 1 (rand-int 8)))))

(defn mutate-string
	"Change a random piece on the board."
	[board]
	(assoc board (rand-int 8) (+ 1 (rand-int 8))))

(defn check-diagonal
	"Check if a queen is on the same diagonal as another queen."
	[board i plus-minus]
	(loop [j (inc i)]
		(if (< j 8)
			(if (= (nth board j) (plus-minus (nth board i) (- j i)))
				1
				(recur (inc j)))
			0)))

(defn fitness
	"Judge the fitness of a board. Return the number of non-attacking pairs."
	[board]
	(loop [pairs 0 i 0]
		(if (< i 8)
			(recur 
				(+ (check-diagonal board i +) (check-diagonal board i -) pairs) 
				(inc i))
		(- 28 (+ pairs (- 8 (count (set board))))))))

(defn new-population
	"Provide a new population sorted by fitness."
	([] (new-population [(random-board) (random-board) (random-board) (random-board)]))
	([strings]
	(sort-by :fitness > (reduce #(into %1 [{:string %2 :fitness (fitness %2)}]) [] strings))))

(defn breed-strings
	"Make randomized combinations of the strings and mutate a random element"
	[board1 board2]
	(let [cross-point (+ (rand-int 5) 2)]
		[(mutate-string (into [] (concat (subvec board1 0 cross-point) (subvec board2 cross-point))))
		(mutate-string (into [] (concat (subvec board2 0 cross-point) (subvec board1 cross-point))))]))

(defn new-generation
	"Given a population, provide the next generation ordered by fitness."
	[population]
	(new-population (concat (breed-strings (:string (nth population 0)) (:string (nth population 1))) (breed-strings (:string (nth population 1)) (:string (nth population 2))))))


(defn -main
  "Use the functions provided to solve the eight queens problem"
  [& args]
  (let [population (new-population)]
  	(loop [generation (new-generation population) counter 2]
  		(if (< (:fitness (first generation)) 28)
  			(recur (new-generation generation) (inc counter))
  			(println (str (:string (first generation))) " in " (str counter) " generations.")))))


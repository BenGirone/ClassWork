(ns bmagfinala.core
  (:gen-class))

(def Romania
	{
		:Oradea {:Zerind 71 :Sibiu 151}
		:Zerind {:Oradea 71 :Arad 75}
		:Arad {:Zerind 75 :Sibiu 140 :Timisoara 118}
		:Timisoara {:Arad 118 :Lugoj 111}
		:Lugoj {:Timisoara 111 :Mehadia 70}
		:Mehadia {:Lugoj 70 :Drobeta 75}
		:Drobeta {:Mehadia 75 :Craiova 120}
		:Sibiu {:Oradea 151 :Fagaras 99 :RimnicuVilcea 80 :Arad 140}
		:RimnicuVilcea {:Sibiu 80 :Pitesti 97 :Craiova 146}
		:Craiova {:RimnicuVilcea 146 :Drobeta 120 :Pitesti 138}
		:Fagaras {:Sibiu 99 :Bucharest 211}
		:Pitesti {:RimnicuVilcea 97 :Craiova 138 :Bucharest 101}
		:Bucharest {:Fagaras 211 :Pitesti 101 :Urziceni 85 :Giurgiu 90}
		:Giurgiu {:Bucharest 90}
		:Neamt {:Iasi 87}
		:Iasi {:Neamt 87 :Vaslui 92}
		:Vaslui {:Iasi 92 :Urziceni 142}
		:Urziceni {:Vaslui 142 :Bucharest 85 :Hirsova 98}
		:Hirsova {:Urziceni 98 :Eforie 86}
		:Eforie {:Hirsova 86}
	})

(defn parent-map
	[parent-map parent]
	(concat parent-map (map #(hash-map % parent) (keys (parent Romania)))))

(defn queue-keys
	[queue]
	(reduce #(concat (keys %2) %1) [] queue))

(defn queue-from-map
	[mapping]
	(into [] (map #(into {} [%]) (into [] mapping))))

(defn queue-push
	"Add items to the back of a queue that are not in the explored collection"
	[queue push explored]
	(into [] (concat queue (remove #(some #{(first (keys %))} (concat explored (queue-keys queue))) push))))

(defn breadth-first-search
	[start-city goal-city]
	(loop [
		frontier (queue-from-map (Romania start-city)) 
		explored [] 
		current-node start-city
		cost 0
		path-data {start-city 0}]
		
		(if (empty? frontier) ("no path"))
		(if (= current-node goal-city)
			(loop [path-node current-node result-path [current-node]]
				(if (= (path-data path-node) 0)
					result-path
					(recur (path-data path-node) (into [] (concat [(path-data path-node)] result-path)))))
			(recur
				(queue-push (rest frontier) (queue-from-map ((first (keys (first frontier))) Romania)) (concat explored [current-node]))
				(into [] (concat explored [current-node]))
				(first (keys (first frontier)))
				(+ ((first (keys (first frontier))) (first frontier)) cost)
				(reduce #(if (not (contains? %1 %2)) (assoc %1 %2 current-node) %1) path-data (keys (current-node Romania)))))))

(defn -main
  "Test the breadth-first search algorithm."
  [& args]
  (println "Cities: ")
  (println (clojure.string/join ", " (map name (keys Romania))))
  (println "Enter a start city and goal city (separated by a space): ")
  (try
     (println (apply breadth-first-search (map keyword (clojure.string/split (read-line) #" "))))
     (catch Exception e (println "There was an error with your input. Defaulting to \"Arad Bucharest\" " (str (breadth-first-search :Arad :Bucharest))))))
  
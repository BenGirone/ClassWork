(ns tests.core
  (:gen-class))

(defn destructo
	[[first second & rest]]
	second)

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  )

(defn better-symmetrize-body-parts
  "Expects a seq of maps that have a :name and :size"
  [asym-body-parts]
  (reduce (fn [final-body-parts part]
            (into final-body-parts (set [part (matching-part part)])))
          []
          asym-body-parts))

(defn copy
	[data]
	(reduce (fn [copyData item]
		(into copyData [item item]))
	[]
	data))


(defn titleize
  [topic]
  (str topic " for the Brave and True"))
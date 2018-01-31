;; Ben Girone. 1/29/18. CSC 410.
;; This program gives examples of many common structures in Clojure.

(ns bmagprog1.core
  (:gen-class))

(defn is-old
	"A function with one parameter (part 5) that uses 'if' (part 1) and 'do' (part 2) and 'when' (part 3)"
	[age]

	(if (> age 30)
		(print "You are old. ")
		(do (print "You are young. ")
			(print "You have" (- 30 age) "years until you are old")))

	(when (> age 150)
		(print "You should see a doctor. ")
		(print "You might be an immortal porpoise."))

	(println ""))

(defn zero-param
	"A function with no parameters (part 4)"
	[]

	(println "I'm lonely because I have no params."))

(defn multi-arity
	"A multi-arity function (part 6)"
	([name] (multi-arity name "a while ago"))
	([name DoB] 
		(println name "was born" DoB)))

(defn add-stuff
"A function with default values (part 7)"

	)


(defn -main
	"Tests the various functions."
	[& args]

	;;test the is-old function
	(is-old 25)
	(is-old 60)
	(is-old 160)
	(zero-param)
	(multi-arity "Billy" "October 1st 1999")
	(multi-arity "Susie"))

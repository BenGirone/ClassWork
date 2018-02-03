;; Ben Girone. 1/29/18. CSC 410.
;; This program gives examples of many common structures in Clojure.

(ns bmagprog1.core
  (:gen-class))

(defn is-old
	"A function with one parameter (part 5) that uses 'if' (part 1) and 'do' (part 2) and 'when' (part 3)"
	[age]

	;;test if and do
	(if (> age 30)
		(print "You are old. ")
		(do (print "You are young. ")
			(print "You have" (- 30 age) "years until you are old")))

	;;test when
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
	([firstName] 
		(println "Hello" firstName "I wish I knew your last name."))
	([firstName lastName] 
		(println "Hello" firstName lastName "I'm glad I know your last name.")))

(defn liam-neeson
"A function with default values (part 7)"
	([] (liam-neeson "find you"))
	([action1] (liam-neeson action1 "kill you"))
	([action1 action2] (println "I will" action1 "... and I will" action2)))

(defn add-a-lot
"A function with a variable parameter list (part 8)"
	[& numbers]
	(println (reduce + numbers)))

(defn -main
	"Tests the various functions."
	[& args]

	(is-old 25)
	(is-old 60)
	(is-old 160)
	(zero-param)
	(multi-arity "Billy" "Smith")
	(multi-arity "Susie")
	(liam-neeson)
	(liam-neeson "find you" "put a smile on your face")
	(add-a-lot 1 2 3)
	(add-a-lot 1 2 3 4))


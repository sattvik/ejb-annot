; Copyright © 2010 Sattvik Software & Technology Resources, Ltd. Co.
; All rights reserved.
;
; ejb-annot may be used under the terms of either the GNU Lesser General Public
; License (LGPL) or the Eclipse Public License (EPL).  As a recipient of
; ejb-annot, you may choose which license to receive the code under.  See the
; LICENSE file distributed with sh-clojure for details.
;
; Written by Daniel Solano Gómez

(ns ^{:doc "An implementation of the simple stateless session bean example
           from OpenEJB.

           http://openejb.apache.org/3.0/simple-stateless-example.html"
       :author "Daniel Solano Gómez"}
  ejb.stateless
  (:import [javax.ejb Remote Stateless]))

(definterface CalculatorLocal
  (^int sum [^int add1, ^int add2])
  (^int multiply [^int mult1, ^int mult2]))

(definterface ^{Remote {}} CalculatorRemote
  (^int sum [^int add1, ^int add2])
  (^int multiply [^int mult1, ^int mult2]))

(deftype ^{Stateless {}} CalculatorImpl []
  CalculatorLocal
  CalculatorRemote
  (sum [this add1 add2]
    (+ add1 add2))
  (multiply [this mult1 mult2]
    (* mult1 mult2)))

; Copyright © 2010 Sattvik Software & Technology Resources, Ltd. Co.
; All rights reserved.
;
; ejb-annot may be used under the terms of either the GNU Lesser General Public
; License (LGPL) or the Eclipse Public License (EPL).  As a recipient of
; ejb-annot, you may choose which license to receive the code under.  See the
; LICENSE file distributed with sh-clojure for details.
;
; Written by Daniel Solano Gómez

(ns #^{:doc "An implementation of the simple stateful session bean example
            from OpenEJB.

            http://openejb.apache.org/3.0/simple-stateful-example.html"
       :author "Daniel Solano Gómez"}
  ejb.stateful
  (:import [javax.ejb Remote Stateful]))

(definterface CounterLocal
  (#^int increment [])
  (#^int reset []))

(definterface #^{Remote {}} CounterRemote
  (#^int increment [])
  (#^int reset []))

(gen-class
  :name #^{Stateful {}} ejb.stateful.CounterImpl
  :implements [ejb.stateful.CounterLocal
               ejb.stateful.CounterRemote]
  :state state
  :init init
  :prefix "-"
  :main false)

(defn -init []
  "Initialiser for CounterImpl, creates an atom of an integer"
  [[] (atom 0)])

(defn -increment [this]
  "Increments the state."
  (swap! (.state this) inc))

(defn -reset [this]
  "Resets the state to zero."
  (swap! (.state this) (constantly 0)))

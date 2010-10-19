; Copyright © 2010 Sattvik Software & Technology Resources, Ltd. Co.
; All rights reserved.
;
; ejb-annot may be used under the terms of either the GNU Lesser General Public
; License (LGPL) or the Eclipse Public License (EPL).  As a recipient of
; ejb-annot, you may choose which license to receive the code under.  See the
; LICENSE file distributed with sh-clojure for details.
;
; Written by Daniel Solano Gómez

(ns #^{:doc "Performs the tests on the simple stateful example using an
            embedded OpenEJB container."
       :author "Daniel Solano Gómez"}
  ejb.test-stateful
  (:import [ejb.stateful CounterLocal CounterRemote])
  (:use clojure.test
        ejb.test-utils.openejb))

(use-fixtures :once with-ejb-context)

(defn- counter-tests
  "A function that tests a Counter EJB’s functionality."
  [ejb]
  (is (= 0 (.reset ejb)))
  (is (= 1 (.increment ejb)))
  (is (= 2 (.increment ejb)))
  (is (= 0 (.reset ejb))))

(deftest test-stateless-ejb
  "Test the Counter EJB"
  (testing "Local interface"
    (let [ejb (.lookup *ejb-context* "CounterImplLocal")]
      (is (instance? CounterLocal ejb))
      (counter-tests ejb)))
  (testing "Remote interface"
    (let [ejb (.lookup *ejb-context* "CounterImplRemote")]
      (is (instance? CounterRemote ejb))
      (counter-tests ejb))))

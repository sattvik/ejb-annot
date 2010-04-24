; Copyright © 2010 Sattvik Software & Technology Resources, Ltd. Co.
; All rights reserved.
;
; ejb-annot may be used under the terms of either the GNU Lesser General Public
; License (LGPL) or the Eclipse Public License (EPL).  As a recipient of
; ejb-annot, you may choose which license to receive the code under.  See the
; LICENSE file distributed with sh-clojure for details.
;
; Written by Daniel Solano Gómez

(ns #^{:doc "Performs the tests on the simple stateless example using an
            embedded OpenEJB container."
       :author "Daniel Solano Gómez"}
  ejb.test-stateless
  (:import [ejb.stateless CalculatorLocal CalculatorRemote])
  (:use clojure.test
        ejb.test-utils.openejb))

(use-fixtures :once with-ejb-context)

(defn- calculator-tests
  "A function that tests a Calculator EJB’s functionality."
  [ejb]
  (is (= 5 (.sum ejb 2 3)))
  (is (= 6 (.multiply ejb 2 3))))

(deftest test-stateless-ejb
  "Test the Calculator EJB"
  (testing "Local interface"
    (let [ejb (.lookup *ejb-context* "CalculatorImplLocal")]
      (is (instance? CalculatorLocal ejb))
      (calculator-tests ejb)))
  (testing "Remote interface"
    (let [ejb (.lookup *ejb-context* "CalculatorImplRemote")]
      (is (instance? CalculatorRemote ejb))
      (calculator-tests ejb))))

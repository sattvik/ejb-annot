; Copyright © 2010 Sattvik Software & Technology Resources, Ltd. Co.
; All rights reserved.
;
; ejb-annot may be used under the terms of either the GNU Lesser General Public
; License (LGPL) or the Eclipse Public License (EPL).  As a recipient of
; ejb-annot, you may choose which license to receive the code under.  See the
; LICENSE file distributed with sh-clojure for details.
;
; Written by Daniel Solano Gómez

(ns #^{:doc "Provides utilities for performing tests with OpenEJB."
       :author "Daniel Solano Gómez"}
  ejb.test-utils.openejb
  (:import [javax.naming Context InitialContext])
  (:use clojure.contrib.properties))

(def #^{:doc "The default properties to use when constructing *ejb-context*."}
  default-context-properties
  {Context/INITIAL_CONTEXT_FACTORY "org.apache.openejb.client.LocalInitialContextFactory"
   "openejb.embedded.initialcontext.close" "destroy"})
  
(def #^{:doc "The context to use to look up EJBs.  This will be bound when
             using ejb-fixture."}
  *ejb-context* nil)

(defn with-ejb-context
  ([f]
   (with-ejb-context f default-context-properties))
  ([f props]
   (let [ejb-context (InitialContext. (as-properties props))]
     (binding [*ejb-context* ejb-context]
       (f))
     (.close ejb-context))))

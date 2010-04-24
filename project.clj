; Copyright © 2010 Sattvik Software & Technology Resources, Ltd. Co.
; All rights reserved.
;
; ejb-annot may be used under the terms of either the GNU Lesser General Public
; License (LGPL) or the Eclipse Public License (EPL).  As a recipient of
; ejb-annot, you may choose which license to receive the code under.  See the
; LICENSE file distributed with ejb-annot for details.
;
; Written by Daniel Solano Gómez

(defproject ejb-annot "0.0.1-SNAPSHOT"
  :description "A simple project that shows off the power of Clojure’s new
               annotations to create EJBs."
  :url "http://github.com/sattvik/ejb-annot"
  :dependencies [[org.clojure/clojure "1.2.0-master-SNAPSHOT"]
                 [org.clojure/clojure-contrib "1.2.0-SNAPSHOT"]
                 [org.apache.openejb/javaee-api "5.0-2"]]
  :dev-dependencies [[org.apache.openejb/openejb-core "3.1.2"]]
  :namespaces [ejb.stateless])

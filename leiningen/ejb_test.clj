; Copyright © 2010 Sattvik Software & Technology Resources, Ltd. Co.
; All rights reserved.
;
; ejb-annot may be used under the terms of either the GNU Lesser General Public
; License (LGPL) or the Eclipse Public License (EPL).  As a recipient of
; ejb-annot, you may choose which license to receive the code under.  See the
; LICENSE file distributed with sh-clojure for details.
;
; Written by Daniel Solano Gómez

(ns #^{:doc "A simple leiningen plug-in to perform the EJB tests.  I would have
            used the standard test plug-in except there are some class loader
            issues when OpenEJB runs."
       :author "Daniel Solano Gómez"}
  leiningen.ejb-test
  (:require lancet)
  (:import [org.apache.tools.ant.taskdefs Copy Delete])
  (:use [clojure.contrib [find-namespaces :only [find-namespaces-in-dir]]
                         [java-utils :only [file]]]
        [leiningen [compile :only [eval-in-project]]
                   [test :only [form-for-testing-namespaces]]]))

(defn ejb-handler
  "A handler function for leiningen's eval-in-project function.  It sets the
  task name and forces Java to fork into a new VM."
  [java]
  (doto java
    (.setTaskName "ejb-test")
    (.setFork true)))

(defn ejb-test
  "The ejb-test function accomplishes much the same job as the standard
  leiningen test.  There are two primary differences:

  1. ejb-test forces the tests to run a forked JVM.

  2. ejb-test copies the ejb-jar.xml file from the resources directory to the
  compile directory so that OpenEJB can find EJBs automatically."
  [project & namespaces]
  (let [namespaces (if (empty? namespaces)
                     (find-namespaces-in-dir (file (:test-path project)))
                     (map symbol namespaces))
        xml-path   "META-INF/ejb-jar.xml"]
    (doto (Copy.)
      (.setProject lancet/ant-project)
      (.setTaskName "copy")
      (.setTofile (file (:compile-path project) xml-path))
      (.setFile (file (:resources-path project) xml-path))
      (.execute))
    (eval-in-project project (form-for-testing-namespaces namespaces) ejb-handler)
    (doto (Delete.)
      (.setProject lancet/ant-project)
      (.setTaskName "delete")
      (.setFile (file (:compile-path project) xml-path))
      (.execute))))

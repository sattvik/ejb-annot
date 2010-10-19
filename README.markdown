ejb-annot
=========

This is a simple example of using Clojure’s new annotation features to generate
EJBs.


Running the tests
-----------------

As of version 0.2.0, ejb-annot is compiled using Maven.  In order to run the
tests, run `mvn test`.  Note that this will create a lot of log messages.  On
recent versions of the Oracle JDK, there will be a few stack traces, but these
are safe to ignore. The key output you will be looking for will be:

    Ran 2 tests containing 16 assertions.
    0 failures, 0 errors.

Alternatively, you can run `mvn jar:jar` to create the file
`target/ejb-annot-0.2.0.jar`, which you should be able to drop into any EJB
container.


License
-------

Copyright © 2010 Sattvik Software & Technology Resources, Ltd. Co.

sh-clojure may be used under the terms of either the GNU Lesser General Public
License (LGPL) or the Eclipse Public License (EPL).  As a recipient of
sh-clojure, you may choose which license to receive the code under.  See the
LICENSE file distributed with sh-clojure for details.


### GNU Lesser General Public License

sh-clojure is free software; you can redistribute it and/or modify it under the
terms of the GNU Lesser General Public License as published by the Free
Software Foundation; either version 3 of the License, or (at your option) any
later version.

sh-clojure is distributed in the hope that it will be useful, but WITHOUT ANY
WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more
details.

You should have received a copy of the GNU Lesser General Public License along
with sh-clojure.  If not, see <http://www.gnu.org/licenses/>.


### Eclipse Public License

This program and the accompanying materials are made available under the terms
of the Eclipse Public License v1.0 which accompanies this distribution, and is
available at <http://www.eclipse.org/legal/epl-v10.html>.

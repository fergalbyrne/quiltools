(defproject org.clojars.fergalbyrne/quiltools "0.1.2"
  :description "Functional geometry wrapper for quil"
  :url "https://github.com/fergalbyrne/quiltools"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [quil "3.1.0"]
                 [org.clojure/tools.namespace "1.0.0"]]
  :profiles {:dev {:plugins [[com.jakemccrary/lein-test-refresh "0.24.1"]
                             [venantius/ultra "0.6.0"]]}}
  :repl-options {:init-ns quiltools.core})

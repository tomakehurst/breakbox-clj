(defproject breakbox-clj "0.1.0-SNAPSHOT"
  :description "A small web API for configuring network faults"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [compojure "1.1.6"]]
  :plugins [[lein-ring "0.8.7"] [lein-midje "3.0.0"]]
  :ring {:handler breakbox.handler/app}
  :aot :all
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]
                        [midje "1.5.1"]]}})

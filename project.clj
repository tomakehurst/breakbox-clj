(defproject breakbox-clj "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [compojure "1.1.6"]]
  :plugins [[lein-ring "0.8.7"]]
  :ring {:handler breakbox-clj.handler/app}
  :aot :all
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]]}})

(ns breakbox-clj.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]))

(defroutes app-routes
  (GET "/" [] "Hello World")
  (GET "/yes" [] "<h1>YES!</h1>")
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))

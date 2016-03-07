(ns gitrepos.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [clostache.parser :as clostache]))

(defn read-template [template-name]
  (slurp (clojure.java.io/resource
    (str "templates/" template-name ".mustache"))))

(defn render-template [template-file params]
   (clostache/render (read-template template-file) params))

(defn index []
  (render-template "index" {:greeting "Bonjour"}))

(defroutes app-routes
  (GET "/" [] (index))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))

(ns game-of-life.controller
  (:require [cljfx.api :as fx]
            [clojure.core.async :as async]
            [game-of-life.view :as view]
            [game-of-life.model :as model])
   (:gen-class))

(def renderer 
  (fx/create-renderer
    :middleware  
    (fx/wrap-map-desc assoc :fx/type view/root)))

(defn run [interval]
  (println "Running with interval: " interval)
  (fx/mount-renderer
    model/*state
    renderer)
  (async/thread 
    (while true
      (do
        (flush)
        (Thread/sleep interval)
        (model/upd-state!)))))

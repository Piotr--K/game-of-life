(ns game-of-life.app
  (:require [game-of-life.controller :as cntrl])
  (:import [javafx.application Platform]))

(defn -main [& {:keys [interval_ms] :or {interval_ms 1000}}] ;call with default inteval value 1000ms if no args
  (Platform/setImplicitExit true)
    (println interval_ms)
  (cntrl/run interval_ms)) 

;; Could alswo be written as:
;; this method fails when calling via: clj -M:run-m "{:interval_ms 100}"
;; as argument is treated as string, so would need to be converted to map
;; something i don't understand why is not the case when calling via: clj -X:run-x "{:interval_ms 100}"
;; good overview of implementing clojure functions with multiple arities: 
;; https://erp12.github.io/post/clj-default-arg/
; (defn -main2 
;   ([]
;     (let [interval_ms 1000]
;       (Platform/setImplicitExit true)
;       (println interval_ms)
;       (cntrl/run interval_ms)))
;   ([{:keys [interval_ms]}]
;     (Platform/setImplicitExit true)
;     (println interval_ms)
;     (cntrl/run interval_ms)))

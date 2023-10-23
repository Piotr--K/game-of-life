(ns game-of-life.model 
  (:require
    [cljfx.api :as fx]))

(defn- create-grid [n]
  (vec (for [x (range (inc (- n 1)))]
    (vec (for [y (range (inc (- n 1)))]
      {:status (rand-nth [1 0]) :pos {:x x :y y}}))))) ;; Cell definition {:state 1/0 :pos {:x x :y y}}

(def *state
    (atom {:cells (create-grid 10)}))

(defn- get-cell-status [x y]
  (:status (get-in @*state [:cells x y])))

(defn- left-alive? [x y]
  (if (> x 0) 
    (get-cell-status (- x 1) y)
    0))

(defn- left-alive-top? [x y]
  (if (and (> x 0) (> y 0))
    (get-cell-status (- x 1) (- y 1))
    0))

(defn- right-alive? [x y]
  (if (< x (- (count (:cells @*state)) 1))
    (get-cell-status (+ x 1) y) 
    0))

(defn- right-alive-top? [x y]
  (if (and (< x (- (count (:cells @*state)))) (> y 0))
    (get-cell-status (+ x 1) (- y 1))
    0))

(defn- top-alive? [x y]
  (if (> y 0) 
    (get-cell-status x (- y 1))
    0))

(defn- bottom-alive? [x y]
  (if (< y (- (count (:cells @*state)) 1))
    (get-cell-status x (+ y 1))
    0))

(defn- left-alive-bottom? [x y]
  (if (and (> x 0) (< y (- (count (:cells @*state)) 1)))
    (get-cell-status (- x 1) (+ y 1))
    0))

(defn- right-alive-bottom? [x y]
  (if (and (< x (- (count (:cells @*state)) 1)) (< y (- (count (:cells @*state)) 1)))
    (get-cell-status (+ x 1) (+ y 1))
    0))

(defn- count-alive-neighbours [c]
  (let [x (get-in c [:pos :x])
        y (get-in c [:pos :y])]
    (+ (left-alive? x y) 
       (right-alive? x y) 
       (top-alive? x y) 
       (bottom-alive? x y)
       (left-alive-top? x y)
       (right-alive-top? x y)
       (left-alive-bottom? x y)
       (right-alive-bottom? x y))))

(defn upd-cell-state [cell new-status]
  (assoc cell :status new-status))

(defn- should-survive? [alive-count current-status]
  (and (or (= alive-count 3) (= alive-count 2)) (= current-status 1)))

(defn- should-become-alive? [alive-count current-status]
  (and (= alive-count 3) (= current-status 0)))

(defn- update-cell! [cell]
  (let [alive-count (count-alive-neighbours cell)]
    (cond
      (should-survive? alive-count (:status cell)) (upd-cell-state cell 1)
      (should-become-alive? alive-count (:status cell)) (upd-cell-state cell 1)
      :else (upd-cell-state cell 0))))

(defn upd-state! []
  (swap! *state update-in [:cells]
    (fn [cells]
      (mapv (fn [row]
        (mapv update-cell! row))
        cells))))

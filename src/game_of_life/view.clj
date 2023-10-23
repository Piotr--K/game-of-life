(ns game-of-life.view
  (:require [cljfx.api :as fx])
  (:gen-class))

(defn grid-game-of-life [{:keys  [cells]}]
    (let [
        num-rows (count cells)
        num-cols (if (seq cells) (count (first cells)) 0)]
    {:fx/type :grid-pane
      :style {:-fx-grid-lines-visible :true}

        :children (for [row (range num-rows)
                        col (range num-cols)]
          {:fx/type :label
           :grid-pane/column col 
           :grid-pane/row row
           :style {:-fx-background-color (if (= 1 (:status (get-in cells [row col]))) :gray :none)
                   :fx/min-width 55
                   :fx/min-height 55
                   :fx/pref-width 55
                   :fx/pref-height 55}
           :text (str "Status: " (:status (get-in cells [row col])))})}))
  
(defn root [{:keys [cells]}]
    {:fx/type :stage
     :showing true
     :title "game of life"
     :scene {:fx/type :scene
             :root {:fx/type :v-box
                    :padding 25
                    :spacing 40
                    :children [{:fx/type grid-game-of-life :cells cells}]}}})

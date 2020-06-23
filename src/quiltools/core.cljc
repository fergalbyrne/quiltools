(ns quiltools.core
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(defn stroke-weight []
  (.strokeWeight (q/current-graphics)))

(defn spin [theta p]
  (fn []
    (q/with-rotation [theta] (p))))

(defn at [x y p]
  (fn []
    (q/with-translation [x y] (p))))

(defn in [w h p]
  (fn []
    (q/push-matrix)
    (q/push-style)
    (q/stroke-weight (/ (stroke-weight) (/ (+ w h) 2)))
    (q/scale w h)
    (p)
    (q/pop-style)
    (q/pop-matrix)))

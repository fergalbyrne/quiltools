(ns quiltools.core
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(defn stroke-weight []
  "gets the stroke weight of the current graphics context"
  (.strokeWeight (q/current-graphics)))

(defn spin [theta p]
  "returns a draw-fn which draws `p` at angle `theta`"
  (fn []
    (q/with-rotation [theta] (p))))

(defn at [x y p]
  "returns a draw-fn which draws `p` at origin `[x y]`"
  (fn []
    (q/with-translation [x y] (p))))

(defn in [w h p]
  "returns a draw-fn which draws `p` in a box of size `[w h]`"
  (fn []
    (q/push-matrix)
    (q/push-style)
    (q/stroke-weight (/ (stroke-weight) (/ (+ w h) 2)))
    (q/scale w h)
    (p)
    (q/pop-style)
    (q/pop-matrix)))

(ns quiltools.core
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(defn stroke-weight
  "gets the stroke weight of the current graphics context"
  []
  (.strokeWeight (q/current-graphics)))

(defn spin
  "returns a draw-fn which draws `p` at angle `theta`"
  [theta p]
  (fn []
    (q/with-rotation [theta] (p))))

(defn at
  "returns a draw-fn which draws `p` at origin `[x y]`"
  [x y p]
  (fn []
    (q/with-translation [x y] (p))))

(defn in
  "returns a draw-fn which draws `p` in a box of size `[w h]`"
  [w h p]
  (fn []
    (q/push-matrix)
    (q/push-style)
    (q/stroke-weight (/ (stroke-weight) (/ (+ w h) 2)))
    (q/scale w h)
    (p)
    (q/pop-style)
    (q/pop-matrix)))

(defn divides?
  "returns `true` if `d` divides `n`"
  [n d]
  (zero? (mod n d)))

(defn n-ticks?
  "returns true only every `n` ticks"
  [n]
  (divides? (q/frame-count) n))

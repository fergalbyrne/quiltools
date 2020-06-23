(ns quiltools.core-test
  (:require [clojure.test :refer :all]
            [quiltools.core :refer :all]
            [quil.core :as q]
            [quil.middleware :as m]))

(defn wheel []
  (q/arc 0.5 0 1 1 0 q/PI))

(defn car []
  (let [h 0.3
        y (- 1 h)
        x 0.05]
    (q/rect 0 0.4 1 (- y 0.4))
    ((->> wheel (in h h) (at x y)))
    ((->> wheel (in h h) (at (- 1 h x) y)))))

(defn envelope []
  (q/rect 0 0 1 1)
  (q/arc 0.5 0 1 1 0 q/PI))

(defn setup []
  (q/frame-rate 50)
  (q/color-mode :hsb)
  {:color 0
   :angle 0})

(defn update-state [state]
  (let [{:keys [color angle]} state]
    {:color (mod (+ color 0.7) 255)
     :angle (mod (+ angle 0.02) q/TWO-PI)}))

(defn draw-state [state]
  (q/background 240)
  (q/fill (:color state) 255 255)
  (let [angle (:angle state)
        x (* 150 (q/cos angle))
        y (* 150 (q/sin angle))]
    (q/with-translation [(/ (q/width) 2)
                         (/ (q/height) 2)]
      ((->> envelope (in 100 100) (spin (- q/QUARTER-PI angle))))
      ((->> car (in 50 50) (spin (* angle 2)) (at x y))))))

(deftest a-test
  (testing "FIXME, I fail."
    (is (= 0 1))))

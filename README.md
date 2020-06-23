# quiltools

Functional geometry wrapper for quil

[![Clojars Project](https://img.shields.io/clojars/v/org.clojars.fergalbyrne/quiltools.svg)](https://clojars.org/org.clojars.fergalbyrne/quiltools)

## Usage

### Leiningen/Boot

    [org.clojars.fergalbyrne/quiltools "0.1.1"]

### Clojure CLI/deps.edn

    org.clojars.fergalbyrne/quiltools {:mvn/version "0.1.1"}

### Example:

```clojure
(ns quilplayground
  (:require [quiltools.core :refer :all]
            [quil.core :as q]
            [quil.middleware :as m]))

(defn wheel []
  "draws an inverted semicircle in the top half of its box"
  (q/arc 0.5 0 1 1 0 q/PI))

(defn car []
  "draws a car"
  (let [h 0.3
        y (- 1 h)
        x 0.05]
    (q/rect 0 0.4 1 (- y 0.4))
    ((->> wheel (in h h) (at x y)))
    ((->> wheel (in h h) (at (- 1 h x) y)))))

(defn envelope []
  "draws an envelope"
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
;
(q/defsketch quil-playground
  :title "You spin my circle right round"
  :size [500 500]
  :setup setup
  :update update-state
  :draw draw-state
  :features [:keep-on-top]
  :middleware [m/fun-mode])

```

The functions `wheel`, `car` and `envelope` draw their graphics in a box with origin [0 0] and size [1 1].
`quiltools` provides the functions `at`, `in` and `spin` which each take a drawing-function as last argument,
returning a drawing function which draws *at* a location, *in* a box of given size, and with a given *spin* angle.

To draw a `car` in a given sized box, at a given point, with a given spin, pipe the `car` function and call the result:

```clojure
((->> car (in 50 50) (spin (* angle 2)) (at x y)))
```

## License

Copyright © 2020 Fergal Byrne & Louise Klodt

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.

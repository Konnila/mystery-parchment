(ns parchment.core
  (:gen-class)
  (:require [clojure.java.io :refer [resource]]))

(use 'mikera.image.core)
(use 'mikera.image.colours)

(defn get-resource [path-to-file]
  (-> path-to-file resource load-image))

(defn get-image-pixels [img]
  (get-pixels img))

(def secret-message
  (str 
    "OCDNDNVNZXMZOGDNOJANKZGGNOCVODCVQZDIQZIOZYHTN"
    "ZGAGVIBGJXFBGPZNOCZOJIBPZOJOCZMJJAJAOCZHJPOCGZ" 
    "QDXJMKPNGDAONOCZCZQDXODHDIOJOCZVDMWTOCZDMVIFGZ" 
    "GDWZMVXJMKPNDNVXJPIOZMEDISAJMGZQDXJMKPNHPAAGDV" 
    "OJADGGNDONOVMBZONZVMNRDOCPIDYZIODADVWGZWPUUDIB" 
    "NZXOPHNZHKMVDNAJMZIZHDZN"))

(defn replace-pixels-for-color
  "Given a primitive int of color to replace, replaces it with a black pixel"
  [file primitive-int-of-color-to-replace]
  (let [pixels (get-image-pixels file)]
    (dotimes [i (count pixels)]
      (let [rgbint (aget pixels i)]
        (when (= rgbint primitive-int-of-color-to-replace) (aset pixels i (rgb-from-components 0 0 0)))))
    (set-pixels file pixels)))

(defn decrypt-caesar-message 
  "assumes shifts-to-right to be between 0 and 25." 
  [message shifts-to-right]
  (let [alphabet "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        shifted-alphabet (->> (cycle alphabet) (take 52) (drop shifts-to-right))
        relation-zipmap (zipmap alphabet shifted-alphabet)] ;map the original alphabets to our new shifted alphabet for easy lookup with a key-value map
          (apply str (map relation-zipmap message)))) ;map every char in message to the corresponding value present in relation-zipmap and create a string of the result

(defn -main
  "Solves mysteries with ease!"
  [& args]
    (let [mysterious-file-buffer (get-resource "parchment.png")]
      ;Note: -199194 hardcoded primitive int that I found out by manual testing the rgb colors of the pixels in this file.
      ;The -199194 is signed 32int representation of 0xFFFCF5E6 which translates to rgb values of R:252, G:245, B:230.
      ;There was one another color very close, but I picked this one since it was the non-prevalent one of the two.
      (replace-pixels-for-color mysterious-file-buffer -199194)
      (show mysterious-file-buffer)
      ;; now we can see the mysterious message, let's magically decipher with 5 shifts to right. Not like I tested this to be the right one or anything.
      (println (decrypt-caesar-message secret-message 5))))


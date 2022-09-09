# Parchment

A coding challenge. If you know you know :).

Huge shout-out to @mikera and his https://github.com/mikera/imagez image processing library. Never used it before, but seemed to work very well :).

All code can be found in core.clj file under src folder.

## Pre-Requisites
Java, personally ran this with java 9. Should work with 8+ (not tested).

If running with lein, clojure 1.4 or above required for imagez library.

## Usage

In project root, with just java installed you can run this uberjar with

    $ java -jar parchment-0.1.0-standalone.jar

If you have clojure + leiningen installed, in root folder you can also do

    $ lein run

An image with the correct spell should pop up, and from console/terminal you can see it deciphered.

It takes around 7 seconds to manipulate the image on my subpar computer.

## License

Copyright © 2022 Toni Könnilä

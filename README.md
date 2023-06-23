# usda-java-flour-scan

written by @nico6bury

wjrfoot/ars-spieru by @wjrfoot used as reference for twain scanner interactions and imagej java processing

## Description

This repository has the purpose of collecting images from an EPSON scanner, saving the images, running them through imagej, and then processing that output for the purpose of analyzing flour samples.

## Package Explanation

### Config

This package is used for serialization and deserialization of various configuration options. Classes are structures such that ConfigConfig is used for actually reading and writing to files, and the other classes are more like structs for just storing the properties and their defaults.

### Scan

This package is specifically used for communicating with the scanner and getting images. It doesn't do a whole lot else.

### Utils

This package is meant to keep smaller classes or collections of static functions for various uses in other packages.

### View

This package contains the GUI, as one might expect. The main method in MainWindow.java is meant to be the entry point for the application, and in MVC terms, this package serves as both the view and controller, coordinating the other packages based on user selections. The GUI itself is built off Swing. The initial calls are done in the MainWindow constructor, and otherwise things are triggered by button clicks by the user.

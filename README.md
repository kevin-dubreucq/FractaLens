# FractaLens - A Fractal Generator Library Developed in Lens.

This project provides a *Java* library for generating fractal images.
It allows users to choose from a variety of complex sequences, including the
Julia set, Mandelbrot set, and other generalized sequences, to create
static or animated fractal images using different color palettes and
transformations.

## Overview

This library is composed of the following classes:

```plantuml
hide empty members
skinparam linetype ortho
skinparam packageStyle rectangle

' --------------- '
' Complex Numbers '
' --------------- '

package "complex" {

    interface IComplex {
        + {abstract} getRealPart() : double
        + {abstract} getImaginaryPart() : double
        + {abstract} abs() : double
        + {abstract} negate() : IComplex
        + {abstract} conjugate() : IComplex
        + {abstract} add(other : IComplex) : IComplex
        + {abstract} subtract(other : IComplex) : IComplex
        + {abstract} multiply(value : double) : IComplex
        + {abstract} multiply(other : IComplex) : IComplex
        + {abstract} divide(other : IComplex) : IComplex
        + {abstract} pow(p : int) : IComplex
    }

    class Complex implements IComplex {
        - realPart : double
        - imaginaryPart : double
        + Complex(realPart : double, imaginaryPart : double)
    }

    interface IComplexPlane {
        + {abstract} asComplex(row : int, column : int) : IComplex
    }

    class ComplexPlane implements IComplexPlane {
        - height : int
        - width : int
        + ComplexPlane(height : int, width : int)
    }

    class ScaledComplexPlane implements IComplexPlane {
        - zoomFactor : double
        + ScaledComplexPlane(plane : IComplexPlane, zoomFactor : double)
    }

    class TranslatedComplexPlane implements IComplexPlane {
        - center : IComplex
        + TranslatedComplexPlane(plane : IComplexPlane, center : IComplex)
    }

    IComplexPlane --> IComplex : << creates >>
    ScaledComplexPlane o--> "1" IComplexPlane : plane
    TranslatedComplexPlane o--> "1" IComplexPlane : plane
}

' ----------------- '
' Complex Sequences '
' ----------------- '

package "complex.sequence" {

    interface IComplexSequence extends Iterable<IComplex> {
        + {abstract} computeNext(previous : IComplex) : IComplex
        + {abstract} iterator() : Iterator<IComplex>
    }

    class ComplexIterator implements Iterator<IComplex> {
        - maxIterations : int
        - compteurIteration : int
        - currentNumber : IComplex
        + ComplexIterator(currentNumber : IComplex, sequence : IComplexSequence, maxIterations : int)
        + hasNext() : boolean
        + next() : IComplex
        + getSequence() : IComplexSequence
        + setCurrentNumber(currentNumber : IComplex) : void
        + setMaxIterations(maxIterations : int) : void
        + setSequence(sequence : IComplexSequence) : void
    }

    class ComplexSequence implements IComplexSequence {
        - first : IComplex
        - nbTerms : int
        - function : BinaryOperator<IComplex>
        + ComplexSequence(first : IComplex, nbTerms : int, function : BinaryOperator<IComplex>)
        + {static} createMandelbrot(first : IComplex, nbTerms : int) : IComplexSequence
        + {static} createMandelbrotGeneric(first : IComplex, nbTerms : int, p : int) : IComplexSequence
        + {static} createTricorn(first : IComplex, nbTerms : int, constant : IComplex) : IComplexSequence
        + {static} createTricornGeneric(first : IComplex, nbTerms : int, constant : IComplex, p : int) : IComplexSequence
        + {static} createBurningShip(first : IComplex, nbTerms : int) : IComplexSequence
        + {static} genericBurningShip(z0 : IComplex, maxIter : int, constant : IComplex, p : int) : IComplexSequence
        + {static} createJuliaGeneric(z0 : IComplex, maxIter : int, constant : IComplex) : IComplexSequence
    }

    class JuliaSequence implements IComplexSequence {
        - first : IComplex
        - constant : IComplex
        - nbTerms : int
        + JuliaSequence(first : IComplex, constant : IComplex, nbTerms : int)
    }

    class MandelbrotSequence implements IComplexSequence {
        - first : IComplex
        - nbTerms : int
        + MandelbrotSequence(first : IComplex, nbTerms : int)
    }

    class MultiJuliaSequence implements IComplexSequence {
        - first : IComplex
        - constant : IComplex
        - power : int
        - nbTerms : int
        + MultiJuliaSequence(first : IComplex, constant : IComplex, power : int, nbTerms : int)
    }

    class MultiBrotSequence implements IComplexSequence {
        - first : IComplex
        - power : int
        - nbTerms : int
        + MultiBrotSequence(first : IComplex, power : int, nbTerms : int)
    }

    enum ComplexSequenceType {
        JULIA
        MANDELBROT
        BURNING_SHIP
        MULTIBROT_3
        TRICORN
        CUSTOM_SEQUENCE
        + getFactory() : BiFunction<IComplex, Integer, IComplexSequence>
    }

    IComplexSequence --> IComplex : << generates >>
    ComplexIterator --> "1" IComplexSequence : sequence
    ComplexSequence ..> ComplexIterator : << creates >>
    JuliaSequence ..> ComplexIterator : << creates >>
    MandelbrotSequence ..> ComplexIterator : << creates >>
    MultiJuliaSequence ..> ComplexIterator : << creates >>
    MultiBrotSequence ..> ComplexIterator : << creates >>
    ComplexSequenceType ..> ComplexSequence : << creates >>
}

' ------------- '
' Color Palette '
' ------------- '

package "color" {

    interface IColorPalette {
        + {abstract} getColor(iterations : int, maxIterations : int) : Color
    }

    class GrayscalePalette implements IColorPalette

    class GradientPalette implements IColorPalette {
        - color1 : Color
        - color2 : Color
        + GradientPalette(color1 : Color, color2 : Color)
    }

    class FixedColorPalette implements IColorPalette {
        - {static} COLORS : Color[]
    }

    class InvertedColorPalette implements IColorPalette {
        + InvertedColorPalette(palette : IColorPalette)
    }

    class ColorMaskPalette implements IColorPalette {
        - keepRed : boolean
        - keepGreen : boolean
        - keepBlue : boolean
        + ColorMaskPalette(palette : IColorPalette, keepRed : boolean, keepGreen : boolean, keepBlue : boolean)
    }

    enum ColorPaletteType {
        GRAYSCALE
        GRADIENT_YELLOW_RED
        GRADIENT_BLUE_BLACK
        GRADIENT_BLACK_YELLOW
        INVERTED_GRAYSCALE
        FIXED
        + getPalette() : IColorPalette
    }

    InvertedColorPalette o--> "1" IColorPalette : palette
    ColorMaskPalette o--> "1" IColorPalette : palette
    ColorPaletteType --> "*" IColorPalette : << provides >>
}

' -------------- '
' Image Handling '
' -------------- '

package "image" {

    interface IFractalImage {
        + {abstract} getHeight() : int
        + {abstract} getWidth() : int
        + {abstract} setColor(row : int, column : int, color : Color) : void
        + {abstract} saveAs(path : String) : void
    }

    class FractalImage implements IFractalImage {
        - image : BufferedImage
        + FractalImage(width : int, height : int)
        + getImage() : BufferedImage
    }
}

' ------------------ '
' Fractal Generation '
' ------------------ '

package "generator" {

    interface IFractalGenerator {
        + {abstract} generateImage() : IFractalImage
        + {abstract} computePixelColor(row : int, column : int) : Color
        + {abstract} saveImage() : void
    }

    class FractalGenerator implements IFractalGenerator {
        - width : int
        - height : int
        - maxIterations : int
        - outputPath : String
        - sequenceFactory : BiFunction<IComplex, Integer, IComplexSequence>
        + FractalGenerator(width : int, height : int, complexPlane : IComplexPlane, sequenceFactory : BiFunction<IComplex, Integer, IComplexSequence>, colorPalette : IColorPalette, maxIterations : int, outputPath : String)
    }

    class AnimatedFractalGenerator implements IFractalGenerator {
        - outputPath : String
        - delayMs : int
        + AnimatedFractalGenerator(generators : List<IFractalGenerator>, outputPath : String, frameDelayMs : int)
    }

    class FractalGeneratorBuilder {
        - width : int
        - height : int
        - scale : double
        - focusX : double
        - focusY : double
        - maxIterations : int
        - outputPath : String
        - frameCount : int
        - zoomFactor : double
        - frameDelayMs : int
        - isRotationAnimation : boolean
        + width(width : int) : FractalGeneratorBuilder
        + height(height : int) : FractalGeneratorBuilder
        + dimensions(width : int, height : int) : FractalGeneratorBuilder
        + scale(scale : double) : FractalGeneratorBuilder
        + zoom(zoom : double) : FractalGeneratorBuilder
        + focus(focusX : double, focusY : double) : FractalGeneratorBuilder
        + focus(focus : IComplex) : FractalGeneratorBuilder
        + maxIterations(maxIterations : int) : FractalGeneratorBuilder
        + sequence(sequenceFactory : BiFunction<IComplex, Integer, IComplexSequence>) : FractalGeneratorBuilder
        + sequence(sequenceType : ComplexSequenceType) : FractalGeneratorBuilder
        + palette(colorPalette : IColorPalette) : FractalGeneratorBuilder
        + palette(paletteType : ColorPaletteType) : FractalGeneratorBuilder
        + outputPath(outputPath : String) : FractalGeneratorBuilder
        + zoomAnimation(frameCount : int, zoomFactor : double, frameDelayMs : int) : FractalGeneratorBuilder
        + rotationAnimation(frameCount : int, initialC : IComplex, frameDelayMs : int) : FractalGeneratorBuilder
        + build() : IFractalGenerator
    }

    AnimatedFractalGenerator o--> "1..*" IFractalGenerator : frames
}

' --------------------- '
' Applications (client) '
' --------------------- '

package "fractalens" {

    class Main {
        + {static} main(args : String[]) : void
    }

    class FractalCli implements Runnable {
        - fractalName : ComplexSequenceType
        - height : int
        - width : int
        - scale : double
        - focusX : double
        - focusY : double
        - nbIterations : int
        - paletteName : ColorPaletteType
        - type : String
        - output : String
        - frameCount : int
        - zoomFactor : double
        - frameDelay : int
        + run() : void
        + {static} main(args : String[]) : void
    }
}

' ------------------------ '
' Dependencies between     '
' packages                 '
' ------------------------ '

IFractalGenerator --> IFractalImage : << produces >>
IFractalGenerator --> IComplexSequence : << uses >>
FractalGenerator --> "1" IComplexPlane : complexPlane
FractalGenerator --> "1" IColorPalette : colorPalette
FractalGenerator ..> FractalImage : << creates >>
AnimatedFractalGenerator ..> FractalImage : << reads >>
FractalGeneratorBuilder ..> IFractalGenerator : << builds >>
FractalGeneratorBuilder ..> ComplexSequenceType : << uses >>
FractalGeneratorBuilder ..> ColorPaletteType : << uses >>
FractalGeneratorBuilder ..> ComplexPlane : << creates >>
FractalGeneratorBuilder ..> ScaledComplexPlane : << creates >>
FractalGeneratorBuilder ..> TranslatedComplexPlane : << creates >>
Main ..> FractalGeneratorBuilder : << uses >>
FractalCli ..> FractalGeneratorBuilder : << uses >>
```

## Feature list

| Features                                               | Design Pattern(s) (?)                          | Author(s)                                  |
|--------------------------------------------------------|------------------------------------------------|--------------------------------------------|
| Complex number                                         | Immutable object                               | Lucas Vanstraceele                         |
| Default complex plane                                  | Decorator (concrete component)                 | Thomas Barloy                              |
| Scaled complex plane                                   | Decorator                                      | Kévin Dubreucq                             |
| Translated complex plane                               | Decorator                                      | Lucas Vanstraceele                         |
| Sequence of complex numbers                            | Strategy, Static Factory Method                | Thomas Barloy                              |
| Iterator over a sequence of complex numbers            | Iterator                                       | Kévin Dubreucq, Thomas Barloy              |
| Julia sequence                                         | Strategy (concrete strategy)                   | Maxence Beauvais                           |
| Mandelbrot sequence                                    | Strategy (concrete strategy)                   | Maxence Beauvais                           |
| Multijulia sequence                                    | Strategy (concrete strategy)                   | Lucas Vanstraceele                         |
| Multibrot sequence                                     | Strategy (concrete strategy)                   | Kévin Dubreucq                             |
| Generic sequences (Burning Ship, Tricorn, generalized) | Strategy, Static Factory Method                | Thomas Barloy, Maxence Beauvais            |
| Enumeration of the sequence types                      | Factory (enum-based)                           | Thomas Barloy                              |
| Color palette                                          | Strategy                                       | Lucas Vanstraceele                         |
| Grey scale palette                                     | Strategy (concrete strategy)                   | Lucas Vanstraceele                         |
| Red/Green/Blue/... scale palette                       | Decorator                                      | Lucas Vanstraceele                         |
| Inverted color palette                                 | Decorator                                      | Maxence Beauvais                           |
| Gradient-based color palette                           | Strategy (concrete strategy)                   | Thomas Barloy                              |
| Predefined color palette                               | Strategy (concrete strategy)                   | Kévin Dubreucq                             |
| Enumeration of the color palette types                 | Factory (enum-based)                           | Kévin Dubreucq                             |
| `BufferedImage` used as an `IFractalImage`             | Adapter                                        | Maxence Beauvais                           |
| Static image generation                                | Strategy (context)                             | Thomas Barloy                              |
| Scale-based animated image generation                  | Composite                                      | Thomas Barloy, Maxence Beauvais            |
| Rotation-based animated image generation               | Composite                                      | Thomas Barloy, Lucas Vanstraceele          |
| Configuration of the fractal generator                 | Builder (fluent interface)                     | Lucas Vanstraceele, Maxence Beauvais       |
| Command-line interface                                 | Command (picocli)                              | Lucas Vanstraceele                         |

## Team

This project has been developed by:

- Dubreucq Kévin
- Vanstraceele Lucas
- Beauvais Maxence
- Barloy Thomas
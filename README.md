# FractaLens - A Fractal Generator Library Developed in Lens.

This project provides a *Java* library for generating fractal images.
It allows users to choose from a variety of complex sequences, including the
Julia set, Mandelbrot set, and other generalized sequences, to create
static or animated fractal images using different color palettes and
transformations.

## Overview

This library is composed of the following packages. The first diagram gives the
big picture; the following ones detail each group of packages.

### Global view

```plantuml
hide members
hide circle
skinparam nodesep 30
skinparam ranksep 40
skinparam packageStyle rectangle

package "complex" {
    interface IComplex
    interface IComplexPlane
    class Complex
    class ComplexPlane
    class ScaledComplexPlane
    class TranslatedComplexPlane
}

package "complex.sequence" {
    interface IComplexSequence
    class ComplexIterator
    class ComplexSequence
    class JuliaSequence
    class MandelbrotSequence
    class MultiJuliaSequence
    class MultiBrotSequence
    enum ComplexSequenceType
}

package "color" {
    interface IColorPalette
    class GrayscalePalette
    class GradientPalette
    class FixedColorPalette
    class InvertedColorPalette
    class ColorMaskPalette
    enum ColorPaletteType
}

package "image" {
    interface IFractalImage
    class FractalImage
}

package "generator" {
    interface IFractalGenerator
    class FractalGenerator
    class AnimatedFractalGenerator
    class FractalGeneratorBuilder
}

package "fractalens" {
    class Main
    class FractalCli
}

Main ..> FractalGeneratorBuilder
FractalCli ..> FractalGeneratorBuilder
FractalGeneratorBuilder ..> IFractalGenerator : builds
FractalGenerator --> IComplexPlane
FractalGenerator --> IComplexSequence
FractalGenerator --> IColorPalette
FractalGenerator ..> IFractalImage : produces
```

### Complex numbers and planes

```plantuml
hide empty members
skinparam nodesep 30

interface IComplex {
    + getRealPart() : double
    + getImaginaryPart() : double
    + abs() : double
    + negate() : IComplex
    + conjugate() : IComplex
    + add(other : IComplex) : IComplex
    + subtract(other : IComplex) : IComplex
    + multiply(value : double) : IComplex
    + multiply(other : IComplex) : IComplex
    + divide(other : IComplex) : IComplex
    + pow(p : int) : IComplex
}

interface IComplexPlane {
    + asComplex(row : int, column : int) : IComplex
}

class Complex
class ComplexPlane {
    - height : int
    - width : int
}
class ScaledComplexPlane {
    - zoomFactor : double
}
class TranslatedComplexPlane {
    - center : IComplex
}

IComplex <|.. Complex
IComplexPlane <|.. ComplexPlane
IComplexPlane <|.. ScaledComplexPlane
IComplexPlane <|.. TranslatedComplexPlane
ScaledComplexPlane o--> IComplexPlane : plane
TranslatedComplexPlane o--> IComplexPlane : plane
IComplexPlane ..> IComplex : creates
```

### Complex sequences

```plantuml
hide empty members
skinparam nodesep 30

interface IComplexSequence extends Iterable {
    + computeNext(previous : IComplex) : IComplex
    + iterator() : Iterator<IComplex>
}

class ComplexIterator implements Iterator {
    - maxIterations : int
    - currentNumber : IComplex
    + hasNext() : boolean
    + next() : IComplex
}

class ComplexSequence {
    - function : BinaryOperator<IComplex>
    + {static} createMandelbrot(...)
    + {static} createMandelbrotGeneric(...)
    + {static} createTricorn(...)
    + {static} createTricornGeneric(...)
    + {static} createBurningShip(...)
    + {static} genericBurningShip(...)
    + {static} createJuliaGeneric(...)
}

class JuliaSequence {
    - constant : IComplex
}
class MandelbrotSequence
class MultiJuliaSequence {
    - constant : IComplex
    - power : int
}
class MultiBrotSequence {
    - power : int
}

enum ComplexSequenceType {
    JULIA
    MANDELBROT
    BURNING_SHIP
    MULTIBROT_3
    TRICORN
    CUSTOM_SEQUENCE
    + getFactory() : BiFunction
}

IComplexSequence <|.. ComplexSequence
IComplexSequence <|.. JuliaSequence
IComplexSequence <|.. MandelbrotSequence
IComplexSequence <|.. MultiJuliaSequence
IComplexSequence <|.. MultiBrotSequence
ComplexIterator --> IComplexSequence : sequence
IComplexSequence ..> ComplexIterator : creates
ComplexSequenceType ..> ComplexSequence : creates
```

### Color palettes

```plantuml
hide empty members
skinparam nodesep 30

interface IColorPalette {
    + getColor(iterations : int, maxIterations : int) : Color
}

class GrayscalePalette
class GradientPalette {
    - color1 : Color
    - color2 : Color
}
class FixedColorPalette
class InvertedColorPalette
class ColorMaskPalette {
    - keepRed : boolean
    - keepGreen : boolean
    - keepBlue : boolean
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

IColorPalette <|.. GrayscalePalette
IColorPalette <|.. GradientPalette
IColorPalette <|.. FixedColorPalette
IColorPalette <|.. InvertedColorPalette
IColorPalette <|.. ColorMaskPalette
InvertedColorPalette o--> IColorPalette : palette
ColorMaskPalette o--> IColorPalette : palette
ColorPaletteType ..> IColorPalette : provides
```

### Images, generators and clients

```plantuml
hide empty members
skinparam nodesep 40
skinparam ranksep 40

package "image" {
    interface IFractalImage {
        + getHeight() : int
        + getWidth() : int
        + setColor(row : int, column : int, color : Color) : void
        + saveAs(path : String) : void
    }
    class FractalImage {
        - image : BufferedImage
        + getImage() : BufferedImage
    }
}

package "generator" {
    interface IFractalGenerator {
        + generateImage() : IFractalImage
        + computePixelColor(row : int, column : int) : Color
        + saveImage() : void
    }
    class FractalGenerator {
        - width : int
        - height : int
        - maxIterations : int
        - outputPath : String
    }
    class AnimatedFractalGenerator {
        - outputPath : String
        - delayMs : int
    }
    class FractalGeneratorBuilder {
        + dimensions(width, height)
        + scale(scale) / zoom(zoom)
        + focus(x, y) / focus(focus)
        + maxIterations(n)
        + sequence(type)
        + palette(type)
        + outputPath(path)
        + zoomAnimation(frames, factor, delay)
        + rotationAnimation(frames, c, delay)
        + build() : IFractalGenerator
        + zoom(List<IFractalGenerator> generators)
        + rotation(List<IFractalGenerator> generators)
    }
}

package "fractalens" {
    class Main
    class FractalCli
}

IFractalImage <|.. FractalImage
IFractalGenerator <|.. FractalGenerator
IFractalGenerator <|.. AnimatedFractalGenerator
AnimatedFractalGenerator o--> "1..*" IFractalGenerator : frames
IFractalGenerator ..> IFractalImage : produces
FractalGenerator ..> FractalImage : creates
FractalGeneratorBuilder ..> IFractalGenerator : builds
Main ..> FractalGeneratorBuilder
FractalCli ..> FractalGeneratorBuilder
```

## Feature list

<<<<<<< README.md
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

## Command-line usage

FractaLens can be used directly from a terminal, without writing any Java code.

### Running the command

From the root of the project, use the Gradle wrapper (on Windows, replace `./gradlew` by `gradlew.bat`):

```bash
./gradlew run --args="<options>"
```

For instance, the following command generates a Mandelbrot image named `fractal.png`
in the current directory (all the options have a default value):

```bash
./gradlew run
```

> **Tip:** `./gradlew run` executes the command from the project directory,
> so a relative output path (such as `fractal.png`) is created there.

### Options

| Option                       | Description                                                     | Default       |
|------------------------------|-----------------------------------------------------------------|---------------|
| `-f`, `--fractal-name`       | Fractal to generate (see the list below)                        | `MANDELBROT`  |
| `-w`, `--width`              | Width of the image, in pixels                                   | `800`         |
| `-h`, `--height`             | Height of the image, in pixels                                  | `800`         |
| `-s`, `--scale`              | Scale of the image: the smaller it is, the more zoomed it is    | `0.001`       |
| `-x`, `--focus-x`            | Real part of the center of the image                            | `0.0`         |
| `-y`, `--focus-y`            | Imaginary part of the center of the image                       | `0.0`         |
| `-n`, `--nb-iterations`      | Maximum number of iterations                                    | `250`         |
| `-p`, `--palette-name`       | Color palette (see the list below)                              | `GRAYSCALE`   |
| `-t`, `--type`               | Type of image: `single`, `zoom` or `rotation`                   | `single`      |
| `-o`, `--output`             | Output file (its extension gives the format: `png`, `gif`...)   | `fractal.png` |
| `--frames`                   | Number of frames of an animation                                | `30`          |
| `--zoom-factor`              | Factor applied to the scale at each frame of a `zoom` animation | `0.85`        |
| `--delay`                    | Delay between two frames of an animation, in milliseconds       | `80`          |
| `--help`                     | Displays the help and exits                                     |               |
| `-V`, `--version`            | Displays the version and exits                                  |               |

> **Be careful:** `-h` stands for `--height`. To display the help, use `--help`.

Available fractals (`-f`): `JULIA`, `MANDELBROT`, `BURNING_SHIP`, `MULTIBROT_3`, `TRICORN`, `CUSTOM_SEQUENCE`.

Available palettes (`-p`): `GRAYSCALE`, `GRADIENT_YELLOW_RED`, `GRADIENT_BLUE_BLACK`,
`GRADIENT_BLACK_YELLOW`, `INVERTED_GRAYSCALE`, `FIXED`.

### Examples

A static Julia image with a color gradient:

```bash
./gradlew run --args="-f JULIA -p GRADIENT_YELLOW_RED -w 800 -h 800 -x 0.1 -y 0.6 -o julia.png"
```

A Burning Ship zoomed on one of its "ships":

```bash
./gradlew run --args="-f BURNING_SHIP -p GRADIENT_BLACK_YELLOW -x -1.755 -y -0.03 -s 0.0001 -n 64 -o burningship.png"
```

A GIF zooming progressively into the Mandelbrot set (30 frames, each one 15% more zoomed than the previous one):

```bash
./gradlew run --args="-t zoom -f MANDELBROT -p GRADIENT_BLUE_BLACK -w 400 -h 400 -x -0.743643887037151 -y 0.131825904205330 -s 0.005 -n 200 --frames 30 --zoom-factor 0.85 --delay 80 -o mandelbrot_zoom.gif"
```

A GIF in which the constant of a Julia set rotates:

```bash
./gradlew run --args="-t rotation -p GRADIENT_YELLOW_RED -w 400 -h 400 -s 0.004 -n 150 --frames 30 --delay 80 -o julia_rotation.gif"
```

Displaying the help of the command:

```bash
./gradlew run --args="--help"
```

### Good to know

- For an animation (`zoom` or `rotation`), use a `.gif` output file.
- A `rotation` animation always rotates a Julia set: the option `-f` is ignored in this case.
- In a `zoom` animation, the option `-s` gives the scale of the first frame; a `--zoom-factor` below `1` zooms in, above `1` zooms out.
- Large images with many iterations can take a while to compute: start with a small size (for instance `-w 200 -h 200`).
- An invalid value (for instance an unknown fractal name) is reported by the command, with the list of the accepted values.


## Team

This project has been developed by:

- Dubreucq Kévin
- Vanstraceele Lucas
- Beauvais Maxence
- Barloy Thomas
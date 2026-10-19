/*
 * FractaLens - A Fractal Generator Library Developed in Lens.
 *
 * Copyright (c) 2022-2026 Romain Wallon - Université d'Artois.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to
 * deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or
 * sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE
 * USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package fr.univartois.butinfo.fractalens.complex.sequence;

import fr.univartois.butinfo.fractalens.complex.IComplex;


import java.util.Iterator;

/**
 * Represents a Julia sequence of complex numbers.
 *
 * @author Maxence Beauvais
 * @version 0.1.0
 */
public class JuliaSequence implements IComplexSequence {

    /**
     * The first term of the sequence.
     */
    private final IComplex first;

    /**
     * The constant complex number.
     */
    private final IComplex constant;

    /**
     * The maximum number of terms.
     */
    private final int nbTerms;

    /**
     * Creates a new Julia sequence.
     *
     * @param first The initial complex number.
     * @param constant The constant complex number.
     * @param nbTerms The maximum number of terms.
     */
    public JuliaSequence(IComplex first, IComplex constant, int nbTerms) {
        this.first = first;
        this.constant = constant;
        this.nbTerms = nbTerms;
    }

    /**
     * Computes the next term of the sequence from the previous one.
     *
     * @param previous The previous complex number.
     * @return The next complex number.
     */
    @Override
    public IComplex computeNext(IComplex previous) {
        return previous.multiply(previous).add(constant);
    }

    /**
     * Returns an iterator over the sequence terms.
     *
     * @return An iterator for this sequence.
     */
    @Override
    public Iterator<IComplex> iterator() {
        return new ComplexIterator(first,this,nbTerms);
    }
}
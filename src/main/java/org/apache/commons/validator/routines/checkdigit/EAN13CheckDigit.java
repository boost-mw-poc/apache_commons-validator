/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.validator.routines.checkdigit;

/**
 * Modulus 10 <strong>EAN-13</strong> / <strong>UPC</strong> / <strong>ISBN-13</strong> Check Digit
 * calculation/validation.
 * <p>
 * Check digit calculation is based on <em>modulus 10</em> with digits in
 * an <em>odd</em> position (from right to left) being weighted 1 and <em>even</em>
 * position digits being weighted 3.
 * <p>
 * For further information see:
 * <ul>
 *   <li>EAN-13 - see
 *       <a href="https://en.wikipedia.org/wiki/European_Article_Number">Wikipedia -
 *       European Article Number</a>.</li>
 *   <li>UPC - see
 *       <a href="https://en.wikipedia.org/wiki/Universal_Product_Code">Wikipedia -
 *       Universal Product Code</a>.</li>
 *   <li>ISBN-13 - see
 *       <a href="https://en.wikipedia.org/wiki/ISBN">Wikipedia - International
 *       Standard Book Number (ISBN)</a>.</li>
 * </ul>
 *
 * @since 1.4
 */
public final class EAN13CheckDigit extends ModulusCheckDigit {

    private static final long serialVersionUID = 1726347093230424107L;

    /** Singleton EAN-13 Check Digit instance */
    public static final CheckDigit EAN13_CHECK_DIGIT = new EAN13CheckDigit();

    /** Weighting given to digits depending on their right position */
    private static final int[] POSITION_WEIGHT = {3, 1};

    /**
     * Constructs a modulus 10 Check Digit routine for EAN/UPC.
     */
    public EAN13CheckDigit() {
    }

    /**
     * <p>Calculates the <em>weighted</em> value of a character in the
     * code at a specified position.</p>
     *
     * <p>For EAN-13 (from right to left) <strong>odd</strong> digits are weighted
     * with a factor of <strong>one</strong> and <strong>even</strong> digits with a factor
     * of <strong>three</strong>.</p>
     *
     * @param charValue The numeric value of the character.
     * @param leftPos The position of the character in the code, counting from left to right
     * @param rightPos The position of the character in the code, counting from right to left
     * @return The weighted value of the character.
     */
    @Override
    protected int weightedValue(final int charValue, final int leftPos, final int rightPos) {
        return charValue * POSITION_WEIGHT[rightPos % 2];
    }
}

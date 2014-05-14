/**
 * Copyright (c) 2012, 2014, Credit Suisse (Anatole Tresch), Werner Keil and others by the @author tag.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.javamoney.moneta.internal;

import java.math.RoundingMode;

import javax.money.CurrencyUnit;
import javax.money.MonetaryContext;

import org.javamoney.moneta.Money;
import org.javamoney.moneta.spi.AbstractAmountFactory;

/**
 * Implementation of {@link javax.money.MonetaryAmountFactory} creating instances of {@link Money}.
 *
 * @author Anatole Tresch
 */
public class MoneyAmountFactory extends AbstractAmountFactory<Money> {

	static final MonetaryContext DEFAULT_CONTEXT = new MonetaryContext.Builder(
			Money.class).setPrecision(64)
			.setMaxScale(63).setObject(RoundingMode.HALF_EVEN).build();
	static final MonetaryContext MAX_CONTEXT = new MonetaryContext.Builder(
			Money.class).setPrecision(0)
			.setMaxScale(-1).setObject(RoundingMode.HALF_EVEN).build();

	@Override
	protected Money create(Number number, CurrencyUnit currency,
                           MonetaryContext monetaryContext) {
		return Money.of(number, currency,
                        MonetaryContext.from(monetaryContext, Money.class));
	}

	@Override
	public Class<Money> getAmountType() {
		return Money.class;
	}

	@Override
	protected MonetaryContext loadDefaultMonetaryContext() {
		return DEFAULT_CONTEXT;
	}

	@Override
	protected MonetaryContext loadMaxMonetaryContext() {
		return MAX_CONTEXT;
	}

}
